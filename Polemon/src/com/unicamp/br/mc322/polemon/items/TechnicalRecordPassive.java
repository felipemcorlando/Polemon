package com.unicamp.br.mc322.polemon.items;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Position;
import com.unicamp.br.mc322.polemon.abilities.passive.Bersek;
import com.unicamp.br.mc322.polemon.abilities.passive.FlameBody;
import com.unicamp.br.mc322.polemon.abilities.passive.IPassiveAbility;
import com.unicamp.br.mc322.polemon.abilities.passive.Intimidate;
import com.unicamp.br.mc322.polemon.abilities.passive.SheerForce;

public class TechnicalRecordPassive extends TechnicalRecord {

	private IPassiveAbility ability;

	public TechnicalRecordPassive(String name, IPassiveAbility ability, Position position) {
		this.ability = ability;
		this.name = name;
		this.position = position;
	}
	
	public TechnicalRecordPassive (String register) {
		String[] p = register.split(" ");
		this.name = p[0];

		switch(p[1]) {
		case "Bersek":
			this.ability = new Bersek();
			break;
		case "FlameBody":
			this.ability = new FlameBody();
			break;
		case "Intimidate":
			this.ability = new Intimidate();
			break;
		case "SheerForce":
			this.ability = new SheerForce();
			break;
		}		
		
		this.position = new Position(Integer.parseInt(p[2]), Integer.parseInt(p[3]), Integer.parseInt(p[4]));
		
	}
	
	@Override
	public boolean useItem(Pokemon target) {
		// TODO Auto-generated method stub
		if(ability.canLearn(target)) {
			boolean a = target.teachAbility(ability);
			return a;
		}else {
			System.out.println("Nao foi possivel ensinar a habilidade " + ability.getName()+"!");
			return false;
		}
	}

	@Override
	public String toString(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
