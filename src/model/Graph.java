package model;

import java.util.HashSet;

/**
 * Clase que implementa el proceso de busqueda de solucion con un grafo
 * 
 * @author flanciskinho
 * @version 0.1
 */
public class Graph extends Search {
	private HashSet<Node> hash = new HashSet<Node>();
	
	public Graph(Node root, Strategy strategy) {
		super(root, strategy);
	}
	
	@Override
	public String getSearchName() {
		return "Grafo";
	}
	
	@Override
	public boolean search(){
		Node node, root = rootNode;
		
		strategy.insertNode(root);
		
		while (true) {
			if (strategy.isEmpty()) //Si la frontera esta vacia
				return false;
			
			node = strategy.getNode();
			if (node.isGoal()) {
				goalNode = node;
				return true;
			}

			/* Miramos si el nodo ya esta */
			if (hash.contains(node)) {
				
				continue;
			}
			hash.add(node);
			
			addNumNodes(node.expand());
			strategy.insertNodes(node.getSuccessors());
		}

	}
}

