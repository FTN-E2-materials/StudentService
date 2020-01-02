package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Predmet implements Serializable {

	private static final long serialVersionUID = 8472634341468604231L;
	private String sifra;
	private String ime;
	private int semestar;
	private int godina;
	private Profesor pred_prof;
	private ArrayList<Student> studenti;

	public Predmet() {}
	
	public Predmet(String sifra, String ime, int semestar, int godina) {
		super();
		this.sifra = sifra;
		this.ime = ime;
		this.semestar = semestar;
		this.godina = godina;
		this.pred_prof = new Profesor();
		this.studenti = new ArrayList<Student>();
	}
	
	public ArrayList<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(ArrayList<Student> studenti) {
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

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
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
