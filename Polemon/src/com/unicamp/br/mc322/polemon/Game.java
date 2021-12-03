package com.unicamp.br.mc322.polemon;
import com.unicamp.br.mc322.polemon.items.*;
import com.unicamp.br.mc322.polemon.map.*;

import java.io.IOException;
import java.util.*;

public class Game {

	private boolean exitSelected;

	private ArrayList<Pokemon> wildPokemons; 

	private Player player;
	private int movements;

	private ArrayList<Plan> plans;

	public Game() {
		this.exitSelected = false;
		this.loadMappables(4);

		//creating the Player
		Pokemon p1 = new Pokemon("Totodile",Types.WATER,null,44,50,65,new Position(0,0,0));
		Position initialPos = new Position(1,1,0); //switch to (1,1,0)
		Island i1 = this.getIslandByPosition(initialPos);

		this.player = new Player(initialPos, p1, i1);
		this.loadPokemons("src/com/unicamp/br/mc322/polemon/PokemonInstancesInfo");
		this.loadCollectables();
		this.movements = 0;
	}

	//Main methods of class Game
	public void start() {
		this.exitSelected = false;
		this.movements =  Dice.roll(6, 2);
		System.out.println("Game started!");

		while(!this.exitSelected) {
			this.drawBoard();
			String command = this.readInput();
			this.updateGame(command);
			if (this.wildPokemons.isEmpty()) {
				System.out.println("CONGRATS! You won the Polemon Game!");
				break;
			}
				
		}
		System.out.println("Game terminated. Bye!");
	}

	private String readInput() {
		String ret = Input.readKeyboard();
		if (ret.toLowerCase() == "quit")
			this.exitSelected = true;

		return ret;
	}

	private void updateGame(String command) {
		if (!this.player.isInCombat()) { //is in movement/action mode
			if (this.inMovementMode()) { 
				Island island = this.getPlayerActualIsland();
				Position actual = this.player.getGlobalPosition();
				switch(command) {
				case "w":
					if (!this.canMove(new Position(actual.getX(), actual.getY()-1, actual.getZ())))
						return;

					this.player.getGlobalPosition().travel(0, -1, 0);
					this.movements--;
					this.useMappable(island);
					break;
				case "s":
					if (!this.canMove(new Position(actual.getX(), actual.getY()+1, actual.getZ())))
						return;

					this.player.getGlobalPosition().travel(0, 1, 0);
					this.movements--;
					this.useMappable(island);		
					break;
				case "a":
					if (!this.canMove(new Position(actual.getX()-1, actual.getY(), actual.getZ())))
						return;

					this.player.getGlobalPosition().travel(-1, 0, 0);
					this.movements--;
					this.useMappable(island);
					break;
				case "d":
					if (!this.canMove(new Position(actual.getX()+1, actual.getY(), actual.getZ())))
						return;

					this.player.getGlobalPosition().travel(1, 0, 0);
					this.movements--;
					this.useMappable(island);
					break;
				default:
					return;
				}
				Collectable newItem = this.player.collectItem(this.plans.get(this.player.getGlobalPosition().getZ()));
				if (newItem != null)
					System.out.println("Item collected: "+ newItem.toString());

				this.healPokemons();

			} else { //is in action mode
				switch(command) {
				case "1":
					this.choosePokemon(); 
					//volta para o indice
					break;
				case "2":
					this.useItem();
					break;
				case "3":
					Pokemon target = this.chooseAvailablePokemons();
					if (target != null) {
						this.player.setInCombat(true);
						Combat newCombat = new Combat(this.player, target, true);
						if (newCombat.runCombat()) {
							//ganhou o combate, perguntar se quer add na bag
							System.out.println("\n"+target.getName()+" fainted. Do you want to add to your bag?\n1-YES 2-NO : ");
							int c = Integer.parseInt(Input.readKeyboard().substring(0,1));
							if (c == 1) {
								target.setHp(target.getInitialHp());
								this.player.getMinePokemons().addPokemon(target);
							}
							
							this.wildPokemons.remove(target);
							this.getPlayerActualIsland().getPokemons().remove(target);
							
						} else {
							//acabou o jogo
							System.out.println("GAME OVER!!!");
							this.exitSelected = true;
						}
							
					}
					break;
				case "4":
					Pokemon tar = this.chooseAvailablePokemons();
					if (tar != null) {
						if (!this.capturePokemon(tar)) { //nao deu certo, entra no modo combat
							Combat newCombat = new Combat(this.player, tar, false);
							if (newCombat.runCombat()) {
								//ganhou o combate, perguntar se quer add na bag
								System.out.println(tar.getName()+" fainted. Do you want to add to your bag?\n1-YES 2-NO : ");
								int c = Integer.parseInt(Input.readKeyboard().substring(0,1));
								if (c == 1) {
									tar.setHp(tar.getInitialHp());
									this.player.getMinePokemons().addPokemon(tar);
								}
								
								this.wildPokemons.remove(tar);
								this.getPlayerActualIsland().getPokemons().remove(tar);
							} else {
								//acabou o jogo
								System.out.println("GAME OVER!!!");
								this.exitSelected = true;
							}
						//
						}
					}
						break;
				case "5":
					this.movements = Dice.roll(6,2);
					break;
				default:
					return;

				}
			}
		} 
	}

	private void drawBoard() {
		cleanScreen();
		Position playerPosition = this.player.getGlobalPosition();
		System.out.println("Position: ("+playerPosition.getX()+","+playerPosition.getY()+","+playerPosition.getZ()+")");

		Island island = this.getPlayerActualIsland();

		//create the island matrix representation
		int size = island.getSize() + 2; //+2, the border
		char[][] table = new char [size][size];
		for (int i = 0; i < size; i++) 
			for (int j = 0; j < size; j++) {
				if (i == 0 || i == size-1 || j == 0 || j == size-1)
					table[i][j] = '-'; //border
				else
					table[i][j] = '#';
			}

		int xOffset = (int) island.getPosition().getX()-1;
		int yOffset = (int) island.getPosition().getY()-1;

		ArrayList<Pokemon> pl = island.getPokemons();
		for (Pokemon p : pl)
			if (this.pokemonInRange(p))
				table[p.getPosition().getX()-xOffset][p.getPosition().getY()-yOffset] = 'P';


		ArrayList<Collectable> itens = island.getItens();
		for (Collectable item : itens)
			table[item.getPosition().getX()-xOffset][item.getPosition().getY()-yOffset] = item.getChar();

		ArrayList<Mappable> places = island.getMoveObjects();
		for (Mappable map : places) 
			table[map.getPosition().getX()-xOffset][map.getPosition().getY()-yOffset] = map.getChar();

		table[playerPosition.getX()-xOffset][playerPosition.getY()-yOffset] = 'O';

		this.printMatrix(table);

		System.out.println("Island "+(island.getIndexOnPlan()+1)+" - ("+island.getType()+")");
		System.out.println("---------------------------------------------");
		System.out.println("Pokemon bag: "+this.player.getMinePokemons().toString());
		System.out.println("Inventory: "+this.player.getInventory().toString());
		System.out.println("---------------------------------------------");

		if (!this.inMovementMode()) { //in action mode
			System.out.println("Available actions:");
			System.out.println("1-Activate a pokemon from pokemon bag");
			System.out.println("2-Use item from inventory");
			System.out.println("3-Attack a pokemon in the island");
			System.out.println("4-Capture a pokemon");
			System.out.println("5-End action mode. Roll dices");

		} else 
			System.out.println("Movements left: "+this.movements);

		System.out.print("Enter a command: ");
	}

	private void printMatrix(char[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[j][i]+" ");
			}
			System.out.println();
		} 
	}

	//Load methods
	private void loadMappables(int plans) {
		this.plans = new ArrayList<Plan>(plans);
		for (int i = 0; i < plans; i++)
			this.plans.add(new Plan(i, 8));
	}

	private void loadPokemons(String path) {
		this.wildPokemons = new ArrayList<Pokemon>();
		for (int i = 0; i < Input.countLines(path); i++) {
			Pokemon p = new Pokemon(Input.readLineFromFile(path, i));
			this.wildPokemons.add(p);
			getIslandByPosition(p.getPosition()).addPokemon(p);
		}
	}

	private void loadCollectables() {
		this.loadFruits("src/com/unicamp/br/mc322/polemon/items/FruitInstancesInfo");
		this.loadTRActive("src/com/unicamp/br/mc322/polemon/items/TRActiveInstancesInfo");
		this.loadTRPassive("src/com/unicamp/br/mc322/polemon/items/TRPassiveInstancesInfo");
	}
	
	private void loadFruits(String path) {
		for (int i = 0; i < Input.countLines(path); i++) {
			Fruit f = new Fruit(Input.readLineFromFile(path, i));
			getIslandByPosition(f.getPosition()).addCollectable(f);
		}
	}
	
	private void loadTRActive(String path) {
		for (int i = 0; i < Input.countLines(path); i++) {
			TechnicalRecordActive t = new TechnicalRecordActive(Input.readLineFromFile(path, i));
			getIslandByPosition(t.getPosition()).addCollectable(t);
		}
	}
	private void loadTRPassive(String path) {
		for (int i = 0; i < Input.countLines(path); i++) {
			TechnicalRecordPassive t = new TechnicalRecordPassive(Input.readLineFromFile(path, i));
			getIslandByPosition(t.getPosition()).addCollectable(t);
		}
	}

	//Use methods
	private void useMappable (Island last) {
		//checks if there is a Mappable in the same position of player
		ArrayList<Mappable> map = last.getMoveObjects();
		for (Mappable m : map) {
			if (m.getPosition().equals(this.player.getGlobalPosition())) {

				m.movePlayer(this.player);
				this.getPlayerActualIsland(); //updates the actual island

				return;
			}
		}
	}

	private void useItem() {
		cleanScreen();
		System.out.println("Select one item to use:");
		ArrayList<Collectable> inv = this.player.getInventory().getItemsList();
		for (int i = 0; i < inv.size(); i++) 
			System.out.println((i+1)+" - "+inv.get(i).getName());

		try {
			String str = Input.readKeyboard();
			if (str == "")
				return;

			int choice = Integer.parseInt(str);
			this.player.useItem(inv.get(choice-1));

		} catch (Exception e) {
			printException(e.getMessage());
		}
	}

	//Get methods
	private Island getPlayerActualIsland() {
		//Updating because it is good to 
		this.player.setActualIsland(this.plans.get(this.player.getGlobalPosition().getZ()).findIsland(this.player.getGlobalPosition()));
		return this.player.getActualIsland();
	}

	private Island getIslandByPosition(Position pos) {
		return this.plans.get(pos.getZ()).findIsland(pos);	
	}

	//Boolean methods
	private boolean inMovementMode() {
		return !(this.movements == 0);
	}

	private boolean canMove(Position target) {
		Island i = this.getIslandByPosition(target);
		if (i == null) { //maybe there is a bridge in the border
			i = this.getPlayerActualIsland();
			ArrayList<Mappable> map = i.getMoveObjects();
			for (Mappable m : map)
				if (m.getPosition().equals(target)) //there is a bridge in border
					return true;

			return false;
		} else return true; 
	}

	private boolean pokemonInRange(Pokemon p) {
		if (p.getPosition().getZ() != this.player.getGlobalPosition().getZ())
			return false;

		int x1 = p.getPosition().getX()-p.getD();
		int y1 = p.getPosition().getY()-p.getD();
		int x2 = p.getPosition().getX()+p.getD();
		int y2 = p.getPosition().getY()+p.getD();
		int x = this.player.getGlobalPosition().getX();
		int y = this.player.getGlobalPosition().getY();
		if (x >= x1 && x <= x2 && y >= y1 && y <= y2)
			return true;

		return false;
	}

	//Utility methods
	public void printException(String error) {
		System.out.println(error);
	}

	public final static void cleanScreen() {
		//Jumps 20 lines to 'clear' console
		for (int i = 0; i < 20; ++i) System.out.println();
		
	}

	//Other methods
	public Pokemon chooseAvailablePokemons() {
		cleanScreen();

		ArrayList<Pokemon> l = this.getPlayerActualIsland().getPokemons();
		int i = 1;

		if (l != null) {
			System.out.println("Choose one Pokemon to attack: ");
			for (Pokemon p : l) {
				if (this.pokemonInRange(p)) {
					System.out.println(i+" - "+p.toString());
					i++;
				}
			}
		}

		if (i == 1 || l == null) {
			System.out.println("No pokemons available!");
			return null;
		}

		try {
			String str = Input.readKeyboard();
			if (str == "")
				return null;

			int choice = Integer.parseInt(str);
			//verificar erro qnd soh o 2o da lista de pokemons esta no range
			//o programa pega o outro pokemon q nao est� no range
			return l.get(choice-1);

		} catch (Exception e) {
			printException(e.getMessage());
		}
		
		return null;
	}

	private void choosePokemon() {
		cleanScreen();
		System.out.println("Choose one pokemon:");
		ArrayList<Pokemon> pb = this.player.getMinePokemons().getPokemonList();
		for (int i = 0; i < pb.size(); i++) 
			System.out.println((i+1)+" - "+pb.get(i));

		try {
			String str = Input.readKeyboard();
			if (str == "")
				return;

			int choice = Integer.parseInt(str);
			this.player.choosePokemon(choice-1);

		} catch (Exception e) {
			printException(e.getMessage());
		}
	}

	public boolean capturePokemon(Pokemon target) {
		//true se capturou
		//false se nao capturou em 2 tentativas no max, entao vai comecar um combate (no updateGame)
		int pokemon = target.getD()+target.getK(), acumuladora = 0;
		while(acumuladora < 2) {
			int player = Dice.roll(6, 2);
			if(player > pokemon) { //Pokemon capturado.
				this.player.capturePokemon(target);
				System.out.println(target.getName()+" capturado!");
				this.getPlayerActualIsland().getPokemons().remove(target);
				return true;
			}else { //Tentativa falha de captura
				acumuladora += 1;
				if(acumuladora == 1)
					System.out.println("Primeira tentativa de captura falhou!");
				else
					System.out.println("Segunda tentativa de captura falhou! "+target.getName()+" se tornou hostil!");
			}
		}
		return false;
	}

	private void healPokemons() {
		for (Pokemon p : this.player.getMinePokemons().getPokemonList())
			p.setHp(p.getHp()+1);
	}

}