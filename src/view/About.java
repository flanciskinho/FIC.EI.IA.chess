package view;

import java.awt.Desktop;
import java.awt.Font;
import java.net.URI;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.F_JPanel;


/**
 * Clase que muestra un frame con el tipico about...
 * 
 * @author flanciskinho
 * @version 0.1
 */
public class About {
	private JFrame frame;
	
	private final static String FRAN_WEB = "http://www.linkedin.com/in/flanciskinho";
	private final static String PABLO_WEB = "";
	
	private final ImageIcon appIcon;
	
	private JButton button = new JButton("Aceptar");
	
	private JLabel image = new JLabel(""),
		app = new JLabel(""),
		version = new JLabel("v0.1"),
		developer = new JLabel("Desarrolladores:"),
		fran = new JLabel("<html><a href='"+FRAN_WEB+"'>Francisco Abel Cedr—n Santaefemia</a></html>"),
		pablo = new JLabel("<html><a href='"+PABLO_WEB+"'>Pablo Garc’a Vila</a></html>");
	
	private final String appName; 
	
	public About(String name, ImageIcon icon) {
		appName = name;
		appIcon = icon;
		
		frame = new JFrame("About");
		frame.setIconImage(icon.getImage());
		
		preconfigure();
		insert();
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
	}
	
	public void show() {
		frame.setVisible(true);
	}
	public void dispose() {
		frame.setVisible(false);
	}

	private void insert() {
		JPanel p1 = new F_JPanel(5,40,5,40);
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		
		F_JPanel pImage = new F_JPanel(20,1,10,1);
        pImage.add(image);
        
        F_JPanel pAppName = new F_JPanel(5,1,0,1);
        pAppName.add(app);
        
        F_JPanel pVersion = new F_JPanel(0,1,15,1);
        pVersion.add(version);
        
        F_JPanel pDeveloper = new F_JPanel(5,1,0,1);
        pDeveloper.add(developer);
        
        F_JPanel pFran = new F_JPanel(0,0,0,0);
        pFran.add(fran);
        F_JPanel pPablo = new F_JPanel(0,0,0,0);
        pPablo.add(pablo);      
        
        F_JPanel pButton = new F_JPanel(20,1,5,1);
        pButton.add(button);
        //set elements into main panel
        p1.add(pImage);
        p1.add(pAppName);
        p1.add(pVersion);
        p1.add(pDeveloper);
        p1.add(pFran);
        p1.add(pPablo);
        p1.add(pButton);
        
        frame.setContentPane(p1);
	}
	
	/**
	 * Pone en negrita un label
	 * @param label
	 * @param inc aumento de tama–o
	 */
	private void setBold(JLabel label, int inc) {
		label.setFont(new Font(
				label.getFont().getName(),
				Font.BOLD,
				label.getFont().getSize()+inc
				));
	}
	
	private void preconfigure() {
		app.setText(appName);
		image.setIcon(appIcon);
		
		setBold(app, 5);
		setBold(version, 0);
		setBold(developer, 1);
		
		fran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                go_web(FRAN_WEB);
            }
        });
		
		pablo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                go_web(PABLO_WEB);
            }
        });
		
		button.addActionListener(new java.awt.event.ActionListener() {//click
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	frame.dispose();
            }
        });
        button.addKeyListener(new java.awt.event.KeyAdapter() {//pulsas enter
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
            	if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
            		frame.dispose();
            }
        });
	}
	
	/**
	 * Metodo que abre el navegador
	 * @param web
	 */
	 private void go_web(String web) {
		try {
			if (Desktop.isDesktopSupported()) {
					Desktop desktop = Desktop.getDesktop();
					if (desktop.isSupported(Desktop.Action.BROWSE)) {
						desktop.browse(new URI(web));
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
