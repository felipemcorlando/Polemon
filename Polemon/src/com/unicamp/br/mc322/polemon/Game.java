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
		this.player = new Player( );
		this.loadMappables(4);
		this.loadPokemons("C:\\Users\\wyatt\\git\\polemon\\Polemon\\src\\com\\unicamp\\br\\mc322\\polemon\\PokemonInstancesInfo.txt");
		this.movements = 0;
	}

	private void loadMappables(int plans) {
		this.plans = new ArrayList<Plan>(plans);
		for (int i = 0; i < plans; i++)
			this.plans.add(new Plan(i, 8));
	}

	public void printException(String error) {
		System.out.println(error);
	}

	private void loadPokemons(String path) {
		this.wildPokemons = new ArrayList<Pokemon>();
		for (int i = 0; i < Input.countLines(path); i++) {
			Pokemon p = new Pokemon(Input.readLineFromFile(path, i));
			this.wildPokemons.add(p);
			getIslandByPosition(p.getPosition()).addPokemon(p);
		}
	}

	private void loadCollectables() {}

	public void start() {
		this.exitSelected = false;
		this.movements =  Dice.roll(6, 2);
		System.out.println("Game started!");

		while(!exitSelected) {
			this.drawBoard();
			String command = this.readInput();
			this.updateGame(command);
		}
		System.out.println("Game terminated. Bye!");

	}

	private Island getPlayerActualIsland() {
		return this.plans.get(this.player.getGlobalPosition().getZ()).findIsland(this.player.getGlobalPosition());
	}

	private Island getIslandByPosition(Position pos) {
		return this.plans.get(pos.getZ()).findIsland(pos);	
	}

	private boolean inMovementMode() {
		return !(this.movements == 0);
	}

	private final static void cleanScreen() {
		try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        System.out.println("Erro no cleanScreen:" + e.getMessage());
	    }
	}

	private void updateGame(String command) {
		if (!this.player.isInCombat()) { //is in movement/action mode
			if (this.inMovementMode()) { 
				Island island = this.getPlayerActualIsland();

				switch(command) {
				case "w":
					if (this.player.getGlobalPosition().getY() - 1 < island.getPosition().getY())
						return;

					this.player.getGlobalPosition().travel(0, -1, 0);
					this.movements--;
					break;
				case "s":
					if (this.player.getGlobalPosition().getY() + 1 > island.getPosition().getY()+island.getSize())
						return;

					this.player.getGlobalPosition().travel(0, 1, 0);
					this.movements--;
					break;
				case "a":
					if (this.player.getGlobalPosition().getX() - 1 < island.getPosition().getX())
						return;

					this.player.getGlobalPosition().travel(-1, 0, 0);
					this.movements--;
					break;
				case "d":
					if (this.player.getGlobalPosition().getX() + 1 > island.getPosition().getX())
						return;

					this.player.getGlobalPosition().travel(1, 0, 0);
					this.movements--;
					break;
				default:
					return;
				}
				Collectable newItem = this.player.collectItem(this.plans.get(this.player.getGlobalPosition().getZ()));
				if (newItem != null)
					System.out.println("Item collected: "+ newItem.toString());

			} else { //is in action mode
				switch(command) {
				case "1":
					this.choosePokemon();
					break;
				case "2":
					//this.useItem();
					break;
				case "3":

					break;
				case "4":

					break;
				case "5":
					this.movements = Dice.roll(6,2);
					break;
				default:
					return;

				}
			}



		} else { //is in combat mode

		}
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
	/*
	private void useItem() {
		cleanScreen();
		System.out.println("Select one item to use:");
		Iventory inv = this.player.getInventory();
		for (int i = 0; i < inv.itens.length; i++) 
			System.out.println((i+1)+" - "+inv.itens[i]);

		try {
			String str = Input.readKeyboard();
			if (str == "")
				return;

			int choice = Integer.parseInt(str);
			this.player.useItem(inv.itens[choice-1]);

		} catch (Exception e) {
			printException(e.getMessage());
		}

	}
	 */
	private String readInput() {
		String ret = Input.readKeyboard();
		if (ret.toLowerCase() == "quit")
			this.exitSelected = true;

		return ret;
	}

	private void drawBoard() {
		cleanScreen();
		Position playerPosition = this.player.getGlobalPosition();
		System.out.println("Position: ("+playerPosition.getX()+","+playerPosition.getY()+")");

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
		int yOffset = (int) (island.getPosition().getY()-1.);

		ArrayList<Pokemon> pl = island.getPokemons();
		for (Pokemon p : pl)
			table[p.getPosition().getX()-xOffset][p.getPosition().getY()-yOffset] = 'P';

		ArrayList<Collectable> itens = island.getItens();
		for (Collectable item : itens)
			table[item.getPosition().getX()-xOffset][item.getPosition().getY()-yOffset] = 'I'; //item.getChar();

		ArrayList<Mappable> places = island.getMoveObjects();
		for (Mappable map : places) 
			table[map.getPosition().getX()-xOffset][map.getPosition().getY()-yOffset] = 'M';// map.getChar();

		table[playerPosition.getX()-xOffset][playerPosition.getY()-yOffset] = 'O';

		this.printMatrix(table);

		System.out.println("Type of Island: "+island.getType());
		System.out.println("---------------------------------------------");
		System.out.println(this.player.getMinePokemons().toString());
		System.out.println(this.player.getInventory().toString());
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

	}

	private void printMatrix(char[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		} 
	}

}