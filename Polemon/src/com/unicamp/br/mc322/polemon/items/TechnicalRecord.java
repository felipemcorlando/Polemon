package com.unicamp.br.mc322.polemon.items;

import com.unicamp.br.mc322.polemon.Position;

public abstract class TechnicalRecord implements Collectable {

	protected String name;
	protected Position position;
	
	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public String getName() {
		return name;
	}
	
	public char getChar() {
		// TODO Auto-generated method stub
		return 'T';
	}
	
}
