package com.unicamp.br.mc322.polemon.map;

import java.util.ArrayList;

import com.unicamp.br.mc322.polemon.Player;
import com.unicamp.br.mc322.polemon.Position;

public interface Mappable {
	public char getChar();
	public void movePlayer(Player player);
	public Position  getPosition();
}
