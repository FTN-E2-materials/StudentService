package controller;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.BazaStudenata;
import view.Tabele;
import view.Toolbar;
import view.dijalozi.DijalogDodajProfesora;
import view.dijalozi.DijalogIzmeniProfesora;
import view.dijalozi.DijalogIzmeniS;
import view.dijalozi.DijalogObrisiProfesora;
import view.dijalozi.DijalogObrisiS;
import view.dijalozi.DijalogStudent;

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
		// TODO!! 
		if(Tabele.tab_curr == 0) {
			dijalog = new DijalogStudent(new JFrame());
		}
		else if (Tabele.tab_curr == 1) {
			dijalog = new DijalogDodajProfesora();
		}
		else if (Tabele.tab_curr == 2) {
			//dijalog = new DijalogPredmet(new JFrame());
		}		
		
		
	}
	
	public void izmeniEntitet() {
		// TODO!!
		if(Tabele.tab_curr == 0) {
			dijalog = new DijalogIzmeniS();
		}
		else if (Tabele.tab_curr == 1) {
			dijalog = new DijalogIzmeniProfesora();
		}
	}
	
	public void obrisiEntitet() {
		// TODO!!
		if(Tabele.tab_curr == 0) {
			dijalog = new DijalogObrisiS();
		}
		if(Tabele.tab_curr == 1) {
			dijalog = new DijalogObrisiProfesora();
		}
	}
	
	public void pretragaEntitet(int tab) {
		// TODO!
		if(Tabele.tab_curr == 0) {
			StudentController.getInstance().pretraziStudenta();
		}
	}
}
