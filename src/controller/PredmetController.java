package controller;

import javax.swing.JOptionPane;

import model.BazaPredmeta;
import model.BazaProfesora;
import model.BazaStudenata;
import model.Predmet;
import model.Profesor;
import model.Profesor.Zvanje;
import model.Student;
import view.PredmetiJTable;
import view.StudentJTable;
import view.Toolbar;
import view.dijalozi.DijalogDodajPredmet;
import view.dijalozi.DijalogIzmeniPredmet;
import view.dijalozi.DodajProfesoraNaPredmet;
import view.dijalozi.DodajStudentaNaPredmet;

public class PredmetController {
	public static PredmetController instance = null;
	public static int flag; 
	
	public static PredmetController getInstance() {
		if (instance == null) {
			instance = new PredmetController();
		} 
		return instance;
	}
	private PredmetController() {}
	
	public boolean dodajPredmet() {
		if(DijalogDodajPredmet.sifraP.getText().isEmpty() || DijalogDodajPredmet.imeP.getText().isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Niste popunili sva obavezna polja.\nPolja sa * su obavezna.", "GRESKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		String sifra = DijalogDodajPredmet.sifraP.getText();
		String ime = DijalogDodajPredmet.imeP.getText();
		int godina = DijalogDodajPredmet.godina.getSelectedIndex() + 1;
		int semestar = DijalogDodajPredmet.semestar.getSelectedIndex() + 1;
		
		if (godina == 1) {
			if (semestar != 1 && semestar != 2) {
				JOptionPane.showMessageDialog(null, "Ne podudaraju se semestri sa godinom, proverite ponovo.", "Greska", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else if (godina == 2) {
			if (semestar != 3 && semestar != 4) {
				JOptionPane.showMessageDialog(null, "Ne podudaraju se semestri sa godinom, proverite ponovo.", "Greska", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else if (godina == 3) {
			if (semestar != 5 && semestar != 6) {
				JOptionPane.showMessageDialog(null, "Ne podudaraju se semestri sa godinom, proverite ponovo.", "Greska", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			if (semestar != 7 && semestar != 8) {
				JOptionPane.showMessageDialog(null, "Ne podudaraju se semestri sa godinom, proverite ponovo.", "Greska", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		
		
		BazaPredmeta.getInstance().dodajPredmet(sifra, ime, godina, semestar);
		
		if(!DijalogDodajPredmet.profesor.getText().isEmpty()) 
			if(!BazaPredmeta.getInstance().dodajProfesora(DijalogDodajPredmet.profesor.getText(), sifra)) {
				JOptionPane.showMessageDialog(null, "Profesor ne postoji u bazi.\nProverite da li ste uneli dobro broj licne karte.", "GRESKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		
		PredmetiJTable.refresh();
		return true;
	}
	
	public boolean dodajStudentaNaPredmet() {
		if(DodajStudentaNaPredmet.bri.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Niste popunili sva obavezna polja.\nPolja sa * su obavezna.", "GRESKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String bri = DodajStudentaNaPredmet.bri.getText();
		Predmet p = BazaPredmeta.getInstance().getRow(PredmetiJTable.curr_row);
		Student s = null;
		
		for (Student st : BazaStudenata.getInstance().getStudenti()) {
			if (st.getBri().equals(bri)) {
				s = st;
			}
		}
		
		if (s == null) {
			JOptionPane.showMessageDialog(null, "Student ne postoji u bazi.\nProverite da li ste uneli dobro broj indeksa.", "Greska", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(s.getGodina_stud() != p.getGodina()) {
			JOptionPane.showMessageDialog(null, "Student ne pohadja datu godinu studija.", "Greska", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		for (Student st : p.getStudenti()) {
			if (st.getBri().equals(bri)) {
				JOptionPane.showMessageDialog(null, "Student vec pohadja dati predmet.", "Greska", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		BazaPredmeta.getInstance().dodajStudenta(p.getSifra(), s);
		BazaStudenata.getInstance().dodajPredmet(s, p);
		PredmetiJTable.refresh();
		StudentJTable.refresh();
		
		return true;
	}
	
	public void brisanjeSaPredmeta(Predmet p, String bri) {

		BazaPredmeta.getInstance().obrisiStudenta(p, bri);
		BazaStudenata.getInstance().obrisiPredmet(p, bri);

		StudentJTable.refresh();
		
	}
	
	public boolean dodajProfesoraNaPredmet() {
		if (DodajProfesoraNaPredmet.brlk.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Niste uneli broj licne karte.", "Greska", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		Predmet p = BazaPredmeta.getInstance().getRow(PredmetiJTable.curr_row);
		String brlk = DodajProfesoraNaPredmet.brlk.getText();
		
		Profesor pr = null;
		for (Profesor prof : BazaProfesora.getInstance().getProfesori()) {
			if (prof.getBrlk().equals(brlk)) {
				pr = prof;
			}
		}
				
		if (pr == null) {
			JOptionPane.showMessageDialog(null, "Profesor sa datim brojem licne karte ne postoji u bazi.\nProverite da li ste uneli dobro broj licne karte.", "Greska", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (pr.getZvanje() == Zvanje.Asistent) {
			JOptionPane.showMessageDialog(null, "Izabrali ste asistenta. Asistent ne moze biti predmetni profesor.\nProverite da li ste uneli dobro broj licne karte.", "Greska", JOptionPane.ERROR_MESSAGE);
			return false;
		}
				
		BazaPredmeta.getInstance().dodajProfesora(brlk, p.getSifra());		
		PredmetiJTable.refresh();
		return true;
	}
	
	public boolean izmeniPredmet() {
		if(DijalogIzmeniPredmet.sifraP.getText().isEmpty() || DijalogIzmeniPredmet.imeP.getText().isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Niste popunili sva obavezna polja.\nPolja sa * su obavezna.", "GRESKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		String sifra = DijalogIzmeniPredmet.sifraP.getText();
		String ime = DijalogIzmeniPredmet.imeP.getText();
		int godina = DijalogIzmeniPredmet.godina.getSelectedIndex() + 1;
		int semestar = DijalogIzmeniPredmet.semestar.getSelectedIndex() + 1;
		
		if (godina == 1) {
			if (semestar != 1 && semestar != 2) {
				JOptionPane.showMessageDialog(null, "Ne podudaraju se semestri sa godinom, proverite ponovo.", "Greska", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else if (godina == 2) {
			if (semestar != 3 && semestar != 4) {
				JOptionPane.showMessageDialog(null, "Ne podudaraju se semestri sa godinom, proverite ponovo.", "Greska", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else if (godina == 3) {
			if (semestar != 5 && semestar != 6) {
				JOptionPane.showMessageDialog(null, "Ne podudaraju se semestri sa godinom, proverite ponovo.", "Greska", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			if (semestar != 7 && semestar != 8) {
				JOptionPane.showMessageDialog(null, "Ne podudaraju se semestri sa godinom, proverite ponovo.", "Greska", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		
		Predmet predmet = new Predmet(sifra, ime, semestar, godina);
		BazaPredmeta.getInstance().izmeniPredmet(predmet);
		
		if(!DijalogIzmeniPredmet.profesor.getText().isEmpty()) 
			if(!BazaPredmeta.getInstance().dodajProfesora(DijalogIzmeniPredmet.profesor.getText(), sifra)) {
				JOptionPane.showMessageDialog(null, "Profesor ne postoji u bazi.\nProverite da li ste uneli dobro broj licne karte.", "GRESKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		
		
		PredmetiJTable.refresh();
		return true;
	}

	public void obrisiPredmet() {
		Predmet p = BazaPredmeta.getInstance().getRow(PredmetiJTable.curr_row);
		BazaPredmeta.getInstance().obrisiPredmet(p.getSifra());
		PredmetiJTable.refresh();
		
	}
	public void pretraziPredmet() {
		if (Toolbar.pretraga.getText().isEmpty()) {
			flag = 0;
			BazaPredmeta.getInstance().pretraziPredmet("");
		} else {
			flag = 1;
			BazaPredmeta.getInstance().pretraziPredmet(Toolbar.pretraga.getText());
		}
		
		PredmetiJTable.refresh();
		
	}
	public boolean obrisiProfesora() {
		Predmet p = BazaPredmeta.getInstance().getRow(PredmetiJTable.curr_row);
		
		if (p.getPred_prof() != null) {
			Profesor profa = p.getPred_prof();
			BazaPredmeta.getInstance().obrisiProfesora(profa, p);
			BazaProfesora.getInstance().obrisiPredmet(profa, p);
		}
		PredmetiJTable.refresh();
		return true;
	}
}
	

