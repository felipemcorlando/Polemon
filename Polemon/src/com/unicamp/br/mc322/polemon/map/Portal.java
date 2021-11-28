package com.unicamp.br.mc322.polemon.map;

import java.util.ArrayList;

import com.unicamp.br.mc322.polemon.Player;
import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Position;

public class Portal implements Mappable {
	private Position position;
	
	public Portal(Position newPosition) {
		position=newPosition;
	}
	
	
	@Override
	public String getChar() {
		// TODO Auto-generated method stub
		return "0";
	}

	@Override
	public void movePlayer(Player player) {
		// TODO Auto-generated method stub
		ArrayList<Island> visitedIslands=player.getVisitedIslands();
		if(visitedIslands.isEmpty()) {
			System.out.println("No visited islands");
			return;
		}
		System.out.println("Chose one island :");
		for (Island aux : visitedIslands) {
			aux.getLowInfo();
			System.out.println("Press "+visitedIslands.indexOf(aux));
		}
		// adicionar aqui o leitor do comando 
	}


	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

}
