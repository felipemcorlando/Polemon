package com.unicamp.br.mc322.polemon.map;

import com.unicamp.br.mc322.polemon.Position;

public abstract class Elevator implements Mappable {
	Position position;
	public Elevator(Position newPosition) {
		position=newPosition;
	}
}
