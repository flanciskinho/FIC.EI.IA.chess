package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JWindow;

import model.Node;
import ui.F_JFieldPositiveInt;
import ui.F_JPanel;
import controller.ControllerInterface;

/**
 * vista principal
 * 
 * @author flanciskinho
 * @version 0.1
 */
public class Window implements ViewInterface{
	private static final int MILIS = 1100;
	private static final int MILIS_END = 1500;
	private static final String APP_NAME = "Practica 1 de IA";
	private static final ImageIcon ICON = new ImageIcon(new Object().getClass().getResource("/pictures/icon.png"));
	
	private About fAbout = new About(APP_NAME, ICON);
	private Loading lFrame;
	private JFrame frame;
	private JDialog dialog;
	
	private boolean stopAnimation = false;//si se vuelve a true se para la animacion
	
	/**
	 * Label donde se muestra la informacion referente a la solucion del problema
	 */
	private JTextArea tInfoGoal = new JTextArea();
	
	JTextField _tPos = new F_JFieldPositiveInt(5),
			 _tCost = new F_JFieldPositiveInt(5);
	
	private JRadioButton rGoal = new JRadioButton("Meta"),
			rInit = new JRadioButton("Inicial");
	
	private JLabel lPos = new JLabel("(x,y)", JLabel.CENTER),
			lCost = new JLabel("Coste: ");
	
	private JButton bStart = new JButton("Buscar"),
			bStop = new JButton("Parar animaci—n");
	/* Parte del menu */
	private JMenu file = new JMenu("Archivo"),
			strategy = new JMenu("Estrategia"),
			search = new JMenu("Bœsqueda"),
			tool = new JMenu("Herramientas"),
			help = new JMenu("Ayuda");
	private JMenuItem exit = new JMenuItem("Salir"),
			clear = new JMenuItem("Restablecer costes"),
			random = new JMenuItem("Coste aleatorio..."),
			about = new JMenuItem("Acerca de...");
	private JRadioButton rTree = new JRadioButton("çrbol", true),
			rGraph = new JRadioButton("Grafo");
	private JRadioButton rDepth = new JRadioButton("Profundidad"),
			rBreadth = new JRadioButton("Anchura", true),
			rH0 = new JRadioButton("Heur’stica 0"),
			rH1 = new JRadioButton("Heur’stica 1"),
			rH2 = new JRadioButton("Heur’stica 2");
	/**
	 * Rejilla que contine los botones
	 */
	private F_JToggleButton[][] grid;
	private JPanel pGrid;
	/**
	 * Grupo al que pertenecen los botones
	 */
	private ButtonGroup group = new ButtonGroup();
	private JTextField tCost = new F_JFieldPositiveInt(5);
	/**
	 * Botton actual que esta seleccionado
	 */
	private F_JToggleButton bFocus = null;
	/**
	 * Para indicar las posiciones de inicio y final
	 */
	private F_JToggleButton bInit = null,
			bGoal = null;
	
	/*Tamano del tablero*/
	private final int size;
	
	//Controlador asociado
	private ControllerInterface controller;
	
	public Window(ControllerInterface control, int size) {
		lFrame = new Loading(this);
		this.size = size;
		controller = control;
		grid = new F_JToggleButton[size][size];
		
		frame = new JFrame(APP_NAME);
		frame.setIconImage(ICON.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		preconfigure();
		insert();
		
		putMenuBar();
		
		/*para que tenga scroll*/
		JPanel p = (JPanel) frame.getContentPane();
		JScrollPane pane = new JScrollPane(p);
		pane.setViewportView(p);
		frame.setContentPane(pane);
		
		
		frame.pack();
		frame.setMinimumSize(new Dimension(300,300));
		frame.setMaximumSize(frame.getSize());
		frame.setLocationRelativeTo(null);
		
		/* Establezco las posiciones iniciales */
		bFocus = bGoal = bInit = grid[0][0];
		bInit.putStatus(true, bInit.isGoal(), bInit.getCost());
		bGoal.putStatus(bGoal.isInit(), true, bGoal.getCost());
		setFocusButton(bGoal);
		bGoal.setRequestFocusEnabled(true);
		grid[0][0].setSelected(true);
		
		frame.setVisible(true);
	}
	
	/**
	 * Establece las acciones a los menus
	 */
	private void menuItemActions() {
		exit.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				System.exit(0);
			}
		});
		
		clear.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				clearCost();
			}
		});
		random.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				randomCost();
			}
		});
		
		about.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				if (fAbout == null)
					fAbout = new About(APP_NAME, ICON);
				fAbout.show();
			}
		});
	}
	
	private void putMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		file.add(exit);
		
		ButtonGroup aux = new ButtonGroup();
		search.add(rTree);
		search.add(rGraph);
		aux.add(rTree);
		aux.add(rGraph);
		
		
		JMenu menu = new JMenu("A*");
		menu.add(rH0);
		menu.add(rH1);
		menu.add(rH2);
		strategy.add(rDepth);
		strategy.add(rBreadth);
		strategy.add(menu);
		/* put radiobuttons into a same group */
		aux = new ButtonGroup();
		aux.add(rH0);
		aux.add(rH1);
		aux.add(rH2);
		aux.add(rDepth);
		aux.add(rBreadth);
		
		
		tool.add(clear);
		tool.add(random);
		
		help.add(about);
		
		menuBar.add(file);
		menuBar.add(search);
		menuBar.add(strategy);
		menuBar.add(tool);
		menuBar.add(help);
		
		frame.setJMenuBar(menuBar);
		
		menuItemActions();
	}
	
	private void insert() {
		JPanel p1 = new F_JPanel(8,8,8,8);
		JPanel p2 = new F_JPanel(5,5,5,5);
		 pGrid = new F_JPanel(5,5,5,5);
		JPanel p4 = new F_JPanel(5,5,5,5);
		
		JPanel aux;
		
		GridLayout grid = new GridLayout(size+1, size+1,  1,1);
		
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		pGrid.setLayout(grid);
		p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));
		
		p4.setBackground(new Color(0x66,0xCC,0xFF));//Color.WHITE);
		
		/* Insert into p4 */
		aux = new JPanel(new FlowLayout(FlowLayout.CENTER));
		aux.setOpaque(false);
		aux.add(lPos);
		p4.add(aux);
		
		aux = new JPanel(new FlowLayout(FlowLayout.LEFT));
		aux.setOpaque(false);
		aux.add(rInit);
		p4.add(aux);
		
		aux = new JPanel(new FlowLayout(FlowLayout.LEFT));
		aux.setOpaque(false);
		aux.add(rGoal);
		p4.add(aux);
		
		aux = new JPanel(new FlowLayout(FlowLayout.LEFT));
		aux.setOpaque(false);
		aux.add(lCost);
		aux.add(tCost);
		p4.add(aux);
		
		/* Insert into p3 */
		for (int i = 0; i < size+1; i++) {
			for (int j = 0; j < size +1; j++) {
				if (i == 0) {
					if (j == 0)
						pGrid.add(new JLabel(new ImageIcon(new Object().getClass().getResource("/pictures/none.png"))));
					else
						pGrid.add( putBold(new JLabel(new Integer(j-1).toString(), JLabel.CENTER)) );
				} else
					if (j == 0)
						pGrid.add( putBold(new JLabel(new Integer(i-1).toString(), JLabel.CENTER)) );
					else {
						this.grid[i-1][j-1] = new F_JToggleButton(this, j-1, i-1, 0);
						group.add(this.grid[i-1][j-1]);
						pGrid.add(this.grid[i-1][j-1]);
					}
						
				}
		}
		
		
		/* Insert into p2 */
		p2.add(pGrid);
		p2.add(p4);
		
		/* Insert into p1 */
		aux = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		aux.add(bStop);
		aux.add(bStart);
		p1.add(p2);
		p1.add(aux);
		JScrollPane scroll = new JScrollPane(tInfoGoal);
		p1.add(scroll);
		
		frame.setContentPane(p1);
	}
	
	private JLabel putBold(JLabel label) {
		label.setFont(new Font(label.getFont().getName(), Font.BOLD, label.getFont().getSize()));
		return label;
	}
	
	private void pre_label() {
		lPos.setFont(new Font(lPos.getFont().getName(), Font.BOLD, lPos.getFont().getSize()));
		lCost.setFont(new Font(lCost.getFont().getName(), Font.BOLD, lCost.getFont().getSize()));
	}
	
	private void pre_tf() {
		tCost.setText("0");
		
		tCost.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                updateAction();
            }
        });
	}
	
	private void pre_ta() {
		tInfoGoal.setEditable(false);
		tInfoGoal.setLineWrap(true);
		tInfoGoal.setAutoscrolls(true);
		tInfoGoal.setColumns(15);
		tInfoGoal.setRows(3);
		tInfoGoal.setText("");
		
	}
	
	private void pre_rb() {
		rInit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateAction();
            }
        });
		rInit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                updateAction();
            }
        });
		
		rGoal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	updateAction();
            }
        });
		rGoal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                updateAction();
            }
        });
	}
	
	/**
	 * Cuando se actualiza algo en una posicion del tablero
	 */
	private void updateAction() {
		if (bFocus == null)
			return;
		
		if (rInit.isSelected())//Init position -> cost 0
			tCost.setText("0");
		
		int aux = 0;
		try {
			aux = new Integer(tCost.getText());
		} catch(Exception e) {
			JOptionPane.showMessageDialog(frame, "El coste no es un numero v‡lido", "", JOptionPane.WARNING_MESSAGE, new javax.swing.ImageIcon(getClass().getResource("/pictures/icon_warning.png")));
		}
		
		bFocus.putStatus(rInit.isSelected(), rGoal.isSelected(), aux);
		if (rInit.isSelected())
			setButtonInit(bFocus);
		if (rGoal.isSelected())
			setButtonGoal(bFocus);
	}
	
	private void on_button_start() {
		lFrame.show();
		lFrame.setText("Realizando la bœsqueda...");
		if (!controller.startSearch())
			lFrame.dipose();
	}
	
	
	private void pre_button() {
		bStart.setForeground(new Color(0x0,0x80,0xFF));
		
		bStart.addActionListener(new java.awt.event.ActionListener() {//click
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	on_button_start();
            }
        });
        bStart.addKeyListener(new java.awt.event.KeyAdapter() {//pulsas enter
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
            	if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
            		on_button_start();
            }
        });
        
        bStop.setForeground(new Color(0xFF,0x0,0x0));
		bStop.setVisible(false);
        
        bStop.addActionListener(new java.awt.event.ActionListener() {//click
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	stopAnimation = true;
            }
        });
        bStop.addKeyListener(new java.awt.event.KeyAdapter() {//pulsas enter
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
            	if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
            		stopAnimation = true;
            }
        });
	}
	
	private void preconfigure() {
		pre_ta();
		pre_tf();
		pre_rb();
		pre_label();
		pre_button();
	}
	
	/**
	 * Es lo que hace cuando pulsas un boton del tablero
	 */
	public void updatePos(int x, int y, boolean isInit, boolean isGoal, int cost){
		lPos.setText(String.format("(%d, %d)", x, y));
		rGoal.setSelected(isGoal);
		rInit.setSelected(isInit);
		tCost.setText(new Integer(cost).toString());
	}
	
	/**
	 * Establece el boton que esta activo
	 */
	void setFocusButton(F_JToggleButton button) {
		bFocus = button;
		updatePos(bFocus.getPosX(), bFocus.getPosY(), bFocus.isInit(), bFocus.isGoal(), bFocus.getCost());
	}
	/**
	 * Etablece el boton como el nuevo de inicio
	 */
	void setButtonInit(F_JToggleButton button) {
		if (bInit != null) {
			bInit.putStatus(false, bInit.isGoal(), bInit.getCost());
		}
		
		bInit = button;
		bInit.putStatus(true, bInit.isGoal(), bInit.getCost());//por si acaso el de antes y el de ahora es el mismo
	}
	/**
	 * Devuelve el frame principal
	 * @return
	 */
	JFrame getJFrame() {
		return frame;
	}
	/**
	 * Etablece el boton como el nuevo de meta
	 */
	void setButtonGoal(F_JToggleButton button) {
		if (bGoal != null) {
			bGoal.putStatus(bGoal.isInit(), false, bGoal.getCost());
		}
		
		bGoal = button;
		bGoal.putStatus(bGoal.isInit(), true, bGoal.getCost());//por si acaso el de antes y el de ahora es el mismo
	}
	/**
	 * Reestablece todos los costes a cero
	 */
	private void clearCost() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) 
				grid[i][j].putStatus(grid[i][j].isInit(), grid[i][j].isGoal(), 0);
	}
	
	private void randomCost() {
		dialog = new JDialog(frame, true);
		
		JButton bOk = new JButton("Aceptar");
		
		dialog.setContentPane(new F_JPanel(10,15,17,15));
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2,2,  5,5));
		p.add(new JLabel("Posiciones:"));
		p.add(_tPos);
		p.add(new JLabel("Coste:"));
		p.add(_tCost);
		main.add(p);
		
		p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p.add(bOk);
		main.add(p);
		
		dialog.add(main);
		
		bOk.setRequestFocusEnabled(true);
		
		bOk.addActionListener(new java.awt.event.ActionListener() {//click
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
            		_randomCost(new Integer(_tCost.getText()),new Integer(_tPos.getText()));
            		dialog.dispose();
            	} catch (Exception e) {
            		dialog.dispose();
            		showError("Nœmeros invalidos.");
            	}
            }
        });
		bOk.addKeyListener(new java.awt.event.KeyAdapter() {//pulsas enter
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
            	if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
            		try {
                		_randomCost(new Integer(_tCost.getText()),new Integer(_tPos.getText()));
                		dialog.dispose();
                	} catch (Exception e) {
                		dialog.dispose();
                		showError("Nœmeros invalidos.");
                	}
            }
        });
		
		dialog.pack();
		dialog.setLocationRelativeTo(frame);
		dialog.setResizable(false);
		dialog.setVisible(true);
	}
	
	/**
	 * Pone en posiciones aleatorias un coste predeterminado 
	 */
	private void _randomCost(int c, int p) {
		if (c < 0)  {
			showError("No se puede poner coste negativo.");
			return;
		}
		if (p < 0)
			return;
		
		int pos = p;
		int cost = c;
		
		clearCost();//vaciamos primero el contenido
		
		/* Array para no repetir las posiciones */
		ArrayList<Dimension> xx = new ArrayList<Dimension>(pos);
		
		java.util.Random rnd = new java.util.Random();
		int cnt = 0;
		int iteration = -1;//se usa para evitar bucles infinitos
		int _x, _y;
		while (true){
			iteration++;
			if (iteration == size*size*2) //llevamos muchas iteraciones vamos a pillarlas secuancialmente
				break;
			if (iteration % 7 == 0) //reiniciamos la semilla
				rnd.setSeed(rnd.nextLong());
			
			_x = rnd.nextInt(size);
			_y = rnd.nextInt(size);
			if (xx.indexOf(new Dimension(_x,_y)) != -1) 
				continue;
			
			if (grid[_x][_y].isInit())
				continue;
			
			grid[_x][_y].putStatus(grid[_x][_y].isInit(), grid[_x][_y].isGoal(), cost);
			xx.add(new Dimension(_x,_y));
			cnt++;
			
			if (cnt == pos)
				break;
		}
		

		if (cnt != pos) {
			for (int i = size-1; i >= 0; i--)
				for (int j = 0; j < size; j++) {
					if (xx.indexOf(new Dimension(i,j)) != -1)
						continue;

					if (grid[i][j].isInit())
						continue;
					
					grid[i][j].putStatus(grid[i][j].isInit(), grid[i][j].isGoal(), cost);
					xx.add(new Dimension(i,j));
					cnt++;
					if (cnt == pos)
						return;
				}
		}
		
		if (bFocus != null)
			setFocusButton(bFocus);

	}
	
	/*Interface implementation*/
	@Override
	public boolean isTree(){
		return rTree.isSelected();
	}
	@Override
	public boolean isGraph(){
		return rGraph.isSelected();
	}
	@Override
	public boolean isDepth(){
		return rDepth.isSelected();
	}
	
	@Override
	public boolean isBreadth(){
		return rBreadth.isSelected();
	}

	@Override
	public boolean isAStar(){
		return rH0.isSelected() || rH1.isSelected() || rH2.isSelected();
	}
	
	@Override
	public boolean isH0(){
		return rH0.isSelected();
	}
	@Override
	public boolean isH1(){
		return rH1.isSelected();
	}
	@Override
	public boolean isH2(){
		return rH2.isSelected();
	}
	
	@Override
	public Dimension getInit() {
		if (bInit == null)
			return null;
		if (!bInit.isInit())
			return null;
		
		return new Dimension(bInit.getPosX(), bInit.getPosY());
	}
	@Override
	public Dimension getGoal() {
		if (bGoal == null)
			return null;
		if (!bGoal.isGoal())
			return null;
		return new Dimension(bGoal.getPosX(), bGoal.getPosY());
	}
	
	@Override
	public int[][] getBoard() {
		int [][]aux = new int[size][size];
		
		for (int i=0; i < size; i++)
			for (int j = 0; j < size; j++)
				aux[i][j] = grid[j][i].getCost();//Para que ponga bien los costes con respecto a la pantalla
		
		return aux;
	}
	
	@Override
	public void showError(String error) {
		if (lFrame != null)
			lFrame.dipose();
		JOptionPane.showMessageDialog(frame, error, "", JOptionPane.WARNING_MESSAGE, new javax.swing.ImageIcon(getClass().getResource("/pictures/icon_warning.png")));
	}
	
	@Override
	public void endSearch(boolean status, String path) {
		if (lFrame != null)
			lFrame.dipose();
		
		if (status) {
			tInfoGoal.setForeground(new Color(0x00,0x00,0x0));
			tInfoGoal.setText("Bœsqueda exitosa");
		} else {
			tInfoGoal.setForeground(new Color(0xff,0x0,0x0));
			tInfoGoal.setText("El algoritmo de bœsqueda finaliz— sin encontrar ninguna soluci—n.");
		}
		
		if (path != null) {
			tInfoGoal.append("\n"+path);
			frame.pack();
		}
	}
	
	
	@Override
	public void showWayGoal(ArrayList<Dimension> way) {
		//El primero es la posicion inicial as’ que lo desechamos
		way.remove(0);
		if (way.size() == 0)
			return;
		
		unlock(false);
		bStop.setVisible(true);
		stopAnimation = false;
		
		Dimension n;
		int pX, pY;
		
		while (!way.isEmpty()) {
			if (stopAnimation)
				break;
			n = way.remove(0);
			pX = (int) n.getWidth();
			pY = (int) n.getHeight();
			if (!way.isEmpty())
				showImage(pX, pY,new ImageIcon(new Object().getClass().getResource("/pictures/smallGifHorse.gif")), MILIS);
			else
				showImage(pX, pY,new ImageIcon(new Object().getClass().getResource("/pictures/sirHorse.png")), MILIS_END);
		}
		
		unlock(true);
		bStop.setVisible(false);
	}
	
	private void unlock(boolean enabled) {
		frame.getJMenuBar().setEnabled(enabled);
		bStart.setEnabled(enabled);
		rInit.setEnabled(enabled);
		rGoal.setEnabled(enabled);
		lCost.setEnabled(enabled);
		tCost.setEnabled(enabled);
		file.setEnabled(enabled);
		search.setEnabled(enabled);
		strategy.setEnabled(enabled);
		tool.setEnabled(enabled);
		help.setEnabled(enabled);
		pGrid.setEnabled(enabled);
		/*
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				grid[i][j].setEnabled(enabled);
				*/
	}
	
	private void showImage(int y, int x, Icon image, long milis) {
		Icon icon = grid[x][y].getIcon();
		grid[x][y].setIcon(image);
		
		try {
			int div = 10;
			for (int i = 0; i < milis/div ; i++)
				if (!stopAnimation)
					Thread.sleep(10);
				else
					break;
				
		} catch (Exception e) {
			
		}
		
		grid[x][y].setIcon(icon);
	}
	
}


/* ******************************* */
/* ******************************* */
/* ******************************* */
/* ******************************* */
/* ******************************* */
/* ******************************* */
/* ******************************* */
/* ******************************* */
/* ******************************* */


/**
 * Clase auxiliar para ayudar a simplificar la programacion de los botones
 * 
 * @author flanciskinho
 * @version 0.1
 */
 class F_JToggleButton extends JToggleButton {
	/**
	 * Auto-generated
	 */
	private static final long serialVersionUID = 431167874759606465L;

	/**
	 * Posicion que representa
	 */
	private final int posX, posY;
	
	/**
	 * 0 -> clear
	 * 
	 * 1 -> init
	 * 2 -> goal
	 * 4 -> cost
	 * 
	 * 3 -> init + goal
	 * 5 -> init + cost
	 * 6 -> goal + cost
	 * 7 -> init + goal + cost
	 */
	private int status = 0;
	private int cost = 0;
	
	private Window parent;
	
	F_JToggleButton(Window w, int x, int y, int c) {
		super();
		posX = x;
		posY = y;
		cost = c;
		putImage();
		
		parent = w;
		
		this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                on_click_button();
            }
        });
	}
	
	int getPosX() {
		return posX;
	}
	int getPosY() {
		return posY;
	}
	int getCost() {
		return cost;
	}
	boolean isInit() {
		return ((status & 1) != 0);
	}
	boolean isGoal() {
		return ((status & 2) != 0);
	}
	
	void putStatus(boolean isInit, boolean isGoal, int c) {
		cost = c;
		status = 0;
		
		if (isInit) {
			status += 1;
			cost = 0;//init position -> cost 0
		}
		if (isGoal)
			status += 2;
		if (cost != 0)
			status += 4;
		
		putImage();
	}
	
	void putImage() {
		ImageIcon image = null;
		switch (status) {
		case 0:
			image = new ImageIcon(new Object().getClass().getResource("/pictures/none.png"));
			break;
			
		case 1:
			image = new ImageIcon(new Object().getClass().getResource("/pictures/smallHorse.png"));
			break;
		case 2:
			image = new ImageIcon(new Object().getClass().getResource("/pictures/smallGoal.png"));
			break;
		case 4:
			image = new ImageIcon(new Object().getClass().getResource("/pictures/penalty.png"));
			break;
			
		case 3:
			image = new ImageIcon(new Object().getClass().getResource("/pictures/both.png"));
			break;
		case 5:
			image = new ImageIcon(new Object().getClass().getResource("/pictures/penaltyHorse.png"));
			break;
		case 6:
			image = new ImageIcon(new Object().getClass().getResource("/pictures/penaltyGoal.png"));
			break;
		case 7:
			image = new ImageIcon(new Object().getClass().getResource("/pictures/all.png"));
			break;
		}
		if (image != null)
			super.setIcon(image);
	}
	
	/* PRIVATE METHODS */
	private void on_click_button(){
		parent.setFocusButton(this);
		System.out.println(String.format("(%d, %d)", posX, posY));
	}
}
 
 /* ******************************* */
 /* ******************************* */
 /* ******************************* */
 /* ******************************* */
 /* ******************************* */
 /* ******************************* */
 /* ******************************* */
 /* ******************************* */
 /* ******************************* */
/**
 * Clase que muestra una ventana de loading 
 * 
 * @author fran
 * @author pablo
 * @version 0.1
 *
 */
 class Loading {
	 private Window parent;
	 private JWindow frame;
	 
	 private JLabel lInfo = new JLabel("Texto de Informacion");
	 private JLabel lIcon = new JLabel(new ImageIcon(new Object().getClass().getResource("/pictures/loading.gif")));
	 
	 Loading(Window parent) {
		 this.parent = parent;
		 frame = new JWindow(this.parent.getJFrame());
		 frame.setAlwaysOnTop(true);
		 
		 preconfigure();
		 insert();
		 
		 frame.pack();
		 frame.setLocationRelativeTo(parent.getJFrame());
	 }
	 
	 public void setText(String str) {
		 if (str == null)
			 return;
		 lInfo.setText(str);
		 
		 show();
	 }
	 
	 public void show() {
		 frame.pack();
		 if (!frame.isVisible()) {
			 frame.setLocationRelativeTo(parent.getJFrame());
		 }
		 frame.setVisible(true);
		 parent.getJFrame().setEnabled(false);
	 }
	 
	 public void dipose() {
		 frame.dispose();
		 parent.getJFrame().setEnabled(true);
	 }
	 
	 private void insert() {
		 frame.setContentPane(new F_JPanel(10,10,10,10));
		 frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		 
		 frame.add(lIcon);
		 
		 JPanel aux = new F_JPanel(0,10,0,10);
		 aux.setLayout(new FlowLayout(FlowLayout.CENTER));
		 aux.add(lInfo);
		 
		 frame.add(lInfo);
	 }
	 
	 private void preconfigure() {
		 
	 }
 }