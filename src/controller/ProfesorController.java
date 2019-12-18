package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import model.BazaProfesora;
import model.Profesor;
import view.ProfesoriJTable;
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
		
		if(DijalogDodajProfesora.imeP.getText().isEmpty()) {
			return false;
		}
		else {
			String ime = DijalogDodajProfesora.imeP.getText();
			prof.setIme(ime);
		}
		
		if(DijalogDodajProfesora.przP.getText().isEmpty()) {
			return false;
		}
		else {
			String prz = DijalogDodajProfesora.przP.getText();
			prof.setPrezime(prz);
		}
		
		if(DijalogDodajProfesora.adresaP.getText().isEmpty()) {
			return false;
		}
		else {
			String adresa = DijalogDodajProfesora.adresaP.getText();
			prof.setAdresa(adresa);
		}
		
		if(DijalogDodajProfesora.brlk.getText().isEmpty()) {
			return false;
		}
		else {
			String brlk = DijalogDodajProfesora.brlk.getText();
			prof.setBrlk(brlk);
		}
		
		if(DijalogDodajProfesora.brTel.getText().isEmpty()) {
			return false;
		}
		else {
			String brt = DijalogDodajProfesora.brTel.getText();
			prof.setBr_tel(brt);
		}
		
		if(DijalogDodajProfesora.datRP.getText().isEmpty()) {
			return false;
		}
		else {
			try {
				Date datum = new Date();
				datum = parseDate(DijalogIzmeniProfesora.datRP.getText());
				prof.setDatumr(datum);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "GRESKA PRILIKOM UNOSA DATUMA!", "GRESKA", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(DijalogDodajProfesora.email.getText().isEmpty()) {
			return false;
		}
		else {
			String email = DijalogDodajProfesora.email.getText();
			prof.setEmail(email);
		}
		
		if(DijalogDodajProfesora.kancelarija.getText().isEmpty()) {
			return false;
		}
		else {
			String kancelarija = DijalogDodajProfesora.kancelarija.getText();
			prof.setKancelarija(kancelarija);
		}
		
		if(DijalogDodajProfesora.titula.getText().isEmpty()) {
			return false;
		}
		else {
			String titula = DijalogDodajProfesora.titula.getText();
			prof.setTitula(titula);
		}
		
		if(DijalogDodajProfesora.zvanje.getText().isEmpty()) {
			return false;
		}
		else {
			String zvanje = DijalogDodajProfesora.zvanje.getText();
			prof.setZvanje(zvanje);
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
		
		if(DijalogIzmeniProfesora.imeP.getText().isEmpty()) {
			return false;
		}
		else {
			String ime = DijalogIzmeniProfesora.imeP.getText();
			prof.setIme(ime);
		}
		
		if(DijalogIzmeniProfesora.przP.getText().isEmpty()) {
			return false;
		}
		else {
			String prz = DijalogIzmeniProfesora.przP.getText();
			prof.setPrezime(prz);
		}
		
		if(DijalogIzmeniProfesora.adresaP.getText().isEmpty()) {
			return false;
		}
		else {
			String adresa = DijalogIzmeniProfesora.adresaP.getText();
			prof.setAdresa(adresa);
		}
		
		if(DijalogIzmeniProfesora.brlk.getText().isEmpty()) {
			return false;
		}
		else {
			String brlk = DijalogIzmeniProfesora.brlk.getText();
			prof.setBrlk(brlk);
		}
		
		if(DijalogIzmeniProfesora.brTel.getText().isEmpty()) {
			return false;
		}
		else {
			String brt = DijalogIzmeniProfesora.brTel.getText();
			prof.setBr_tel(brt);
		}
		
		if(DijalogIzmeniProfesora.datRP.getText().isEmpty()) {
			return false;
		}
		else {
			try {
				Date datum = new Date();
				datum = parseDate(DijalogIzmeniProfesora.datRP.getText());
				prof.setDatumr(datum);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "GRESKA PRILIKOM UNOSA DATUMA!", "GRESKA", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(DijalogIzmeniProfesora.email.getText().isEmpty()) {
			return false;
		}
		else {
			String email = DijalogIzmeniProfesora.email.getText();
			prof.setEmail(email);
		}
		
		if(DijalogIzmeniProfesora.kancelarija.getText().isEmpty()) {
			return false;
		}
		else {
			String kancelarija = DijalogIzmeniProfesora.kancelarija.getText();
			prof.setKancelarija(kancelarija);
		}
		
		if(DijalogIzmeniProfesora.titula.getText().isEmpty()) {
			return false;
		}
		else {
			String titula = DijalogIzmeniProfesora.titula.getText();
			prof.setTitula(titula);
		}
		
		if(DijalogIzmeniProfesora.zvanje.getText().isEmpty()) {
			return false;
		}
		else {
			String zvanje = DijalogIzmeniProfesora.zvanje.getText();
			prof.setZvanje(zvanje);
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
	         return new SimpleDateFormat("dd.MM.yyyy").parse(date);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	     
	  }
}
