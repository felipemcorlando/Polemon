package com.unicamp.br.mc322.polemon.abilities.active;

import java.util.ArrayList;
import java.util.Random;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Types;

public class Bite implements IActiveAbility {

	private String name = "Bite"; //Habilidade comum
	private int damage = 60;
	private int accuracy = 100;
	private int criticalHitAcc = 5;
	private ArrayList<Types> typesAllowed = new ArrayList<Types>();

	public Bite() {
		this.typesAllowed.add(Types.DRAGON);
		this.typesAllowed.add(Types.PSYCHIC);
		this.typesAllowed.add(Types.FIRE);
		this.typesAllowed.add(Types.WATER);
		this.typesAllowed.add(Types.ELECTRIC);
		this.typesAllowed.add(Types.GRASS);
	}
	
	@Override
	public void useHability(Pokemon self, Pokemon opponent) {
		// TODO Auto-generated method stub
		double damageDealt = 0;
		Random rand = new Random();
		if(rand.nextInt(100) < this.accuracy) { //Ataque acertou
			//Algum feedback caso acertou?
			double extraDamage = 1;
			if(rand.nextInt(100) < this.criticalHitAcc) //Teve critical Hit
				//Algum feedback caso acertou critcial hit?
				extraDamage = 1.5;
			damageDealt = extraDamage*this.damage;
			opponent.setHp(opponent.getHp()-damageDealt);
		}
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
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

}
