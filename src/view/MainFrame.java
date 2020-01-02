package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.time.LocalDate;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import controller.MyWindowListener;


public class MainFrame extends JFrame {


	private static final long serialVersionUID = 7058058994426193403L;
	public static JFrame frame;
	private static MainFrame instance = null;
	
	public static MainFrame getInstance() {
		if(instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}
	
	private JTable tabelaStudenata;
	private JTable tabelaProfesora;
	private JTable tabelaPredmeta;
	
	public MainFrame() {
	
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenH = screenSize.height;
		int screenW = screenSize.width;
		setSize(screenW*3/4, screenH*3/4);
		setTitle("Studentska sluzba");
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
	    
		
		
		frame = this;
		
		
		
		
	}
	
	public void azurirajPrikazStudenata(String akcija, int vrednost) {
		AbstractTableModelStudenti model = (AbstractTableModelStudenti) tabelaStudenata.getModel();
		if(akcija != null) {
			
		}
		model.fireTableDataChanged();
		validate();
	}
	public void azurirajPrikazProfesora(String akcija, int vrednost) {
		AbstractTableModelProfesori model = (AbstractTableModelProfesori) tabelaProfesora.getModel();
		if(akcija != null) {
			
		}
		model.fireTableDataChanged();
		validate();
	}
	/*
	public void prikaziTabeluStudenata() {
		tabelaStudenata = new StudentJTable();
		JScrollPane scrollPane = new JScrollPane(tabelaStudenata);
		panel_add(scrollPane, BorderLayout.CENTER);
		this.azurirajPrikaz(null, -1);
	}
*/
}

