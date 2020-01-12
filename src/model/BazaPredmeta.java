package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controller.PredmetController;

public class BazaPredmeta {
	private static BazaPredmeta instance = null;
	
	public static BazaPredmeta getInstance() {
		if(instance == null) {
			instance = new BazaPredmeta();
		}
		
		return instance;
	}

	private List<Predmet> predmeti; // lista koja sadrzi sve predmete
	private List<String> kolone;
	private List<Predmet> filterPredmet; // lista koja sadrzi filtrirane predmete (nadjene pretragom)
	private List<Predmet> tekuciPredmet; // lista koja se prikazuje u svakom trenutku (smenjuju se u njoj vrednosti)
	

	
	private BazaPredmeta() {
		
		this.predmeti = new ArrayList<Predmet>();
		this.tekuciPredmet = new ArrayList<Predmet>();
		this.filterPredmet = new ArrayList<Predmet>();
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("Šifra predmeta");
		this.kolone.add("Naziv predmeta");
		this.kolone.add("Semestar");
		this.kolone.add("Godina studija");
		this.kolone.add("Predmetni profesor");
		this.kolone.add("Lista studenata");
		
		this.deserialize();
		this.setTrentunoStanje();
	}
	
	public List<Predmet> getFilterPredmet() {
		return filterPredmet;
	}

	public void setFilterPredmet(List<Predmet> filterPredmet) {
		this.filterPredmet = filterPredmet;
	}

	public List<Predmet> getTekuciPredmet() {
		return tekuciPredmet;
	}

	public void setTekuciPredmet(List<Predmet> tekuciPredmet) {
		this.tekuciPredmet = tekuciPredmet;
	}

	public List<Predmet> getFilter_Predmet() {
		return this.filterPredmet;
	}

	public void setFilter_Predmet(ArrayList<Predmet> filter_Predmet) {
		this.filterPredmet = filter_Predmet;
	}

	public List<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
		this.setTrentunoStanje();
	}
	
	public Predmet getRow(int rowIndex) {
		return this.tekuciPredmet.get(rowIndex);
	}
	
	public int getColumnCount() {
		return 6;
	}
	
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	
	public String getValueAt(int row, int column) {
		Predmet predmet = this.tekuciPredmet.get(row);
		
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
			if(predmet.getPred_prof() == null)
				return "";
			else {
				return predmet.getPred_prof().getBrlk() + ", " + predmet.getPred_prof().getIme() + " " + predmet.getPred_prof().getPrezime();
			}
		default: 
			return null;
		}
	}

	public void dodajPredmet(String sifra, String ime, int godina, int semestar) {
		Predmet p = new Predmet(sifra, ime, semestar, godina);
		p.setPred_prof(null);
		this.predmeti.add(p);
		this.setTrentunoStanje();
	}
	
	public void obrisiPredmet(String sifra) {
		for (Predmet p : predmeti ) {
			if(p.getSifra().equals(sifra)) {
				for (Student s : p.getStudenti()) {
					s.getPredmeti().remove(p);
				}
				if(p.getPred_prof() != null)
					p.getPred_prof().getPredmeti().remove(p);
				predmeti.remove(p);
				break;
			}
		}
		
		for (Predmet p : filterPredmet ) {
			if(p.getSifra().equals(sifra)) {
				for (Student s : p.getStudenti()) {
					s.getPredmeti().remove(p);
				}
				if(p.getPred_prof() != null)
					p.getPred_prof().getPredmeti().remove(p);
				filterPredmet.remove(p);
				break;
			}
		}
		this.setTrentunoStanje();
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
		// menjala sam u obe liste da bi se odmah promenili podaci i u filtriranoj listi
		
		for (Predmet p : filterPredmet) {
			if(p.getSifra().equals(sifra)) {
				p.setIme(ime);
				p.setGodina(godina);
				p.setSemestar(semestar);
				p.setPred_prof(prof);
			}
		}
		this.setTrentunoStanje();
	}
	
	public boolean dodajProfesora(String brlk, String sifraP) {
		boolean retVal = false;
	
		for (Profesor p : BazaProfesora.getInstance().getProfesori()) {
			if (p.getBrlk().equals(brlk)) {
				for (Predmet pred : predmeti) {
					if(pred.getSifra().equals(sifraP)) {
						pred.setPred_prof(p);
						retVal = true;
					}
				}
			} 
		}
		// menjala sam u obe liste da bi se odmah promenili podaci i u filtriranoj listi
		
		for (Profesor p : BazaProfesora.getInstance().getFilter_Profesor()) {
			if (p.getBrlk().equals(brlk)) {
				for (Predmet pred : predmeti) {
					if(pred.getSifra().equals(sifraP)) {
						pred.setPred_prof(p);
						retVal = true;
					}
				}
			} 
		}
		this.setTrentunoStanje();
		return retVal;
	}
	
	public void dodajStudenta(String sifra, Student s) {
		for (Predmet p : this.predmeti) {
			if(p.getSifra().equals(sifra)) {
				p.getStudenti().add(s);
			}
		}
		
		this.setTrentunoStanje();		
	}

	public void obrisiStudenta(Predmet p, String bri) {
		for (Predmet pr : this.predmeti) {
			if (pr.getSifra().equals(p.getSifra())) {
				for (Student s : pr.getStudenti()) {
					if (s.getBri().equals(bri)) {
						pr.getStudenti().remove(s);
						break;
					}
				}
			
			}
		}
		this.setTrentunoStanje();
	}

	public void obrisiProfesora(Profesor p, Predmet pp) {
		for (Predmet pred : this.predmeti) {
			if (pred.getSifra().equals(pp.getSifra())) {
				pred.setPred_prof(null);
			}
		}
		this.setTrentunoStanje();
	}

	public void izmeniPredmet(Predmet predmet) {
		for (Predmet p : predmeti) {
			if (p.getSifra().equals(predmet.getSifra())) {
				p.setGodina(predmet.getGodina());
				p.setIme(predmet.getIme());
				p.setSemestar(predmet.getSemestar());
				p.setPred_prof(predmet.getPred_prof());
			}
		}
		// menjala sam u obe liste da bi se odmah promenili podaci i u filtriranoj listi
		
		for (Predmet p : filterPredmet) {
			if (p.getSifra().equals(predmet.getSifra())) {
				p.setGodina(predmet.getGodina());
				p.setIme(predmet.getIme());
				p.setSemestar(predmet.getSemestar());
				p.setPred_prof(predmet.getPred_prof());
			}
		}
		this.setTrentunoStanje();
	}

	public void pretraziPredmet(String text) {

		if(PredmetController.flag == 0) {
			this.tekuciPredmet = this.predmeti;
			return;
		}
		ArrayList<Predmet> predmetiNadjeni = new ArrayList<Predmet>();
	
		int i = 0;
		String[] deli = text.split(";"); 
		String[] kriterijumi = new String[4];
		String[] podaci = new String[4];
		
		for (String s : deli) {
			String[] pom = s.split(":");
			kriterijumi[i] = pom[0];
			podaci[i] = pom[1];
			i++;
		}
		
		if(!kriterijumi[0].equals("sifra") && !kriterijumi[0].equals("naziv") && !kriterijumi[0].equals("godina") && !kriterijumi[0].equals("semestar")) {
			JOptionPane.showMessageDialog(null, "Kriterijum pretrage je: \n[sifra:'Sifra'];[naziv:'Naziv'];[godina:'Godina'];[semestar]:'Semestar']", "GREŠKA", JOptionPane.ERROR_MESSAGE);
		
		} else {
			boolean isSubject = false;
			
			for (Predmet p : this.predmeti) {
				
				// logika pretrage je da prodje kroz sve kriterijume
				// i da kako prolazi kroz kriterijume pretrage
				// da menja indikator za to da li je predmet u pitanju ili nije
				
				for (int j = 0; j < i; j++) {
					if (kriterijumi[j].equals("sifra")) {
						if(p.getSifra().equals(podaci[j])) {
							isSubject = true;
						} else {
							isSubject = false;
							break;
						}
					}
						
						if (kriterijumi[j].equals("naziv")) {
							if (p.getIme().equals(podaci[j])) {
								isSubject = true;
							} else {
								isSubject = false;
								break;
							}
						}
						
						if (kriterijumi[j].equals("godina")) {
							if (p.getGodina() == Integer.parseInt(podaci[j])) {
								isSubject = true;
							} else {
								isSubject = false;
								break;
							}
						}
						if (kriterijumi[j].equals("semestar")) {
							if (p.getSemestar() == Integer.parseInt(podaci[j])) {
								isSubject = true;
							} else {
								isSubject = false;
								break;
							}
						}
				}
				if (isSubject) 
					predmetiNadjeni.add(p);
			}
		}

		if (predmetiNadjeni.isEmpty()) {
			// ako je lista pronadjenih predmeta i dalje prazna
			// izbacujemo gresku
			JOptionPane.showMessageDialog(null, "Nije pronađen nijedan predmet datim kriterijumom.", "Neuspešno traženje", JOptionPane.ERROR_MESSAGE);
			PredmetController.flag = 0;
		} else {
			this.setFilter_Predmet(predmetiNadjeni);
		}
		this.setTrentunoStanje();
	}
	
	public void obrisiProfesoraIzBazeProf(Profesor p, Predmet pp) {
		// samo poostavi da je profesor null
		pp.setPred_prof(null);
	}
	
	public void obrisiStudentaPosle(String bri) {
		// funkcija sluzi za brisanje svih studenata na datim predmetima
		// prolazimo kroz listu svih predmeta
		// i gledamo da li dati predmet ima studenta 
		// koji je trenutno obrisan
		// ako ima, obrisi
		for (Predmet p : this.predmeti) {
			for (Student s : p.getStudenti()) {
				if (s.getBri().equals(bri)) {
					p.getStudenti().remove(s);
					break;
				}
			}
		}
		
	}
	public void obrisiProfesoraPosle(String brlk) {
		// funkcija sluzi za brisanje svih profesira na datim predmetima
		// prolazimo kroz listu svih predmeta
		// i gledamo da li dati predmet ima profesora 
		// koji je trenutno obrisan
		// ako ima, obrisi
		for (Predmet p : this.predmeti) {
			if (p.getPred_prof() != null) {
				if (p.getPred_prof().getBrlk().equals(brlk)) {
					p.setPred_prof(null);
				}
			}
		}
	}


	public void obrisiSveStudente(String sifra) {
		// brisemo sve studente posle izmene godine predmeta
		// jer verovatno nisu vise ista godina
		
		
		// kod studenata nisam pravila ovu funkiju (kad se promeni godina studenta da obrise sve predmete)
		// jer moze da se desi da student prenese predmet ?
		
		ArrayList<Student> studenti = new ArrayList<Student>();
		
		for (Predmet p : this.predmeti) {
			if (p.getSifra().equals(sifra)) {
				p.setStudenti(studenti);
				break;
			}
		}
		
	}
	
	// serijalizacija i deserijalizacija

	@SuppressWarnings("unchecked")
	private void deserialize() {

		try {
			FileInputStream fis = new FileInputStream("data/dataSubjects.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.predmeti = (ArrayList<Predmet>) ois.readObject();
			
			ois.close();
			fis.close();
		} catch (IOException ioe) 
        {
            System.out.println(ioe.getMessage());
        } 
        catch (ClassNotFoundException c) 
        {
            System.out.println("Class not found");
            System.out.println(c.getMessage());
            return;
        }
		
		this.setTekuciPredmet(this.predmeti);;
	}
	
	public void serialize() {
		
		try {
			FileOutputStream fos = new FileOutputStream("data/dataSubjects.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.predmeti);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		
	}
	
	public void setTrentunoStanje() {
		// funkcija koja sluzi da u svakom trenutku odrzi konzistentno stanje
		// filtriranih i cele baze studenata
		
		if (PredmetController.flag == 0) {
			this.setTekuciPredmet(this.predmeti);
		} else {
			this.setTekuciPredmet(this.filterPredmet);
		}
	}

	public void izmeniProfesoraPosleIzmene(String brlk) {
		// ako profesor promeni ime, mora da se promeni i kod predmeta
		for (Predmet pred : this.predmeti) {
			if(pred.getPred_prof() != null) {
				if (pred.getPred_prof().getBrlk().equals(brlk)) {
					for (Profesor p : BazaProfesora.getInstance().getProfesori()) {
						if (p.getBrlk().equals(brlk)) {
							pred.setPred_prof(p);
							break;
						}
					}
				}
			}
		}
	}

}
