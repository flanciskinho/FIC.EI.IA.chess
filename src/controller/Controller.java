/**
 * 
 */
package controller;

import java.awt.Dimension;
import java.util.ArrayList;

import model.AStar;
import model.Breadth;
import model.Depth;
import model.Graph;
import model.Horse;
import model.Node;
import model.Search;
import model.Strategy;
import model.Tree;
import view.ViewInterface;
import view.Window;

/**
 * Clase que interactua entre la vista y el modelo
 * 
 * @author flanciskinho
 * @version 0.1
 *
 */
public class Controller implements ControllerInterface, Runnable{
	private ViewInterface view;
	
	/* Los datos */
	private static final int SIZE_BOARD = 10;
	private int x0, y0, xf, yf;
	private int [][]board;
	private Strategy strategy;
	private int heuristic;
	private Node rootNode;
	private Search search;
	
	private ArrayList<Dimension> way2goal;
	
	public Controller() {
		view = new Window(this, SIZE_BOARD);
	}
	
	
	/**
	 * Recoge los datos de la vista antes de comenzar con la busqueda
	 */
	@Override
	public boolean startSearch() {
		String str = "";
		
		Dimension d = view.getInit();
		if (d == null) {
			str = str + "Falta la posicion de inicio.\n";
		} else {
			y0 = (int) d.getHeight();
			x0 = (int) d.getWidth();
		}
		
		d = view.getGoal();
		if (d == null) {
			str = str + "Falta la posicion de meta.\n";
		} else {
			yf = (int) d.getHeight();
			xf = (int) d.getWidth();
		}
		
		if (view.isBreadth()) {
			strategy = new Breadth();
		} else if (view.isDepth()) {
			strategy = new Depth();
		} else if (view.isAStar()) {
			strategy = new AStar();
			if (view.isH0()) {
				heuristic = Horse.H0;
			} else if (view.isH1()) {
				heuristic = Horse.H1;
			} else if (view.isH2()) {
				heuristic = Horse.H2;
			} else
				str = str + "Falta por escoger el tipo de heur’stica.\n";
		} else
			str = str + "Falta la estrategia de bœsqueda.\n";
		
		board = view.getBoard();
		
		if (str.equals("")) {
			rootNode = new Node(new Horse(x0,y0,xf,yf, board, heuristic));
		}
		
		if (view.isTree()) {
			if (str.equals(""))
				search = new Tree(rootNode, strategy);
		} else if (view.isGraph()) {
			if (str.equals(""))
				search = new Graph(rootNode, strategy);
		} else 
			str = str + "Falta el algoritmo de bœsqueda.\n";
		
		if (!str.equals("")) {
			view.showError(str);
			return false;
		}
		
		goSearch();
		return true;
	}
	
	@Override
	public void run() {
		boolean end = search.search();		
		String str = null;
		
		if (end)
			str = search.getGoalNode().toStringReversePath();
		view.endSearch(end, str);
		
		System.out.println("************************************************");
		System.out.println("Bœsqueda: "+search.getSearchName());
		System.out.println("Estrategia: "+strategy.getStrategyName());
		System.out.println("Nodos generados: "+search.getNumNodes());
		if (end) {
			System.out.println("Estado: "+search.getGoalNode().getState());
			System.out.println("Profun: "+search.getGoalNode().getDepth());
			way2goal = new ArrayList<Dimension>();
			Node aux = search.getGoalNode();
			
			int pX,pY, index;
			while (aux != null){
				str = aux.getState().toString().replace('(', ' ').replace(')', ' ');
				index = str.indexOf(',');
				//str = str.substring(index);
				pX = new Integer(str.substring(0,index).trim());
				pY = new Integer(str.substring(index+1).trim());
				way2goal.add(0, new Dimension(pX,pY));
				aux = aux.getParent();
			}
			
			view.showWayGoal(way2goal);
		}
	}
	
	private void goSearch() {//Para que no se quede bloqueda la interfaz
		Thread t = new Thread(this);
		t.start();
	}
	
	
	public static void main(String[] args) {
		new Controller();

	}

}
