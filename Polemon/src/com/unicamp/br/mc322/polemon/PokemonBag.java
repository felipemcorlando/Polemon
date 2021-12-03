package com.unicamp.br.mc322.polemon;

import java.util.ArrayList;

public class PokemonBag {

	private ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();
	
	public void addPokemon(Pokemon pokemon) {
		this.pokemonList.add(pokemon);
	}
	
	public void removePokemon(Pokemon pokemon) {
		this.pokemonList.remove(pokemon);
	}
	
	public ArrayList<Pokemon> getPokemonList() {
		return this.pokemonList;
	}
	
	public Pokemon getPokemon(String name) {
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
			s = s + p.getName()+ "("+p.getHp()+"HP) ";
		}
		return s;
	}
	
	public boolean checkPokemonsLife() {
		for(Pokemon p : pokemonList) {
			if(p.getHp()>0)
				return true;
		}
		return false;
	}
}
