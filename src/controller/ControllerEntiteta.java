package controller;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.BazaPredmeta;
import model.BazaStudenata;
import model.Predmet;
import model.Student;
import view.Tabele;
import view.Toolbar;
import view.dijalozi.DijalogDodajPredmet;
import view.dijalozi.DijalogDodajProfesora;
import view.dijalozi.DijalogIzmeniProfesora;
import view.dijalozi.DijalogIzmeniS;
import view.dijalozi.DijalogObrisiProfesora;
import view.dijalozi.DijalogObrisiS;
import view.dijalozi.DijalogStudent;
import view.dijalozi.DodajStudentaNaPredmet;

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
			dijalog = new DijalogStudent(new JFrame());
		}
		else if (Tabele.tab_curr == 1) {
			dijalog = new DijalogDodajProfesora();
		}
		else if (Tabele.tab_curr == 2) {
			dijalog = new DijalogDodajPredmet(new JFrame());
		}			
	}
	
	public void izmeniEntitet() {	
		if(Tabele.tab_curr == 0) {
			dijalog = new DijalogIzmeniS();
		}
		else if (Tabele.tab_curr == 1) {
			dijalog = new DijalogIzmeniProfesora();
		}
	}

	public void obrisiEntitet() {
		if(Tabele.tab_curr == 0) {
			dijalog = new DijalogObrisiS();
		}
		if(Tabele.tab_curr == 1) {
			dijalog = new DijalogObrisiProfesora();
		}
	}
	
	public void pretragaEntitet(int tab) {
		if(Tabele.tab_curr == 0) {
			StudentController.getInstance().pretraziStudenta();
		}
		else if(Tabele.tab_curr == 1) {
			ProfesorController.getInstance().pretragaProfesora();
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
	
}
