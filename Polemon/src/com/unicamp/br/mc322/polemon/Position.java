package com.unicamp.br.mc322.polemon;

public class Position {
	private int x, y, z;
	
	public Position(int x, int y, int z) {
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	
	public Position(Position o) {
		this.setX(o.getX());
		this.setY(o.getY());
		this.setZ(o.getZ());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public Position travel(Position distance) {
		this.x += distance.getX();
		this.y += distance.getY();
		this.z += distance.getZ();
		
		return this;
	}
	
	public Position travel(int x, int y, int z) {
		this.x += x;
		this.y += y;
		this.z += z;
		
		return this;
	}
	
	public boolean equals(int x, int y, int z) {
		return (this.x == x && this.y == y && this.z == z);
	}
	
	public boolean equals(Position o) {
		return (this.x == o.x && this.y == o.y && this.z == o.z);
	}
	
}
