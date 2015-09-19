package model;


/**
 * Clase que contiene la logica comun de los algoritmos de busqueda (tree & graph)
 * @author flanciskinho
 * @version 0.1
 */
public abstract class Search {

	/**
	 * Estrategia que se sigue a la hora de escoger un nodo para expandir
	 */
	protected final Strategy strategy;
	/**
	 * Numero de nodos generados
	 */
	private int numNodes = 1;
	
	/**
	 * Nodo raiz con el que se inicia el proceso de busqueda
	 */
	protected final Node rootNode;
	
	/**
	 * Es el nodo que contiene el estado solucion 
	 */
	protected Node goalNode = null;
	
	/**
	 * constructor ¡¡¡COMPROBAR QUE EL NODO NO ES NULO!!!!
	 * @param root
	 */
	protected Search(Node root, Strategy s) {
		rootNode = root;
		strategy = s;
	}
	
	/**
	 * Metodo para saber si un nodo es objetivo o no
	 * @param n
	 * @return
	 */
	public boolean isGoal(Node n) {
		return n.getState().goalTest();
	}
	
	/**
	 * 
	 * @return devuelve la estrategia empleada en el algoritmo de busqueda
	 */
	public Strategy getStrategy() {
		return strategy;
	}
	
	/**
	 * 
	 * @return el nodo con el que se empezo la busqueda
	 */
	public Node getRootNode() {
		return rootNode;
	}
	
	/**
	 * 
	 * @return en caso de que la busqueda tenga solucion devuelve el nodo que la contiene
	 */
	public Node getGoalNode() {
		return goalNode;
	}
	
	/**
	 * 
	 * @return numero de nodos generados
	 */
	public int getNumNodes() {
		return numNodes;
	}
	
	/**
	 * Metodo que incrementa el numero de nodos generados
	 * @param num
	 */
	protected void addNumNodes(int num) {
		numNodes += num;
	}
	
	/**
	 * Metodo que realiza la busqueda de la solucion
	 * @return
	 */
	public abstract boolean search();
	
	/**
	 * 
	 * @return el nombre del metodo de busqueda
	 */
	public abstract String getSearchName();
}
