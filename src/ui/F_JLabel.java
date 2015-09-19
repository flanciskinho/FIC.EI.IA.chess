package ui;

import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author flanciskinho
 * @version 0.1
 */
public class F_JLabel extends JLabel{
    /**
	 * Auto-generated
	 */
	private static final long serialVersionUID = -9129000089140176873L;
	private int sizeNormal;
    private String nameFont;
    private int style;
    
    public F_JLabel() {
        super();
        sizeNormal = getFont().getSize();
        nameFont = getFont().getName();
        style = getFont().getStyle();
    }
    
    public F_JLabel(String text) {
        super(text);
    }
    
    public void setItalic() {
        style = style  | Font.ITALIC;
        setFont(new Font(nameFont, style, sizeNormal));
    }
    
    public void setBold() {
        style =  Font.BOLD;
        setFont(new Font(nameFont, style, sizeNormal));
    }
    
    public void size(int size) {
        setFont(new Font(nameFont, style , sizeNormal+=size));
    }
}
