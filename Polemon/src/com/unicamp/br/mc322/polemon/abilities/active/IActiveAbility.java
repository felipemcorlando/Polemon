package com.unicamp.br.mc322.polemon.abilities.active;

import com.unicamp.br.mc322.polemon.Pokemon;

public interface IActiveAbility {

	public double useHability(Pokemon self, Pokemon opponent);
	
	public boolean canLearn(Pokemon learner);
	
	public String getName();
	
}
