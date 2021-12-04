package com.unicamp.br.mc322.polemon.abilities.passive;

import com.unicamp.br.mc322.polemon.Pokemon;

public interface IPassiveAbility {

	public double useHability(double d,Pokemon self, Pokemon opponent); //Esse double d é passado pois algumas passivas modificam o dano.
	
	public boolean canLearn(Pokemon learner);
	
	public String getName();
	
	public String getTypesAllowed();
	
}
