package model;

import java.util.ArrayList;
import java.util.List;

public class BazaPredmeta {
	private static BazaPredmeta instance = null;
	
	public static BazaPredmeta getInstance() {
		if(instance == null) {
			instance = new BazaPredmeta();
		}
		
		return instance;
	}

	private List<Predmet> predmeti;
	private List<String> kolone;
	private ArrayList<Predmet> filter_Predmet = new ArrayList<Predmet>();
	
	
	private BazaPredmeta() {
		initPredmete();
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("Sifra predmeta");
		this.kolone.add("Naziv predmeta");
		this.kolone.add("Semestar");
		this.kolone.add("Godina studija");
		this.kolone.add("Predmetni profesor");
		this.kolone.add("Lista studenata");
		
	}

	private void initPredmete() {

		this.predmeti = new ArrayList<Predmet>();
		predmeti.add(new Predmet("E2G53", "Osnovi informacionih sistema i softverskog inzenjerstva", 5, 3));
		predmeti.add(new Predmet("E2312", "Matematicka analiza 1", 1, 1));
		predmeti.add(new Predmet("E2134", "Logicko projektovanje racunarskih sistema", 3, 2));
	}

	public ArrayList<Predmet> getFilter_Predmet() {
		return filter_Predmet;
	}

	public void setFilter_Predmet(ArrayList<Predmet> filter_Predmet) {
		this.filter_Predmet = filter_Predmet;
	}

	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	
	public Predmet getRow(int rowIndex) {
		return this.predmeti.get(rowIndex);
	}
	
	public int getColumnCount() {
		return 6;
	}
	
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	
	public String getValueAt(int row, int column) {
		Predmet predmet = this.predmeti.get(row);
		
		switch(column) {
		case 0:
			return predmet.getSifra();
		case 1:
			return predmet.getIme();
		case 2:
			return Integer.toString(predmet.getSemestar());
		case 3:
			return Integer.toString(predmet.getGodina());
		case 4:
			if(predmet.getPred_prof().getIme() == null && predmet.getPred_prof().getPrezime() == null) {
				return "";
			}
			else 
				return predmet.getPred_prof().getIme() + " " + predmet.getPred_prof().getPrezime();
		
		default: 
			return null;
		}
	}
	
	public void dodajPredmet(String sifra, String ime, int godina, int semestar) {
		this.predmeti.add(new Predmet(sifra, ime, semestar, godina));
	}
	
	public void obrisiPredmet(String sifra) {
		for (Predmet p : predmeti ) {
			if(p.getSifra().equals(sifra)) {
				predmeti.remove(p);
				break;
			}
		}
	}
	
	public void izmeniPredmet(String sifra, int godina, int semestar, String ime, Profesor prof) {
		for (Predmet p : predmeti) {
			if(p.getSifra().equals(sifra)) {
				p.setIme(ime);
				p.setGodina(godina);
				p.setSemestar(semestar);
				p.setPred_prof(prof);
			}
		}
	}
	public boolean dodajProfesora(String brlk, String sifraP) {
		for (Profesor p : BazaProfesora.getInstance().getProfesori()) {
			if (p.getBrlk().equals(brlk)) {
				for (Predmet pred : predmeti) {
					if(pred.getSifra().equals(sifraP)) {
						pred.setPred_prof(p);
						return true;
					}
				}
			} 
		}
		return false;
	}
	public void dodajStudenta(String sifra, Student s) {
		for (Predmet p : predmeti) {
			if(p.getSifra().equals(sifra)) {
				p.getStudenti().add(s);
				s.getPredmeti().add(p);
			}
		}
		
	}

	public void obrisiStudenta(Predmet p, Student s) {
		p.getStudenti().remove(s);
		s.getPredmeti().remove(p);
	}
	
}
