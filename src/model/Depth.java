package model;

/**
 * Clase que implementa la estrategia de profundidad
 * 
 * @author flanciskinho
 * @version 0.1
 */
public class Depth extends Strategy {

	public Depth() {
		super();
	}
	
	@Override
	public String getStrategyName() {
		return "Profundidad";
	}
	
	/**
	 * La estrategia de profundidad, la frontera es LIFO
	 */
	@Override
	public void insertNode(Node n){
		fringe.insertFirst(n);
	}
}
