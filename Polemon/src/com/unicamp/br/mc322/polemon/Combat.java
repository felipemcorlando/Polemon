package com.unicamp.br.mc322.polemon;

import java.util.ArrayList;

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
				if(!pl.checkPokemonsLife())
					return;
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
					e.getMessage();
				}
			}
			playerAttack(pl,po);
		}
	}
	
	public void playerAttack(Player pl, Pokemon po) {
		System.out.println("\nBase attack , Press 0");
		System.out.println("\nChoose one ability , Press 1");
		int command = Integer.parseInt(Input.readKeyboard());
		while(command !=0 || command !=1) {
			System.out.println("Invalid Command , try again : ");
			command = Integer.parseInt(Input.readKeyboard());
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
			double damage = pl.getActivatedPokemon().getActiveAbility(command).useHability(pl.getActivatedPokemon(), po);
			po.setHp(po.getHp()-damage);
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
