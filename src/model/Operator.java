package model;


/**
 * Enumerado para representar los posibles estados que puede tener un estado
 * 
 * @author flanciskinho
 * @version 0.1
 */
public enum Operator {
	/* x,y */
	OP0(0,1,"-1,-2"),
	OP1(1,1,"+1,-2"),
	OP2(2,1,"+2,-1"),
	OP3(3,1,"+2,+1"),
	OP4(4,1,"+1,+2"),
	OP5(5,1,"-1,+2"),
	OP6(6,1,"-2,+1"),
	OP7(7,1,"-2,-1");
	
	/**
	 * Identificador unico del operador
	 */
	private int key;
	
	/**
	 * Coste que supone realizar el operador
	 */
	private int cost;
	/**
	 * Descripcion del operador
	 */
	private String str;
	
	/**
	 * Constructor
	 * @param id Identificador unico
	 * @param c Coste de aplicar el operador
	 * @param s Descripcion del operador
	 */
	Operator(int id, int c, String s) {
		key = id;
		cost = c;
		str = s;
	}
	
	
	/**
	 * 
	 * @return devuelve el identificador del operador
	 */
	public int getKey(){
		return key;
	}
	
	/**
	 * 
	 * @return coste de aplicar el operador
	 */
	public int getCost(){
		return cost;
	}
	
	/**
	 * String del operador
	 */
	@Override
	public String toString(){
		return str;
	}
}
