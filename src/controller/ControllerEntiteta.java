package controller;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.BazaPredmeta;
import model.BazaStudenata;
import model.Predmet;
import model.Profesor;
import model.Student;
import view.MainFrame;
import view.Tabele;
import view.Toolbar;
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
			dijalog = new DijalogStudent(MainFrame.frame);
		}
		else if (Tabele.tab_curr == 1) {
			dijalog = new DijalogDodajProfesora(MainFrame.frame);
		}
		else if (Tabele.tab_curr == 2) {
			dijalog = new DijalogDodajPredmet(MainFrame.frame);
		}			
	}
	
	public void izmeniEntitet() {	
		if(Tabele.tab_curr == 0) {
			dijalog = new DijalogIzmeniS(MainFrame.frame);
		}
		else if (Tabele.tab_curr == 1) {
			dijalog = new DijalogIzmeniProfesora(MainFrame.frame);
		} else if (Tabele.tab_curr == 2) {
			dijalog = new DijalogIzmeniPredmet(MainFrame.frame);
		}
	}

	public void obrisiEntitet() {
		if(Tabele.tab_curr == 0) {
			dijalog = new DijalogObrisiS(MainFrame.frame);
		}
		else if(Tabele.tab_curr == 1) {
			dijalog = new DijalogObrisiProfesora(MainFrame.frame);
		} else if (Tabele.tab_curr == 2) {
			dijalog = new DijalogObrisiPredmet(MainFrame.frame);
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
		dijalog = new DodajStudentaNaPredmet(new JFrame());
	}

	public void brisanjesaPredmetaStudent(Student s, String sifra) {
		StudentController.getInstance().obrisiPredmet(s, sifra);
	}


	public void brisanProfesoraSaPredmeta(Profesor p, String sifra) {
		ProfesorController.getInstance().obrisiPredmet(p, sifra);
		
	}

	public void dodajProfesoraNaPredmetu() {
		dijalog = new DodajProfesoraNaPredmet(new JFrame());
		
	}

	public void pokaziHelp() {
		dijalog = new HelpDialog(new JFrame());
		
	}
	
}
