package com.unicamp.br.mc322.polemon.abilities.active;

import java.util.ArrayList;
import java.util.Random;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Types;

public class Healer implements IActiveAbility {

	private String name = "Healer"; //Habilidade cura a vida do pokemon
	private ArrayList<Types> typesAllowed = new ArrayList<Types>();
	
	public Healer() {
		this.typesAllowed.add(Types.GRASS);
		this.typesAllowed.add(Types.PSYCHIC);
		this.typesAllowed.add(Types.WATER);
	}
	
	@Override
	public double useHability(Pokemon self, Pokemon opponent) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int i = rand.nextInt(100);
		if(i < 70) { //Buff de 10% no hp
			self.setHp(self.getHp() + 0.1*self.getInitialHp());
		}else if(i < 89) { //Buff de 20% no hp
			self.setHp(self.getHp() + 0.2*self.getInitialHp());
		}else { //Buff de 0% no hp
			self.setHp(self.getHp() + 0);
		}
		return 0;
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
		String s = this.getName()+"(Heal Ability)";
		return s;
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
