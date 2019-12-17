package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import model.BazaStudenata;
import model.Student;
import model.Student.Status;
import view.MainFrame;
import view.StudentJTable;
import view.dijalozi.DijalogStudent;

public class StudentController {
// TODO: Implementirati funkcije za dijaloge
// za dodavanje, brisanje i izmenu studenta
	
	public static StudentController instance = null;
	
	public static StudentController getInstance() {
		if(instance == null) {
			instance = new StudentController();
		}
		
		return instance;
	}
	
	private StudentController() {}
	public boolean dodajStudenta() {
		// TODO: Implementirati da uzima podatke iz dijaloga : VAZI ZA SVE FUNKCIJE
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
			st.setDatumr(parseDate(DijalogStudent.datRodj.getText()));
		}
		else 
			return false;
		
		if(!DijalogStudent.datumU.getText().isEmpty()) {
//			st.setDatum_upisa(parseDate(DijalogStudent.datumU.getText()));
			
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
		//BazaStudenata.getInstance().izmeniStudenta(st.getBri(), st.getIme(), , godina_stud, email, status, prosek, br_tel);
		StudentJTable.refresh();
		return true;
		
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
