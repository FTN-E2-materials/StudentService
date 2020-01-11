package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import model.BazaPredmeta;
import model.BazaProfesora;
import model.Predmet;
import model.Profesor;
import model.Profesor.Titula;
import model.Profesor.Zvanje;
import view.PredmetiJTable;
import view.ProfesoriJTable;
import view.Toolbar;
import view.dijalozi.DijalogDodajProfesora;
import view.dijalozi.DijalogIzmeniProfesora;

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
		
		Profesor prof = new Profesor();
		
		if (!proveriBrlk(DijalogDodajProfesora.brlk.getText()) || !proveriImePrz(DijalogDodajProfesora.imeP.getText()) || !proveriImePrz(DijalogDodajProfesora.przP.getText()) 
				|| !proveriAdresu(DijalogDodajProfesora.adresaP.getText()) || !proveriBrTel(DijalogDodajProfesora.brTel.getText()) || !proveriEmail(DijalogDodajProfesora.email.getText())) {
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
			
	
		Date datum = new Date();
		datum = parseDate(DijalogDodajProfesora.datRP.getText());
		
		if (datum == null) {
			return false;
		} else  {
			prof.setDatumr(datum);
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
			
		if (titula.equals("Doktor profesor")) {
			prof.setTitula(Titula.ProfDr);
		} else if (titula.equals("Doktor")) {
			prof.setTitula(Titula.Dr);
		} else {
			prof.setTitula(Titula.Ms);
		}
		
		for (Profesor p : BazaProfesora.getInstance().getProfesori())
			if (prof.getBrlk().equals(p.getBrlk())) {
				JOptionPane.showMessageDialog(null, "Profesor sa datim brojem lične karte vec postoji.\nProverite Vas unos", "GREŠKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		
		BazaProfesora.getInstance().dodajProfesora(prof.getIme(), prof.getPrezime(), prof.getDatumr(), prof.getAdresa(), prof.getBr_tel(), prof.getEmail(), prof.getKancelarija(), prof.getBrlk(), prof.getZvanje(), prof.getTitula());
		ProfesoriJTable.refresh();
		return true;
	}
	
	public void izbrisiProfesora(int rowSelectedIndex) {

		Profesor prof = BazaProfesora.getInstance().getRow(rowSelectedIndex);
		BazaProfesora.getInstance().izbrisiProfesora(prof.getBrlk());
		BazaPredmeta.getInstance().obrisiProfesoraPosle(prof.getBrlk());
		ProfesoriJTable.refresh();		
	}
	
	public boolean izmeniProfesora() {
		
		Profesor prof = BazaProfesora.getInstance().getRow(ProfesoriJTable.curr_row);
		
		if (!proveriBrlk(DijalogIzmeniProfesora.brlk.getText()) || !proveriImePrz(DijalogIzmeniProfesora.imeP.getText()) || !proveriImePrz(DijalogIzmeniProfesora.przP.getText()) 
				|| !proveriAdresu(DijalogIzmeniProfesora.adresaP.getText()) || !proveriBrTel(DijalogIzmeniProfesora.brTel.getText()) || !proveriEmail(DijalogIzmeniProfesora.email.getText())) {
			return false;
		}
		
		if (!prof.getBrlk().equals(DijalogIzmeniProfesora.brlk.getText())) {
			JOptionPane.showMessageDialog(null, "Ne možete menjati broj lične karte profesora.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
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
		
		Date datum = new Date();
		datum = parseDate(DijalogIzmeniProfesora.datRP.getText());
		
		if (datum == null) {
			return false;
		} else  {
			prof.setDatumr(datum);
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
			
		if (titula.equals("Doktor profesor")) {
			prof.setTitula(Titula.ProfDr);
		} else if (titula.equals("Doktor")) {
			prof.setTitula(Titula.Dr);
		}
		else {
			prof.setTitula(Titula.Ms);
		}
		
		BazaProfesora.getInstance().izmeniProfesora(prof.getIme(), prof.getPrezime(), prof.getDatumr(), prof.getAdresa(), prof.getBr_tel(), prof.getEmail(), prof.getKancelarija(), prof.getBrlk(), prof.getZvanje(), prof.getTitula());
		ProfesoriJTable.refresh();
		return true;
	}
	
	public void pretragaProfesora() {
		if(Toolbar.pretraga.getText().trim().isEmpty()) {
			flag = 0;
			BazaProfesora.getInstance().pretraziProfesora("");
		}
		else {
			flag = 1;
			BazaProfesora.getInstance().pretraziProfesora(Toolbar.pretraga.getText().trim());
		}
		ProfesoriJTable.refresh();
	}
	

	public void obrisiPredmet(Profesor p, String sifra) {
		Predmet pp = new Predmet();
		String[] sif = sifra.split(" ");
		
		for (Predmet pred : p.getPredmeti()) {
			if (pred.getSifra().equals(sif[0]))
				pp = pred;
		}
		
		BazaPredmeta.getInstance().obrisiProfesora(p, pp);
		BazaProfesora.getInstance().obrisiPredmet(p, pp);
		PredmetiJTable.refresh();
		
	}
	
	// ------------------------------------
	// PROVERE

	private boolean proveriImePrz(String text) {
		boolean matches = false;
		boolean matches2 = false;
		
		if (text.contains(" ")) {
			String[] delovi = text.split(" ");
			for (String pom : delovi) {
				matches = pom.matches("\\p{IsLatin}+"); 
				matches2 = pom.matches("^\\p{IsCyrillic}+$");
			}
		} else if (text.contains("-")) {
			String[] delovi = text.split("-");
			for (String pom : delovi) {
				matches = pom.matches("\\p{IsLatin}+"); 
				matches2 = pom.matches("^\\p{IsCyrillic}+$");
			}
		} else {
			matches = text.matches("\\p{IsLatin}+"); 
			matches2 = text.matches("^\\p{IsCyrillic}+$");
		}
		
		if(matches || matches2) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Greška prilikom unosa imena ili prezimena.\nSamo karakteri su dozvoljeni.","GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}	
	}

	 public static Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("dd.MM.yyyy.").parse(date);
	     } catch (Exception e) {
	    	 JOptionPane.showMessageDialog(null, "Greška prilikom unosa datuma.\\nFormat datuma je 'dd.mm.yyyy.'", "GREŠKA", JOptionPane.ERROR_MESSAGE);
	         return null;
	     }
	     
	  }
	private boolean proveriEmail(String text) {
		String patternString = "[A-Z0-9a-z._%+-]+";
		//[A-Za-z0-9.-]+\\\\.([A-Za-z0-9.-]+\\\\\\\\.[A-Za-z0-9.-]+\\\\\\\\.)?[A-Za-z]{2,64}";
		String[] splitMail;
		String[] splitEnd;
		
		try {
		splitMail = text.split("@");
		splitEnd = splitMail[1].split("\\.");
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Greška prilikom unosa elektronske pošte.\n","GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String patternString2 = "[A-Z0-9a-z._%+-]+";
		
        Pattern pattern = Pattern.compile(patternString);
        Pattern pattern2 = Pattern.compile(patternString2);

        Matcher matcher1 = pattern.matcher(splitMail[0]);
       
		
        boolean matches1 = matcher1.matches();	
		boolean matches2 = false;
		
		for(String delovi : splitEnd) {
			 Matcher matcher2 = pattern2.matcher(delovi);
			 matches2 = matcher2.matches();
		}
		
		if(matches1 && matches2) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Greška prilikom unosa elektronske pošte.\n","GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}	
	}
	
	private boolean proveriBrlk(String text) {
		String patternString = "[A-Za-z0-9]+";

        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(text);
		boolean matches = matcher.matches();
		
		if(matches) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Greška prilikom unosa broja lične karte.\nSamo brojevi i slova su dozvoljeni.","GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	private boolean proveriAdresu(String text) {
		
		if(!text.contains("%") || !text.contains("&") || !text.contains("*") || !text.contains("#")) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Greška prilikom unosa adrese.\nNeispravna adresa.","GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
	}
	
	private boolean proveriBrTel(String text) {
		String patternString = "[0-9][0-9/-]+";

        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(text);
		boolean matches = matcher.matches();
		
		if(matches) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Greška prilikom unosa broja telefona.\nSamo brojevi su dozvoljeni (minimalno 8).","GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
