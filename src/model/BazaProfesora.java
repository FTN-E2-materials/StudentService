package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.ProfesorController;

public class BazaProfesora {
	private static BazaProfesora instance = null;
	
	public static BazaProfesora getInstance() {
		if(instance == null) {
			instance = new BazaProfesora();
		}
		
		return instance;
	}

	private long generator;
	
	private List<Profesor> tekuci_profesori;
	private List<String> kolone;
	private ArrayList<Profesor> filter_Profesor = new ArrayList<Profesor>();
	private ArrayList<Profesor> profesori;
	
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
		
		Profesor p1 = new Profesor("Nikola", "Nikolic", parseDate("29.12.1960"), "Braca Tatica", "123456", "nikolicn@gmail.com", "104", "123456", "Redovni profesor", "Doktor nauka");
		profesori.add(p1);
		Profesor p2 = new Profesor("Marko", "Markovic", parseDate("29.12.1960"), "Braca Tatica", "468798", "nikolicn@gmail.com", "104", "6544354", "Docent", "Doktor nauka");
		Profesor p3 = new Profesor("Jovan", "Jovanovic", parseDate("29.12.1960"), "Braca Tatica", "8751354", "nikolicn@gmail.com", "104", "513151", "Vanredni profesor", "Doktor nauka");
		
		profesori.add(p2);
		profesori.add(p3);
		this.tekuci_profesori = this.profesori;
		
	
	}

	public List<Profesor> getProfesori() {
		return tekuci_profesori;
	}

	public void setProfesori(List<Profesor> profesori) {
		this.tekuci_profesori = profesori;
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
		
		this.profesori.add(new Profesor(ime, prezime, datumr, adresa, brtel, email, kancelarija, brlk, zvanje, titula));
		this.tekuci_profesori = this.profesori;
	}
	
	public void izbrisiProfesora(String brlk) {
		for(Profesor p : profesori) {
			if(p.getBrlk().equals(brlk)) {
				profesori.remove(p);
				break;
				
			}
		}
		this.tekuci_profesori = this.profesori;
	}
	
	public void izmeniProfesora(String ime, String prezime, Date datumr, String adresa, String brt, String email, String kancelarija, String brlk, String zvanje, String titula) {
		
		for(Profesor p : profesori) {
			if(p.getBrlk().equals(brlk)) {
				p.setIme(ime);
				p.setPrezime(prezime);
				p.setDatumr(datumr);
				p.setAdresa(adresa);
				p.setBr_tel(brt);
				p.setEmail(email);
				p.setKancelarija(kancelarija);
				p.setZvanje(zvanje);
				p.setTitula(titula);
			}
		}

		this.tekuci_profesori = this.profesori;
		
	}
	
	public void pretraziProfesora(String txt) {
		
		if(ProfesorController.flag == 0) {
			this.tekuci_profesori = this.profesori;
			return;
		}
		
		String[] kriterijumi = txt.split(";");
		String[] pom1 = kriterijumi[0].split(":");
		String ime;
		ime = pom1[1];
		String[] pom2 = kriterijumi[1].split(":");
		String prezime = pom2[1];
		String[] pom3 = kriterijumi[2].split(":");
		String brlk = pom3[1];
		
		for(Profesor p : profesori) {
			System.out.println(p.getBrlk());
			if(p.getBrlk().equals(brlk) && p.getIme().equals(ime) && p.getPrezime().equals(prezime)) {
				filter_Profesor.add(p);
			}
		}
		
		this.tekuci_profesori = this.filter_Profesor;
		
		
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
