package com.unicamp.br.mc322.polemon.map;

import com.unicamp.br.mc322.polemon.Player;
import com.unicamp.br.mc322.polemon.Position;

public interface Mappable {
	public String getChar();
	public void movePlayer(Player player);
	public Position  getPosition();
}
