package com.unicamp.br.mc322.polemon.abilities.passive;

import java.util.ArrayList;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Types;

public class Bersek implements IPassiveAbility {

	private boolean ativada = false;
	private String name = "Bersek"; //Enquanto o pokemon tiver menos da metade dos HPs, os seus pontos de ataco sao dobrados.
	private ArrayList<Types> typesAllowed = new ArrayList<Types>();
	
	public Bersek() {
		this.typesAllowed.add(Types.ELECTRIC);
		this.typesAllowed.add(Types.DRAGON);
	}
		
	@Override
	public double useHability(double d,Pokemon self, Pokemon opponent) {
		// TODO Auto-generated method stub
		if(ativada == false) {
			if(self.getHp() < self.getInitialHp()/2) {
				self.setAttackPoints(self.getAttackPoints()*2); //Dobra os AttackPoints do pokemon se tiver com a vida reduzida;
				ativada = true;
				System.out.println("Bersek Activated, Pokemon Attack Points buffed!");
			}
		}else {
			if(self.getHp() >= self.getInitialHp()/2) {
				self.setAttackPoints(self.getAttackPoints()/2); //Reverte a passiva.]
				ativada = false;
				System.out.println("Bersek Dectivated, Pokemon Attack Points debuffed!");
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
