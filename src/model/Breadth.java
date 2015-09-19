package model;

/**
 * Clase que implementa la estrategia de anchura
 * 
 * @author flanciskinho
 * @version 0.1
 */
public class Breadth extends Strategy {

	public Breadth() {
		super();
	}
	
	@Override
	public String getStrategyName() {
		return "Anchura";
	}
	
	/**
	 * La estrategia de anchura, la frontera es FIFO
	 */
	@Override
	public void insertNode(Node n){
		fringe.insertLast(n);
	}
}
