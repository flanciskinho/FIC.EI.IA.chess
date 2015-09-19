package model;

/**
 * Clase que implementa la estrategia de A-Estrella
 * @author flanciskinho
 *
 */
public class AStar extends Strategy {

	public AStar() {
		super();
	}
	
	@Override
	public String getStrategyName() {
		return "A*";
	}
	
	/**
	 * En la estrategia de A-Estrella, el nodo se inserta de manera ordenada
	 */
	@Override
	public void insertNode(Node n){
		fringe.orderedInsert(n);
	}
}
