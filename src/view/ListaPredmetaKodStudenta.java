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
import model.BazaStudenata;
import model.Predmet;
import model.Student;

public class ListaPredmetaKodStudenta extends JDialog {

	private static final long serialVersionUID = 2107788705884014377L;
	public static String row = "";
	
	public ListaPredmetaKodStudenta(JFrame parent, int ind) {
		
		// dijalog za prikazivanje liste predmeta kod studenta
		// koristila sam JList za prikazivanje listi
	
		super(parent, "Lista predmeta", true);
		this.setSize(MainFrame.width/4, MainFrame.height/3);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	
		JPanel panelStud = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panelDugmici = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		DefaultListModel<String> listaStudenata = new DefaultListModel<>();
		
		Student s = BazaStudenata.getInstance().getRow(ind);
		for (Predmet p : s.getPredmeti()) {
			listaStudenata.addElement(p.getSifra() + " " + p.getIme());
		}
		
		JList<String> lista = new JList<>(listaStudenata);
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
		
		obrisiB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(listaStudenata.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ne postotji predmet kod datog studenta.");
				} else {		
					ControllerEntiteta.getInstance().brisanjesaPredmetaStudent(BazaStudenata.getInstance().getRow(StudentJTable.curr_row), row);
					listaStudenata.removeElement(row);
					lista.updateUI();
				}
			}
		});
	
		odustaniB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();	
			}
		});
			
		this.addWindowListener(new WindowAdapter() { 
		  public void windowClosed(WindowEvent e)
		  {
			  // da bih opet postavila trenutni red na -1
			  // zbog otvaranja izmene i obrisi
		    StudentJTable.refresh();
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
