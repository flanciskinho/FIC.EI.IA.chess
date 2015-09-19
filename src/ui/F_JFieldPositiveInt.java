package ui;


import java.awt.Toolkit;

/**
 * Aqui solo se permite meter numeros positivos y enteros
 * @author flanciskinho
 * @version 0.1
 */
public class F_JFieldPositiveInt extends F_JTextFieldNumber {

    /**
	 * Auto-generated
	 */
	private static final long serialVersionUID = -8399385907732888518L;

	public F_JFieldPositiveInt(int size) {
        super(size);
        
        super.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent ke) {
                if (especial_key(ke.getKeyCode()))
                    return;

                if (Character.isDigit(ke.getKeyChar()))
                    return;
                
                if (ke.getKeyChar() == 8)//Es el backspace
                    return;
                if (ke.getKeyChar() == 127)//Es el delete
                    return;
                
                ke.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        });
    }
    
    /**
     * Metodo que devuelve el numero entero que se encuentra en el campo
     * @return 
     */
    public Integer getInteger() {
        try {
            return new Integer(getText());
        } catch (Exception e) {
            return null;
        }
    }
    
}
