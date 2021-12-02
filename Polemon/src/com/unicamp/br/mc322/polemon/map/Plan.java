package com.unicamp.br.mc322.polemon.map;

import java.util.ArrayList;

import com.unicamp.br.mc322.polemon.Position;

public class Plan {
	private ArrayList<Island> islands;
	private int tier ; // "andar" do plano
	private int sizeOfIslands;
	
	public Plan(int newTier,int newSizeOfIslands) {
		tier=newTier;
		sizeOfIslands=newSizeOfIslands;
		islands = new ArrayList<Island>();
		int aux=0;
		
		for(;aux<4;aux++) {
			islands.add(new Island(newTier,aux,sizeOfIslands));
		}
	}
	
	public Island findIsland(Position position) {
		if((position.getX()%(sizeOfIslands+1)==0) ||position.getY()==0 ||position.getX()==0||position.getY()==(sizeOfIslands+1)) {
			//System.out.println("No island in this position");
			return null;
		}
		
		int index = position.getX()/(this.sizeOfIslands+1);
		return this.islands.get(index);
	}
}
