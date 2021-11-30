package com.unicamp.br.mc322.polemon.items;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.abilities.active.IActiveAbility;

public class TechnicalRecordActive extends TechnicalRecord {

	private IActiveAbility ability;
	
	@Override
	public void useItem(Pokemon target) {
		// TODO Auto-generated method stub
		if(ability.canLearn(target)) {
			target.teachAbility(ability);
		}
		//Usar método do gustavo para printar erro.
	}
	
}
