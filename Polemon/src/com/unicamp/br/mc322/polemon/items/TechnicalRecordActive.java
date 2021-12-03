package com.unicamp.br.mc322.polemon.items;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Position;
import com.unicamp.br.mc322.polemon.abilities.active.Absorb;
import com.unicamp.br.mc322.polemon.abilities.active.Bite;
import com.unicamp.br.mc322.polemon.abilities.active.BodySlam;
import com.unicamp.br.mc322.polemon.abilities.active.Bubble;
import com.unicamp.br.mc322.polemon.abilities.active.Confusion;
import com.unicamp.br.mc322.polemon.abilities.active.DragonBreath;
import com.unicamp.br.mc322.polemon.abilities.active.DragonClaw;
import com.unicamp.br.mc322.polemon.abilities.active.DragonRage;
import com.unicamp.br.mc322.polemon.abilities.active.Ember;
import com.unicamp.br.mc322.polemon.abilities.active.FirePunch;
import com.unicamp.br.mc322.polemon.abilities.active.FireSpin;
import com.unicamp.br.mc322.polemon.abilities.active.Flamethrower;
import com.unicamp.br.mc322.polemon.abilities.active.FuryAttack;
import com.unicamp.br.mc322.polemon.abilities.active.Healer;
import com.unicamp.br.mc322.polemon.abilities.active.IActiveAbility;
import com.unicamp.br.mc322.polemon.abilities.active.MegaDrain;
import com.unicamp.br.mc322.polemon.abilities.active.Outrage;
import com.unicamp.br.mc322.polemon.abilities.active.PetalDance;
import com.unicamp.br.mc322.polemon.abilities.active.PsyBeam;
import com.unicamp.br.mc322.polemon.abilities.active.Psychic;
import com.unicamp.br.mc322.polemon.abilities.active.QuickAttack;
import com.unicamp.br.mc322.polemon.abilities.active.RazorLeaf;
import com.unicamp.br.mc322.polemon.abilities.active.Surf;
import com.unicamp.br.mc322.polemon.abilities.active.Tackle;
import com.unicamp.br.mc322.polemon.abilities.active.ThunderBolt;
import com.unicamp.br.mc322.polemon.abilities.active.ThunderPunch;
import com.unicamp.br.mc322.polemon.abilities.active.ThunderShock;
import com.unicamp.br.mc322.polemon.abilities.active.WaterGun;
import com.unicamp.br.mc322.polemon.abilities.active.Waterfall;

public class TechnicalRecordActive extends TechnicalRecord {

	private IActiveAbility ability;
	
	public TechnicalRecordActive(String name, IActiveAbility ability, Position position) {
		this.ability = ability;
		this.name = name;
		this.position = position;
	}
	
	public TechnicalRecordActive (String register) {
		String[] p = register.split(" ");
		this.name = p[0];

		switch(p[1]) {
		case "FuryAttack":
			this.ability = new FuryAttack();
			break;
		case "Absorb":
			this.ability = new Absorb();
			break;
		case "FireSpin":
			this.ability = new FireSpin();
			break;
		case "Bubble":
			this.ability = new Bubble();
			break;
		case "Tackle":
			this.ability = new Tackle();
			break;
		case "DragonRage":
			this.ability = new DragonRage();
			break;
		case "MegaDrain":
			this.ability = new MegaDrain();
			break;
		case "Ember":
			this.ability = new Ember();
			break;
		case "WaterGun":
			this.ability = new WaterGun();
			break;
		case "QuickAttack":
			this.ability = new QuickAttack();
			break;
		case "ThunderShock":
			this.ability = new ThunderShock();
			break;
		case "DragonBreath":
			this.ability = new DragonBreath();
			break;
		case "Confusion":
			this.ability = new Confusion();
			break;
		case "Bite":
			this.ability = new Bite();
			break;
		case "Healer":
			this.ability = new Healer();
			break;
		case "RazorLeaf":
			this.ability = new RazorLeaf();
			break;
		case "FirePunch":
			this.ability = new FirePunch();
			break;
		case "Waterfall":
			this.ability = new Waterfall();
			break;
		case "ThunderPunch":
			this.ability = new ThunderPunch();
			break;
		case "DragonClaw":
			this.ability = new DragonClaw();
			break;
		case "PsyBeam":
			this.ability = new PsyBeam();
			break;
		case "BodySlam":
			this.ability = new BodySlam();
			break;
		case "PetalDance":
			this.ability = new PetalDance();
			break;
		case "Flamethrower":
			this.ability = new Flamethrower();
			break;
		case "Surf":
			this.ability = new Surf();
			break;
		case "ThunderBolt":
			this.ability = new ThunderBolt();
			break;
		case "Outrage":
			this.ability = new Outrage();
			break;
		case "Psychic":
			this.ability = new Psychic();
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
	
}
