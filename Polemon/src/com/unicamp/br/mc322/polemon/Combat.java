package com.unicamp.br.mc322.polemon;

import com.unicamp.br.mc322.polemon.abilities.*;
import com.unicamp.br.mc322.polemon.abilities.active.*;
import com.unicamp.br.mc322.polemon.abilities.passive.*;

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
		Game.cleanScreen();
		System.out.println("-----------------------------------------");
		System.out.println("COMBAT MODE:");
		System.out.println("Player vs "+this.targetPokemon.getName());
		while (player.getActivatedPokemon().getHp()>0 && targetPokemon.getHp()>0) {
			System.out.println("  Your: "+this.player.getActivatedPokemon().toString());
			System.out.println("  Target: "+this.targetPokemon.toString()+"\n");
			this.shift();
		}
		
		this.player.setInCombat(false);
	}
	
	public void shift() {//turno de ataque
		if (this.playerShift) {

			//player ataca primeiro 
			this.playerAttack();
			if(this.targetPokemon.getHp()<1)
				return;
			else
				this.pokemonAttack();
		}
		else {
			//pokemon ataca primeiro
			this.pokemonAttack();
			if(this.player.getActivatedPokemon().getHp()<1) {
				if(!this.player.checkPokemonsLife())
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
			this.playerAttack();
		}
	}
	
	public void playerAttack() {
		System.out.println("Your turn! Choose one action:");
		System.out.println(" 0 - Base attack");
		System.out.println(" Abilities:");
		int i = 1;
		ArrayList<IActiveAbility> abs = this.player.getActivatedPokemon().getActives();
		
		for (IActiveAbility a : abs) {
			System.out.println(i+" - "+a.getName());
			i++;
		}
		
		if (i == 1)
			System.out.println(" No abilities!");
		

		int command;
		do {
			double damage;
			System.out.print("Your Action: ");
			command = Integer.parseInt(Input.readKeyboard());
			if(command==0) {
				damage = baseAttack(this.player.getActivatedPokemon(),this.targetPokemon);
				this.targetPokemon.setHp(this.targetPokemon.getHp()-damage);
				System.out.println("You damaged "+this.targetPokemon.getName()+" by "+damage+" hp!");
				return;
			}
			
			if (command < i && command > 0) {
				damage = abs.get(i-1).useHability(this.player.getActivatedPokemon(), this.targetPokemon);
				this.targetPokemon.setHp(this.targetPokemon.getHp()-damage);
				System.out.println("You damaged "+this.targetPokemon.getName()+" by "+damage+" hp!");
				return;
			}
		}
		while (command >= i || command < 0); //Input errado
		
	}
	
	public void pokemonAttack() {
		this.player.getActivatedPokemon().setHp(this.player.getActivatedPokemon().getHp()-baseAttack(this.targetPokemon,this.player.getActivatedPokemon()));
	}
	
	public double baseAttack(Pokemon attacker , Pokemon defensor ) {
		double damage = attacker.getAttackPoints()-defensor.getDefensePoints();
		if(damage<1)
			return 1.0;
		return damage;
	}
}
