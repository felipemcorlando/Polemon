package com.unicamp.br.mc322.polemon.abilities.passive;

import java.util.ArrayList;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Types;

public class SheerForce implements IPassiveAbility {

	private String name = "SheerForce"; //Um pokemon com essa habilidade recebe um boost em 30% nos seus ataques.
	private ArrayList<Types> typesAllowed = new ArrayList<Types>();
	
	public SheerForce() {
		this.typesAllowed.add(Types.WATER);
		this.typesAllowed.add(Types.FIRE);
		this.typesAllowed.add(Types.DRAGON);
	}
		
	@Override
	public double useHability(double d,Pokemon self, Pokemon opponent) {
		// TODO Auto-generated method stub
		d = d + d*0.3;
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
