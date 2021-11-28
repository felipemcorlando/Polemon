package com.unicamp.br.mc322.polemon;

import java.util.ArrayList;
import com.unicamp.br.mc322.polemon.items.Collectable;


public class Inventory {
	
	private ArrayList<Collectable> itemsList = new ArrayList<Collectable>();
	
	public void addItem(Collectable item) {
		this.itemsList.add(item);
	}
	
	public void removeItem(Collectable item) {
		this.itemsList.remove(item);
	}
	
	public ArrayList<Collectable> getItemsList () {
		return this.itemsList;
	}
	
	public Collectable getItem(String name) {
		Collectable item = null;
		for(Collectable i : this.itemsList) {
			if(i.getName() == name) {
				item = i;
			}
		}
		return item;
	}
	
	public String toString() {
		String s = "";
		for(Collectable i : this.itemsList) {
			s = s + i.getName()+" ";
		}
		return s;
	}
	
}
