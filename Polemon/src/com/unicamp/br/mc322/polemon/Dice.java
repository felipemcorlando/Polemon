package com.unicamp.br.mc322.polemon;

public final class Dice {

	public static int roll(int faces, int nDices) {
		int total = 0;
		for (int i = 0; i < nDices; i++)
			total += 1 + (int)(Math.random() * faces);
		
		return total;
	}
}

