package view.dijalozi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.PredmetController;

public class DijalogDodajPredmet extends JDialog {
	private static final long serialVersionUID = 7073855243408753112L;

	public static JTextField sifraP;
	public static JTextField imeP;
	public static JTextField godina;
	public static JTextField semestar;
	public static JTextField profesor;
	
	public DijalogDodajPredmet(JFrame parent) {
		super(parent, "Dodavanje predmeta", true);
		this.setSize(500, 250);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		
		JPanel up = new JPanel(new GridBagLayout());
		
		JLabel labSifra = new JLabel("*Sifra predmeta:");
		labSifra.setToolTipText("Unesite sifru predmeta");
		sifraP = new JTextField();
		
		JLabel labIme = new JLabel("*Naziv predmeta:");
		labIme.setToolTipText("Unesite naziv predmeta");
		imeP = new JTextField();
		
		JLabel labGodina = new JLabel("*Godina:");
		labGodina.setToolTipText("Unesite godina na kojoj se slusa predmet");
		godina = new JTextField();
		
		JLabel labSemestar = new JLabel("*Semestar:");
		labSemestar.setToolTipText("Unesite semestar na kom se slusa predmet");
		semestar = new JTextField();
		
		JLabel labProfesor = new JLabel("Predmetni profesor");
		labProfesor.setToolTipText("Unesite broj licne karte predmetnog profesora");
		profesor = new JTextField();
		
		up.add(labSifra, lbl(0, 0));
		up.add(sifraP, tf(1, 0));
		up.add(labIme, lbl(0, 1));
		up.add(imeP, tf(1, 1));
		up.add(labGodina, lbl(0, 2));
		up.add(godina, tf(1, 2));
		up.add(labSemestar, lbl(0, 3));
		up.add(semestar, tf(1, 3));
		up.add(labProfesor, lbl(0, 4));
		up.add(profesor, tf(1, 4));
		
		JPanel dugmici = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton ok = new JButton("Potvrda");
		JButton notOk = new JButton("Odustanak");
		
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(PredmetController.getInstance().dodajPredmet())
					dispose();
			}
		});
		
		notOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();		
			}
		});
		
		dugmici.add(notOk);
		dugmici.add(ok);
		
		this.add(up, BorderLayout.NORTH);
		this.add(dugmici, BorderLayout.SOUTH);
		this.setVisible(true);
		
	}
	private GridBagConstraints lbl(int x,int y) {
		GridBagConstraints gbc= new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 20, 5, 10);
		return gbc;
	}
	
	private GridBagConstraints tf(int x,int y) {
		GridBagConstraints gbc= new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 3;
		gbc.weightx = 100;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 20, 0, 20);
		return gbc;
	}	
}
