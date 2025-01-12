package com.unicamp.br.mc322.polemon;

public enum Types {
	FIRE, GRASS, WATER, ELECTRIC, PSYCHIC, DRAGON;
	
	public Types getType(int index) { //Método para usado para pegar um método aleatório para a ilha.
		switch(index) {
		case 0:
			return Types.FIRE;
		case 1:
			return Types.GRASS;
		case 2:
			return Types.WATER;
		case 3:
			return Types.ELECTRIC;
		case 4:
			return Types.PSYCHIC;
		case 5:
			return Types.DRAGON;
		default:
			return null;
		}
	}
	
	public String toString(Types t) {
		switch(t) {
		case FIRE:
			return "Fire";
		case WATER:
			return "Water";
		case GRASS:
			return "Grass";
		case ELECTRIC:
			return "Electric";
		case PSYCHIC:
			return "Psychic";
		case DRAGON:
			return "Dragon";
		default:
			return null;
		}
	}
}
