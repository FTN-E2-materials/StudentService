package view;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.PredmetController;
import controller.ProfesorController;
import controller.StudentController;
import model.BazaPredmeta;
import model.BazaProfesora;
import model.BazaStudenata;

public class Tabele extends JTabbedPane {


	private static final long serialVersionUID = -8387562986093340815L;
	public static int tab_curr = 0;
	
	public Tabele() {
		super();
		
		StudentJTable tabelaStudenata = new StudentJTable();
		ProfesoriJTable tabelaProfesora = new ProfesoriJTable();
		PredmetiJTable tabelaPredmeta = new PredmetiJTable();
		
		//tabelaStudenata.setPreferredSize(new Dimension(500, 500));
		JScrollPane scrollPane = new JScrollPane(tabelaStudenata);
		scrollPane.setBorder(new EmptyBorder(60,30,10,30));
		
		//tabelaProfesora.setPreferredSize(new Dimension(500, 500));
		JScrollPane scrollPaneProf = new JScrollPane(tabelaProfesora);
		scrollPaneProf.setBorder(new EmptyBorder(60,30,10,30));
		
		//tabelaPredmeta.setPreferredSize(new Dimension(500, 500));
		JScrollPane scrollPanePred = new JScrollPane(tabelaPredmeta);
		scrollPanePred.setBorder(new EmptyBorder(60, 30, 10, 30));
		
		this.addTab("Student", null, scrollPane, "Student");
		this.addTab("Profesor", null, scrollPaneProf, "Profesor");
		this.addTab("Predmeti", null, scrollPanePred, "Predmet");
		
		
		// ovo je osluskivac koji prati promene na tabovima
		// dodaje ikonicu za dodavanje studenta i profesora na predemet 
		
		this.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent event) {
				
			    JTabbedPane tabbedPane = (JTabbedPane)event.getSource();
			    tab_curr = tabbedPane.getSelectedIndex();
			    if(tab_curr == 2) {
			    	StudentController.flag = 0;
			    	ProfesorController.flag = 0;
			    	
			    	BazaStudenata.getInstance().setTrenutnoStanje();
			    	BazaProfesora.getInstance().setTrenutnoStanje();
			    	
			    	
			    	ProfesoriJTable.refresh();
			    	StudentJTable.refresh();
			    	
			    	Toolbar.setDugmici();
			    }
			    else
			    {
			    	Toolbar.unSetDugmici();
			    
		    	if (tab_curr == 0) {
			    		
				    ProfesorController.flag = 0;
				    PredmetController.flag = 0;
				    BazaPredmeta.getInstance().setTrentunoStanje();
				    BazaProfesora.getInstance().setTrenutnoStanje();
				    	
				    	
				    ProfesoriJTable.refresh();
				    PredmetiJTable.refresh();
		    	} else if (tab_curr == 1) {
			    		
			    	PredmetController.flag = 0;
			    	StudentController.flag = 0;
			    	BazaStudenata.getInstance().setTrenutnoStanje();
			    	BazaPredmeta.getInstance().setTrentunoStanje();
				    	
			    	StudentJTable.refresh();
			    	PredmetiJTable.refresh();
			    	}
			    }
			}
		    });
		
	}
	
	
	
}
