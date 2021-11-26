package com.unicamp.br.mc322.polemon.abilities;

import com.unicamp.br.mc322.polemon.Pokemon;

public interface IAbility {

	public void useHability(Pokemon attacker, Pokemon defender);
	
	public boolean canLearn(Pokemon learner);
	
	public String getName();
	
}
