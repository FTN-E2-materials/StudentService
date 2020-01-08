package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
import model.BazaPredmeta;
import model.Predmet;
import model.Student;

public class ListaStudenata extends JDialog {

	private static final long serialVersionUID = -6256559413662556456L;
	public static String row = "";
	
	public ListaStudenata(JFrame parent, int ind) {
		super(parent, "Lista Studenata", null);
		this.setSize(250, 250);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JPanel panelStud = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelDugmici = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		DefaultListModel<String> listaS = new DefaultListModel<>();
		Predmet p = BazaPredmeta.getInstance().getRow(ind);
		
		for (Student s : p.getStudenti()) {
			listaS.addElement(s.getBri());
		}
		
		JList<String> lista = new JList<>(listaS);
		
		lista.setFixedCellWidth(200);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		lista.setVisibleRowCount(5);
		
		JButton obrisiB = new JButton("Obriši");
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
				PredmetiJTable.refresh();
			}
		});
		
		obrisiB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(listaS.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ne postotji student na datom predmetu!");
				} else {
					
					ControllerEntiteta.getInstance().brisanjeStudentaSaPredmeta(BazaPredmeta.getInstance().getRow(PredmetiJTable.curr_row), row);
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
