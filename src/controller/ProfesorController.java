package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import model.BazaPredmeta;
import model.BazaProfesora;
import model.BazaStudenata;
import model.Predmet;
import model.Profesor;
import model.Profesor.Titula;
import model.Profesor.Zvanje;
import view.ProfesoriJTable;
import view.StudentJTable;
import view.Toolbar;
import view.dijalozi.DijalogDodajProfesora;
import view.dijalozi.DijalogIzmeniProfesora;
import view.dijalozi.DijalogStudent;

public class ProfesorController {
	public static ProfesorController instance = null;
	public static int flag;
	
	public static ProfesorController getInstance() {
		if(instance == null) {
			instance = new ProfesorController();
		}
		
		return instance;
	}
	
	public ProfesorController() {}
	
	public boolean DodajProfesora() {
		
		if(ProfesoriJTable.curr_row < 0) {
			return false;
		}
	
		Profesor prof = new Profesor();
		
		if(DijalogDodajProfesora.imeP.getText().isEmpty() || DijalogDodajProfesora.przP.getText().isEmpty() || DijalogDodajProfesora.adresaP.getText().isEmpty() 
				|| DijalogDodajProfesora.brlk.getText().isEmpty() || DijalogDodajProfesora.brTel.getText().isEmpty() || DijalogDodajProfesora.datRP.getText().isEmpty() ||
				DijalogDodajProfesora.email.getText().isEmpty() || DijalogDodajProfesora.kancelarija.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Niste popunili sva obazvezna polja."
					+ "\nPolja sa * su obavezna.", "GRESKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		String ime = DijalogDodajProfesora.imeP.getText();
		prof.setIme(ime);
	
		String prz = DijalogDodajProfesora.przP.getText();
		prof.setPrezime(prz);
	
		String adresa = DijalogDodajProfesora.adresaP.getText();
		prof.setAdresa(adresa);
		
		String brlk = DijalogDodajProfesora.brlk.getText();
		prof.setBrlk(brlk);
		String brt = DijalogDodajProfesora.brTel.getText();
		prof.setBr_tel(brt);
			
		try {
			Date datum = new Date();
			datum = parseDate(DijalogDodajProfesora.datRP.getText());
			prof.setDatumr(datum);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Greska prilikom unosa datuma.\nFormat datuma je 'dd.mm.yyyy.'", "GRESKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
		String email = DijalogDodajProfesora.email.getText();
		prof.setEmail(email);
		
		
		String kancelarija = DijalogDodajProfesora.kancelarija.getText();
		prof.setKancelarija(kancelarija);
	
		String zvanje = DijalogDodajProfesora.zvanje.getSelectedItem().toString();
		if(zvanje.equals("Asistent")) {
			prof.setZvanje(Zvanje.Asistent);
		} else if (zvanje.equals("Saradnik u nastavi")) {
			prof.setZvanje(Zvanje.Saradnik);
		} else if (zvanje.equals("Vanredni profesor")) {
			prof.setZvanje(Zvanje.VProfesor);
		} else if (zvanje.equals("Redovni profesor")) {
			prof.setZvanje(Zvanje.RProfesor);
		} else {
			prof.setZvanje(Zvanje.Docent);
		}
			
		String titula = DijalogDodajProfesora.titula.getSelectedItem().toString();
			
		if (titula.equals("Doktor")) {
			prof.setTitula(Titula.Dr);
		} else {
			prof.setTitula(Titula.Ms);
		}
		
		for (Profesor p : BazaProfesora.getInstance().getProfesori())
			if (prof.getBrlk().equals(p.getBrlk())) {
				JOptionPane.showMessageDialog(null, "Profesor sa datim brojem licne karte vec postoji.\nProverite Vas unos", "GRESKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		
		BazaProfesora.getInstance().dodajProfesora(prof.getIme(), prof.getPrezime(), prof.getDatumr(), prof.getAdresa(), prof.getBr_tel(), prof.getEmail(), prof.getKancelarija(), prof.getBrlk(), prof.getZvanje(), prof.getTitula());
		ProfesoriJTable.refresh();
		return true;
	}
	
	public void izbrisiProfesora(int rowSelectedIndex) {
		if(rowSelectedIndex < 0) {
			return;
		}
		
		Profesor prof = BazaProfesora.getInstance().getRow(rowSelectedIndex);
		BazaProfesora.getInstance().izbrisiProfesora(prof.getBrlk());
		ProfesoriJTable.refresh();
		
	}
	
	public boolean izmeniProfesora() {
		if(ProfesoriJTable.curr_row < 0) {
			return false;
		}
		
		Profesor prof = BazaProfesora.getInstance().getRow(ProfesoriJTable.curr_row);
		
		if(DijalogIzmeniProfesora.imeP.getText().isEmpty() || DijalogIzmeniProfesora.przP.getText().isEmpty() || DijalogIzmeniProfesora.adresaP.getText().isEmpty() 
				|| DijalogIzmeniProfesora.brlk.getText().isEmpty() || DijalogIzmeniProfesora.brTel.getText().isEmpty() || DijalogIzmeniProfesora.datRP.getText().isEmpty() ||
				DijalogIzmeniProfesora.email.getText().isEmpty() || DijalogIzmeniProfesora.kancelarija.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Niste popunili sva obazvezna polja."
					+ "\nPolja sa * su obavezna.", "GRESKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		String ime = DijalogIzmeniProfesora.imeP.getText();
		prof.setIme(ime);
		
		String prz = DijalogIzmeniProfesora.przP.getText();
		prof.setPrezime(prz);
		
		String adresa = DijalogIzmeniProfesora.adresaP.getText();
		prof.setAdresa(adresa);
		
		String brlk = DijalogIzmeniProfesora.brlk.getText();
		prof.setBrlk(brlk);
		
		String brt = DijalogIzmeniProfesora.brTel.getText();	
		prof.setBr_tel(brt);
		
		try {
			Date datum = new Date();
			datum = parseDate(DijalogIzmeniProfesora.datRP.getText());
			prof.setDatumr(datum);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Greska prilikom unosa datuma.\\nFormat datuma je 'dd.mm.yyyy.'", "GRESKA", JOptionPane.ERROR_MESSAGE);
		}
		
		String email = DijalogIzmeniProfesora.email.getText();
		prof.setEmail(email);
		String kancelarija = DijalogIzmeniProfesora.kancelarija.getText();
		prof.setKancelarija(kancelarija);
			

		String zvanje = DijalogIzmeniProfesora.zvanje.getSelectedItem().toString();
		if(zvanje.equals("Asistent")) {
			prof.setZvanje(Zvanje.Asistent);
		} else if (zvanje.equals("Saradnik u nastavi")) {
			prof.setZvanje(Zvanje.Saradnik);
		} else if (zvanje.equals("Vanredni profesor")) {
			prof.setZvanje(Zvanje.VProfesor);
		} else if (zvanje.equals("Redovni profesor")) {
			prof.setZvanje(Zvanje.RProfesor);
		} else {
			prof.setZvanje(Zvanje.Docent);
		}
			
		String titula = DijalogIzmeniProfesora.titula.getSelectedItem().toString();
			
		if (titula.equals("Doktor")) {
			prof.setTitula(Titula.Dr);
		} else {
			prof.setTitula(Titula.Ms);
		}
		
	
		BazaProfesora.getInstance().izmeniProfesora(prof.getIme(), prof.getPrezime(), prof.getDatumr(), prof.getAdresa(), prof.getBr_tel(), prof.getEmail(), prof.getKancelarija(), prof.getBrlk(), prof.getZvanje(), prof.getTitula());
		
		ProfesoriJTable.refresh();
		
		return true;
	}
	
	public void pretragaProfesora() {
		if(Toolbar.pretraga.getText().isEmpty()) {
			flag = 0;
			BazaProfesora.getInstance().pretraziProfesora("");
		}
		else {
			flag = 1;
			BazaProfesora.getInstance().pretraziProfesora(Toolbar.pretraga.getText());
		}
		ProfesoriJTable.refresh();
	}
	
	
	 public static Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("dd.MM.yyyy.").parse(date);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	     
	  }

	public void obrisiPredmet(Profesor p, String sifra) {
		Predmet pp = new Predmet();
		String[] sif = sifra.split(" ");
		
		for (Predmet pred : p.getPredmeti()) {
			if (pred.getSifra().equals(sif[0]))
				pp = pred;
		}
		
		BazaPredmeta.getInstance().obrisiProfesora(p, pp);
		ProfesoriJTable.refresh();
		
	}
}
