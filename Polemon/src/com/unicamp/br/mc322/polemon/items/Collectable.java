package com.unicamp.br.mc322.polemon.items;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Position;

public interface Collectable {

	public boolean useItem(Pokemon target);
	
	public char getChar();
	
	public String toString();
	
	public Position getPosition();
	
	public String getName();
	
}
