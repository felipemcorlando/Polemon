package com.unicamp.br.mc322.polemon.map;

import com.unicamp.br.mc322.polemon.Position;

public class Plan {
	Island islands [];
	int tier ; // "andar" do plano
	
	
	public Island findIsland(Position position) {
		if((position.getY()%9==0) ||position.getY()==0 ||position.getX()==0||position.getX()==9) {
			System.out.println("No island in this position");
			return null;
		}
		
		int posY=position.getY();
		int index= posY/9;
		
		return islands[index];
		
	}
}
