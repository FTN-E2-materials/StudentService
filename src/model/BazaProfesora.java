package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import controller.ProfesorController;
import model.Profesor.Titula;
import model.Profesor.Zvanje;

public class BazaProfesora {
	private static BazaProfesora instance = null;
	
	public static BazaProfesora getInstance() {
		if(instance == null) {
			instance = new BazaProfesora();
		}
		
		return instance;
	}
	
	private List<Profesor> tekuci_profesori;
	private List<String> kolone;
	private List<Profesor> filter_Profesor;
	private List<Profesor> profesori;
	
	private BazaProfesora() {

		tekuci_profesori = new ArrayList<Profesor>();
		filter_Profesor = new ArrayList<Profesor>();
		profesori = new ArrayList<Profesor>();
		this.kolone = new ArrayList<String>();
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Broj lične karte");
		this.kolone.add("Zvanje");
		this.kolone.add("Titula");
		this.kolone.add("Kancelarija");
		this.kolone.add("Adresa stanovanja");
		this.kolone.add("Datum rođenja");
		this.kolone.add("Email");
		this.kolone.add("Kontakt telefon");
		this.kolone.add("Lista predmeta");
		
		this.deserialize();
		
		this.setTrenutnoStanje();
	}
	
	
	public List<Profesor> getProfesori() {
		return this.profesori;
	}

	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}
	
	public int getColumnCount() {
		return 11;
	}

	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	public Profesor getRow(int rowIndex) {
		return this.tekuci_profesori.get(rowIndex);
	}	
	
	public List<Profesor> getTekuci_profesori() {
		return tekuci_profesori;
	}

	public void setTekuci_profesori(List<Profesor> tekuci_profesori) {
		this.tekuci_profesori = tekuci_profesori;
	}

	public List<Profesor> getFilter_Profesor() {
		return filter_Profesor;
	}

	public void setFilter_Profesor(ArrayList<Profesor> filter_Profesor) {
		this.filter_Profesor = filter_Profesor;
	}

	public void setProfesori(ArrayList<Profesor> profesori) {
		this.profesori = profesori;
	}
	
	public Object getValueAt(int row, int column) {
		Profesor profesor = this.tekuci_profesori.get(row);
	
		switch(column) {
		case 0:
			return profesor.getIme();
		case 1:
			return profesor.getPrezime();
		case 2: 
			return profesor.getBrlk();
		case 3:
			if (profesor.getZvanje() == Zvanje.Asistent) {
				return "Asistent";
			} else if (profesor.getZvanje() == Zvanje.Docent) {
				return "Docent";
			} else if (profesor.getZvanje() == Zvanje.RProfesor) {
				return "Redovni profesor";
			} else if (profesor.getZvanje() == Zvanje.VProfesor) {
				return "Vanredni profesor";
			} else if (profesor.getZvanje() == Zvanje.Saradnik) {
				return "Saradnik u nastavi";
			}
			else {
				return "Docent";
			}
		case 4:
			if (profesor.getTitula() == Titula.ProfDr) { 
				return "Prof. Dr.";
			} else if (profesor.getTitula() == Titula.Dr) {
				return "Doktor";
			} else {
				return "Master";
			}
		case 5:
			return profesor.getKancelarija();
		case 6:
			return profesor.getAdresa();
		case 7:
			return profesor.getDatumr();
		case 8:
			return profesor.getEmail();
		case 9:
			return profesor.getBr_tel();
		default:
			return null;
		}
	}

	public void dodajProfesora(String ime, String prezime, Date datumr, String adresa, String brtel, String email, 
			String kancelarija, String brlk,  Zvanje zvanje, Titula titula) {
		
		this.profesori.add(new Profesor(ime, prezime, datumr, adresa, brtel, email, kancelarija, brlk, zvanje, titula));
		this.setTrenutnoStanje();
	}


	public void izbrisiProfesora(String brlk) {
		for(Profesor p : profesori) {
			if(p.getBrlk().equals(brlk)) {
				profesori.remove(p);
				break;
				
			}
		}
		
		for(Profesor p : filter_Profesor) {
			if(p.getBrlk().equals(brlk)) {
				profesori.remove(p);
				break;
				
			}
		}
		this.tekuci_profesori = this.profesori;
	}
	
	public void izmeniProfesora(String ime, String prezime, Date datumr, String adresa, String brt, String email, String kancelarija, String brlk, Zvanje zvanje, Titula titula) {
		
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
		
		for(Profesor p : filter_Profesor) {
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
		
		this.setTrenutnoStanje();
	}
	
	public void pretraziProfesora(String text) {
		
		if(ProfesorController.flag == 0) {
			this.tekuci_profesori = this.profesori;
			return;
		}
		ArrayList<Profesor> profesoriNadjeni = new ArrayList<Profesor>();
		System.out.println(BazaProfesora.getInstance().getProfesori().size());
		int i = 0;
		System.out.println(text);
		String[] deli = text.split(";"); 
		String[] kriterijumi = new String[3];
		String[] podaci = new String[3];

		
		for (String s : deli) {
			String[] pom = s.split(":");
			kriterijumi[i] = pom[0];
			podaci[i] = pom[1];
			i++;
		}
		if(!kriterijumi[0].equals("ime") && !kriterijumi[0].equals("prezime") && !kriterijumi[0].equals("brlk") && !kriterijumi[0].equals("zvanje") && !kriterijumi[0].equals("titula")) {
			JOptionPane.showMessageDialog(null, "Kriterijum pretrage je: \n[ime:'Ime'];[prezime'Prezime'];[brlk:'Indeks'];]zvanje:'Zvanje'];[titula:'Titula']", "GREŠKA", JOptionPane.ERROR_MESSAGE);
		
		} else {
			boolean isProfesor = false;
			
			for (Profesor p : BazaProfesora.getInstance().getProfesori()) {
				
				for (int j = 0; j < i; j++) {
					if (kriterijumi[j].equals("ime")) {
						if(p.getIme().equals(podaci[j])) {
							isProfesor = true;
						} else {
							isProfesor = false;
							break;
						}
					}
						
					else if (kriterijumi[j].equals("prezime")) {
							if (p.getPrezime().equals(podaci[j])) {
								isProfesor = true;
							} else {
								isProfesor = false;
								break;
							}
						}
						
					else if (kriterijumi[j].equals("brlk")) {
							if (p.getBrlk().equals(podaci[j])) {
								isProfesor = true;
							} else {
								isProfesor = false;
								break;
							}
						}
						
						else if (kriterijumi[j].equals("zvanje")) {
							String zvanje1 = p.getZvanje().toString();
							String zvanje2 = podaci[j];
							if (zvanje1.equals(zvanje2)) {
								isProfesor = true;
							} else {
								isProfesor = false;
								break;
							}
						}
						
						else if (kriterijumi[j].equals("titula")) {
							String titula1 = p.getTitula().toString();
							String titula2 = podaci[j];
							if (titula2.equals("Doktor")) {
								titula2 = "Dr";
							} else if (titula2.equals("Master")) {
								titula2 = "Ms";
							}
							System.out.println(titula1);
							if (titula1.equals(titula2)) {
								isProfesor = true;
							} else {
								isProfesor = false;
								break;
							}
					}
				
				}
				if (isProfesor) 
					profesoriNadjeni.add(p);
			}
		}

		if (profesoriNadjeni.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nije pronađen nijedan profesor datim kriterijumom.", "Neuspešno traženje", JOptionPane.ERROR_MESSAGE);
			ProfesorController.flag  = 0;
		} else {
			this.setFilter_Profesor(profesoriNadjeni);
		} 
		this.setTrenutnoStanje();
		
	}

	 public static Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("dd.MM.yyyy.").parse(date);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	     
	  }
	 
	public void dodajPredmet(Profesor prof, Predmet pred) {
		for (Profesor profa : this.profesori) {
			if (profa.getBrlk().equals(prof.getBrlk())) {
				profa.getPredmeti().add(pred);
			}
		}
	}


	public void obrisiPredmet(Profesor p, Predmet pp) {
		for (Profesor prof : this.profesori) {
			if (prof.getBrlk().equals(p.getBrlk())) {
				for (Predmet pr : prof.getPredmeti()) {
					if (pr.getSifra().equals(pp.getSifra())) {
						prof.getPredmeti().remove(pr);
						break;
					}
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void deserialize() {

		try {
			FileInputStream fis = new FileInputStream("data/dataProfessor.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.profesori = (ArrayList<Profesor>) ois.readObject();
				
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
			
		this.setTekuci_profesori(this.profesori);
		}


	public void obrisiPredmetProfesorima(String sifra) {
		for (Profesor p : this.getProfesori()) {
			for (Predmet pred : p.getPredmeti()) {
				if (pred.getSifra().equals(sifra)) {
					p.getPredmeti().remove(pred);
					break;
				}
			}
		}
	}
	
	public void serialize() {	
		try {
			FileOutputStream fos = new FileOutputStream("data/dataProfessor.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(profesori);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}			
	}

	public void setTrenutnoStanje() {
		if (ProfesorController.flag == 0) {
			this.setTekuci_profesori(this.profesori);
		} else {
			this.setTekuci_profesori(filter_Profesor);
		}
		
	}




}
