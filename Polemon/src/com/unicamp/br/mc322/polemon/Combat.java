package com.unicamp.br.mc322.polemon;

public class Combat {

	private Player player;
	private Pokemon targetPokemon;
	private boolean firstPlayer;
	
	public Combat (Player pl, Pokemon po, boolean isPlayerFirst) {
		this.player = pl;
		this.targetPokemon = po;
		this.firstPlayer = isPlayerFirst;
	}
	
	public void drawCombat () {
		
	}
	
}
