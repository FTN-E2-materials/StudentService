package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import controller.MyWindowListener;

public class MainFrame extends JFrame {


	private static final long serialVersionUID = 7058058994426193403L;
	private static MainFrame instance = null;
	public static int width;
	public static int height;
	public static MainFrame getInstance() {
		if(instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	
	public MainFrame() {
	
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenH = screenSize.height;
		int screenW = screenSize.width;
		setSize(screenW*3/4, screenH*3/4);
		width = this.getWidth();
		height = this.getHeight();
		setTitle("Studentska služba");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout());
		
		addWindowListener(new MyWindowListener());
		
		// MENI
		MenuBar menu = new MenuBar();
		this.setJMenuBar(menu);
		
		// TOOLBAR
		Toolbar toolbar = new Toolbar();
		add(toolbar, BorderLayout.NORTH);
		
		// STATUS BAR
		StatusBar status = new StatusBar();
		add(status, BorderLayout.SOUTH);
		
		// TABELE
		Tabele tabovi = new Tabele();
		this.add(tabovi, BorderLayout.CENTER);
		
		setVisible(true);

		Image icon = Toolkit.getDefaultToolkit().getImage("images/logo.png");
		this.setIconImage(icon);
	    
	
		
	}
	
}

