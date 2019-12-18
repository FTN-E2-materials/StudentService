package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import model.BazaStudenata;
import model.Student;
import model.Student.Status;
import view.MainFrame;
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
		
		if(!DijalogStudent.imeS.getText().isEmpty()) {
			String ime = DijalogStudent.imeS.getText();
			st.setIme(ime);
		}
		else
			return false;
		
		if(!DijalogStudent.przS.getText().isEmpty()) {
			String prz = DijalogStudent.przS.getText();
			st.setPrezime(prz);
		}
		else 
			return false;
		
		if(!DijalogStudent.adresa.getText().isEmpty()) {
			String adresa = DijalogStudent.adresa.getText();
			st.setAdresa(adresa);
		}
		else 
			return false;
		
		if(!DijalogStudent.briS.getText().isEmpty()) {
			String bri = DijalogStudent.briS.getText();
			st.setBri(bri);
		}
		else 
			return false;
		
		if(!DijalogStudent.brtel.getText().isEmpty()) {
			String br = DijalogStudent.brtel.getText();
			st.setBr_tel(br);
		}
		else 
			return false;
		
		if(!DijalogStudent.email.getText().isEmpty()) {
			String em = DijalogStudent.email.getText();
			st.setEmail(em);
		}
		else 
			return false;
		
		if(!DijalogStudent.datRodj.getText().isEmpty()) {
			Date datum = new Date();
			datum = parseDate(DijalogStudent.datRodj.getText());
			st.setDatumr(datum);
		}
		else 
			return false;
		
		if(!DijalogStudent.datumU.getText().isEmpty()) {
			Date datum = new Date();
			datum = parseDate(DijalogStudent.datumU.getText());
			st.setDatum_upisa(datum);
			
		}
		else 
			return false;
		
		
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
			if(!DijalogStudent.prosOc.getText().isEmpty()) {
				double pros = Double.parseDouble(DijalogStudent.prosOc.getText());
				st.setProsek(pros);
			}
			else 
				return false;
		} 
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "GRESKA PRILIKOM PARSIRANJA PROSEKA!", "GRESKA", JOptionPane.ERROR_MESSAGE);
			
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
		
		Student st = BazaStudenata.getInstance().getRow(StudentJTable.curr_row);
		
		if(!DijalogIzmeniS.imeS.getText().isEmpty()) {
			String ime = DijalogIzmeniS.imeS.getText();
			st.setIme(ime);
		}
		else
			return false;
		
		if(!DijalogIzmeniS.przS.getText().isEmpty()) {
			String prz = DijalogIzmeniS.przS.getText();
			st.setPrezime(prz);
		}
		else 
			return false;
		
		if(!DijalogIzmeniS.adresa.getText().isEmpty()) {
			String adresa = DijalogIzmeniS.adresa.getText();
			st.setAdresa(adresa);
		}
		else 
			return false;
		
		if(!DijalogIzmeniS.briS.getText().isEmpty()) {
			String bri = DijalogIzmeniS.briS.getText();
			st.setBri(bri);
		}
		else 
			return false;
		
		if(!DijalogIzmeniS.brtel.getText().isEmpty()) {
			String br = DijalogIzmeniS.brtel.getText();
			st.setBr_tel(br);
		}
		else 
			return false;
		
		if(!DijalogIzmeniS.email.getText().isEmpty()) {
			String em = DijalogIzmeniS.email.getText();
			st.setEmail(em);
		}
		else 
			return false;
		
		if(!DijalogIzmeniS.datRodj.getText().isEmpty()) {
			Date datum = new Date();
			datum = parseDate(DijalogIzmeniS.datRodj.getText());
			st.setDatumr(datum);
		}
		else 
			return false;
		
		if(!DijalogIzmeniS.datumU.getText().isEmpty()) {
			Date datum = new Date();
			datum = parseDate(DijalogIzmeniS.datumU.getText());
			st.setDatum_upisa(datum);
			
		}
		else 
			return false;
		
		
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
			if(!DijalogIzmeniS.prosOc.getText().isEmpty()) {
				double pros = Double.parseDouble(DijalogIzmeniS.prosOc.getText());
				st.setProsek(pros);
			}
			else 
				return false;
		} 
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "GRESKA PRILIKOM PARSIRANJA PROSEKA!", "GRESKA", JOptionPane.ERROR_MESSAGE);
			
		}
		

		BazaStudenata.getInstance().izmeniStudenta(st.getBri(), st.getIme(), st.getPrezime(), st.getDatumr(), st.getAdresa(), st.getBr_tel(), st.getDatum_upisa(), st.getGodina_stud(),  st.getEmail(), st.getStatus(), st.getProsek());
		
		StudentJTable.refresh();
		return true;
		
	}
	public void pretraziStudenta() {
		if(Toolbar.pretraga.getText().isEmpty()) {
		//	JOptionPane.showMessageDialog(null, "Morate uneti tekst za pretragu!", "GRESKA", JOptionPane.ERROR_MESSAGE);
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
	
}
