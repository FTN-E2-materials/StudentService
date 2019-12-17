package controller;

import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import model.BazaProfesora;
import model.Profesor;
import view.ProfesoriJTable;
import view.dijalozi.DijalogDodajProfesora;

public class ProfesorController {
	public static ProfesorController instance = null;
	
	public static ProfesorController getInstance() {
		if(instance == null) {
			instance = new ProfesorController();
		}
		
		return instance;
	}
	
	public ProfesorController() {}
	
	public boolean DodajProfesora() {
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
				SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
				prof.setDatumr(format.parse(DijalogDodajProfesora.datRP.getText()));
				
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
	
	public void izmeniProfesora(int rowSelectedIndex) {
		if(rowSelectedIndex < 0) {
			return;
		}
	}
}
