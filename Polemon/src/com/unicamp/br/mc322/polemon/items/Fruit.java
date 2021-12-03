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
	
	public Fruit (String register) {
		String[] p = register.split(" ");
		this.name = p[0];	
		
		this.healQtd = Integer.parseInt(p[1]);
		
		this.position = new Position(Integer.parseInt(p[2]), Integer.parseInt(p[3]), Integer.parseInt(p[4]));
		
	}
	
	@Override
	public boolean useItem(Pokemon target) {
		// TODO Auto-generated method stub
		target.setHp(target.getHp()+healQtd);
		System.out.println("Foi possivel utilizar a fruta "+this.toString()+"!");
		return true;
		
	}
	
	@Override
	public String toString() {
		String s = this.name+"("+this.healQtd+" HP)";
		return s;
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

	@Override
	public char getChar() {
		// TODO Auto-generated method stub
		return 'F';
	}

}
