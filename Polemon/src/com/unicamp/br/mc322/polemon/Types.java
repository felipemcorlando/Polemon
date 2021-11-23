package com.unicamp.br.mc322.polemon;

public enum Types {
	Fire, Grass, Water, Eletric, Normal;

	public double typesRelations(Types attackingType, Types defendingType){
		/*This method returns a multiplier to be applied in the damage given to the defending
		 * pokemon*/
		if (defendingType == Types.Fire) {
			if(attackingType == Types.Water)
				return 2;
			else if(attackingType == Types.Fire)
				return 0.5;
			else if(attackingType == Types.Grass)
				return 0.5;
			else
				return 1;
		}else if(defendingType == Types.Water) {
			if(attackingType == Types.Fire)
				return 0.5;
			else if(attackingType == Types.Water)
				return 0.5;
			else if(attackingType == Types.Grass)
				return 2;
			else if(attackingType == Types.Eletric)
				return 2;
			else
				return 1;
		}else if(defendingType == Types.Grass) {
			if(attackingType == Types.Fire)
				return 2;
			else if(attackingType == Types.Water)
				return 0.5;
			else if(attackingType == Types.Grass)
				return 0.5;
			else if(attackingType == Types.Eletric)
				return 0.5;
			else
				return 1;
		}else if(defendingType == Types.Eletric) {
			if(attackingType == Types.Eletric)
				return 0.5;
			else
				return 1;
		}else
			return 1;
	}
}
