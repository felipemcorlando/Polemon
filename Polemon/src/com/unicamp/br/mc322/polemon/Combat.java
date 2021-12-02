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
			if(pl.getActivatedPokemon().getHp()<1) {
				//Ver com Gu como mudaremos o pokemon pensando no index do pokemon bag , o pokemon ativo sempre deve ser o primeiro ?
			}
			//adicionar um check player.getActivatedPokemon().getHp()>0
			playerAttack(pl,po);
		}
	}
	
	public void playerAttack(Player pl, Pokemon po) {
		System.out.println("\nBase attack , Press 0");
		System.out.println("\nChoose one ability , Press 1");
		int command = Integer.parseInt(Input.readKeyboard());
		while(command !=0 || command !=1) {
			System.out.println("Invalid Command , try again : ");
			command = Integer.parseInt(Input.readKeyboard())
		}
		
		if(command==0) {
			po.setHp(po.getHp()-baseAttack(pl.getActivatedPokemon(),po));
			return;
		}
		else {
			System.out.println("Disponible abilities : ");
			pl.getActivatedPokemon().getAbilitiesInfo();
			System.out.println("Choose one : ");
			command = Integer.parseInt(Input.readKeyboard());
			// preciso ver com o felipe como vai funcionar o usar habilidade
		}		
	}
	
	public void pokemonAttack(Player pl, Pokemon po) {
		pl.getActivatedPokemon().setHp(pl.getActivatedPokemon().getHp()-baseAttack(po,pl.getActivatedPokemon()));
	}
	
	public double baseAttack(Pokemon attacker , Pokemon defensor ) {
		double damage = attacker.getAttackPoints()-defensor.getDefensePoints();
		if(damage<1)
			return 1.0;
		return damage;
	}
}
