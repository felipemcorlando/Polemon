package com.unicamp.br.mc322.polemon.map;

import java.util.ArrayList;

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
		 Position newPosition = new Position (1,1 ,player.getGlobalPosition().getZ()+1);
		 if (newPosition.getZ()>3) {
			 System.out.println("Impossible movement");
			 return;
		 }
		 player.setGlobalPosition(newPosition);
		 //set actual island
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

}
