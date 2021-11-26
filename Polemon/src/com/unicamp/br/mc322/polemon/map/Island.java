package com.unicamp.br.mc322.polemon.map;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Position;
import com.unicamp.br.mc322.polemon.Types;
import com.unicamp.br.mc322.polemon.items.Collectable;

public class Island {
	Position position ; // posição superior esquerda ;
	Types type ; // tipo da ilha
	Pokemon pokemons[];//vetor de pokemons da ilha
	Collectable itens[];//vetor de itens presentes na ilha
	int size;// tamanho da ilha 
	Mappable moveObjects[];//Obbjetos de mudança de posição
	
	public Island(int newTier,int indexOnPlan,int sizeOfIslands) {
		size=sizeOfIslands;
		position = new Position((size*indexOnPlan)+(1*(indexOnPlan+1)),1,newTier);
		itens = new Collectable[4];
		moveObjects= new Mappable[3];
		pokemons=new Pokemon[2];
		
		
		
	}
	
	
	
	public Position getPosition() {
		return position;
	}
	
}
