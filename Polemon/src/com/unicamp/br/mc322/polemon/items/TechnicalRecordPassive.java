package com.unicamp.br.mc322.polemon.items;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.abilities.passive.IPassiveAbility;

public class TechnicalRecordPassive extends TechnicalRecord {

	private IPassiveAbility ability;

	@Override
	public void useItem(Pokemon target) {
		// TODO Auto-generated method stub
		if(ability.canLearn(target)) {
			target.teachAbility(ability);
		}
		//Usar método do gustavo para printar erro.
	}

}
