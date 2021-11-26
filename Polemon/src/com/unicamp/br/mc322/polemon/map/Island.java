package com.unicamp.br.mc322.polemon.map;

import java.util.ArrayList;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Position;
import com.unicamp.br.mc322.polemon.Types;
import com.unicamp.br.mc322.polemon.items.Collectable;
import java.util.Random;

public class Island {
	private Position position ; // posição superior esquerda ;
	private Types type ; // tipo da ilha
	private ArrayList <Pokemon> pokemons;//vetor de pokemons da ilha
	private ArrayList <Collectable>  itens;//vetor de itens presentes na ilha
	private int size;// tamanho da ilha 
	private ArrayList<Mappable> moveObjects;//Obbjetos de mudança de posição
	
	public Island(int newTier,int indexOnPlan,int sizeOfIslands) {
		size=sizeOfIslands;
		position = new Position((size*indexOnPlan)+(1*(indexOnPlan+1)),1,newTier);
		itens = new ArrayList<Collectable>();
		moveObjects= new ArrayList<Mappable>();
		pokemons=new ArrayList<Pokemon>();
		
		Random random=new Random();
		int typeIndex=random.nextInt(5);
		type= Types.values()[typeIndex];
		
		
	}
	
	public int getSize() {
		return this.size;
	}
	
	public ArrayList <Collectable> getItens() {
		return this.itens;
	}
	
	public void addPokemon(Pokemon p) {
		this.pokemons.add(p);
	}
	
	public Position getPosition() {
		return position;
	}
	
}
