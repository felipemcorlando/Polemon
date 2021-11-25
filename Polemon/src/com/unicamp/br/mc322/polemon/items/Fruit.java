package com.unicamp.br.mc322.polemon.items;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Position;

public class Fruit implements Collectable{

	private String name;
	private Position position;
	private int healQtd; //Quantidade de hp que a fruit cura;
	
	public Fruit(String name, int healQtd, Position position) {
		this.name = name;
		this.position = position;
		this.healQtd = healQtd;
	}
	
	@Override
	public void useItem(Pokemon target) {
		// TODO Auto-generated method stub
		target.setHp(target.getHp()+healQtd);
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return this.position;
	}
	
	@Override
	public String getName() {
		return name;
	}

}
