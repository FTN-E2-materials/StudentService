package model;

import java.util.ArrayList;

public class Predmet {
	private int sifra;
	private String ime;
	private int semestar;
	private int godina;
	private Profesor pred_prof;
	private ArrayList<Student> studenti;

	
	
	public Predmet(int sifra, String ime, int semestar, int godina, Profesor pred_prof, ArrayList<Student> studenti) {
		super();
		this.sifra = sifra;
		this.ime = ime;
		this.semestar = semestar;
		this.godina = godina;
		this.pred_prof = pred_prof;
		this.studenti = studenti;
	}

	public Predmet(Predmet p) {
		super();
		sifra = p.sifra;
		ime = p.ime;
		semestar = p.semestar;
		godina = p.godina;
		pred_prof = p.pred_prof;
		studenti = p.studenti;
	}

	public int getSifra() {
		return sifra;
	}

	public void setSifra(int sifra) {
		this.sifra = sifra;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public int getSemestar() {
		return semestar;
	}

	public void setSemestar(int semestar) {
		this.semestar = semestar;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public Profesor getPred_prof() {
		return pred_prof;
	}

	public void setPred_prof(Profesor pred_prof) {
		this.pred_prof = pred_prof;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Predmet: " + ime + "\nSifra predmeta: " + sifra + 
				"\nGodina:  " + godina + "\nSemestar: " + semestar + "\nPredavac: " + pred_prof;
	}
	
	
	
}
