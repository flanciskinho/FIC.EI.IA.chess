package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para implementar el nodo y sus caracteristicas
 * 
 * @author flanciskinho
 * @version 0.1
 */
public class Node implements Comparable<Node>{
	private final int pathCost;
	private final int depth;
	private Node parent;
	private List<Node> successors;
	private final State state;
	private final Operator action;
	
	/**
	 * Constructor !!!MIRAR QUE EL ESTADO NO SEA NULO
	 * @param c Coste hasta este nodo
	 * @param d Profundidad del nodo
	 * @param p Nodo padre
	 * @param s Estado que tiene el nodo
	 * @param op Accion que se realizo para llegar a ese nodo
	 */
	private Node(int c, int d, Node p, State s, Operator op) {
		pathCost = c;
		depth = d;
		parent = p;
		state = s;
		successors = new ArrayList<Node>();
		action = op;
	}
	
	/**
	 * Constructor para el nodo raiz
	 * @param s Estado en el que empieza el nodo raiz
	 */
	public Node(State s) {
		this(0,0,null,s,null);
	}
	
	/**
	 * 
	 * @return la profundidad en la que esta el nodo
	 */
	public int getDepth() {
		return depth;
	}
	
	/**
	 * 
	 * @return el nodo padre que apunta a el
	 */
	public Node getParent() {
		return parent;
	}
	
	/**
	 * 
	 * @return devuelve el coste acumulado hasta este nodo
	 */
	public int getPathCost() {
		return pathCost;
	}
	
	/**
	 * 
	 * @return devuelve el estado que hay en el nodo
	 */
	public State getState() {
		return state;
	}
	
	
	/**
	 * Estimacion de lo que cuesta la solucion pasando por este nodo
	 * @return pathCost + heuristic
	 */
	public int f() {
		return pathCost + state.getHeuristic();
	}
	
	/**
	 * 
	 * @return la estimaci—n de lo que queda para llegar al final
	 */
	public int getHeuristic() {
		return state.getHeuristic();
	}
	
	/**
	 * Metodo que indica si el estado del nodo contiene la solucion
	 * @return 
	 */
	public boolean isGoal() {
		return state.goalTest();
	}
	
	/**
	 * 
	 * @return devuelve un conjunto de nodos que se pueden alcanzar desde el actual
	 */
	public List<Node> getSuccessors() {
		return successors;
	}
	
	
	private boolean addSuccessors(Node n){
		return successors.add(n);
	}
	
	/**
	 * Metodo que expande los nodos
	 * @return el numero de nodos generados
	 */
	public int expand() {
		if (!successors.isEmpty())
			return 0;
		
		Operator[] actions = Operator.values();
		
		int nodes = 0;
		Node aux;
		State s;
		for (int cnt = 0; cnt < actions.length; cnt++) {
			if (state.executable(actions[cnt])) {
				s = state.successor(actions[cnt]);
				aux = new Node(pathCost+actions[cnt].getCost()+s.getCost(), depth+1, this, s, actions[cnt]);
				if (addSuccessors(aux))
					nodes++;
			}
		}
		
		return nodes;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Node)
			return ((Node) o).state.equals(state);
		
		return false;
	}
	
	@Override	
	public String toString() {
		return "State: "+state+"\n"+
				"Depth: "+depth+"\n"+
				"PathCost: "+pathCost+"\n"+
				"Action: "+action+"\n"+
				"Heuristic: "+getHeuristic();
	}

	@Override
	public int compareTo(Node n) {
		return (new Integer(this.f())).compareTo(new Integer(n.f()));
	}
	
	public String toStringReversePath() {
		boolean VERBOSE = true;
		
		if (parent == null)
			return (VERBOSE)?state.toString():"";
		
		String str = parent.toStringReversePath();
		
		str = str + action.toString() +"->";
		//Seria el verbose o no
		if (VERBOSE) {
			str = str+state.toString();
		}
		
		return str;
	}
	
	@Override
	public int hashCode() {
		return state.hashCode();
	}
	
	/*/
	public static void main(String args[]){
		int SIZE = 10;
		int board[][] = new int[SIZE][SIZE];
		int init = 1;
		int end = 5;
		
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				board[i][j] = 0;
		
		Horse horse = new Horse(init,init, end,end, board, 1);
		
		Node n = new Node(horse);
		System.out.println(n);
		System.out.println("\t\t\t"+n.expand());
		
		for (Node aux: n.successors){
			System.out.println(aux+"\n");
		}
	}
	/**/
}
