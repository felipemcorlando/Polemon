package com.unicamp.br.mc322.polemon.abilities.active;

import java.util.ArrayList;
import java.util.Random;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Types;

public class QuickAttack implements IActiveAbility {

	private String name = "QuickAttack"; //Habilidade comum
	private int damage = 40;
	private int accuracy = 100;
	private int criticalHitAcc = 5;
	private ArrayList<Types> typesAllowed = new ArrayList<Types>();

	public QuickAttack() {
		this.typesAllowed.add(Types.DRAGON);
		this.typesAllowed.add(Types.PSYCHIC);
		this.typesAllowed.add(Types.FIRE);
		this.typesAllowed.add(Types.WATER);
		this.typesAllowed.add(Types.ELECTRIC);
		this.typesAllowed.add(Types.GRASS);
	}
	
	@Override
	public double useHability(Pokemon self, Pokemon opponent) { //Retorna o dano dado
		// TODO Auto-generated method stub
		double damageDealt = 0;
		Random rand = new Random();
		if(rand.nextInt(100) < this.accuracy) { //Ataque acertou
			double extraDamage = 1;
			if(rand.nextInt(100) < this.criticalHitAcc) //Teve critical Hit
				extraDamage = 1.5;
			damageDealt = extraDamage*this.damage;			
		}
		return damageDealt;
	}

	@Override
	public boolean canLearn(Pokemon learner) {
		// TODO Auto-generated method stub
		for(Types pokemon : learner.getPokemonType()) {
			for(Types allowed : this.typesAllowed) {
				if(pokemon == allowed) {
					return true; //O Pokemon consegue aprender a habilidade
				}
			}
		}
		return false; //O Pokemon não consegue aprender a habilidade
	}
	
	@Override
	public String toString() {
		String s = this.getName()+"(Damage = "+this.damage+")";
		return s;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

}
