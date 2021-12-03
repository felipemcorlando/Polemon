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
		case "Absorb":
			this.ability = new Absorb();
		case "FireSpin":
			this.ability = new FireSpin();
		case "Bubble":
			this.ability = new Bubble();
		case "Tackle":
			this.ability = new Tackle();
		case "DragonRage":
			this.ability = new DragonRage();
		case "MegaDrain":
			this.ability = new MegaDrain();
		case "Ember":
			this.ability = new Ember();
		case "WaterGun":
			this.ability = new WaterGun();
		case "QuickAttack":
			this.ability = new QuickAttack();
		case "ThunderShock":
			this.ability = new ThunderShock();
		case "DragonBreath":
			this.ability = new DragonBreath();
		case "Confusion":
			this.ability = new Confusion();
		case "Bite":
			this.ability = new Bite();
		case "Healer":
			this.ability = new Healer();
		case "RazorLeaf":
			this.ability = new RazorLeaf();
		case "FirePunch":
			this.ability = new FirePunch();
		case "Waterfall":
			this.ability = new Waterfall();
		case "ThunderPunch":
			this.ability = new ThunderPunch();
		case "DragonClaw":
			this.ability = new DragonClaw();
		case "PsyBeam":
			this.ability = new PsyBeam();
		case "BodySlam":
			this.ability = new BodySlam();
		case "PetalDance":
			this.ability = new PetalDance();
		case "Flamethrower":
			this.ability = new Flamethrower();
		case "Surf":
			this.ability = new Surf();
		case "ThunderBolt":
			this.ability = new ThunderBolt();
		case "Outrage":
			this.ability = new Outrage();
		case "Psychic":
			this.ability = new Psychic();
		}		
		
		this.position = new Position(Integer.parseInt(p[2]), Integer.parseInt(p[3]), Integer.parseInt(p[4]));
		
	}
	
	@Override
	public void useItem(Pokemon target) {
		// TODO Auto-generated method stub
		if(ability.canLearn(target)) {
			target.teachAbility(ability);
		}
		//Usar método do gustavo para printar erro.
	}
	
}
