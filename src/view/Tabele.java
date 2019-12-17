package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Tabele extends JTabbedPane {


	private static final long serialVersionUID = -8387562986093340815L;
	public static int tab_curr = 0;
	
	public Tabele() {
		super();
		
		StudentJTable tabelaStudenata = new StudentJTable();
		ProfesoriJTable tabelaProfesora = new ProfesoriJTable();
		
		// TODO!
		
		// Ovo ti implementiraj
		
		// PredmetiJTable tabelaPredmeta = new PredmetiJTable();
		
		tabelaStudenata.setPreferredSize(new Dimension(500, 500));
		JScrollPane scrollPane = new JScrollPane(tabelaStudenata);
		scrollPane.setBorder(new EmptyBorder(30,20,10,20));
		
		
		tabelaProfesora.setPreferredSize(new Dimension(500, 500));
		JScrollPane scrollPaneProf = new JScrollPane(tabelaProfesora);
		scrollPaneProf.setBorder(new EmptyBorder(30,20,10,20));
		
		this.addTab("Student", null, scrollPane, "Student");
		this.addTab("Profesor", null, scrollPaneProf, "Profesor");
		this.addTab("Predmeti", null);
		
		
		// ovo je osluskivac koji prati promene na tabovima
		// dodaje ikonicu za dodavanje studenta i profesora na predemet 
		
		this.addChangeListener(new ChangeListener() {


			@Override
			public void stateChanged(ChangeEvent event) {
				
			    JTabbedPane tabbedPane = (JTabbedPane)event.getSource();
			    tab_curr = tabbedPane.getSelectedIndex();
			    if(tab_curr == 2) {
			    	Toolbar.setDugmici();
			    }
			    else
			    {
			    	Toolbar.unSetDugmici();
			    }
			}
		    });
		
	}
	
}
