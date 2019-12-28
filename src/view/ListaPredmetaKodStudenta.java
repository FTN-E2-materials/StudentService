package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.ControllerEntiteta;
import model.BazaPredmeta;
import model.BazaStudenata;
import model.Predmet;
import model.Student;

public class ListaPredmetaKodStudenta extends JDialog {

	private static final long serialVersionUID = 2107788705884014377L;
	public static String row = "";
	
	public ListaPredmetaKodStudenta() {
		super();
		this.setSize(500, 500);
		this.setLayout(new BorderLayout());
		this.setTitle("Lista predmeta");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JPanel panelStud = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelDugmici = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		DefaultListModel<String> listaS = new DefaultListModel<>();
		
		for (Predmet p : BazaStudenata.getInstance().getRow(StudentJTable.curr_row).getPredmeti()) {
			listaS.addElement(p.getSifra());
		}
		
		JList<String> lista = new JList<>(listaS);
		lista.setFixedCellWidth(200);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		JButton obrisiB = new JButton("Obrisi");
		JButton odustaniB = new JButton("Odustani");
		
		lista.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					row = lista.getSelectedValue();
				}
				
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
					JOptionPane.showMessageDialog(null, "Ne postotji student na datom predmetu!");
				} else {
					ControllerEntiteta.getInstance().brisanjesaPredmetaStudent(BazaStudenata.getInstance().getRow(StudentJTable.curr_row), row);
					listaS.removeElement(row);
					lista.updateUI();
					PredmetiJTable.refresh();
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
