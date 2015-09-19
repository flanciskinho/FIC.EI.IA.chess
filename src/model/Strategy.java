package model;

import java.util.List;

/**
 * Clase padre para poder definir estrategias
 * 
 * @author flanciskinho
 * @version 0.1
 */
public abstract class Strategy {
	protected Fringe fringe;
	
	/**
	 * constructor
	 */
	protected Strategy() {
		fringe = new Fringe();
	}

	/**
	 * Devuelve el primer nodo de la frontera, ¡¡y se elimina de ella!!
	 * @return
	 */
	public Node getNode() {
		return fringe.delete();
	}
	
	/**
	 * 
	 * @return devuelve true si la lista esta vacia
	 */
	public boolean isEmpty() {
		return fringe.isEmpty();
	}
	
	/**
	 * Metodo que inserta una coleccion de nodos en la frontera
	 * @param nodes
	 */
	public void insertNodes(List<Node> nodes) {
		if (nodes == null)
			return;
		
		for (Node n: nodes){
			insertNode(n);
		}
	}
	
	/**
	 * Metodo que inserta un nodo en la lista
	 * @param n
	 */
	public abstract void insertNode(Node n);
	
	/**
	 * 
	 * @return nombre de la estrategia a seguir
	 */
	public abstract String getStrategyName();
}
