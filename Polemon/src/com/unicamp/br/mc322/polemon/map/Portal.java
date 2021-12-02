package com.unicamp.br.mc322.polemon.map;

import java.util.ArrayList;

import com.unicamp.br.mc322.polemon.Input;
import com.unicamp.br.mc322.polemon.Player;
import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Position;

public class Portal implements Mappable {
	private Position position;

	public Portal(Position newPosition) {
		position=newPosition;
	}


	@Override
	public char getChar() {
		// TODO Auto-generated method stub
		return '0';
	}

	@Override
	public void movePlayer(Player player) {
		// TODO Auto-generated method stub
		ArrayList<Island> visitedIslands=player.getVisitedIslands();
		if(visitedIslands.isEmpty()) {
			System.out.println("No visited islands");
			return;
		}
		System.out.println("\nChoose one island :");
		for (Island aux : visitedIslands) {
			System.out.print(visitedIslands.indexOf(aux)+"-");
			aux.getLowInfo();
			//System.out.println("Press "+visitedIslands.indexOf(aux));
		}

		try {
			int command = Integer.parseInt(Input.readKeyboard())-1;
			
			player.setGlobalPosition(new Position (visitedIslands.get(command).getPosition()));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}


	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

}
