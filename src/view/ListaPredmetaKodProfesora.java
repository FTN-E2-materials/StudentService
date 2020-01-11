package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.ControllerEntiteta;
import model.BazaProfesora;
import model.Predmet;
import model.Profesor;

public class ListaPredmetaKodProfesora extends JDialog {

	private static final long serialVersionUID = -1730105969728269226L;
	public static String row = "";

	public ListaPredmetaKodProfesora(JFrame parent, int ind) {
		super(parent, "Lista predmeta", true);
		this.setSize(MainFrame.width/4, MainFrame.height/3);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JPanel panelStud = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelDugmici = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		DefaultListModel<String> listaS = new DefaultListModel<>();
		Profesor prof = BazaProfesora.getInstance().getRow(ind);
		for (Predmet p : prof.getPredmeti()) {
			listaS.addElement(p.getSifra() + " " + p.getIme());
		}
		JList<String> lista = new JList<>(listaS);
		lista.setFixedCellWidth(this.getWidth()*3/4);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton obrisiB = new JButton("Obriši");
		JButton odustaniB = new JButton("Zatvori");
		
		lista.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					row = lista.getSelectedValue();
				}
			
			}
		});
		
		this.addWindowListener(new WindowAdapter() { 
			  public void windowClosed(WindowEvent e)
			  {
				// kad se zatvori dijalog potrebno je da se refreshuje zbog podataka
				// moj refresh radi tako sto postavi red na -1
				  ProfesoriJTable.refresh();
			  }
			});
		
		odustaniB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();	
				
			}
		});
		
		obrisiB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(listaS.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ne postoje predmeti kod datog profeosra!");
				} else {
					ControllerEntiteta.getInstance().brisanProfesoraSaPredmeta(BazaProfesora.getInstance().getRow(ind), row);
					listaS.removeElement(row);
					lista.updateUI();
				}
			}
		});
		
		panelStud.add(new JScrollPane(lista));
		panelStud.add(lista);
		panelDugmici.add(odustaniB);
		panelDugmici.add(obrisiB);
		this.add(panelStud, BorderLayout.CENTER);
		this.add(panelDugmici, BorderLayout.SOUTH);

		this.setVisible(true);
		
		
	}
}
