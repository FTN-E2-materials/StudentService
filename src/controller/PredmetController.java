package controller;

import javax.swing.JOptionPane;

import model.BazaPredmeta;
import model.BazaStudenata;
import model.Predmet;
import model.Student;
import view.PredmetiJTable;
import view.dijalozi.DijalogDodajPredmet;
import view.dijalozi.DodajStudentaNaPredmet;

public class PredmetController {
	public static PredmetController instance = null;
	
	public static PredmetController getInstance() {
		if (instance == null) {
			instance = new PredmetController();
		} 
		return instance;
	}
	private PredmetController() {}
	
	public boolean dodajPredmet() {
		if(DijalogDodajPredmet.sifraP.getText().isEmpty() || DijalogDodajPredmet.imeP.getText().isEmpty()
				|| DijalogDodajPredmet.godina.getText().isEmpty() || DijalogDodajPredmet.semestar.getText().isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Niste popunili sva obavezna polja.\nPolja sa * su obavezna.", "GRESKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		String sifra = DijalogDodajPredmet.sifraP.getText();
		String ime = DijalogDodajPredmet.imeP.getText();
		int godina = Integer.parseInt(DijalogDodajPredmet.godina.getText());
		int semestar = Integer.parseInt(DijalogDodajPredmet.semestar.getText());
		
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
			JOptionPane.showMessageDialog(null, "Student ne postoji u bazi.\nProverite da li ste uneli dobro broj indeksa.", "GRESKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		BazaPredmeta.getInstance().dodajStudenta(p.getSifra(), s);
		PredmetiJTable.refresh();
		
		return true;
	}
	
	public void brisanjeSaPredmeta(Predmet p, String bri) {
		Student s = new Student();
		for (Student st : p.getStudenti()) {
			if (st.getBri().equals(bri))
				s = st;
		}
		BazaPredmeta.getInstance().obrisiStudenta(p, s);
		PredmetiJTable.refresh();
		
	}
	
}
