package view.dijalozi;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import controller.DocumentListenerIzmeniProfesora;
import controller.ProfesorController;
import model.BazaProfesora;
import model.Profesor;
import model.Profesor.Titula;
import model.Profesor.Zvanje;
import view.MainFrame;
import view.ProfesoriJTable;

public class DijalogIzmeniProfesora extends JDialog {

	private static final long serialVersionUID = -6556391612051229157L;

	public static JTextField imeP;
	public static JTextField przP;
	public static JTextField datRP;
	public static JTextField adresaP;
	public static JTextField brTel;
	public static JTextField email;
	public static JTextField kancelarija;
	public static JTextField brlk;
	@SuppressWarnings("rawtypes")
	public static JComboBox titula;
	@SuppressWarnings("rawtypes")
	public static JComboBox zvanje;

	public static JButton btnOk;
	private DocumentListener documentListener = new DocumentListenerIzmeniProfesora();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DijalogIzmeniProfesora(JFrame parent) {

		super(parent, "Izmena profesora", true);
		if(BazaProfesora.getInstance().getProfesori().size() == 0) {
			JOptionPane.showMessageDialog(null, "Ne postoji nijedan profesor", "Greska", JOptionPane.ERROR_MESSAGE);
	
		}
		else {
		
		this.pack();
		this.setSize(MainFrame.width/3, MainFrame.height*3/4);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(parent);
		
		Profesor prof  = new Profesor();
		prof = BazaProfesora.getInstance().getRow(ProfesoriJTable.curr_row);

		JPanel gornjiPanel = new JPanel(new GridBagLayout());
		
		JLabel labIme = new JLabel("*Ime");
		imeP = new JTextField();
		imeP.setText(prof.getIme());
		
		gornjiPanel.add(labIme, constraintLbl(0, 0));
		gornjiPanel.add(imeP, constraintTF(1, 0));
		
		JLabel labPrz = new JLabel("*Prezime:");
		przP = new JTextField();
		przP.setText(prof.getPrezime());
		
		gornjiPanel.add(labPrz, constraintLbl(0, 1));
		gornjiPanel.add(przP, constraintTF(1, 1));
		
		JLabel labDat = new JLabel("*Datum rođenja:");
		datRP = new JTextField();
		SimpleDateFormat Dformat = new SimpleDateFormat("dd.MM.yyyy.", Locale.ENGLISH);
		datRP.setText(Dformat.format(prof.getDatumr()));
		
		gornjiPanel.add(labDat, constraintLbl(0, 2));
		gornjiPanel.add(datRP, constraintTF(1, 2));
		
		JLabel labAdresa = new JLabel("*Adresa stanovanja: ");
		adresaP = new JTextField();
		adresaP.setText(prof.getAdresa());
		
		gornjiPanel.add(labAdresa, constraintLbl(0, 3));
		gornjiPanel.add(adresaP, constraintTF(1, 3));
		
		JLabel labBrt = new JLabel("*Broj telefona: ");
		brTel = new JTextField();
		brTel.setText(prof.getBr_tel());
		
		gornjiPanel.add(labBrt, constraintLbl(0, 4));
		gornjiPanel.add(brTel, constraintTF(1, 4));
		
		JLabel labEmail = new JLabel("*Email adresa: ");
		email = new JTextField();
		email.setText(prof.getEmail());
		
		gornjiPanel.add(labEmail, constraintLbl(0, 5));
		gornjiPanel.add(email, constraintTF(1, 5));
		
		JLabel labBrlk = new JLabel("*Broj lične karte: ");
		brlk = new JTextField();
		brlk.setText(prof.getBrlk());
		
		
		gornjiPanel.add(labBrlk, constraintLbl(0, 6));
		gornjiPanel.add(brlk, constraintTF(1, 6));
		
		
		JLabel labKanc = new JLabel("*Kancelarija: ");
		kancelarija = new JTextField();
		kancelarija.setText(prof.getKancelarija());
		
		gornjiPanel.add(labKanc, constraintLbl(0, 7));
		gornjiPanel.add(kancelarija, constraintTF(1, 7));
		
		String[] zvanja = { "Asistent", "Saradnik u nastavi", "Redovni profesor", "Vanredni profesor", "Docent" };
		JLabel labZvanje = new JLabel("*Zvanje: ");
		zvanje = new JComboBox(zvanja);
		
		if (prof.getZvanje() == Zvanje.Asistent) {
			zvanje.setSelectedIndex(0);
		} else if (prof.getZvanje() == Zvanje.Saradnik) {
			zvanje.setSelectedIndex(1);
		} else if (prof.getZvanje() == Zvanje.RProfesor) {
			zvanje.setSelectedIndex(2);
		} else if (prof.getZvanje() == Zvanje.VProfesor) {
			zvanje.setSelectedIndex(3);
		} else {
			zvanje.setSelectedIndex(4);
		}
		
		gornjiPanel.add(labZvanje, constraintLbl(0, 9));
		gornjiPanel.add(zvanje, constraintTF(1, 9));
		
		String[] titule = { "Doktor profesor", "Doktor", "Master" };
		JLabel labTitula = new JLabel("*Titula: ");
		titula = new JComboBox(titule);
		
		if (prof.getTitula() == Titula.ProfDr) {
			titula.setSelectedIndex(0);
		} else if (prof.getTitula() == Titula.Dr) {
			titula.setSelectedIndex(1);
		} else {
			titula.setSelectedIndex(2);
		}
		
		
		gornjiPanel.add(labTitula, constraintLbl(0, 8));
		gornjiPanel.add(titula, constraintTF(1, 8));
		
		JPanel donjiPanel = new JPanel();
		

		imeP.getDocument().addDocumentListener(documentListener);
		przP.getDocument().addDocumentListener(documentListener);
		datRP.getDocument().addDocumentListener(documentListener);
		adresaP.getDocument().addDocumentListener(documentListener);
		brTel.getDocument().addDocumentListener(documentListener);
		email.getDocument().addDocumentListener(documentListener);
		kancelarija.getDocument().addDocumentListener(documentListener);
		brlk.getDocument().addDocumentListener(documentListener);
		
		btnOk = new JButton("Potvrda");
		
		btnOk.setEnabled(true);
		
		JButton btnNotOk = new JButton("Odustanak");
		
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(ProfesorController.getInstance().izmeniProfesora())
					dispose();
			}
			
		});
		
		btnNotOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
			
		});
		
		donjiPanel.add(btnNotOk);
		donjiPanel.add(btnOk);
		
		this.add(gornjiPanel, BorderLayout.NORTH);
		this.add(donjiPanel, BorderLayout.SOUTH);
		this.setVisible(true);
		
		
		}
	}
	private GridBagConstraints constraintLbl(int x,int y) {
		GridBagConstraints gbc= new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 20, 0, 0);
		return gbc;
	}
	private GridBagConstraints constraintTF(int x,int y) {
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
		if(imeP.getText().trim().isEmpty() || przP.getText().trim().isEmpty() || datRP.getText().trim().isEmpty() ||
				adresaP.getText().trim().isEmpty() || brTel.getText().trim().isEmpty() || email.getText().trim().isEmpty() ||
				kancelarija.getText().trim().isEmpty() || brlk.getText().trim().isEmpty()) {
			btnOk.setEnabled(false);
		} else {
			btnOk.setEnabled(true);
		}
		
	}
	
}
