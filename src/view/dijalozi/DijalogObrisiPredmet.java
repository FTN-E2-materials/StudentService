package view.dijalozi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.PredmetController;
import model.BazaPredmeta;
import view.MainFrame;

public class DijalogObrisiPredmet extends JDialog {

	private static final long serialVersionUID = -4574157994954943998L;

	public DijalogObrisiPredmet(JFrame parent) {
		super(parent, "Brisanje predmeta", true);
		this.setTitle("Brisanje predmeta");
		
		if(BazaPredmeta.getInstance().getPredmeti().size() == 0) {
			JOptionPane.showMessageDialog(null, "Ne postoji nijedan predmet za brisanje", "GREŠKA", JOptionPane.ERROR_MESSAGE);
		}
		else {
			
			this.pack();
			this.setSize(MainFrame.width/3, MainFrame.height/6);
			this.setLayout(new BorderLayout());
			
			JPanel panel  = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel labela = new JLabel("Da li ste sigurni da želite da obrišete dati predmet?", SwingConstants.CENTER);
			
			add(labela, BorderLayout.CENTER);
			
			JButton btnOk = new JButton("Potvrda");
			JButton btnNotOk = new JButton("Odustanak");
			


			btnOk.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub				
					PredmetController.getInstance().obrisiPredmet();
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
			this.setLocationRelativeTo(parent);
			this.setVisible(true);
		
		}
		
		
	}
}
