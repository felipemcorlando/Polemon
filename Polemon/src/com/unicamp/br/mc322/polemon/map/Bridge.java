package com.unicamp.br.mc322.polemon.map;

import java.util.ArrayList;

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
		Position playerPosition = player.getGlobalPosition();
		Island actualIsland=player.getActualIsland();
		if (actualIsland.getPosition().getX()<position.getX()) {
			player.setGlobalPosition(playerPosition.travel((new Position(1,0,0))));
		}
		else {
			player.setGlobalPosition(playerPosition.travel((new Position(-1,0,0))));
		}
		
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

}
