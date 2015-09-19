package ui;

import java.awt.Color;
import javax.swing.JTextField;

/**
 * En este campo de texto solo se podran introducir numeros
 * @author flanciskinho
 * @version 0.1
 */
public abstract class F_JTextFieldNumber extends JTextField{
    
    /**
	 * Auto-generated
	 */
	private static final long serialVersionUID = -7742380969771493426L;
	private Color colorNormal = Color.BLACK;
    private Color colorError  = Color.RED;
    
    private boolean normal = true;//Para indicar que esta en estado normal o en error
    
    protected F_JTextFieldNumber(int size) {
        super(size);
    }
    
    
    public void putColorNormal() {
        this.setForeground(colorNormal);
        normal = true;
    }
    
    public void putColorError() {
        setForeground(colorError);
        normal = false;
    }
    
    public void setColorNormal(Color c) {
        colorNormal = c;
        if (normal)
            putColorNormal();
    }
    
    public void setColorError(Color c) {
        colorError = c;
        if (!normal)
            putColorError();
    }
    
    /**
     * Teclas especiales que se dejan actuar por defecto
     * @param code
     * @return 
     */
    protected boolean especial_key(int code){
        switch (code) {
            case java.awt.event.KeyEvent.VK_ALT:
            case java.awt.event.KeyEvent.VK_ALT_GRAPH:
            case java.awt.event.KeyEvent.VK_BEGIN:
            case java.awt.event.KeyEvent.VK_BACK_SPACE:
            case java.awt.event.KeyEvent.VK_CAPS_LOCK:
            case java.awt.event.KeyEvent.VK_CLEAR:
            case java.awt.event.KeyEvent.VK_CONTROL:
            case java.awt.event.KeyEvent.VK_DECIMAL:
            case java.awt.event.KeyEvent.VK_WINDOWS:
            case java.awt.event.KeyEvent.VK_FINAL:
            case java.awt.event.KeyEvent.VK_HOME:
            case java.awt.event.KeyEvent.VK_INSERT:
            case java.awt.event.KeyEvent.VK_KP_DOWN:
            case java.awt.event.KeyEvent.VK_KP_LEFT:
            case java.awt.event.KeyEvent.VK_KP_RIGHT:
            case java.awt.event.KeyEvent.VK_KP_UP:
            case java.awt.event.KeyEvent.VK_LEFT:
            case java.awt.event.KeyEvent.VK_RIGHT:
            case java.awt.event.KeyEvent.VK_UP:
            case java.awt.event.KeyEvent.VK_DOWN:
            case java.awt.event.KeyEvent.VK_DELETE:
            case java.awt.event.KeyEvent.VK_ENTER:
            case java.awt.event.KeyEvent.VK_ESCAPE:
            case java.awt.event.KeyEvent.VK_PAGE_DOWN:
            case java.awt.event.KeyEvent.VK_PAGE_UP:
            case java.awt.event.KeyEvent.VK_SHIFT:
                return true;
        }
        
        return false;
    }
}
