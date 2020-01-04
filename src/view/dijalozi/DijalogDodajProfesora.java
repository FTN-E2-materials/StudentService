package view.dijalozi;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import controller.DocumentListenerDodajProfesora;
import controller.DocumentListenerIzmeniStudenta;
import controller.ProfesorController;

public class DijalogDodajProfesora extends JDialog {

	private static final long serialVersionUID = -6556391612051229157L;

	public static JTextField imeP;
	public static JTextField przP;
	public static JTextField datRP;
	public static JTextField adresaP;
	public static JTextField brTel;
	public static JTextField email;
	public static JTextField kancelarija;
	public static JTextField brlk;
	public static JComboBox titula;
	public static JComboBox zvanje;
	public static JButton btnOk = new JButton("Potvrda");
	private DocumentListener documentListener = new DocumentListenerDodajProfesora();
	
	public DijalogDodajProfesora(JFrame parent) {
	// poziva roditeljsku metodu, da podesi u odnosu na sta se pravi
	// dijalog
		
	// ekvivalentno ti pravis klase dodaj predmet, obrisi predmet, izmeni predmet
	// uz dodatne klase za dodavanje studenta i profesora na dati predemet
		super(parent, "Dodavanje profesora", true);
		this.setSize(new Dimension(500, 500));
	
			
		// grid bag layout mi je bio najpogodniji za koordinate dodavanja
		// ako ti je tesko da skontas kako radi, mozes i jednostavnije sa 
		// FlowLayout (redjace sa leva na desno i popunjavait)
		
		JPanel gornjiPanel = new JPanel(new GridBagLayout());
		
		JLabel labIme = new JLabel("*Ime");
		imeP = new JTextField();
		
		gornjiPanel.add(labIme, constraintLbl(0, 0));
		gornjiPanel.add(imeP, constraintTF(1, 0));
		
		JLabel labPrz = new JLabel("*Prezime:");
		przP = new JTextField();
		
		gornjiPanel.add(labPrz, constraintLbl(0, 1));
		gornjiPanel.add(przP, constraintTF(1, 1));
		
		JLabel labDat = new JLabel("*Datum rodjenja:");
		datRP = new JTextField();
		
		gornjiPanel.add(labDat, constraintLbl(0, 2));
		gornjiPanel.add(datRP, constraintTF(1, 2));
		
		JLabel labAdresa = new JLabel("*Adresa stanovanja: ");
		adresaP = new JTextField();
		
		
		gornjiPanel.add(labAdresa, constraintLbl(0, 3));
		gornjiPanel.add(adresaP, constraintTF(1, 3));
		
		JLabel labBrt = new JLabel("*Broj telefona: ");
		brTel = new JTextField();
		
		gornjiPanel.add(labBrt, constraintLbl(0, 4));
		gornjiPanel.add(brTel, constraintTF(1, 4));
		
		JLabel labEmail = new JLabel("*Email adresa: ");
		email = new JTextField();
		
		gornjiPanel.add(labEmail, constraintLbl(0, 5));
		gornjiPanel.add(email, constraintTF(1, 5));
		
		JLabel labBrlk = new JLabel("*Broj licne karte: ");
		brlk = new JTextField();
		
		gornjiPanel.add(labBrlk, constraintLbl(0, 6));
		gornjiPanel.add(brlk, constraintTF(1, 6));
		
		
		JLabel labKanc = new JLabel("*Kancelarija: ");
		kancelarija = new JTextField();
		
		gornjiPanel.add(labKanc, constraintLbl(0, 7));
		gornjiPanel.add(kancelarija, constraintTF(1, 7));
		
		
		String[] titule = { "Doktor", "Master" };
		JLabel labTitula = new JLabel("*Titula: ");
		titula = new JComboBox(titule);
		
		gornjiPanel.add(labTitula, constraintLbl(0, 8));
		gornjiPanel.add(titula, constraintTF(1, 8));
		
		String[] zvanja = { "Asistent", "Saradnik u nastavi", "Redovni profesor", "Vanredni profesor", "Docent" };
		JLabel labZvanje = new JLabel("*Zvanje: ");
		zvanje = new JComboBox(zvanja);
		
		gornjiPanel.add(labZvanje, constraintLbl(0, 9));
		gornjiPanel.add(zvanje, constraintTF(1, 9));
		
		
		
		JPanel donjiPanel = new JPanel();
		
		JButton btnNotOk = new JButton("Odustanak");

		imeP.getDocument().addDocumentListener(documentListener);
		przP.getDocument().addDocumentListener(documentListener);
		datRP.getDocument().addDocumentListener(documentListener);
		adresaP.getDocument().addDocumentListener(documentListener);
		brTel.getDocument().addDocumentListener(documentListener);
		email.getDocument().addDocumentListener(documentListener);
		kancelarija.getDocument().addDocumentListener(documentListener);
		brlk.getDocument().addDocumentListener(documentListener);
		
		btnOk.setEnabled(false);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(ProfesorController.getInstance().DodajProfesora())
					dispose();
				
			}
		
		});
		btnNotOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
					dispose();
				
			}
			
		});
		
		donjiPanel.add(btnNotOk);
		donjiPanel.add(btnOk);
				
		this.add(gornjiPanel, BorderLayout.NORTH);
		this.add(donjiPanel, BorderLayout.SOUTH);
		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		
		
	}
	private GridBagConstraints constraintLbl(int x,int y) {
		// funkcija koja zadaje koordinate 
		// po kojoj grid bag layout redja komponente
		
		// POSEBNO ZA LABELE (jer su drugacije dimenzije od unosa texta)
		
		GridBagConstraints ogr = new GridBagConstraints();
		
		
		ogr.gridx = x;
		ogr.gridy = y;
		ogr.gridwidth = 1;
		ogr.anchor = GridBagConstraints.WEST;
		ogr.insets = new Insets(10, 20, 0, 0);
		return ogr;
	}
	private GridBagConstraints constraintTF(int x,int y) {
		
		// posebno za text field !!!
		
		GridBagConstraints ogr = new GridBagConstraints();
		
		ogr.gridx = x;
		ogr.gridy = y;
		ogr.gridwidth = 3;
		ogr.weightx = 100;
		ogr.fill = GridBagConstraints.HORIZONTAL;
		ogr.insets = new Insets(10, 20, 0, 20);
		return ogr;
	}
	
	public static void proveriPopunjenost() {
		if(imeP.getText().trim().isEmpty() || przP.getText().trim().isEmpty() || datRP.getText().trim().isEmpty() ||
				adresaP.getText().trim().isEmpty() || brTel.getText().trim().isEmpty() || email.getText().trim().isEmpty() ||
				kancelarija.getText().trim().isEmpty() || brlk.getText().trim().isEmpty()) {
			btnOk.setEnabled(false);
		} else {
			btnOk.setEnabled(true);
		}
		
	}
	
}
