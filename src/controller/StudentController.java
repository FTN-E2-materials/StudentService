package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import model.BazaPredmeta;
import model.BazaStudenata;
import model.Predmet;
import model.Student;
import model.Student.Status;
import view.PredmetiJTable;
import view.StudentJTable;
import view.Toolbar;
import view.dijalozi.DijalogIzmeniS;
import view.dijalozi.DijalogStudent;

public class StudentController {

	

// TODO: Implementirati funkcije za dijaloge
// za dodavanje, brisanje i izmenu studenta
	
	public static StudentController instance = null;
	public static  int flag;
	public static StudentController getInstance() {
		if(instance == null) {
			instance = new StudentController();
		}	
		return instance;
	}
	
	private StudentController() {}
	
	public boolean dodajStudenta() {
		Student st = new Student();
		// provera ispravnosti unetog stringa 
		
		if(!proveriImePrz(DijalogStudent.imeS.getText().trim()) || !proveriImePrz(DijalogStudent.przS.getText().trim())
				|| !proveriEmail(DijalogStudent.email.getText().trim()) || !proveriBrTel(DijalogStudent.brtel.getText().trim()) || 
				!proveriAdresu(DijalogStudent.adresa.getText().trim())) {
			return false;
		}	
		
		// provera da li student vec postoji u bazi
		
		for (Student s : BazaStudenata.getInstance().getStudenti()) {
			if (s.getBri().equals(DijalogStudent.briS.getText().trim())) {
				JOptionPane.showMessageDialog(null, "Student sa datim brojem indeksa već postoji.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}				
		st.setIme(DijalogStudent.imeS.getText().trim());
		st.setPrezime(DijalogStudent.przS.getText().trim());			
		st.setAdresa(DijalogStudent.adresa.getText().trim());
		st.setBri(DijalogStudent.briS.getText().trim());
		st.setBr_tel(DijalogStudent.brtel.getText().trim());
		st.setEmail(DijalogStudent.email.getText().trim());
		
		Date datumR = new Date();
			
		datumR = parseDate(DijalogStudent.datRodj.getText().trim());
		if (datumR == null) {
			return false;
		}
			
		Date datumU = new Date();
	
		datumU = parseDate(DijalogStudent.datumU.getText().trim());
		if (datumU == null) {
			return false;
		} else {
			if(datumR.compareTo(datumU) == 0 || datumR.compareTo(datumU) > 0) {
				JOptionPane.showMessageDialog(null, "Datum upisa mora biti posle datuma rođenja.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
				return false;
			} else
				st.setDatumr(datumR);
				st.setDatum_upisa(datumU);
		}
		

		int ind = DijalogStudent.godStud.getSelectedIndex();
		st.setGodina_stud(ind + 1);
		
		// mogucnost da student prve godine nema prosek ako se unese / ili 0.0
		// u ostalim slucajevima prosek mora biti izmedju 6 i 10 (mogu i studenti prve godine)
		
		if (st.getGodina_stud() == 1) {
			if(DijalogStudent.prosOc.getText().trim().equals("/") || DijalogStudent.prosOc.getText().trim().equals("0.0")) {
				System.out.println("waat");
				st.setProsek(0.0);
			} else {
				if(proveriProsek(DijalogStudent.prosOc.getText().trim())) {
					try {
						double pros = Double.parseDouble(DijalogStudent.prosOc.getText().trim());
						st.setProsek(pros);
					}
					catch (Exception e) {
						System.out.println(e.getMessage());
						JOptionPane.showMessageDialog(null, "Greška prilikom parsiranja proseka.\nProverite ponovo Vaš unos.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				} else {
					return false;
				}
			}
			
		} else {
			if(proveriProsek(DijalogStudent.prosOc.getText().trim())) {
				try {
					double pros = Double.parseDouble(DijalogStudent.prosOc.getText().trim());
					st.setProsek(pros);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					JOptionPane.showMessageDialog(null, "Greška prilikom parsiranja proseka.\nProverite ponovo Vaš unos.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} else {
				return false;
			}
		}
		
		
		if(DijalogStudent.budzet.isSelected()) {
			st.setStatus(Status.B);
		}
		else if (DijalogStudent.samofin.isSelected()) {
			st.setStatus(Status.S);
		} else {
			JOptionPane.showMessageDialog(null, "Morate označiti status studenta!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		BazaStudenata.getInstance().dodajStudenta(st.getBri(), st.getIme(), st.getPrezime(), st.getDatumr(), st.getAdresa(), st.getBr_tel(), st.getEmail(), st.getDatum_upisa(), st.getGodina_stud(), st.getStatus(), st.getProsek());
		StudentJTable.refresh();
			
		return true;
		
	}

	public void izbrisiStudenta() {

		Student student = BazaStudenata.getInstance().getRow(StudentJTable.curr_row);
		BazaStudenata.getInstance().izbrisiStudenta(student.getBri());
		BazaPredmeta.getInstance().obrisiStudentaPosle(student.getBri());
		StudentJTable.refresh();
	}
	
	public boolean izmeniStudenta() {
		// provera ispravnosti unetog stringa
		
		if(!proveriUnosIndeksa(DijalogIzmeniS.briS.getText().trim()) || !proveriImePrz(DijalogIzmeniS.imeS.getText().trim()) || !proveriImePrz(DijalogIzmeniS.przS.getText().trim())
				|| !proveriEmail(DijalogIzmeniS.email.getText().trim()) || !proveriBrTel(DijalogIzmeniS.brtel.getText().trim()) || !proveriAdresu(DijalogIzmeniS.adresa.getText().trim())) {
			return false;
		}
		
		Student st = BazaStudenata.getInstance().getRow(StudentJTable.curr_row);
		
		st.setIme(DijalogIzmeniS.imeS.getText().trim());
		st.setPrezime(DijalogIzmeniS.przS.getText().trim());			
		st.setAdresa(DijalogIzmeniS.adresa.getText().trim());
		
		st.setBr_tel(DijalogIzmeniS.brtel.getText().trim());
		st.setEmail(DijalogIzmeniS.email.getText().trim());
		
		
		if (!st.getBri().equals(DijalogIzmeniS.briS.getText().trim())) {
			JOptionPane.showMessageDialog(null, "Nije dozvoljeno menjanje broja indeksa!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		Date datumR = new Date();
		
		
		datumR = parseDate(DijalogIzmeniS.datRodj.getText().trim());
		
		if (datumR == null) {
			return false;
		}
	
		Date datumU = new Date();
				
		datumU = parseDate(DijalogIzmeniS.datumU.getText().trim());
		
		if (datumU == null) {
			return false;
		} else {
			if(datumR.compareTo(datumU) == 0 || datumR.compareTo(datumU) > 0) {
				JOptionPane.showMessageDialog(null, "Datum upisa mora biti posle datuma rođenja.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
				return false;
			} else
				st.setDatumr(datumR);
				st.setDatum_upisa(datumU);
		}
				
		int ind = DijalogIzmeniS.godStud.getSelectedIndex();
		st.setGodina_stud(ind + 1);

		if (st.getGodina_stud() == 1) {
			if(DijalogIzmeniS.prosOc.getText().trim().equals("/") || DijalogIzmeniS.prosOc.getText().trim().equals("0.0")) {
				System.out.println("waat");
				st.setProsek(0.0);
			} else {
				if(proveriProsek(DijalogIzmeniS.prosOc.getText().trim())) {
					try {
						double pros = Double.parseDouble(DijalogIzmeniS.prosOc.getText().trim());
						st.setProsek(pros);
					}
					catch (Exception e) {
						System.out.println(e.getMessage());
						JOptionPane.showMessageDialog(null, "Greška prilikom parsiranja proseka.\nProverite ponovo Vaš unos.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				} else {
					return false;
				}
			}
			
		} else {
			if(proveriProsek(DijalogIzmeniS.prosOc.getText().trim())) {
				try {
					double pros = Double.parseDouble(DijalogIzmeniS.prosOc.getText().trim());
					st.setProsek(pros);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					JOptionPane.showMessageDialog(null, "Greška prilikom parsiranja proseka.\nProverite ponovo Vaš unos.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} else {
				return false;
			}
		}
		
		if(DijalogIzmeniS.budzet.isSelected()) {
			st.setStatus(Status.B);
		}
		else if (DijalogIzmeniS.samofin.isSelected()) {
			st.setStatus(Status.S);
		} else {
			JOptionPane.showMessageDialog(null, "Morate označiti status studenta!", "GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		BazaStudenata.getInstance().izmeniStudenta(st.getBri(), st.getIme(), st.getPrezime(), st.getDatumr(), st.getAdresa(), st.getBr_tel(), st.getDatum_upisa(), st.getGodina_stud(),  st.getEmail(), st.getStatus(), st.getProsek());
		StudentJTable.refresh();
		return true;
		
	}
	public void pretraziStudenta() {	
		if(Toolbar.pretraga.getText().trim().isEmpty()) {
			flag = 0;
			BazaStudenata.getInstance().pretraziStudenta("");
		}
		else {			
			flag = 1;
			BazaStudenata.getInstance().pretraziStudenta(Toolbar.pretraga.getText().trim());
		}
		
		StudentJTable.refresh();
	}

	public void obrisiPredmet(Student s, String text) {
		Predmet p = new Predmet();
		
		String[] sifra = text.split(" ");
		
		for (Predmet pred : s.getPredmeti()) {
			if (pred.getSifra().equals(sifra[0])) {
				p = pred;
			}
		}
		
		BazaStudenata.getInstance().obrisiPredmet(p, s.getBri());
		BazaPredmeta.getInstance().obrisiStudenta(p, s.getBri());
	}
	
	
	// -------------------------------------------
	// PROVERE
	
	 public static Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("dd.MM.yyyy.").parse(date);
	     } catch (Exception e) {
	    	 System.out.println(e.getMessage());
	    	 JOptionPane.showMessageDialog(null, "Greška prilikom parsiranja datuma.\nFormat je 'dd.MM.yyyy'.", "GREŠKA", JOptionPane.ERROR_MESSAGE);
	         return null;
	     }   
	  }
	 
	private boolean proveriUnosIndeksa(String text) {
		if(!text.contains("%") || !text.contains("&") || !text.contains("*") || !text.contains("#")) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Greška prilikom unosa indeksa.\n","GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	
	}
	
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
	
	private boolean proveriProsek(String text) {
		
		String patternString = "[1-9][0-9]?[.][0-9]{1,2}";

        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(text);
		boolean matches = matcher.matches();
		
		if(matches) {
			if (Double.parseDouble(text) <= 10.00 && Double.parseDouble(text) >= 6.00) {
				System.out.println("waaat");
				return true;
			}
			else {
				JOptionPane.showMessageDialog(null, "Greška prilikom unosa proseka.\nProsek može biti između 6 i 10.","GREŠKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Greška prilikom unosa proseka.\nFormat proseka je xx.xx gde su x brojevi.","GREŠKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}
