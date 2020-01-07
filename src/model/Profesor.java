package model;

import java.util.Date;
import java.io.Serializable;
import java.util.ArrayList;

public class Profesor implements Serializable {

	private static final long serialVersionUID = -3177000235646704678L;
	public enum Zvanje implements Serializable { Asistent, Saradnik, RProfesor, VProfesor, Docent };
	public enum Titula implements Serializable { Dr, Ms };
	
	private String ime;
	private String prezime;
	private Date datumr;
	private String adresa;
	private String br_tel;
	private String email;
	private String kancelarija;
	private String brlk;
	private Zvanje zvanje;
	private Titula titula;
	ArrayList<Predmet> predmeti;
	

	public Profesor() {}
	
	public Profesor(String ime, String prezime, Zvanje zvanje, Titula titula, String kancelarija) {
		this.ime = ime;
		this.prezime = prezime;
		this.zvanje = zvanje;
		this.titula = titula;
		this.kancelarija = kancelarija;
	}
	
	public Profesor(String ime, String prezime, Date datumr, String adresa, String br_tel, String email,
			String kancelarija, String brlk, Zvanje zvanje, Titula titula) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.datumr = datumr;
		this.adresa = adresa;
		this.br_tel = br_tel;
		this.email = email;
		this.kancelarija = kancelarija;
		this.brlk = brlk;
		this.zvanje = zvanje;
		this.titula = titula;
		this.predmeti =  new ArrayList<Predmet>(); 
	}
	
	
	public Profesor(Profesor p) {
		ime = p.ime;
		prezime = p.prezime;
		datumr = p.datumr;
		adresa = p.adresa;
		br_tel = p.br_tel;
		email = p.email;
		kancelarija = p.kancelarija;
		brlk = p.brlk;
		zvanje = p.zvanje;
		titula = p.titula;
		predmeti = p.predmeti;
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
	public String getKancelarija() {
		return kancelarija;
	}
	public void setKancelarija(String kancelarija) {
		this.kancelarija = kancelarija;
	}
	public String getBrlk() {
		return brlk;
	}
	public void setBrlk(String brlk) {
		this.brlk = brlk;
	}
	public Zvanje getZvanje() {
		return zvanje;
	}
	public void setZvanje(Zvanje zvanje) {
		this.zvanje = zvanje;
	}
	public Titula getTitula() {
		return titula;
	}
	public void setTitula(Titula titula) {
		this.titula = titula;
	}
	public ArrayList<Predmet> getPredmeti() {
		return predmeti;
	}
	public void setPredmeti(ArrayList<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	
	
}
