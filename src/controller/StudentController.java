package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import model.BazaPredmeta;
import model.BazaStudenata;
import model.Predmet;
import model.Student;
import model.Student.Status;
import view.MainFrame;
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
		// TODO: Implementirati da uzima podatke iz dijaloga : VAZI ZA SVE FUNKCIJE
		
		// Da li ovo treba u Bazi Studenata da se nalazi ? 
		
		Student st = new Student();
				
		if (DijalogStudent.imeS.getText().isEmpty() || DijalogStudent.przS.getText().isEmpty() || DijalogStudent.adresa.getText().isEmpty() || DijalogStudent.briS.getText().isEmpty() ||
				DijalogStudent.brtel.getText().isEmpty() || DijalogStudent.email.getText().isEmpty() || DijalogStudent.datRodj.getText().isEmpty() || DijalogStudent.datumU.getText().isEmpty()) {
				
			JOptionPane.showMessageDialog(null, "Niste popunili sva obazvezna polja."
					+ "\nPolja sa * su obavezna.", "GRESKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
			
		for (Student s : BazaStudenata.getInstance().getStudenti()) {
			if (s.getBri().equals(DijalogStudent.briS.getText())) {
				JOptionPane.showMessageDialog(null, "Student sa datim brojem indeksa vec postoji.", "GRESKA", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
					
		st.setIme(DijalogStudent.imeS.getText());
		st.setPrezime(DijalogStudent.przS.getText());			
		st.setAdresa(DijalogStudent.adresa.getText());
		st.setBri(DijalogStudent.briS.getText());
		st.setBr_tel(DijalogStudent.brtel.getText());
		st.setEmail(DijalogStudent.email.getText());
			
		Date datumR = new Date();
		datumR = parseDate(DijalogStudent.datRodj.getText());
		st.setDatumr(datumR);
			
		Date datumU = new Date();
		datumU = parseDate(DijalogStudent.datumU.getText());
		st.setDatum_upisa(datumU);
		
		String godStud = DijalogStudent.godStud.getSelectedItem().toString();
		
		if(godStud.equals("I (prva)")) {
			st.setGodina_stud(1);
		}
		else if(godStud.equals("II (druga)")) {
			st.setGodina_stud(2);
		}
		else if(godStud.equals("III (treca)")) {
			st.setGodina_stud(3);
		}
		else 
			st.setGodina_stud(4);
		
		
		if(DijalogStudent.budzet.isSelected()) {
			st.setStatus(Status.B);
		}
		else {
			st.setStatus(Status.S);
		}
		
		try {
				double pros = Double.parseDouble(DijalogStudent.prosOc.getText());
				st.setProsek(pros);
		}
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Greska prilikom parsiranja proseka.\nProverite ponovo Vas unos.", "GRESKA", JOptionPane.ERROR_MESSAGE);
			
		}
		
		BazaStudenata.getInstance().dodajStudenta(st.getBri(), st.getIme(), st.getPrezime(), st.getDatumr(), st.getAdresa(), st.getBr_tel(), st.getEmail(), st.getDatum_upisa(), st.getGodina_stud(), st.getStatus(), st.getProsek());
		StudentJTable.refresh();
			
		return true;
		
	}
	
	public void izbrisiStudenta() {
		
		if(StudentJTable.curr_row < 0) {
			return;
		}
		
		Student student = BazaStudenata.getInstance().getRow(StudentJTable.curr_row);
		BazaStudenata.getInstance().izbrisiStudenta(student.getBri());
		StudentJTable.refresh();
	}
	
	public boolean izmeniStudenta() {
		if(StudentJTable.curr_row < 0) {
			return false;
		}
		
		if (DijalogIzmeniS.imeS.getText().isEmpty() || DijalogIzmeniS.przS.getText().isEmpty() || DijalogIzmeniS.adresa.getText().isEmpty() || DijalogIzmeniS.briS.getText().isEmpty() ||
				DijalogIzmeniS.brtel.getText().isEmpty() || DijalogIzmeniS.email.getText().isEmpty() || DijalogIzmeniS.datRodj.getText().isEmpty() || DijalogIzmeniS.datumU.getText().isEmpty()) {
				
			JOptionPane.showMessageDialog(null, "Niste popunili sva obazvezna polja."
					+ "\nPolja sa * su obavezna.", "GRESKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		Student st = BazaStudenata.getInstance().getRow(StudentJTable.curr_row);
		
		st.setIme(DijalogIzmeniS.imeS.getText());
		st.setPrezime(DijalogIzmeniS.przS.getText());			
		st.setAdresa(DijalogIzmeniS.adresa.getText());
		st.setBri(DijalogIzmeniS.briS.getText());
		st.setBr_tel(DijalogIzmeniS.brtel.getText());
		st.setEmail(DijalogIzmeniS.email.getText());
			
		Date datumR = new Date();
		datumR = parseDate(DijalogIzmeniS.datRodj.getText());
		st.setDatumr(datumR);
			
		Date datumU = new Date();
		datumU = parseDate(DijalogIzmeniS.datumU.getText());
		st.setDatum_upisa(datumU);
		
		String godStud = DijalogIzmeniS.godStud.getSelectedItem().toString();
		
		if(godStud.equals("I (prva)")) {
			st.setGodina_stud(1);
		}
		else if(godStud.equals("II (druga)")) {
			st.setGodina_stud(2);
		}
		else if(godStud.equals("III (treca)")) {
			st.setGodina_stud(3);
		}
		else 
			st.setGodina_stud(4);
		
		
		if(DijalogIzmeniS.budzet.isSelected()) {
			st.setStatus(Status.B);
		}
		else {
			st.setStatus(Status.S);
		}
		
		try {
			double pros = Double.parseDouble(DijalogIzmeniS.prosOc.getText());
			st.setProsek(pros);
		}
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Greska prilikom parsiranja proseka.\nProverite ponovo Vas unos.", "GRESKA", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		BazaStudenata.getInstance().izmeniStudenta(st.getBri(), st.getIme(), st.getPrezime(), st.getDatumr(), st.getAdresa(), st.getBr_tel(), st.getDatum_upisa(), st.getGodina_stud(),  st.getEmail(), st.getStatus(), st.getProsek());
		StudentJTable.refresh();
		return true;
		
	}
	public void pretraziStudenta() {
		
		if(Toolbar.pretraga.getText().isEmpty()) {
			flag = 0;
			BazaStudenata.getInstance().pretraziStudenta("");
		}
		else {			
			flag = 1;
			BazaStudenata.getInstance().pretraziStudenta(Toolbar.pretraga.getText());
		}
		
		StudentJTable.refresh();
	}
	
	 public static Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("dd.MM.yyyy").parse(date);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	     
	  }

	public void obrisiPredmet(Student s, String text) {
		Predmet p = new Predmet();
		
		String[] sifra = text.split(" ");
		
		for (Predmet pred : BazaPredmeta.getInstance().getPredmeti()) {
			if (pred.getSifra().equals(sifra[0]))
				p = pred;
		}
		
		BazaStudenata.getInstance().obrisiPredmet(p, s);
		
		PredmetiJTable.refresh();
		StudentJTable.refresh();
	}
	
}
