package model;


/**
 * Clase que representa el problema que hay que resolver para la practica
 * 
 * @author flanciskinho
 * @version 0.1
 */
public class Horse implements State {
	public final static int H0 = 0, H1 = 1, H2 = 2; 
	/**
	 * Posicion inicial del caballo
	 */
	private int posX, posY;
	/**
	 * Posicion del caballo
	 */
	private final int endX, endY;
	/**
	 * Tablero sobre el que se juega
	 * Si una posicion vale cero, no cuesta nada estar ah’
	 * Si una posicion es distinta de cero es el valor del 'peaje' que hay que pagar  
	 */
	private final int board[][];
	
	/**
	 * Tamano del tablero
	 */
	private final int size;
	/**
	 * Heuristica a usar
	 */
	private final int h;
	
	/**
	 * constructor COMPROBAR QUE EL ARRAY DE ENTRADA ES CUADRADO!!!
	 * @param x posicion x del caballo
	 * @param y posicion y del caballo
	 * @param xf posicion x final del caballo
	 * @param yf posicion y final del caballo
	 * @param b tablero con los costes
	 * @param heuristic Numero de heuristica a usar
	 */
	public Horse(int x, int y, int xf, int yf, int [][]b, int heuristic) {
		h = heuristic;
		
		posX = x;
		posY = y;
		
		endX = xf;
		endY = yf;
		
		size = b.length;
		//copy array
		board = new int[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				board[i][j] = b[i][j];
	}
	
	/**
	 * constructor
	 * @param x posicion x del caballo
	 * @param y posicion y del caballo
	 * @param xf posicion x final del caballo
	 * @param yf posicion y final del caballo
	 * @param b tablero con los costes
	 */
	public Horse(int x, int y, int xf, int yf, int [][]b) {
		this(x,y,xf,yf,b,H0);
	}
	
	
	@Override
	public boolean goalTest() {
		return (posX == endX && posY == endY);
	}
	
	/**
	 * Metodo que examina si una posicion esta dento del tablero
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean inside(int x, int y) {
		if (x < 0)
			return false;
		if (y < 0)
			
			return false;
		if (x >= size)
			return false;
		if (y >= size)
			return false;
		
		return true;
	}
	
	@Override
	public boolean executable(Operator action) {
		switch (action) {
		case OP0: return inside(posX-1, posY-2);
		case OP1: return inside(posX+1, posY-2);
		case OP2: return inside(posX+2, posY-1);
		case OP3: return inside(posX+2, posY+1);
		case OP4: return inside(posX+1, posY+2);
		case OP5: return inside(posX-1, posY+2);
		case OP6: return inside(posX-2, posY+1);
		case OP7: return inside(posX-2, posY-1);
		
		default: return false;
		}
	}
	
	@Override
	public State successor(Operator action) {
		if (!executable(action))
			return null;
		
		switch (action) {
		case OP0: return new Horse(posX-1, posY-2, endX, endY, board, h);
		case OP1: return new Horse(posX+1, posY-2, endX, endY, board, h);
		case OP2: return new Horse(posX+2, posY-1, endX, endY, board, h);
		case OP3: return new Horse(posX+2, posY+1, endX, endY, board, h);
		case OP4: return new Horse(posX+1, posY+2, endX, endY, board, h);
		case OP5: return new Horse(posX-1, posY+2, endX, endY, board, h);
		case OP6: return new Horse(posX-2, posY+1, endX, endY, board, h);
		case OP7: return new Horse(posX-2, posY-1, endX, endY, board, h);
		
		default: return null;
		}
		
		
	}
	
	@Override
	public int getCost() {
		return board[posX][posY];
	}
	
	@Override
	public int getHeuristic() {
		int dx, dy;
		
		switch (h) {
		case H0:
			return 0;
		case H1://pablo
			if (posX == endX && posY == endY)
				return 0;
			
			dx = java.lang.Math.abs(posX-endX) +2;
			dy = java.lang.Math.abs(posY-endY) +2;
			
			dx = (int) dx / 3;
			dy = (int) dy / 3;
			
			return (dx>dy)?dx:dy;
		case H2://fran
			if (posX == endX && posY == endY)
				return 0;
			
			dx = java.lang.Math.abs(posX-endX)-1;
			dy = java.lang.Math.abs(posY-endY)-1;
			
			dx = java.lang.Math.abs(dx);
			dy = java.lang.Math.abs(dy);
			
			dx = ((int) dx / 2)+1;
			dy = ((int) dy / 2)+1;
			
			return (dx>dy)?dx:dy;
		default:
			return 0;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Horse))
			return false;
		
		Horse aux = (Horse) o;
		return (posX == aux.posX && posY == aux.posX);
	}
	
	@Override
	public String toString(){
		return "("+posX+", "+posY+")";
	}
	
	@Override
	public int hashCode() {
		return new java.awt.Dimension(posX, posY).hashCode();
	}
}
