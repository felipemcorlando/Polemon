package com.unicamp.br.mc322.polemon;

import java.util.ArrayList;
import com.unicamp.br.mc322.polemon.habilities.IAbility;

public class Pokemon {
	
	private String name;
	private Types[] type = new Types[2];
	private double hp; //Must be a positive value;
	private int initialHp;
	private int attackPoints; //Must be a initial value between 0 and 15;
	private int defensePoints; //Must be a initial value between 0 and 15;
	private ArrayList<IAbility> abilities = new ArrayList<IAbility>(); //The abilities list of the pokemon, there is no max index
	private Position position; //Pokemon initial Position;
	private int d; //Distância máxima de captura;
	private int k; //Dificuldade da captura, vai de 2(mais dificil) a 8(mais facil).
	
	
	public Pokemon(String name, Types type1, Types type2, int newHP, int newAttackPoints, int newDefensePoints,IAbility ab1, IAbility ab2, Position position) throws IllegalValueException{
		//Set the name:
		this.name = name;
		//Set the pokemon type:
		this.type[0] = type1;
		this.type[1] = type2;
		//Set the pokemon HP:
		if(newHP < 0) {
			newHP = 0;
			throw new IllegalValueException("Hit Points must be a positive value");
		}
		this.hp = newHP;
		this.initialHp = newHP;
		//Set the pokemon Attack Points:
		if(newAttackPoints > 15 || newAttackPoints < 0) {
			newAttackPoints = 0;
			throw new IllegalValueException("Attack Points must be between 0 and 15");
		}
		this.attackPoints = newAttackPoints;
		//Set the pokemon Defense Points:
		if(newDefensePoints > 15 || newDefensePoints < 0) {
			newDefensePoints = 0;
			throw new IllegalValueException("Defense Points must be between 0 and 15");
		}	
		this.defensePoints = newDefensePoints;
		//Set the pokemon standard abilities
		this.abilities.add(ab1);
		this.abilities.add(ab2);
		//Set the pokemon initial position:
		this.position = position;
		//Set d:
		this.d = Dice.roll(4, 1);
		//Set k:
		this.k = Dice.roll(4, 2);
	}
	
	public void teachAbility(IAbility newAb) {
		this.abilities.add(newAb);
	}
	
	public Types[] getType() {
		return type;
	}

	public double getHp() {
		return hp;
	}
	
	public int getInitialHp() {
		return this.initialHp;
	}
	
	public void setHp(double newHp) {
		if(newHp <= 0) 
			newHp = 0;
		else if(newHp >= this.initialHp)
			newHp = initialHp;
		this.hp = newHp;
	}


	public int getAttackPoints() {
		return attackPoints;
	}


	public int getDefensePoints() {
		return defensePoints;
	}

	public String getName() {
		return name;
	}

	public Position getPosition() {
		return position;
	}

	public int getD() {
		return d;
	}

	public int getK() {
		return k;
	}
}
