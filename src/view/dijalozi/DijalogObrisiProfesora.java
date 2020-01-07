package view.dijalozi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ProfesorController;
import model.BazaProfesora;
import view.ProfesoriJTable;

public class DijalogObrisiProfesora extends JDialog {

	private static final long serialVersionUID = 6377270772551165630L;

	public DijalogObrisiProfesora(JFrame parent) {
		super(parent, "Brisanje profesora", true);
		this.setTitle("Brisanje profesora");
		if(BazaProfesora.getInstance().getProfesori().size() == 0) {
			JOptionPane.showMessageDialog(null, "Ne postoji nijedan profesor za brisanje", "GRESKA", JOptionPane.ERROR_MESSAGE);
		}
		else {
			this.setSize(new Dimension(400, 100));
			this.setLayout(new BorderLayout());
			
			JPanel panel  = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel labela = new JLabel("Da li ste sigurni da zelite da obrisete datog profesora?");
			
			add(labela, BorderLayout.CENTER);
			
			JButton btnOk = new JButton("Potvrda");
			JButton btnNotOk = new JButton("Odustanak");
			


			btnOk.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub				
					ProfesorController.getInstance().izbrisiProfesora(ProfesoriJTable.curr_row);
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
		
			panel.add(btnNotOk);
			panel.add(btnOk);
			
			this.add(panel, BorderLayout.SOUTH);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		
		}
		
		
	}
}
