package com.unicamp.br.mc322.polemon;

public class Pokemon {
	
	private Types type;
	private double hp; //Must be a positive value;
	private int attackPoints; //Must be a value between 0 and 15;
	private int defensePoints; //Must be a value between 0 and 15;
	
	public Pokemon(Types type, int newHP, int newAttackPoints, int newDefensePoints) throws IllegalValueException{
		this.type = type;
		if(newHP < 0) {
			this.hp = 0;
			throw new IllegalValueException("Hit Points must be a positive value");
		}
		if(newAttackPoints > 15 || newAttackPoints < 0) {
			this.attackPoints = 0;
			throw new IllegalValueException("Attack Points must be between 0 and 15");
		}
		if(newDefensePoints > 15 || newDefensePoints < 0) {
			this.defensePoints = 0;
			throw new IllegalValueException("Defense Points must be between 0 and 15");
		}	
	}
	
	public Types getType() {
		return type;
	}


	public double getHp() {
		return hp;
	}
	
	public void setHp(double newHp) {
		if(newHp < 0) {
			newHp = 0;
		}
		this.hp = 0;
	}


	public int getAttackPoints() {
		return attackPoints;
	}


	public int getDefensePoints() {
		return defensePoints;
	}
}
