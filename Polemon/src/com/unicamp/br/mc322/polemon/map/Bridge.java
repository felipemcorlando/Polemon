package com.unicamp.br.mc322.polemon.map;

import com.unicamp.br.mc322.polemon.Player;
import com.unicamp.br.mc322.polemon.Position;

public class Bridge implements Mappable {
	private Position position;
	
	
	public Bridge (Position newPosition) {
		position=newPosition;
	}
	
	public String getChar() {
		// TODO Auto-generated method stub
		return "=";
	}

	@Override
	public void movePlayer(Player player) {
		// TODO Auto-generated method stub

	}

}
