package com.unicamp.br.mc322.polemon.map;

import com.unicamp.br.mc322.polemon.Player;
import com.unicamp.br.mc322.polemon.Position;

public class DownElevator extends Elevator implements Mappable {

	public DownElevator(Position newPosition) {
		super(newPosition);
		
	}

	@Override
	public String getChar() {
		
		return "E";
	}

	@Override
	public void movePlayer(Player player) {
		

	}

}
