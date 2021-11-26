package com.unicamp.br.mc322.polemon;

import java.util.ArrayList;

public class PokemonBag {

	private ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();
	
	public void addItem(Pokemon pokemon) {
		this.pokemonList.add(pokemon);
	}
	
	public void removeItem(Pokemon pokemon) {
		this.pokemonList.remove(pokemon);
	}
	
	public ArrayList<Pokemon> getPokemonList() {
		return this.pokemonList;
	}
	
	public Pokemon getItem(String name) {
		Pokemon pokemon = null;
		for(Pokemon p : this.pokemonList) {
			if(p.getName() == name) {
				pokemon = p;
			}
		}
		return pokemon;
	}
	
	public String toString() {
		String s = "";
		for(Pokemon p : this.pokemonList) {
			s = s + p.getName()+ " ";
		}
		return s;
	}
}
