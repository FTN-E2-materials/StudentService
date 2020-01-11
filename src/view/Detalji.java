package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.BazaStudenata;
import model.Student;
import model.Student.Status;

public class Detalji extends JDialog {

	private static final long serialVersionUID = 5097941680854157407L;

	public Detalji(JFrame parent, int ind) {
		super(parent, "Detalji", true);
		// kod mene se nije iscrtavalo dugme za 
		// listu predmeta kad sam stavila sva obelezja
		// pa sam dodala jos jedno polje da bi moglo da se prikaze
		
		this.setSize(MainFrame.width/3, MainFrame.height/2);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		JPanel panelStud = new JPanel(new GridBagLayout());
		JPanel panelDugmici = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		Student st = BazaStudenata.getInstance().getRow(ind);
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
		JLabel bri = new JLabel("Broj indeksa:");
		JLabel briStud = new JLabel(st.getBri());
		JLabel ime = new JLabel("Ime:");
		JLabel imeS = new JLabel(st.getIme());
		JLabel prezime = new JLabel("Prezime:");
		JLabel przSt = new JLabel(st.getPrezime());
		JLabel datumR = new JLabel("Datum rođenja: ");
		JLabel datumSt = new JLabel(format.format(st.getDatumr()));
		JLabel adresa = new JLabel("Adresa: ");
		JLabel adresaSt = new JLabel(st.getAdresa());
		JLabel broj = new JLabel("Broj telefona: ");
		JLabel brojTel = new JLabel(st.getBr_tel());
		JLabel godina = new JLabel("Godina studija: ");
		JLabel godinaSt = new JLabel(Integer.toString(st.getGodina_stud()));
		JLabel status = new JLabel("Status: ");

		String statusSt;
		if(st.getStatus() == Status.B) {
			statusSt = "Budžet";
		} else {
			statusSt = "Samofinansiranje";
		}
		
		JLabel statusStud = new JLabel(statusSt);
		JLabel datumUpisa = new JLabel("Datum upisa:");
		JLabel datumUpStud = new JLabel(format.format(st.getDatum_upisa()));
		JLabel prosek = new JLabel("Prosek: ");
		JLabel prosekStud = new JLabel(Double.toString(st.getProsek()));
		
		panelStud.add(bri, lbl(0,0));
		panelStud.add(briStud, lbl(1,0));
		panelStud.add(ime, lbl(0, 1));
		panelStud.add(imeS, lbl(1, 1));
		panelStud.add(prezime, lbl(0, 2));
		panelStud.add(przSt, lbl(1, 2));
		panelStud.add(datumR, lbl(0, 3));
		panelStud.add(datumSt, lbl(1, 3));
		panelStud.add(adresa, lbl(0, 4));
		panelStud.add(adresaSt, lbl(1, 4));
		panelStud.add(broj, lbl(0, 5));
		panelStud.add(brojTel, lbl(1, 5));
		panelStud.add(godina, lbl(0, 6));
		panelStud.add(godinaSt, lbl(1, 6));
		panelStud.add(status, lbl(0, 7));
		panelStud.add(statusStud, lbl(1, 7));
		panelStud.add(datumUpisa, lbl(0, 8));
		panelStud.add(datumUpStud, lbl(1, 8));
		panelStud.add(prosek, lbl(0, 9));
		panelStud.add(prosekStud, lbl(1, 9));
		
		
		JButton okBtn = new JButton("Zatvori");
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		
		panelDugmici.add(okBtn);
		this.add(panelStud, BorderLayout.CENTER);
		this.add(panelDugmici, BorderLayout.SOUTH);

		
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
		
}
