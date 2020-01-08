package view.dijalozi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpDialog extends JDialog {

	private static final long serialVersionUID = -8697131516928005075L;
	private String poruka;
	
	public HelpDialog(JFrame parent) {
		super(parent, "Help", null);
		this.setSize(parent.getWidth()*3/5, parent.getHeight()*3/4);
		this.setLocationRelativeTo(null);
		JButton zatvori = new JButton("Zatvori");
		this.setModal(true);
		
		JPanel komponente = new JPanel();
		JPanel dugmici = new JPanel();

		zatvori.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
			
		});

		dugmici.add(zatvori);
		
		this.setLayout(new BorderLayout());
		
		komponente.setLayout(new GridBagLayout());
		dugmici.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		ImageIcon icon = new ImageIcon("images/student_add.png");
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH ) ;  
	    icon = new ImageIcon(newimg);
	    
		JButton btnNew = new JButton();
		btnNew.setToolTipText("Dodaj...");
		btnNew.setIcon(icon);
	
		btnNew.setPreferredSize(new Dimension(30, 30));
		btnNew.setMinimumSize(new Dimension(30, 30));
		btnNew.setMaximumSize(new Dimension(30, 30));
		
		btnNew.setOpaque(false);
		btnNew.setBorder(null);
		
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			      poruka = "Dugme za dodavanje omogućava dodavanje studenata, profesora i predmeta.\n"
			      		+ "Potrebno je da bi dodavanje bilo uspešno "
			      		+ "da se popune sva polja označena sa zvezdicom.\nFormat za unos datuma je 'dd.MM.yyyy.' gde je dd - dan, mm - mesec i yyyy - godina.\n"
			      		+ "Broj indeksa je unosi u formatu 'XX-bb-yyyy' gde je XX - smer, bb - broj indeksa i \nyyyy - godina upisa.\n"
			      		+ "Prosečnu ocenu studenta unosite kao xx.xx gde se tačkom odvajaju brojevi iza decimalnog zareza.\n"
			      		+ "Ne mozete dodati studenta sa istim brojem indeksa,\nte pogledajte bolje svoj unos ako vam se pojavi to upozorenje.\n"
			      		+ "U tabeli studenata se nalazi još opcija za brisanje predmeta koje student sluša,\nte se to vrši tako što označite željeni predmet i pritisnite dugme Obriši.";
			      
			            JTextArea jta = new JTextArea(20, 50);
			            jta.setText(poruka);
			            jta.setEditable(false);
			            JScrollPane jsp = new JScrollPane(jta);
			            JOptionPane.showMessageDialog(null, jsp);
			         }
			      });
		
		JLabel lblDodaj = new JLabel();
		lblDodaj.setText("Dugme za dodavanje entiteta. Prečica: CTRL + N. Pritisnuti dugme za više informacija");
		
		
		komponente.add(btnNew, tf(1, 0));
		komponente.add(lblDodaj, lbl(0, 0));
		
		
		ImageIcon iconEdit = new ImageIcon("images/edit.png");
		Image imgEdit = iconEdit.getImage() ;  
		Image newimgEdit = imgEdit.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH ) ;  
	    iconEdit = new ImageIcon(newimgEdit);
	    
		JButton btnEdit = new JButton();
		btnEdit.setToolTipText("Izmeni...");
		btnEdit.setIcon(iconEdit);
	
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			      poruka = "Dugme za izmenu studenta, profesora, predmeta. Prilikom klika na dugme\notvara vam se dijalog u kom se već nalaze trenutni podaci\n"
			      		+ "entiteta kog želite da obrišete. Popunite željeno polje za izmenu\ni pritisniete Potvrdi. Nije dozvoljeno menjanje\nbroja indeksa, lične karte, kao ni šifre predmeta\n"
			      		+ "jer se u bazi koriste kao primarni kljuČevi datog entiteta.";
			      
			            JTextArea jta = new JTextArea(20, 40);
			            jta.setText(poruka);
			            jta.setEditable(false);
			            JScrollPane jsp = new JScrollPane(jta);
			            JOptionPane.showMessageDialog(null, jsp);
			         }
			      });
		JLabel lblIzmeni = new JLabel();
		lblIzmeni.setText("Dugme za izmenu entiteta. Prečica: CTRL + E. Pritisnuti dugme za više informacija");

		btnEdit.setOpaque(false);
		btnEdit.setBorder(null);
		

		komponente.add(btnEdit, tf(1, 1));
		komponente.add(lblIzmeni, lbl(0, 1));
		
		ImageIcon iconDelete = new ImageIcon("images/delete.png");
		Image imgDelete = iconDelete.getImage() ;  
		Image newimgDelete = imgDelete.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH ) ;  
	    iconDelete = new ImageIcon(newimgDelete);
	    
		JButton btnDelete = new JButton();
		btnDelete.setToolTipText("Obriši...");
		btnDelete.setIcon(iconDelete);
	
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			      poruka = "Dugme za brisanje studenta, profesora, predmeta. Označite željeni entitet i pritisnete dugme\ni potvrdite izmenu.";
			            JTextArea jta = new JTextArea(20, 50);
			            jta.setText(poruka);
			            jta.setEditable(false);
			            JScrollPane jsp = new JScrollPane(jta);
			            JOptionPane.showMessageDialog(null, jsp);
			         }
			      });
		JLabel lblDelete = new JLabel();
		lblDelete.setText("Dugme za brisanje entiteta. Prečica: CTRL + D. Pritisnuti dugme za više informacija");

		btnDelete.setOpaque(false);
		btnDelete.setBorder(null);
		

		komponente.add(btnDelete, tf(1, 2));
		komponente.add(lblDelete, lbl(0, 2));
		
		ImageIcon iconSearch = new ImageIcon("images/search2.png");
		Image imgSearch = iconSearch.getImage() ;  
		Image newimgSearch = imgSearch.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH ) ;  
	    iconSearch = new ImageIcon(newimgSearch);
	    
		JButton btnSearch = new JButton();
		btnSearch.setToolTipText("Obriši...");
		btnSearch.setIcon(iconSearch);
	
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			      poruka = "Dugme za pretragu studenta, profesora, predmeta. Kriterijumi pretrage su:\n"
			      		+ "Za studenta: [ime:'Ime'];[prezime:'Prezime'];[bri:'Broj indeksa'];[status:'Status] \n(kao skraćenica B, S ili kao Budžet, Samofinansiranje)."
			      		+ "\nZa profesora: [ime:'Ime'];[prezime:'Prezime'];[brlk:'Broj licne karte'];[zvanje:'Zvanje];[titula:'Titula']\n"
			      		+ "Zvanja: Master, Doktor\n"
			      		+ "Titule: Redovni profesor, Vanredni profesor, Asistent, Docent, Saradnik u nastavi"
			      		+ "\nZa predmete: [sifra:'Sifra'];[naziv:'Naziv'];[godina:'Godina'];[semestar:'Semestar']";
			            JTextArea jta = new JTextArea(20, 50);
			            jta.setText(poruka);
			            jta.setEditable(false);
			            JScrollPane jsp = new JScrollPane(jta);
			            JOptionPane.showMessageDialog(null, jsp);
			         }
			      });
		JLabel lblSearch = new JLabel();
		lblSearch.setText("Dugme za brisanje entiteta. Prečica: CTRL + D. Pritisnuti dugme za više informacija");

		btnSearch.setOpaque(false);
		btnSearch.setBorder(null);
		

		komponente.add(btnSearch, tf(1, 3));
		komponente.add(lblSearch, lbl(0, 3));
		

		ImageIcon iconDodSt = new ImageIcon("images/student.png");
		Image imgDodSt = iconDodSt.getImage() ;  
		Image newimgDodSt = imgDodSt.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH ) ;  
		iconDodSt = new ImageIcon(newimgDodSt);
	    
		JButton btnDodaj = new JButton();
		btnDodaj.setToolTipText("Dodaj studenta na predmet...");
		btnDodaj.setIcon(iconDodSt);
	
		btnDodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			      poruka = "Dugme za dodavanje studenta na željeni predmet."
			      		+ "\nOznačite željeni predmete, pritisnite dugme i unesite željeni broj indeksa.\n"
			      		+ "Za pregled svih studenata možete pritisnuti dugme lista studenata.";
			            JTextArea jta = new JTextArea(20, 50);
			            jta.setText(poruka);
			            jta.setEditable(false);
			            JScrollPane jsp = new JScrollPane(jta);
			            JOptionPane.showMessageDialog(null, jsp);
			         }
			      });
		JLabel lblDodajSt = new JLabel();
		lblDodajSt.setText("Dugme za dodavanje studenta na predmet. Prečica: CTRL + D. Pritisnuti dugme za više informacija");

		btnDodaj.setOpaque(false);
		btnDodaj.setBorder(null);
		

		komponente.add(btnDodaj, tf(1, 4));
		komponente.add(lblDodajSt, lbl(0, 4));
		

		ImageIcon icondDodProf = new ImageIcon("images/profesor.png");
		Image imgDodProf = icondDodProf.getImage() ;  
		Image newimgDodProf = imgDodProf.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH ) ;  
		icondDodProf = new ImageIcon(newimgDodProf);
	    
		JButton btnDodProf = new JButton();
		btnDodProf.setToolTipText("Dodaj profesora na predmet...");
		btnDodProf.setIcon(icondDodProf);
	
		btnDodProf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			      poruka = "Dugme za dodavanje profesora na željeni predmet."
			      		+ "\nOznačite željeni predmete, pritisnite dugme i unesite željeni broj lične karte.\n"
			      		+ "Za pregled svih studenata možete pritisnuti dugme lista studenata.";
			            JTextArea jta = new JTextArea(20, 50);
			            jta.setText(poruka);
			            jta.setEditable(false);
			            JScrollPane jsp = new JScrollPane(jta);
			            JOptionPane.showMessageDialog(null, jsp);
			         }
			      });
		JLabel lblDodajProf = new JLabel();
		lblDodajProf.setText("Dugme za dodavanje studenta na predmet. Prečica: CTRL + D. Pritisnuti dugme za više informacija");

		btnDodProf.setOpaque(false);
		btnDodProf.setBorder(null);
		

		komponente.add(btnDodProf, tf(1, 5));
		komponente.add(lblDodajProf, lbl(0, 5));
		
		JLabel lblAbout = new JLabel();
		lblAbout.setText("About deo: Tekst o samoj aplikaciji i autorima aplikacije. Prečica: CTRL + O.");

		JLabel lblHelp = new JLabel();
		lblHelp.setText("Help deo: Tekst o upotrebi same aplikacije. Prečica: CTRL + H.");
		
		komponente.add(lblAbout, lbl(0, 6));
		komponente.add(lblHelp, lbl(0, 7));
		
		this.add(komponente, BorderLayout.CENTER);
		this.add(dugmici, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}


	private GridBagConstraints lbl(int x,int y) {
		GridBagConstraints gbc= new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;

		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(20, 10, 0, 10);
		return gbc;
	}
	
	private GridBagConstraints tf(int x,int y) {
		GridBagConstraints gbc= new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 2;
		gbc.weightx = 10;

		gbc.insets = new Insets(4, 4, 4, 4);
		return gbc;
	}
}
