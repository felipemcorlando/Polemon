package com.unicamp.br.mc322.polemon;

import com.unicamp.br.mc322.polemon.map.Island;

public class Player {
	private Island visitedIslands[];
	private Position position;
	
	public void changePosition(Position newPosition) {
		position=newPosition;
		return;
	}
	public Position getGlobalPosition() {
		return position;
	}
}
