package com.unicamp.br.mc322.polemon.items;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Position;
import com.unicamp.br.mc322.polemon.abilities.IAbility;

public class TechnicalRecord implements Collectable {

	private String name;
	private Position position;
	private IAbility ability;
	
	@Override
	public void useItem(Pokemon target) {
		// TODO Auto-generated method stub
		if(ability.canLearn(target)) {
			target.teachAbility(ability);
		}
		//Usar método do gustavo para printar erro.
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public String getName() {
		return name;
	}

	
}
