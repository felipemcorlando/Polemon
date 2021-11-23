package com.unicamp.br.mc322.polemon.habilities;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Types;

public class ActiveHability implements IHability{

	private String name;
	private float damage;
	private Types type;
	
	public ActiveHability(String name, float damage, Types type) {
		this.name = name;
		this.damage = damage;
		this.type = type;
	}
	
	@Override
	public void useHability(Pokemon attacker, Pokemon defender) {
		// TODO Auto-generated method stub
		
	}

}
