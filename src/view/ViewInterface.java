package view;

import java.awt.Dimension;
import java.util.ArrayList;

/**
 * interfaz que define unas operaciones que tiene que cumplimentar la vista para comunicarse con el controlador
 * 
 * @author flanciskinho
 * @versin 0.1
 */
public interface ViewInterface {
	/**
	 * 
	 * @return true si el algoritmo es en arbol
	 */
	public boolean isTree();
	/**
	 * 
	 * @return true si el algoritmo es en grafo
	 */
	public boolean isGraph();
	
	/**
	 * 
	 * @return si la busqueda en profundidad
	 */
	public boolean isDepth();
	
	/**
	 * @return si la busqueda en anchura
	 */
	public boolean isBreadth();
	
	/**
	 * 
	 * @return si la busqueda es A*
	 */
	public boolean isAStar();
	
	public boolean isH0();
	public boolean isH1();
	public boolean isH2();
	
	/**
	 * 
	 * @return posicion de inicio
	 */
	public Dimension getInit();
	/**
	 * 
	 * @return posicion de finalizacion
	 */
	public Dimension getGoal();
	
	/**
	 * 
	 * @return tablero en el que se realizara la busqueda
	 */
	public int[][] getBoard();
	
	/**
	 * Muestra un mensaje de error
	 * @param error
	 */
	public void showError(String error);
	
	/**
	 * Aviso de que Acabo la busqueda
	 * @param status true si hay solucion, false si no la hay
	 */
	public void endSearch(boolean status, String path);
	
	/**
	 * Muestra el camino hasta el objetivo
	 * @param way
	 */
	public void showWayGoal(ArrayList<Dimension> way);
}
