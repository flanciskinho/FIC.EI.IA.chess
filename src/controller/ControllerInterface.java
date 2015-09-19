package controller;

/**
 * Interfaz que tiene que cumplir la clase que interactua entre la vista y el modelo
 * 
 * @author flanciskinho
 * @version 0.1
 */
public interface ControllerInterface {
	/**
	 * Metodo que intenta iniciar la busqueda
	 * devuelve false si no se pudo iniciar
	 */
	public boolean startSearch();
}
