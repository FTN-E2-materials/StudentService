package view.dijalozi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import controller.DocumentListenerDodajPredmet;
import controller.PredmetController;
import view.MainFrame;

public class DijalogDodajPredmet extends JDialog {
	private static final long serialVersionUID = 7073855243408753112L;

	public static JTextField sifraP;
	public static JTextField imeP;
	@SuppressWarnings("rawtypes")
	public static JComboBox godina;
	@SuppressWarnings("rawtypes")
	public static JComboBox semestar;
	public static JTextField profesor;
	public static JButton ok;
	private DocumentListener documentListener = new DocumentListenerDodajPredmet();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DijalogDodajPredmet(JFrame parent) {
		super(parent, "Dodavanje predmeta", true);

		this.setSize(MainFrame.width/3, MainFrame.height/2);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(parent);
		
		JPanel up = new JPanel(new GridBagLayout());
		
		JLabel labSifra = new JLabel("*Šifra predmeta:");
		labSifra.setToolTipText("Unesite šifru predmeta");
		sifraP = new JTextField();
		
		JLabel labIme = new JLabel("*Naziv predmeta:");
		labIme.setToolTipText("Unesite naziv predmeta");
		imeP = new JTextField();
		
		String[] godine = { "I (prva)", "II (druga)", "III (treca)" , "IV (cetvrta)" };
		JLabel labGodina = new JLabel("*Godina:");
		labGodina.setToolTipText("Unesite godina na kojoj se slusa predmet");
		godina = new JComboBox(godine);
		
		String[] semestri = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII" };
		
		JLabel labSemestar = new JLabel("*Semestar:");
		labSemestar.setToolTipText("Unesite semestar na kom se slusa predmet");
		semestar = new JComboBox(semestri);
		
		JLabel labProfesor = new JLabel("<html> Predmetni profesor: <br/> (broj lične karte) </html>");
		labProfesor.setToolTipText("Unesite broj lične karte predmetnog profesora");
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
		
		ok = new JButton("Potvrda");
		
		sifraP.getDocument().addDocumentListener(documentListener);
		imeP.getDocument().addDocumentListener(documentListener);
		
		JPanel dugmici = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JButton notOk = new JButton("Odustanak");
		
		
		ok.setEnabled(false);
		
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
	public static void proveriPopunjenost() {
		if (sifraP.getText().trim().isEmpty() || imeP.getText().trim().isEmpty()) {
			ok.setEnabled(false);
		} else {
			ok.setEnabled(true);
		}
		
	}	
}
