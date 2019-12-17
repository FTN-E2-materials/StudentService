package model;

import java.util.Date;
import java.util.ArrayList;


public class Student {
	public enum Status { B, S };
	private String ime;
	private String prezime;
	private Date datumr;
	private String adresa;
	private String br_tel;
	private String email;
	private String bri;
	private Date datum_upisa;
	private int godina_stud;
	Status status;
	private double prosek;
	private ArrayList<Predmet> predmeti;
	
	public Student() {}
	
	public Student(String ime, String prezime,
			String br_tel, String email, String bri, 
			int godina_stud, Status status, double prosek) {
		
			super();
			this.ime = ime;
			this.prezime = prezime;
			this.br_tel = br_tel;
			this.email = email;
			this.bri = bri;
			this.godina_stud = godina_stud;
			this.status = status;
			this.prosek = prosek;
	}
	
	public Student(String ime, String prezime, Date datumr, String adresa, String br_tel, String email,
			String bri, Date datum_upisa, int godina_stud, Status status, double prosek) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.datumr = datumr;
		this.adresa = adresa;
		this.br_tel = br_tel;
		this.email = email;
		this.bri = bri;
		this.datum_upisa = datum_upisa;
		this.godina_stud = godina_stud;
		this.status = status;
		this.prosek = prosek;
		this.predmeti = new ArrayList<Predmet>();
	}
	
	
	public Student(Student s) {
		ime = s.ime;
		prezime = s.prezime;
		datumr = s.datumr;
		adresa = s.adresa;
		br_tel = s.br_tel;
		email = s.email;
		bri = s.bri;
		datum_upisa = s.datum_upisa;
		godina_stud = s.godina_stud;
		status = s.status;
		prosek = s.prosek;
		predmeti = s.predmeti;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Date getDatumr() {
		return datumr;
	}

	public void setDatumr(Date datumr) {
		this.datumr = datumr;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBr_tel() {
		return br_tel;
	}

	public void setBr_tel(String br_tel) {
		this.br_tel = br_tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBri() {
		return bri;
	}

	public void setBri(String bri) {
		this.bri = bri;
	}

	public Date getDatum_upisa() {
		return datum_upisa;
	}

	public void setDatum_upisa(Date datum_upisa) {
		this.datum_upisa = datum_upisa;
	}

	public int getGodina_stud() {
		return godina_stud;
	}

	public void setGodina_stud(int godina_stud) {
		this.godina_stud = godina_stud;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getProsek() {
		return prosek;
	}

	public void setProsek(double prosek) {
		this.prosek = prosek;
	}

	public ArrayList<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(ArrayList<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	
}
