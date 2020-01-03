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

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpDialog extends JDialog {

	private static final long serialVersionUID = -8697131516928005075L;
	private String poruka;
	
	public HelpDialog(JFrame parent) {
		super(parent, "Help", null);
		this.setSize(400, 250);
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
		komponente.setLayout(new FlowLayout(FlowLayout.LEFT));
		dugmici.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		ImageIcon icon = new ImageIcon("images/student_add.png");
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH ) ;  
	    icon = new ImageIcon(newimg);
	    
		JButton btnNew = new JButton();
		btnNew.setToolTipText("Dodaj...");
		btnNew.setIcon(icon);
	
		btnNew.setPreferredSize(new Dimension(50, 50));
		btnNew.setMinimumSize(new Dimension(50, 50));
		btnNew.setMaximumSize(new Dimension(50, 50));
		
		btnNew.setOpaque(false);
		btnNew.setBorder(null);
		
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			      poruka = "Dugme za dodavanje omogucava dodavanje studenata, profesora i predmeta.\n"
			      		+ "Potrebno je da bi dodavanje bilo uspesno "
			      		+ "da se popune sva polja oznacena sa zvezdicom.\nFormat za unos datuma je 'dd.MM.yyyy.' gde je dd - dan, mm - mesec i yyyy - godina.\n"
			      		+ "Broj indeksa je unosi u formatu 'XX-bb-yyyy' gde je XX - smer, bb - broj indeksa i \nyyyy - godina upisa.\n"
			      		+ "Prosecnu ocenu studenta unosite kao xx.xx gde se tackom odvajaju brojevi iza decimalnog zareza.\n"
			      		+ "Ne mozete dodati studenta sa istim brojem indeksa,\nte pogledajte bolje svoj unos ako vam se pojavi to upozorednje.\n"
			      		+ "U tabeli studenata se nalazi jos opcija za brisanje predmeta koje student slusa,\nte se to vrsi tako sto oznacite zeljeni predmet i pritisnite dugme Obrisi.";
			      
			            JTextArea jta = new JTextArea(20, 50);
			            jta.setText(poruka);
			            jta.setEditable(false);
			            JScrollPane jsp = new JScrollPane(jta);
			            JOptionPane.showMessageDialog(null, jsp);
			         }
			      });
		
		
		komponente.add(Box.createHorizontalStrut(20));
		komponente.add(btnNew);
		
		this.add(komponente, BorderLayout.CENTER);
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
