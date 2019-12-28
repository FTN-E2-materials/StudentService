package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controller.ControllerEntiteta;
import view.dijalozi.*;

public class Toolbar extends JToolBar {

	private static final long serialVersionUID = 228453752740994815L;
	static JButton btnDodajProf;
	static JButton btnDodajStudenta;
	public static JTextField pretraga;
	
	public Toolbar() {
		super(SwingConstants.HORIZONTAL);
		
		
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		setFloatable(false);

		ImageIcon icon = new ImageIcon("images/student_add.png");
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
	    icon = new ImageIcon(newimg);
	    
		JButton btnNew = new JButton();
		btnNew.setToolTipText("New...");
		btnNew.setIcon(icon);
	
		btnNew.setPreferredSize(new Dimension(30, 30));
		btnNew.setMinimumSize(new Dimension(30, 30));
		btnNew.setMaximumSize(new Dimension(30, 30));
		

		btnNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ControllerEntiteta.getInstance().dodajEntitet();
		
			}
		});
	

		ImageIcon icon2 = new ImageIcon("images/edit.png");
		Image img2 = icon2.getImage() ;  
		Image newimg2= img2.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
	    icon2 = new ImageIcon(newimg2);
	    
		JButton btnEdit = new JButton();
		btnEdit.setToolTipText("Edit...");
		btnEdit.setIcon(icon2);
		
		btnEdit.setPreferredSize(new Dimension(30, 30));
		btnEdit.setMaximumSize(new Dimension(30, 30));
		btnEdit.setMinimumSize(new Dimension(30, 30));	
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ControllerEntiteta.getInstance().izmeniEntitet();
			}
		});
		

		ImageIcon icon3 = new ImageIcon("images/delete.png");
		Image img3 = icon3.getImage() ;  
		Image newimg3 = img3.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
	    icon3 = new ImageIcon(newimg3);
	    
		JButton btnDelete = new JButton();
		btnDelete.setToolTipText("Delete...");
		btnDelete.setIcon(icon3);
		
		btnDelete.setPreferredSize(new Dimension(30, 30));
		btnDelete.setMinimumSize(new Dimension(30, 30));
		btnDelete.setMaximumSize(new Dimension(30, 30));
		
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ControllerEntiteta.getInstance().obrisiEntitet();
			}
		});
		
		btnDodajStudenta = new JButton();
		btnDodajStudenta.setToolTipText("Dodajte studenta na predmet");
		
		ImageIcon icon4 = new ImageIcon("images/student.png");
		Image img4 = icon4.getImage();
		Image newimg4 = img4.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH );
		icon4 = new ImageIcon(newimg4);
		btnDodajStudenta.setIcon(icon4);
		
		
		btnDodajStudenta.setPreferredSize(new Dimension(30, 30));
		btnDodajStudenta.setMinimumSize(new Dimension(30, 30));
		btnDodajStudenta.setMaximumSize(new Dimension(30, 30));
		
		
		btnDodajStudenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ControllerEntiteta.getInstance().dodajEntitetNaPredmet();
			}
		});
		//btnDelete.addActionListener(new ActionListener() {
			
		//	@Override
			//public void actionPerformed(ActionEvent arg0) {);
		
		
		btnDodajProf = new JButton();
		btnDodajProf.setToolTipText("Dodajte profesora na predmet");
		
		ImageIcon icon5 = new ImageIcon("images/profesor.png");
		Image img5 = icon5.getImage();
		Image newimg5 = img5.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH );
		icon5 = new ImageIcon(newimg5);
		btnDodajProf.setIcon(icon5);
		
		
		btnDodajProf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ControllerEntiteta.getInstance().dodajEntitet();
			}
		});
		
		btnDodajProf.setPreferredSize(new Dimension(30, 30));
		btnDodajProf.setMinimumSize(new Dimension(30, 30));
		btnDodajProf.setMaximumSize(new Dimension(30, 30));
		
		
		pretraga = new JTextField(40);
		pretraga.setPreferredSize(new Dimension(80, 25));
		pretraga.setMaximumSize(new Dimension(80, 30));
		pretraga.setMinimumSize(new Dimension(80, 30));
		pretraga.setToolTipText("Search...");
		add(Box.createHorizontalGlue());

		
		JButton btnPretraga = new JButton();
		btnPretraga.setToolTipText("Search...");
		
		ImageIcon icon6 = new ImageIcon("images/search2.png");
		Image img6 = icon6.getImage() ;  
		Image newimg6 = img6.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
	    icon6 = new ImageIcon(newimg6);
	    btnPretraga.setIcon(icon6);
	    btnPretraga.setPreferredSize(new Dimension(30, 30));
	    btnPretraga.setMinimumSize(new Dimension(30, 30));
	    btnPretraga.setMaximumSize(new Dimension(30, 30));
	    
		
		btnPretraga.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ControllerEntiteta.getInstance().pretragaEntitet(Tabele.tab_curr);
			}
		});
		
		
	 
	    this.add(btnNew);
	    this.addSeparator();
	    
	    //this.add(Box.createHorizontalStrut(10));
	    this.add(btnEdit);
	    this.addSeparator();
	    
	    //this.add(Box.createHorizontalStrut(10));
	    this.add(btnDelete);
	    
	    this.addSeparator();
	    
	    //this.add(Box.createHorizontalStrut(10));
	    this.add(btnDodajStudenta);
	    
	    this.addSeparator();
	    //this.add(Box.createHorizontalStrut(10));
	    this.add(btnDodajProf);
	    
	    this.addSeparator();
	    
	    btnDodajStudenta.setVisible(false);
	    btnDodajProf.setVisible(false);
	    
	   // this.add(Box.createHorizontalGlue());
	    this.addSeparator(new Dimension(500, 30));
	    this.add(pretraga);
	    this.addSeparator();
	   // this.add(Box.createHorizontalStrut(5));
	    this.add(btnPretraga);
		
	}
	public static void setDugmici() {
		btnDodajStudenta.setVisible(true);
		btnDodajProf.setVisible(true);
	}
	
	public static void unSetDugmici() {
		btnDodajStudenta.setVisible(false);
		btnDodajProf.setVisible(false);
		
	}
}
