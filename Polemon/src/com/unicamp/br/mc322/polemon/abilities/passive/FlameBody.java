package com.unicamp.br.mc322.polemon.abilities.passive;

import java.util.ArrayList;
import java.util.Random;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Types;

public class FlameBody implements IPassiveAbility {

	private String name = "FlameBody"; //Ao receber dano tem uma chance de 30% de causar 5 pontos de dano ao atacante. Não funciona contra Water ou Fire.
	private int damage = 5;
	private int accuracy = 30;
	private ArrayList<Types> typesAllowed = new ArrayList<Types>();
	
	public FlameBody() {
		this.typesAllowed.add(Types.FIRE);
		this.typesAllowed.add(Types.DRAGON);
	}
		
	@Override
	public double useHability(double d,Pokemon self, Pokemon opponent) {
		// TODO Auto-generated method stub
		if(d > 0) { //Tomou dano
			for(Types i : opponent.getPokemonType()) {
				if(i == Types.WATER || i == Types.FIRE) {
					return d; //A Habilidade não funciona
				}
			}
			Random rand = new Random();
			if(rand.nextInt(100) < this.accuracy) {
				opponent.setHp(opponent.getHp()-this.damage);
			}
		}
		return d;
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
