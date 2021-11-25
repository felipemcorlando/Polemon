package com.unicamp.br.mc322.polemon.items;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Position;

public interface Collectable {

	public void useItem(Pokemon target);
	
	public Position getPosition();
	
	public String getName();
	
}
