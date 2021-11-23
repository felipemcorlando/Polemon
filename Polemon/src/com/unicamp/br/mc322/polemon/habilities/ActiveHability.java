package com.unicamp.br.mc322.polemon.habilities;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Types;

public class ActiveHability implements IHability{

	private String name;
	private int damage;
	private Types type;
	
	public ActiveHability(String name, int damage, Types type) {
		this.name = name;
		this.damage = damage;
		this.type = type;
	}
	
	@Override
	public void useHability(Pokemon attacker, Pokemon defender) {
		// TODO Auto-generated method stub
		double multiplier = type.typesRelations(type, defender.getType());
		double damageDealt = this.damage*multiplier + attacker.getAttackPoints() - defender.getDefensePoints();
		//O multiplicador referente a relação de tipos apenas multiplica o dano da habilidade (atributo damage);
		if (damageDealt < 0)
			damageDealt = 0;
		//Isso é apenas para evitar valores negativos que causariam problemas.
		defender.setHp(defender.getHp() - damageDealt);
	}

	public String getName() {
		return name;
	}

}
