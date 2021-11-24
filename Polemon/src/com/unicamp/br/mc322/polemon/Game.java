package com.unicamp.br.mc322.polemon;

public class Game {

	private boolean exitSelected;
	
	private Player player;
	
	public Game() {
		this.exitSelected = false;
		this.player = new Player( );
	}
	
	public void start() {
		exitSelected = false;
		System.out.println("Game started!");
		while(!exitSelected) {
			//drawBoard();
			//readInput();
			//updateBoard();
		}
		System.out.println("Game terminated. Bye!");
		
	}
	
	private void drawBoard() {
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
		
		table[playerPosition.getX()-xOffset][playerPosition.getY()-yOffset] = 'O';
		
		Pokemon[] p = island.getPokemons();
		for (int i = 0; i < p.length; i++)
			table[p[i].getPosition().getX()-xOffset][p[i].getPosition().getY()-yOffset] = 'P';
			
		Collectable[] itens = island.getItens();
		for (int i = 0; i < p.length; i++)
			table[itens[i].getPosition().getX()-xOffset][itens[i].getPosition().getY()-yOffset] = 'I';
		
		Bridge[] bridges = island.getBridges();
		for (int i = 0; i < bridges.length; i++) 
			table[bridges[i].getPlace1().getX()-xOffset][bridges[i].getPlace1().getY()-yOffset] = '=';
		
		Portal[] portals = island.getPortals();
		for (int i = 0; i < bridges.length; i++) 
			table[portals[i].getPlace1().getX()-xOffset][portals[i].getPlace1().getY()-yOffset] = '0';
		
		this.printMatrix(table);
		
		System.out.println("Type of Island: "+island.getType());
		System.out.println("---------------------------------------------");
		System.out.println(this.player.getMinePokemons().toString());
		System.out.println(this.player.getInventory().toString());
		
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