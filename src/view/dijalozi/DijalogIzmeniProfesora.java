package view.dijalozi;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import controller.ProfesorController;
import model.BazaProfesora;
import model.Profesor;
import model.Profesor.Titula;
import model.Profesor.Zvanje;
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
	public static JComboBox titula;
	public static JComboBox zvanje;
	
	
	public DijalogIzmeniProfesora(JFrame parent) {
	// poziva roditeljsku metodu, da podesi u odnosu na sta se pravi
	// dijalog
		
	// ekvivalentno ti pravis klase dodaj predmet, obrisi predmet, izmeni predmet
	// uz dodatne klase za dodavanje studenta i profesora na dati predemet
		super(parent, "Izmena profesora", true);
		if(BazaProfesora.getInstance().getProfesori().size() == 0) {
			JOptionPane.showMessageDialog(null, "Ne postoji nijedan profesor", "Greska", JOptionPane.ERROR_MESSAGE);
	
		}
		else {
		
		this.setSize(new Dimension(500, 500));
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		
		Profesor prof  = new Profesor();
		prof = BazaProfesora.getInstance().getRow(ProfesoriJTable.curr_row);
		
		
		// grid bag layout mi je bio najpogodniji za koordinate dodavanja
		// ako ti je tesko da skontas kako radi, mozes i jednostavnije sa 
		// FlowLayout (redjace sa leva na desno i popunjavait)
		
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
		
		JLabel labDat = new JLabel("*Datum rodjenja:");
		datRP = new JTextField();
		SimpleDateFormat Dformat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
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
		
		JLabel labBrlk = new JLabel("*Broj licne karte: ");
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
		
		gornjiPanel.add(labZvanje, constraintLbl(0, 8));
		gornjiPanel.add(zvanje, constraintTF(1, 8));
		
		String[] titule = { "Doktor", "Master" };
		JLabel labTitula = new JLabel("*Titula: ");
		titula = new JComboBox(titule);
		
		if (prof.getTitula() == Titula.Dr) {
			titula.setSelectedIndex(0);
		} else {
			titula.setSelectedIndex(1);
		}
		
		
		gornjiPanel.add(labTitula, constraintLbl(0, 9));
		gornjiPanel.add(titula, constraintTF(1, 9));
		
		
		
		JPanel donjiPanel = new JPanel();
		
		JButton btnOk = new JButton("Potvrda");
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
	
}
