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
import javax.swing.event.DocumentListener;

import controller.DocumentListenerDodajProfesoraNaPredmet;
import controller.PredmetController;
import model.BazaPredmeta;
import model.Predmet;
import view.MainFrame;
import view.PredmetiJTable;

public class DodajProfesoraNaPredmet extends JDialog {

	private static final long serialVersionUID = 2623181216772470132L;
	public static JTextField brlk;
	public static JButton ok;
	private DocumentListener documentListener = new DocumentListenerDodajProfesoraNaPredmet();
	
	public DodajProfesoraNaPredmet(JFrame parent) {
		super(parent, "Dodaj profesora", true);
		this.pack();
		this.setSize(MainFrame.width/3, MainFrame.height/5);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		
		JLabel labBrlk = new JLabel("*Broj licne karte profesora:");
		labBrlk.setToolTipText("Unesite broj licne karte");
		brlk = new JTextField();
		
		JPanel up = new JPanel(new GridBagLayout());
		
		up.add(labBrlk, lbl(0, 0));
		up.add(brlk, tf(1, 0));
		
		ok = new JButton("Potvrda");
		ok.setEnabled(false);
		
		brlk.getDocument().addDocumentListener(documentListener);
		JButton notOk = new JButton("Odustanak");
		JButton delete = new JButton("Obrisi");
		Predmet p = BazaPredmeta.getInstance().getRow(PredmetiJTable.curr_row);
		
		if (p.getPred_prof() != null) {
			brlk.setText(p.getPred_prof().getBrlk());
			
			delete.setVisible(true);
			ok.setVisible(false);
			notOk.setVisible(true);
		} else {
			brlk.setText("");
			delete.setVisible(false);
			ok.setVisible(true);
			notOk.setVisible(true);
		}
		
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(PredmetController.getInstance().obrisiProfesora()) {
					dispose();
				}
			}
			
		});
		
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(PredmetController.getInstance().dodajProfesoraNaPredmet()) {
					dispose();
				}
			}
		});
		
		notOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
		});
		
		JPanel dugmici = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		dugmici.add(notOk);
		dugmici.add(delete);
		dugmici.add(ok);
		
		this.add(up, BorderLayout.CENTER);
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
		if (brlk.getText().trim().isEmpty()) {
			ok.setEnabled(false);
		} else {
			ok.setEnabled(true);
		}
		
	}	
}
