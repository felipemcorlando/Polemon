package com.unicamp.br.mc322.polemon;

public class Game {

	private boolean exitSelected;

	private Pokemon[] wildPokemons; 

	private Player player;
	private int movements;

	public Game() {
		this.exitSelected = false;
		this.player = new Player( );
		this.loadPokemons("WildPokemons.txt");
		this.movements = 0;
	}

	private void loadPokemons(String path) {
		this.wildPokemons = new Pokemon[Input.countLines(path)];
		for (int i = 0; i < this.wildPokemons.length; i++)  
			this.wildPokemons[i] = new Pokemon(Input.readLineFromFile(path, i));
	}

	public void start() {
		this.exitSelected = false;
		this.movements =  Dice.roll(6, 2);
		System.out.println("Game started!");

		while(!exitSelected) {
			drawBoard();
			String command = readInput();
			updateGame(command);
		}
		System.out.println("Game terminated. Bye!");

	}

	private boolean inMovementMode() {
		return !(this.movements == 0);
	}

	private void updateGame(String command) {
		if (!this.player.inCombat()) { //is in movement/action mode
			if (this.inMovementMode()) { 
				Island island = this.player.getActualIsland();

				switch(command) {
				case "w":
					if (this.player.getPosition().getY() - 1 < island.getPosition.getY())
						return;

					this.player.getPosition().travel(0, -1, 0);
					this.movements--;
					break;
				case "s":
					if (this.player.getPosition().getY() + 1 > island.getPosition.getY()+island.getSize())
						return;

					this.player.getPosition().travel(0, 1, 0);
					this.movements--;
					break;
				case "a":
					if (this.player.getPosition().getX() - 1 < island.getPosition.getX())
						return;

					this.player.getPosition().travel(-1, 0, 0);
					this.movements--;
					break;
				case "d":
					if (this.player.getPosition().getX() + 1 > island.getPosition.getX())
						return;

					this.player.getPosition().travel(1, 0, 0);
					this.movements--;
					break;
				default:
					return;

					//checks if there is an item in the same position of player, if true collects
					Collectable newItem = this.player.collectItem();
					if (newItem != null)
						System.out.println("Item collected: "+ newItem.toString());

				} else { //is in action mode
					switch(command) {
					case "1":
						this.choosePokemon();
						break;
					case "2":
						this.useItem();
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
			Runtime.getRuntime().exec("cls");
			System.out.println("Choose one pokemon:");
			PokemonBag pb = this.player.getMinePokemons();
			for (int i = 0; i < pb.pokemons.length; i++) 
				System.out.println((i+1)+" - "+pb.pokemons[i]);
			
			String str = Input.readKeyboard();
			if (str == "")
				return;
						
			int choice = (int) str;
			this.player.choosePokemon(pb.pokemons[choice-1]);
		}
		
		private void useItem() {
			Runtime.getRuntime().exec("cls");
			System.out.println("Use one item to use:");
			Iventory inv = this.player.getInventory();
			for (int i = 0; i < inv.itens.length; i++) 
				System.out.println((i+1)+" - "+inv.itens[i]);
			
			String str = Input.readKeyboard();
			if (str == "")
				return;
						
			int choice = (int) str;
			this.player.useItem(inv.itens[choice-1]);
		}

		private String readInput() {
			String ret = Input.readKeyboard();
			if (ret.toLowerCase() == "quit")
				this.exitSelected = true;

			return ret;
		}

		private void drawBoard() {
			Runtime.getRuntime().exec("cls");
			Position playerPosition = this.player.getGlobalPosition();
			System.out.println("Position: ("+playerPosition.getX()+","+playerPosition.getY()+")");

			Island island = this.player.getActualIsland();

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

			int xOffset = island.getPosition().getX()-1;
			int yOffset = island.getPosition().getY()-1.;

			Pokemon[] p = island.getPokemons();
			for (int i = 0; i < p.length; i++)
				table[p[i].getPosition().getX()-xOffset][p[i].getPosition().getY()-yOffset] = 'P';

			Collectable[] itens = island.getItens();
			for (int i = 0; i < p.length; i++)
				table[itens[i].getPosition().getX()-xOffset][itens[i].getPosition().getY()-yOffset] = 'I';

			
			
			// alterar para Mappable  e para obter o char a ser printado -> mappable[index].getChar();
			Bridge[] bridges = island.getBridges();
			for (int i = 0; i < bridges.length; i++) 
				table[bridges[i].getPlace1().getX()-xOffset][bridges[i].getPlace1().getY()-yOffset] = '=';

			Portal[] portals = island.getPortals();
			for (int i = 0; i < bridges.length; i++) 
				table[portals[i].getPlace1().getX()-xOffset][portals[i].getPlace1().getY()-yOffset] = '0';

			table[playerPosition.getX()-xOffset][playerPosition.getY()-yOffset] = 'O';

			this.printMatrix(table);

			System.out.println("Type of Island: "+island.getType());
			System.out.println("---------------------------------------------");
			System.out.println(this.player.getMinePokemons().toString());
			System.out.println(this.player.getInventory().toString());
			System.out.println("---------------------------------------------");

			if (this.inMovementMode()) { //in action mode
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
					System.out.print(matrix[i][j]);
				}
				System.out.println();
			} 
		}

	}