package com.unicamp.br.mc322.polemon;

import java.util.ArrayList;

import com.unicamp.br.mc322.polemon.abilities.active.IActiveAbility;
import com.unicamp.br.mc322.polemon.abilities.passive.IPassiveAbility;

public class Pokemon {
	
	private String name;
	private ArrayList<Types> type = new ArrayList<Types>(2);
	private double hp; //Must be a positive value;
	private int initialHp;
	private int attackPoints; //Must be a positive value;
	private int defensePoints; //Must be a positive value;
	private ArrayList<IActiveAbility> activeAbilities = new ArrayList<IActiveAbility>(); //The Active Abilities list of the pokemon, there is no max index
	private ArrayList<IPassiveAbility> passiveAbilities = new ArrayList<IPassiveAbility>(); //The Passive Abilities list of the pokemon, there is no max index
	private Position position; //Pokemon initial Position;
	private int d; //Distância máxima de captura;
	private int k; //Dificuldade da captura, vai de 2(mais dificil) a 8(mais facil).
	
	
	public Pokemon(String name, Types type1, Types type2, int newHP, int newAttackPoints, int newDefensePoints, Position position){
		//Set the name:
		this.name = name;
		//Set the pokemon type:
		this.type.add(type1);
		if(type2 != null) {
			this.type.add(type2);
		}
		//Set the pokemon HP:
		this.hp = newHP;
		this.initialHp = newHP;
		//Set the pokemon Attack Points:
		this.attackPoints = newAttackPoints;
		//Set the pokemon Defense Points:
		this.defensePoints = newDefensePoints;
		//Set the pokemon initial position:
		this.position = position;
		//Set d:
		this.d = Dice.roll(4, 1);
		//Set k:
		this.k = Dice.roll(4, 2);
	}
	
	public Pokemon (String register) {
		String[] p = register.split(" ");
		this.name = p[0];
		
		this.type.add(this.getType(p[1]));
		this.type.add(this.getType(p[2]));

		this.hp = Integer.parseInt(p[3]);
		this.initialHp = (int) this.hp;

		this.attackPoints = Integer.parseInt(p[4]);

		this.defensePoints = Integer.parseInt(p[5]);

		this.position = new Position(Integer.parseInt(p[6]), Integer.parseInt(p[7]), Integer.parseInt(p[8]));

		this.d = Dice.roll(4, 1);

		this.k = Dice.roll(4, 2);
		
	}
	
	public Types getType(String index) {
		switch(index) {
		case "Types.FIRE":
			return Types.FIRE;
		case "Types.GRASS":
			return Types.GRASS;
		case "Types.WATER":
			return Types.WATER;
		case "Types.ELECTRIC":
			return Types.ELECTRIC;
		case "Types.PSYCHIC":
			return Types.PSYCHIC;
		case "Types.DRAGON":
			return Types.DRAGON;
		default:
			return null;
		}
	}
	
	public boolean teachAbility(IActiveAbility newAb) {
		if(this.findActiveAbility(newAb.getName()) == false) {
			this.activeAbilities.add(newAb);
			System.out.println("Foi possivel ensinar a habilidade " + newAb.getName()+"!");
			return true;
		}else {
			System.out.println("Nao foi possivel ensinar a habilidade " + newAb.getName()+"!");
			return false;
		}
	}
	
	public boolean teachAbility(IPassiveAbility newAb) {
		if(this.findPassiveAbility(newAb.getName()) == false) {
			this.passiveAbilities.add(newAb);
			System.out.println("Foi possivel ensinar a habilidade " + newAb.getName()+"!");
			return true;
		}else {
			System.out.println("Nao foi possivel ensinar a habilidade " + newAb.getName()+"!");
			return false;
		}
	}
	
	public boolean findPassiveAbility(String name) {
		for(IPassiveAbility a : this.passiveAbilities) {
			if(a.getName() == name) {
				return true; //Habilidade encontrada;
			}
		}
		return false; //Habilidade não encontrada;
	}
	
	public boolean findActiveAbility(String name) {
		for(IActiveAbility a : this.activeAbilities) {
			if(a.getName() == name) {
				return true; //Habilidade encontrada;
			}
		}
		return false; //Habilidade não encontrada;
	}
	
	public IActiveAbility getActiveAbility(int index) {
		return activeAbilities.get(index);
	}
	
	public ArrayList<IPassiveAbility> getPassives(){
		return this.passiveAbilities;
	}
	
	public ArrayList<IActiveAbility> getActives(){
		return this.activeAbilities;
	}
	
	public ArrayList<Types> getPokemonType() {
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
			newHp = this.initialHp;
		
		this.hp = newHp;
	}
	
	public void setAttackPoints(int newAP) {
		this.attackPoints = newAP;
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
	
	public String toString() {
		if (this.hp <= 0) //desmaiado
			return this.name + " X-X";
		else
			return this.name+" | HP:"+this.hp+" | ATK:"+this.attackPoints+" | DEF:"+this.defensePoints;
	}
}
