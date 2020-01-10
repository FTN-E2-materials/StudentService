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

import controller.StudentController;
import model.BazaStudenata;
import view.MainFrame;

public class DijalogObrisiS extends JDialog {

	private static final long serialVersionUID = 2569416438893083769L;
	
	public DijalogObrisiS(JFrame parent) {
		super(parent, "Brisanje studenta", true);
		this.setTitle("Brisanje studenta");
		if(BazaStudenata.getInstance().getStudenti().size() == 0) {
			JOptionPane.showMessageDialog(null, "Ne postoji student","GREŠKA", JOptionPane.ERROR_MESSAGE);
		}
		
		else {
			this.setSize(MainFrame.width/3, MainFrame.height/6);
			this.setLayout(new BorderLayout());
			
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JPanel text = new JPanel(new FlowLayout(FlowLayout.CENTER));
			
			JLabel labela = new JLabel("Da li ste sigurni da želite da obrišete datog studenta?");
			
			text.add(labela);
			
			JButton btnOk = new JButton("Potvrda");
			JButton btnNotOk = new JButton("Odustanak");
			
			btnOk.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub				
					StudentController.getInstance().izbrisiStudenta();
					dispose();
				}
				
			});
			
			btnNotOk.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
				
			});
			
			panel.add(btnNotOk);
			panel.add(btnOk);
			
			this.add(text, BorderLayout.CENTER);
			this.add(panel, BorderLayout.SOUTH);
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		}
	}
	
	

}
