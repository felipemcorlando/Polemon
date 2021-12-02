package com.unicamp.br.mc322.polemon;

import com.unicamp.br.mc322.polemon.items.*;
import com.unicamp.br.mc322.polemon.map.*;

import java.util.*;

public class Player {

	private Position globalPosition;
	private Pokemon activatedPokemon;
	
	private Island actualIsland;
	
	private PokemonBag minePokemons;	
	private Inventory inventory;
	private ArrayList<Island> visitedIslands;
	private boolean inCombat;
	
	public Player(Position initialPos, Pokemon starter, Island initialIsland) {
		this.globalPosition = initialPos;
		this.minePokemons = new PokemonBag();
		this.minePokemons.addItem(starter);
		this.activatedPokemon = this.minePokemons.getPokemonList().get(0);
		
		this.inventory = new Inventory();
		
		this.visitedIslands = new ArrayList<Island>();
		this.visitedIslands.add(initialIsland);
		this.actualIsland = initialIsland;
		
		this.inCombat = false;
	}
	
	public Player() {
		this.globalPosition = new Position(1, 1, 0);
	}

	public boolean isInCombat() {
		return this.inCombat;
	}
	
	public Collectable collectItem(Plan actualPlan) {
		//checks if there is an item in the same position of player, if true collects
		Island island = actualPlan.findIsland(this.globalPosition);
		for (Collectable item : island.getItens()){
			if (this.globalPosition.equals(item.getPosition())) {
				inventory.addItem(item);
				island.getItens().remove(item);
				return item;
			}
		}
		
		return null;
	}
	
	public boolean choosePokemon(int i) {
		Pokemon p = this.minePokemons.getPokemonList().get(i);
		if (p == null)
			return false;
		
		this.activatedPokemon = p;
		return true;
	}
	
	public void useItem(Collectable item) {}
	
	public Position getGlobalPosition() {
		return globalPosition;
	}

	public void setGlobalPosition(Position globalPosition) {
		this.globalPosition = globalPosition;
	}

	public PokemonBag getMinePokemons() {
		return this.minePokemons;
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public ArrayList<Island> getVisitedIslands(){
		return this.visitedIslands;
	}

	public Island getActualIsland() {
		return actualIsland;
	}

	public void setActualIsland(Island actualIsland) {
		if (!this.visitedIslands.contains(actualIsland))
			this.visitedIslands.add(actualIsland);
		
		this.actualIsland = actualIsland;
	}

	public Pokemon getActivatedPokemon() {
		return activatedPokemon;
	}

	public void setActivatedPokemon(Pokemon activatedPokemon) {
		this.activatedPokemon = activatedPokemon;
	}
	
	public void setInCombat (boolean x) {
		this.inCombat = x;
	}
	
}