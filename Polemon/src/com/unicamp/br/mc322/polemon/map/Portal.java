package com.unicamp.br.mc322.polemon.map;

import com.unicamp.br.mc322.polemon.Player;
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

	}

}
