package com.unicamp.br.mc322.polemon.map;

import java.util.ArrayList;

import com.unicamp.br.mc322.polemon.Pokemon;
import com.unicamp.br.mc322.polemon.Position;
import com.unicamp.br.mc322.polemon.Types;
import com.unicamp.br.mc322.polemon.items.Collectable;
import java.util.Random;

public class Island {
	private Position position ; // posição superior esquerda ;
	private Types type ; // tipo da ilha
	private ArrayList <Pokemon> pokemons;//vetor de pokemons da ilha
	private ArrayList <Collectable>  itens;//vetor de itens presentes na ilha
	private int size;// tamanho da ilha 
	private ArrayList<Mappable> moveObjects;//Obbjetos de mudança de posição
	private int indexOnPlan;
	
	
	public Island(int newTier,int newIndexOnPlan,int sizeOfIslands) {
		this.indexOnPlan=newIndexOnPlan;
		size=sizeOfIslands;
		position = new Position((size*newIndexOnPlan)+(1*(newIndexOnPlan+1)),1,newTier);
		itens = new ArrayList<Collectable>();
		moveObjects= new ArrayList<Mappable>();
		pokemons=new ArrayList<Pokemon>();
		
		Random random=new Random();
		int typeIndex=random.nextInt(5);
		type= Types.values()[typeIndex];
		
		//metodos que adicionam elevadores 
		if (newIndexOnPlan==0 && newTier!=0) {
			this.addDownElevator();
		}
		if (newIndexOnPlan==3 && newTier!=3) {
			this.addUpElevator();
			this.addPortal(new Position((position.getX()+(size/2)),(position.getY()+(size/2)),position.getZ()));//metodo para adicao de portal na ultima ilha do plano
		}
		
		//metodos que adicionarão bridges;
		if(newIndexOnPlan==0) {
			this.addBridge(new Position((size+1),(size/2),newTier));
		}
		else if(newIndexOnPlan==3) {
			this.addBridge(new Position(position.getX()-1,(size/2),newTier));
		}
		else{
			this.addBridges(new Position(position.getX()+(size),(size/2),newTier),new Position(position.getX()-1,(size/2),newTier));
		}
		
		 
		
		
	}
	
	public int getSize() {
		return this.size;
	}
	
	
	private void addDownElevator() {
		Mappable newDownElevator=new DownElevator(new Position(1,1,position.getZ()));
		moveObjects.add(newDownElevator);
	}
	private void addUpElevator() {
		Mappable newUpElevator=new UpElevator(new Position((size*4)+3,size,position.getZ()));
		moveObjects.add(newUpElevator);
	}
	
	private void addBridge(Position bridgePosition) {
		Mappable newBridge= new Bridge(bridgePosition);
		moveObjects.add(newBridge);
	}
	private void addBridges(Position bridgePosition1,Position bridgePosition2) {
		Mappable newBridge1= new Bridge(bridgePosition1);
		moveObjects.add(newBridge1);
		Mappable newBridge2= new Bridge(bridgePosition2);
		moveObjects.add(newBridge2);
	}
	
	private void addPortal(Position portalPosition) {
		Mappable newPortal= new Portal(portalPosition);
		moveObjects.add(newPortal);
	}
	
	
	public ArrayList <Collectable> getItens() {
		return this.itens;
	}
	
	public void addPokemon(Pokemon p) {
		this.pokemons.add(p);
	}
	
	public Position getPosition() {
		return position;
	}

	public ArrayList<Mappable> getMoveObjects() {
		return moveObjects;
	}
	
	public ArrayList<Pokemon> getPokemons(){
		return pokemons;
	}

	public String getType() {
		// TODO Auto-generated method stub
		return type.name();
	}
	
	public void getLowInfo() {
		//System.out.println("Island "+indexOnPlan+" from plan "+position.getZ()+"\t type : "+type.name() );
		System.out.println("Island "+(indexOnPlan+1)+" ("+type.name()+") from plan "+position.getZ());
		return;
	}
	
	public int getIndexOnPlan() {
		return this.indexOnPlan;
	}
}
