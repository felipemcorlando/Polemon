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
		// TODO Auto-generated method stub

	}

}
