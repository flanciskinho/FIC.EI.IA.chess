package model;

/**
 * Interfaz que representa el estado en el que puede estar un problema
 * 
 * @author flanciskinho
 * @version 0.1
 */
public interface State {


	/**
	 * 
	 * @return Comprueba si el estado es final
	 */
	public boolean goalTest();

	/**
	 * 
	 * @param
	 * @return si se puede aplicar el operador al estado
	 */
	public boolean executable(Operator action);

	/**
	 * Aplica una accion al estado actual
	 * 
	 * @param action
	 * @return el estado originado al aplicar el operador
	 */
	public State successor(Operator action);

	/**
	 * 
	 * @return coste del estado en el que estas
	 */
	public int getCost();

	/**
	 * 
	 * @return coste estimado para llegar a la solucion
	 */
	public int getHeuristic();
	
	/**
	 * Comprueba si el estado actual es igual a otro
	 * 
	 * @param o Estado con el comparar
	 * @return
	 */
	public boolean equals(Object o);

	/**
	 * 
	 * @return un String representativo del estado
	 */
	public String toString();
	
	/**
	 * 
	 */
	public int hashCode();
}
