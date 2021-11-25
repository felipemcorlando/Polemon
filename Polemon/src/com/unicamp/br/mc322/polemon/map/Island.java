package com.unicamp.br.mc322.polemon.map;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Position;
import com.unicamp.br.mc322.polemon.Types;

public class Island {
	Position position ; // posição superior esquerda ;
	Types type ; // tipo da ilha
	Pokemon pokemons[];//vetor de pokemons da ilha
	Collectable itens[];//vetor de itens presentes na ilha
	int size;// tamanho da ilha 
	Mappable moveObjects[];
	
	public Position getPosition() {
		return position;
	}
	
}
