package controller;

import javax.swing.JDialog;

import model.Predmet;
import model.Profesor;
import model.Student;
import view.MainFrame;
import view.PredmetiJTable;
import view.ProfesoriJTable;
import view.StudentJTable;
import view.Tabele;

import view.dijalozi.AboutDialog;
import view.dijalozi.DijalogDodajPredmet;
import view.dijalozi.DijalogDodajProfesora;
import view.dijalozi.DijalogIzmeniPredmet;
import view.dijalozi.DijalogIzmeniProfesora;
import view.dijalozi.DijalogIzmeniS;
import view.dijalozi.DijalogObrisiPredmet;
import view.dijalozi.DijalogObrisiProfesora;
import view.dijalozi.DijalogObrisiS;
import view.dijalozi.DijalogStudent;
import view.dijalozi.DodajProfesoraNaPredmet;
import view.dijalozi.DodajStudentaNaPredmet;
import view.dijalozi.HelpDialog;

public class ControllerEntiteta {
	
	private static ControllerEntiteta instance = null;
	@SuppressWarnings("unused")
	private JDialog dijalog;
	
	public static ControllerEntiteta getInstance() {
		if(instance == null) {
			instance = new ControllerEntiteta();
		}
		return instance;
	}

	private ControllerEntiteta() {}
	
	public void dodajEntitet() {
		if(Tabele.tab_curr == 0) {
			dijalog = new DijalogStudent(MainFrame.getInstance());
		}
		else if (Tabele.tab_curr == 1) {
			dijalog = new DijalogDodajProfesora(MainFrame.getInstance());
		}
		else if (Tabele.tab_curr == 2) {
			dijalog = new DijalogDodajPredmet(MainFrame.getInstance());
		}			
	}
	
	public void izmeniEntitet() {	
		if(Tabele.tab_curr == 0) {
			if (StudentJTable.curr_row != -1)
				dijalog = new DijalogIzmeniS(MainFrame.getInstance());
		}
		else if (Tabele.tab_curr == 1) {
			if (ProfesoriJTable.curr_row != -1)
				dijalog = new DijalogIzmeniProfesora(MainFrame.getInstance());
		} else if (Tabele.tab_curr == 2) {
			if (PredmetiJTable.curr_row != -1)
				dijalog = new DijalogIzmeniPredmet(MainFrame.getInstance());
		}
	}

	public void obrisiEntitet() {
		if(Tabele.tab_curr == 0) {
			if (StudentJTable.curr_row != -1)
				dijalog = new DijalogObrisiS(MainFrame.getInstance());
		}
		else if(Tabele.tab_curr == 1) {
			if (ProfesoriJTable.curr_row != -1)
				dijalog = new DijalogObrisiProfesora(MainFrame.getInstance());
		} else if (Tabele.tab_curr == 2) {
			if (PredmetiJTable.curr_row != -1)
				dijalog = new DijalogObrisiPredmet(MainFrame.getInstance());
		}
	}
	
	public void pretragaEntitet(int tab) {
		if(Tabele.tab_curr == 0) {
			StudentController.getInstance().pretraziStudenta();
		}
		else if(Tabele.tab_curr == 1) {
			ProfesorController.getInstance().pretragaProfesora();
		} else {
			PredmetController.getInstance().pretraziPredmet();
		}
	}
	
	public void brisanjeStudentaSaPredmeta(Predmet p, String bri) {
		PredmetController.getInstance().brisanjeSaPredmeta(p, bri);
	}
	
	public void dodajEntitetNaPredmet() {
		if (PredmetiJTable.curr_row != -1)
			dijalog = new DodajStudentaNaPredmet(MainFrame.getInstance());
	}

	public void brisanjesaPredmetaStudent(Student s, String sifra) {
		StudentController.getInstance().obrisiPredmet(s, sifra);
	}


	public void brisanProfesoraSaPredmeta(Profesor p, String sifra) {
		ProfesorController.getInstance().obrisiPredmet(p, sifra);
		
	}

	public void dodajProfesoraNaPredmetu() {
		if (PredmetiJTable.curr_row != -1)
			dijalog = new DodajProfesoraNaPredmet(MainFrame.getInstance());
		
	}

	public void pokaziHelp() {
		dijalog = new HelpDialog(MainFrame.getInstance());
		
	}

	public void pokaziAbout() {
		dijalog = new AboutDialog(MainFrame.getInstance());
		
	}
	
}
