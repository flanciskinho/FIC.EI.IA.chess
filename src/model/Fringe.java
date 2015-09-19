package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Esta clase es la que implemente la frontera
 * 
 * @author flanciskinho
 * @version 0.1
 */
public class Fringe {
	private ArrayList<Node> fringe;
	
	/**
	 * Constructor
	 */
	public Fringe() {
		fringe = new ArrayList<Node>();
	}
	
	/**
	 * Anade un nodo al principio de la frontera
	 * @param n
	 */
	public void insertFirst(Node n) {
		if (n == null)
			return;
		
		fringe.add(0, n);
	}
	
	/**
	 * Anade un nodo al final de la frontera
	 * @param n
	 */
	public void insertLast(Node n) {
		if (n == null)
			return;
		
		fringe.add(n);
	}
	
	/**
	 * Inserta un nodo en la lista de manera ordenada
	 * @param n
	 */
	public void orderedInsert(Node n) {
		if (n == null)
			return;
		
		fringe.add(n);
		Collections.sort(fringe);
	}
	
	/**
	 * Inserta una lista de nodos en la frontera de manera ordenada
	 * @param nodes
	 */
	public void orderedInsert(ArrayList<Node> nodes) {
		if (nodes == null)
			return;
		
		for (int cnt = 0; cnt < nodes.size(); cnt++)
			fringe.add(nodes.get(cnt));
		
		Collections.sort(fringe);
	}
	
	/**
	 * 
	 * @return el primer nodo que hay en la frontera
	 */
	public Node delete() {
		return fringe.remove(0);
	}
	
	/**
	 * Metodo para mirar si la frontera esta vacia
	 * @return
	 */
	public boolean isEmpty(){
		return fringe.isEmpty();
	}
	
	@Override
	public String toString() {
		String str = "[";
		
		for (int cnt = 0; cnt < fringe.size(); cnt++)
			str = str + fringe.get(cnt).getState().toString()+ " "; 
		
		return str + "]";
	}
	
	//*/
	public static void main(String args[]) {
		int SIZE = 10;
		int board[][] = new int[SIZE][SIZE];
		int init = 1;
		int end = 5;
		
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				board[i][j] = 0;
		
		board[2][3] = 1;
		board[3][2] = 1;
		
		Horse horse = new Horse(init,init, end,end, board, 1);//Create a problem
		
		Node root = new Node(horse);//create a root node
		root.expand();
		
		Fringe fringe = new Fringe();
		fringe.insertLast(root);
		
		for (Node n: root.getSuccessors()) {
			System.out.println(n.getState());
			fringe.insertLast(n);
		}

		System.out.println(fringe);
		System.out.println(fringe.delete().getState());
		System.out.println(fringe.delete().getState());
		System.out.println(fringe.delete().getState());
		System.out.println(fringe.delete().getState());
		System.out.println(fringe.delete().getState());
		
	}
	/**/
}
