package model;

/**
 * Clase que implementa el proceso de busqueda de solucion con un arbol
 * 
 * @author flanciskinho
 * @version 0.1
 */
public class Tree extends Search {

	public Tree(Node root, Strategy strategy) {
		super(root, strategy);
	}
	
	@Override
	public String getSearchName() {
		return "çrbol";
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
			
			addNumNodes(node.expand());
			strategy.insertNodes(node.getSuccessors());
		}

	}
}
