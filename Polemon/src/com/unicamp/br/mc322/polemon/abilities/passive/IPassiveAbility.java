package com.unicamp.br.mc322.polemon.abilities.passive;

import com.unicamp.br.mc322.polemon.Pokemon;

public interface IPassiveAbility {

	public double useHability(double d,Pokemon self, Pokemon opponent);
	
	public boolean canLearn(Pokemon learner);
	
	public String getName();
	
}
