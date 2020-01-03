package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
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

	private long generator;
	
	private List<Profesor> tekuci_profesori;
	private List<String> kolone;
	private ArrayList<Profesor> filter_Profesor = new ArrayList<Profesor>();
	private ArrayList<Profesor> profesori;
	
	private BazaProfesora() {
		generator = 0;
		
		
		tekuci_profesori = new ArrayList<Profesor>();
		filter_Profesor = new ArrayList<Profesor>();
		profesori = new ArrayList<Profesor>();
		//initProfesore();
		this.kolone = new ArrayList<String>();
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Zvanje");
		this.kolone.add("Titula");
		this.kolone.add("Kancelarija");
		this.kolone.add("Adresa stanovanja");
		this.kolone.add("Datum rodjenja");
		this.kolone.add("Email");
		this.kolone.add("Lista predmeta");
		
		this.deserialize();
		
		for (Profesor p : this.profesori) {
			p.setDatumr(parseDate("19.02.1950."));
		}
	}

	private void initProfesore() {
	
		this.profesori = new ArrayList<Profesor>();
		
		/*
		 * rofesor(String ime, String prezime, Date datumr, String adresa, String br_tel, String email,
			String kancelarija, String brlk, String zvanje, String titula) {
		 */
		
		Profesor p1 = new Profesor("Nikola", "Nikolic", parseDate("29.12.1960"), "Braca Tatica", "123456", "nikolicn@gmail.com", "104", "123456", Zvanje.RProfesor, Titula.Dr);
		profesori.add(p1);
		Profesor p2 = new Profesor("Marko", "Markovic", parseDate("29.12.1960"), "Braca Tatica", "468798", "nikolicn@gmail.com", "104", "6544354", Zvanje.Docent, Titula.Dr);
		Profesor p3 = new Profesor("Jovan", "Jovanovic", parseDate("29.12.1960"), "Braca Tatica", "8751354", "nikolicn@gmail.com", "104", "513151", Zvanje.Asistent, Titula.Ms);
		
		profesori.add(p2);
		profesori.add(p3);
		this.tekuci_profesori = this.profesori;
		
		try {
			FileOutputStream fos = new FileOutputStream("data/dataProfessor");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(profesori);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	
	
	public List<Profesor> getProfesori() {
		return tekuci_profesori;
	}

	public void setProfesori(List<Profesor> profesori) {
		this.tekuci_profesori = profesori;
	}
	
	public int getColumnCount() {
		return 9;
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

	public ArrayList<Profesor> getFilter_Profesor() {
		return filter_Profesor;
	}

	public void setFilter_Profesor(ArrayList<Profesor> filter_Profesor) {
		this.filter_Profesor = filter_Profesor;
	}

	public void setProfesori(ArrayList<Profesor> profesori) {
		this.profesori = profesori;
	}
	
	public String getValueAt(int row, int column) {
		Profesor profesor = this.tekuci_profesori.get(row);
		DateFormat datum = new SimpleDateFormat("dd.MM.yyyy.");
		
		switch(column) {
		case 0:
			return profesor.getIme();
		case 1:
			return profesor.getPrezime();
		case 2: 
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
		case 3:
			if (profesor.getTitula() == Titula.Dr) {
				return "Doktor";
			} else {
				return "Master";
			}
		case 4:
			return profesor.getKancelarija();
		case 5:
			return profesor.getAdresa();
		case 6:
			return datum.format(profesor.getDatumr());
		case 7:
			return profesor.getEmail();
		default:
			return null;
		}
	}

	public void dodajProfesora(String ime, String prezime, Date datumr, String adresa, String brtel, String email, 
			String kancelarija, String brlk,  Zvanje zvanje, Titula titula) {
		
		this.profesori.add(new Profesor(ime, prezime, datumr, adresa, brtel, email, kancelarija, brlk, zvanje, titula));
		this.tekuci_profesori = this.profesori;
		
		this.serialize();
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
		
		if(ProfesorController.flag == 0)
			this.tekuci_profesori = this.profesori;
		else 
			this.tekuci_profesori = this.filter_Profesor;
		
		this.serialize();
	}
	
	public void pretraziProfesora(String text) {
		
		if(ProfesorController.flag == 0) {
			this.tekuci_profesori = this.profesori;
			return;
		}
		ArrayList<Profesor> profesoriNadjeni = new ArrayList<Profesor>();
		
		int i = 0;
		String[] deli = text.split(";"); 
		String[] kriterijumi = new String[3];
		String[] podaci = new String[3];
		
		for (String s : deli) {
			String[] pom = s.split(":");
			kriterijumi[i] = pom[0];
			podaci[i] = pom[1];
			i++;
		}
		
		if(!kriterijumi[0].equals("ime") && !kriterijumi[0].equals("prezime") && !kriterijumi[0].equals("brlk")) {
			JOptionPane.showMessageDialog(null, "Kriterijum pretrage je: \n[ime:'Ime'];[prezime'Prezime'];[brlk:'Indeks']", "GRESKA", JOptionPane.ERROR_MESSAGE);
		
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
						
						if (kriterijumi[j].equals("prezime")) {
							if (p.getPrezime().equals(podaci[j])) {
								isProfesor = true;
							} else {
								isProfesor = false;
								break;
							}
						}
						
						if (kriterijumi[j].equals("brlk")) {
							if (p.getBrlk().equals(podaci[j])) {
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
			JOptionPane.showMessageDialog(null, "Nije pronadjen nijedan profesor datim kriterijumom.", "Neuspesno trazenje", JOptionPane.ERROR_MESSAGE);
			this.setTekuci_profesori(this.profesori);
		} else {
			this.setFilter_Profesor(profesoriNadjeni);
			this.setTekuci_profesori(this.filter_Profesor);
		}

		
		
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
		prof.getPredmeti().add(pred);
		this.serialize();
			
	}


	public void obrisiPredmet(Profesor p, Predmet pp) {
		p.getPredmeti().remove(pp);
		this.serialize();
	}
	
	private void deserialize() {

		try {
			FileInputStream fis = new FileInputStream("data/dataProfessor");
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

		
	
	public void serialize() {	
		try {
			FileOutputStream fos = new FileOutputStream("data/dataProfessor");
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
