package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BazaProfesora {
	private static BazaProfesora instance = null;
	
	public static BazaProfesora getInstance() {
		if(instance == null) {
			instance = new BazaProfesora();
		}
		
		return instance;
	}
	
	private long generator;
	
	private List<Profesor> profesori;
	private List<String> kolone;
	
	private BazaProfesora() {
		generator = 0;
		initProfesore();
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Zvanje");
		this.kolone.add("Titula");
		this.kolone.add("Kancelarija");
		
	}
	
	private void initProfesore() {
		// TODO: Implementirati funkciju sa obelezjima 
		// studenta : SAMO GRUB MODEL PROFESORA ZBOG PRAVLJENJA TABELE
		
		this.profesori = new ArrayList<Profesor>();
		
		/*
		 * rofesor(String ime, String prezime, Date datumr, String adresa, String br_tel, String email,
			String kancelarija, String brlk, String zvanje, String titula) {
		 */
		
		Profesor p1 = new Profesor("Nikola", "Nikolic", new Date(1960, 29, 12), "Braca Tatica", "1543153135", "nikolicn@gmail.com", "104", "5484354", "Redovni profesor", "Doktor nauka");
		profesori.add(p1);
		Profesor p2 = new Profesor("Marko", "Markovic", new Date(1960, 29, 12), "Braca Tatica", "1543153135", "nikolicn@gmail.com", "104", "6544354", "Docent", "Doktor nauka");
		Profesor p3 = new Profesor("Jovan", "Jovanovic", new Date(1960, 29, 12), "Braca Tatica", "1543153135", "nikolicn@gmail.com", "104", "513151", "Vanredni profesor", "Doktor nauka");
		
		profesori.add(p2);
		profesori.add(p3);
		
	
	}

	public List<Profesor> getProfesori() {
		return profesori;
	}

	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}
	
	public int getColumnCount() {
		return 5;
	}

	
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	public Profesor getRow(int rowIndex) {
		return this.profesori.get(rowIndex);
	}	
	
	public String getValueAt(int row, int column) {
		Profesor profesor = this.profesori.get(row);
		
		switch(column) {
		case 0:
			return profesor.getIme();
		case 1:
			return profesor.getPrezime();
		case 2: 
			return profesor.getZvanje();
		case 3:
			return profesor.getTitula();
		case 4:
			return profesor.getKancelarija();
		default:
			return null;
		}
	}
	
	public void dodajProfesora(String ime, String prezime, Date datumr, String adresa, String brtel, String email, 
			String kancelarija, String brlk,  String zvanje, String titula) {
		/*
		 * rofesor(String ime, String prezime, Date datumr, String adresa, String br_tel, String email,
			String kancelarija, String brlk, String zvanje, String titula) {
		 */
		
		this.profesori.add(new Profesor(ime, prezime, datumr, adresa, brtel, email, kancelarija, brlk, zvanje, titula));
	}
	
	public void izbrisiProfesora(String brlk) {
		for(Profesor p : profesori) {
			if(p.getBrlk().equals(brlk)) {
				profesori.remove(p);
				break;
				
			}
		}
	}
	
	public void izmeniProfesora(String ime, String prezime, String zvanje, String titula, String kancelarija) {
		// TODO: Implementirati funkciju za izmenu profesora
		// dopuniti sve obelezjima koje profesor jos dodatno ima
		
		// SAMO GRUB MODEL ZA TESTIRANJE TABELE
	}
}
