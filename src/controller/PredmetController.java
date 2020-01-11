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
		
		// provera popunjenosti obaveznih polja
		
		if(DijalogDodajPredmet.sifraP.getText().trim().isEmpty() || DijalogDodajPredmet.imeP.getText().trim().isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Niste popunili sva obavezna polja.\nPolja sa * su obavezna.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	
		String sifra = DijalogDodajPredmet.sifraP.getText().trim();
		
		for (Predmet p : BazaPredmeta.getInstance().getPredmeti()) {
			if (p.getSifra().equals(sifra)) {
				JOptionPane.showMessageDialog(null, "Već postoji predmet sa datom šifrom!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		String ime = DijalogDodajPredmet.imeP.getText().trim();
		int godina = DijalogDodajPredmet.godina.getSelectedIndex() + 1;
		int semestar = DijalogDodajPredmet.semestar.getSelectedIndex() + 1;
		
		// semestar mora biti odgovarajuci sa godinom 
		
		if (godina == 1) {
			if (semestar != 1 && semestar != 2) {
				JOptionPane.showMessageDialog(null, "Ne podudaraju se semestri sa godinom, proverite ponovo.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else if (godina == 2) {
			if (semestar != 3 && semestar != 4) {
				JOptionPane.showMessageDialog(null, "Ne podudaraju se semestri sa godinom, proverite ponovo.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else if (godina == 3) {
			if (semestar != 5 && semestar != 6) {
				JOptionPane.showMessageDialog(null, "Ne podudaraju se semestri sa godinom, proverite ponovo.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			if (semestar != 7 && semestar != 8) {
				JOptionPane.showMessageDialog(null, "Ne podudaraju se semestri sa godinom, proverite ponovo.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		
		boolean flag = false;
		// ako je polje za dodavanje profesora popunjeno
		// proveri da li profesor postoji
		// i proveri da li je asistent
		if(!DijalogDodajPredmet.profesor.getText().trim().isEmpty()) {
			for (Profesor p : BazaProfesora.getInstance().getProfesori()) {
				if (p.getBrlk().equals(DijalogDodajPredmet.profesor.getText())) {
					if (p.getZvanje() == Zvanje.Asistent) {
						// ne moze biti asistent
						JOptionPane.showMessageDialog(null, "Izabrali ste asistenta. Asistent ne može biti predmetni profesor.\nProverite da li ste uneli dobro broj lične karte.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
						return false;
					}
					flag = true;
					break;
				}
			}
		} else {
			BazaPredmeta.getInstance().dodajPredmet(sifra, ime, godina, semestar);
			PredmetiJTable.refresh();
			return true;
		}
	
		if(!flag) {
			JOptionPane.showMessageDialog(null, "Profesor ne postoji u bazi.\nProverite da li ste uneli dobro broj lične karte.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			BazaPredmeta.getInstance().dodajPredmet(sifra, ime, godina, semestar);
			BazaPredmeta.getInstance().dodajProfesora(DijalogDodajPredmet.profesor.getText(), sifra);
			PredmetiJTable.refresh();
			return true;
		}
		
	
	}
	
	public boolean dodajStudentaNaPredmet() {
		if(DodajStudentaNaPredmet.bri.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Niste popunili sva obavezna polja.\nPolja sa * su obavezna.", "GRESKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String bri = DodajStudentaNaPredmet.bri.getText().trim();
		
		Predmet p = BazaPredmeta.getInstance().getRow(PredmetiJTable.curr_row);
		Student s = null;
		
		for (Student st : BazaStudenata.getInstance().getStudenti()) {
			if (st.getBri().equals(bri)) {
				s = st;
			}
		}
		
		if (s == null) {
			JOptionPane.showMessageDialog(null, "Student ne postoji u bazi.\nProverite da li ste uneli dobro broj indeksa.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(s.getGodina_stud() != p.getGodina()) {
			JOptionPane.showMessageDialog(null, "Student ne pohađa datu godinu studija.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		for (Student st : p.getStudenti()) {
			if (st.getBri().equals(bri)) {
				JOptionPane.showMessageDialog(null, "Student već pohađa dati predmet.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		BazaPredmeta.getInstance().dodajStudenta(p.getSifra(), s);
		BazaStudenata.getInstance().dodajPredmet(s, p);
		PredmetiJTable.refresh();		
		return true;
	}
	
	public void brisanjeSaPredmeta(Predmet p, String bri) {

		BazaPredmeta.getInstance().obrisiStudenta(p, bri);
		BazaStudenata.getInstance().obrisiPredmet(p, bri);
		
	}
	
	public boolean dodajProfesoraNaPredmet() {
		// provera da li je popunjeno polje 
		
		if (DodajProfesoraNaPredmet.brlk.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Niste uneli broj lične karte.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		Predmet p = BazaPredmeta.getInstance().getRow(PredmetiJTable.curr_row);
		String brlk = DodajProfesoraNaPredmet.brlk.getText().trim();
		
		Profesor pr = null;
		for (Profesor prof : BazaProfesora.getInstance().getProfesori()) {
			if (prof.getBrlk().equals(brlk)) {
				pr = prof;
			}
		}
		// provera da li dati profesor postoji u bazi
		
		if (pr == null) {
			JOptionPane.showMessageDialog(null, "Profesor sa datim brojem lične karte ne postoji u bazi.\nProverite da li ste uneli dobro broj lične karte.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		// ako je asistent, ne moze biti profesor
		
		if (pr.getZvanje() == Zvanje.Asistent) {
			JOptionPane.showMessageDialog(null, "Izabrali ste asistenta. Asistent ne može biti predmetni profesor.\nProverite da li ste uneli dobro broj lične karte.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
				
		BazaPredmeta.getInstance().dodajProfesora(brlk, p.getSifra());	
		BazaProfesora.getInstance().dodajPredmet(brlk, p);
		PredmetiJTable.refresh();
		return true;
	}
	
	public boolean izmeniPredmet() {
		if(DijalogIzmeniPredmet.sifraP.getText().trim().isEmpty() || DijalogIzmeniPredmet.imeP.getText().trim().isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Niste popunili sva obavezna polja.\nPolja sa * su obavezna.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		Predmet pred = BazaPredmeta.getInstance().getRow(PredmetiJTable.curr_row);
		int prvobitnaGodina = pred.getGodina();
		
		String sifra = DijalogIzmeniPredmet.sifraP.getText().trim();
		
		if (!sifra.equals(pred.getSifra())) {
			JOptionPane.showMessageDialog(null, "Ne možete menjati šifru predmeta!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		String ime = DijalogIzmeniPredmet.imeP.getText();
		int godina = DijalogIzmeniPredmet.godina.getSelectedIndex() + 1;
		int semestar = DijalogIzmeniPredmet.semestar.getSelectedIndex() + 1;
		
		if (godina == 1) {
			if (semestar != 1 && semestar != 2) {
				JOptionPane.showMessageDialog(null, "Ne podudaraju se semestri sa godinom, proverite ponovo.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else if (godina == 2) {
			if (semestar != 3 && semestar != 4) {
				JOptionPane.showMessageDialog(null, "Ne podudaraju se semestri sa godinom, proverite ponovo.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else if (godina == 3) {
			if (semestar != 5 && semestar != 6) {
				JOptionPane.showMessageDialog(null, "Ne podudaraju se semestri sa godinom, proverite ponovo.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			if (semestar != 7 && semestar != 8) {
				JOptionPane.showMessageDialog(null, "Ne podudaraju se semestri sa godinom, proverite ponovo.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		Predmet predmet = new Predmet(sifra, ime, semestar, godina);
		boolean flag = false;
		// ako je polje popunjeno za dodavanje profesora (jer nije obavezno)
		if(!DijalogIzmeniPredmet.profesor.getText().trim().isEmpty()) {
			// proveri da li postoji
			for (Profesor p : BazaProfesora.getInstance().getProfesori()) {
				if (p.getBrlk().equals(DijalogIzmeniPredmet.profesor.getText().trim())) {
					if (p.getZvanje() == Zvanje.Asistent) {
						// ne moze biti asistent
						JOptionPane.showMessageDialog(null, "Izabrali ste asistenta. Asistent ne može biti predmetni profesor.\nProverite da li ste uneli dobro broj lične karte.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
						return false;
					}
					flag = true;
					break;
				}
			}
			// ako polje nije popunjeno, samo dodaj predmet sa null vrednoscu za profesora
		} else {
			predmet.setPred_prof(null);
			BazaPredmeta.getInstance().izmeniPredmet(predmet);
			if (pred.getGodina() != prvobitnaGodina) {
				BazaStudenata.getInstance().obrisiSvePosleIzmene(pred.getSifra());
				BazaPredmeta.getInstance().obrisiSveStudente(pred.getSifra());
			}
			PredmetiJTable.refresh();
			return true;
		}
		
		if(!flag) {
			JOptionPane.showMessageDialog(null, "Profesor ne postoji u bazi.\nProverite da li ste uneli dobro broj lične karte.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			BazaPredmeta.getInstance().izmeniPredmet(predmet);
			BazaPredmeta.getInstance().dodajProfesora(DijalogIzmeniPredmet.profesor.getText(), sifra);
			if (pred.getGodina() != prvobitnaGodina) {
				BazaStudenata.getInstance().obrisiSvePosleIzmene(pred.getSifra());
				BazaPredmeta.getInstance().obrisiSveStudente(pred.getSifra());
			}
			PredmetiJTable.refresh();
			return true;
		}
		
	}

	public void obrisiPredmet() {
		Predmet p = BazaPredmeta.getInstance().getRow(PredmetiJTable.curr_row);
		BazaPredmeta.getInstance().obrisiPredmet(p.getSifra());
		BazaStudenata.getInstance().obrisiDatiPredmetSvimStudentima(p.getSifra());
		BazaProfesora.getInstance().obrisiPredmetProfesorima(p.getSifra());
		PredmetiJTable.refresh();
		StudentJTable.refresh();
	}
	
	public void pretraziPredmet() {
		if (Toolbar.pretraga.getText().trim().isEmpty()) {
			// flagovi sluze za prikazivanje odgovarajuce tabele
			// filtrirane studente ili celu listu studenata
			
			flag = 0;
			BazaPredmeta.getInstance().pretraziPredmet("");
		} else {
			flag = 1;
			BazaPredmeta.getInstance().pretraziPredmet(Toolbar.pretraga.getText().trim());
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
	

