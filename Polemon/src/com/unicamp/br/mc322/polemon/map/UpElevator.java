package com.unicamp.br.mc322.polemon.map;

import com.unicamp.br.mc322.polemon.Player;
import com.unicamp.br.mc322.polemon.Position;

public class UpElevator extends Elevator implements Mappable {

	public UpElevator(Position newPosition) {
		super(newPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getChar() {
		// TODO Auto-generated method stub
		return"E";
	}

	@Override
	public void movePlayer(Player player) {
		 Position newPosition = new Position (player.getGlobalPosition().getX(),player.getGlobalPosition().getY() ,player.getGlobalPosition().getZ()+1);
		 if (newPosition.getZ()>3) {
			 System.out.println("Impossible movement");
			 return;
		 }
		 player.changePosition(newPosition);
	}

}
