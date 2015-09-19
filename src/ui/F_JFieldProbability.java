package ui;

import java.awt.Toolkit;

/**
 * En este campo solo se admiten numeros positivos
 * @author flanciskinho
 * @version 0.1
 */
public class F_JFieldProbability extends F_JTextFieldNumber{
    

    /**
	 * Auto-generated
	 */
	private static final long serialVersionUID = 2787708321320973447L;

	public F_JFieldProbability(int size) {
        super(size);
        
        super.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent ke) {
                String str = getText();
                
                if (ke.getKeyChar() == '.')//Solo se pone una vez
                    if (str.indexOf(".") == -1)
                        return;
                
                
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
    public Double getDouble() {
        try {
            Double db = new Double(getText());
            
            if (db.isInfinite())
                return null;
            if (db.isNaN())
                return null;
            
            
            return db;
        } catch (Exception e) {
            return null;
        }
    }
    

}

