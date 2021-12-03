package com.unicamp.br.mc322.polemon.abilities.passive;

import java.util.ArrayList;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Types;

public class Intimidate implements IPassiveAbility {

	private String name = "Intimidate"; //Reduz todos os danos recebidos por outros pokemons de 50%, exceto danos recebidos de outros pokemon Psychic.
	private ArrayList<Types> typesAllowed = new ArrayList<Types>();
	
	public Intimidate() {
		this.typesAllowed.add(Types.FIRE);
		this.typesAllowed.add(Types.DRAGON);
		this.typesAllowed.add(Types.PSYCHIC);
	}
		
	@Override
	public double useHability(double d,Pokemon self, Pokemon opponent) {
		// TODO Auto-generated method stub
		for(Types t : opponent.getPokemonType()) {
			if(t == Types.PSYCHIC) { //Se for PSYCHIC d = d e retorna;
				return d;
			}
		}
		System.out.println("Damage reduced by Intimidate");
		return (d/2); //Caso contrario d = d/2 e retorna;
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
	
	@Override
	public String getTypesAllowed() {
		// TODO Auto-generated method stub
		String s = "";
		for(Types t : this.typesAllowed) {
			s += t.toString(t)+" ";
		}
		return s;
	}

}
