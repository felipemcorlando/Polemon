package com.unicamp.br.mc322.polemon;

public class Combat {

	private Player player;
	private Pokemon targetPokemon;
	private boolean playerShift;
	
	public Combat (Player pl, Pokemon po, boolean isPlayerFirst) {
		this.player = pl;
		this.targetPokemon = po;
		this.playerShift = isPlayerFirst;

	}
	
	public void drawCombat () {
		while (player.getActivatedPokemon().getHp()>0 && targetPokemon.getHp()>0) {
			shift(player,targetPokemon,playerShift) ;
		}
	}
	
	
	
	
	public void shift(Player pl, Pokemon po, boolean playerShift) {//turno de ataque
		if (playerShift) {
			//player ataca primeiro 
			playerAttack(pl,po);
			if(po.getHp()<1)
				return;
			else
				pokemonAttack(pl,po);
		}
		else {
			//pokemon ataca primeiro
			pokemonAttack(pl,po);
			//adicionar um check player.getActivatedPokemon().getHp()>0
			playerAttack(pl,po);
		}
	}
	
	public void playerAttack(Player pl, Pokemon po) {
		System.out.println("\nBase attack , Press 0");
		System.out.println("\nChoose one ability , Press 1");
		try {
			int command = Integer.parseInt(Input.readKeyboard());
			;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		
	}
}
