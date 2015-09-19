package ui;

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Clase que extiende de JPanel al que se le añade el metodo de padding
 * @author flanciskinho
 * @version 0.1
 */
public class F_JPanel extends JPanel{
    
    /**
	 * Auto-generated
	 */
	private static final long serialVersionUID = -368811428219168269L;

	/**
     * Constructor que por defecto le pone un padding de 5px a los bordes
     */
    public F_JPanel() {
        this(5,5,5,5);
        
    }
    
    public F_JPanel(int u, int l, int d, int r) {
        super(new FlowLayout(FlowLayout.CENTER));
        putPadding(u, l, d, r);
    }
    
    /**
     * Metodo que añade un padding al panel
     * @param u up
     * @param l left
     * @param d down
     * @param r right
     */
    public void putPadding(int u, int l, int d, int r) {
        setBorder(BorderFactory.createEmptyBorder(u, l, d, r));
    }
}
