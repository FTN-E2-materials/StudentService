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

import controller.StudentController;
import model.Student.Status;

public class BazaStudenata {
	private static BazaStudenata instance = null;
	
	public static BazaStudenata getInstance() {
		if(instance == null) {
			instance = new BazaStudenata();
		}
		return instance;
	}
	
	public List<Student> studenti;
	private List<String> kolone;
	public List<Student> filter_Student;
	public List<Student> tekuca_lista;
	
	private BazaStudenata() {

		this.kolone = new ArrayList<String>();
		this.kolone.add("Indeks");
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Godina studija");
		this.kolone.add("Status");
		this.kolone.add("Prosek");
		this.kolone.add("Datum rođenja");
		this.kolone.add("Datum upisa");
		this.kolone.add("Lista predmeta");
		this.kolone.add("Detalji");
		
		this.studenti = new ArrayList<Student>();
		this.tekuca_lista = new ArrayList<Student>();
		this.filter_Student = new ArrayList<Student>();
		
		this.deserialize();
		this.setTrenutnoStanje();
	}
	
	@SuppressWarnings("unchecked")
	public void deserialize() {

		try {
			FileInputStream fis = new FileInputStream("data/dataStudents.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.studenti = (ArrayList<Student>) ois.readObject();
			
			ois.close();
			fis.close();
		}         catch (IOException ioe) 
        {
            System.out.println(ioe.getMessage());
        } 
        catch (ClassNotFoundException c) 
        {
            System.out.println("Class not found");
            System.out.println(c.getMessage());
            return;
        }
	}
	
	public List<Student> getStudenti() {
		return this.studenti;
	}

	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
		this.setTrenutnoStanje();
	}

	public int getColumnCount() {
		return 10;
	}

	
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	public Student getRow(int rowIndex) {
		return this.tekuca_lista.get(rowIndex);
	}
	
	public Object getValueAt(int row, int column) {
		Student student = this.tekuca_lista.get(row);
	
		switch(column) {
		case 0:
			return student.getBri();
		case 1:
			return student.getIme();
		case 2:
			return student.getPrezime();
		case 3:
			return Integer.toString(student.getGodina_stud());
		case 4:
			if(student.getStatus() == Status.B)
				return "Budžet";
			else 
				return "Samofinansiranje";
		case 5:
			return student.getProsek();
		case 6:
			return student.getDatumr();
		case 7:
			return student.getDatum_upisa();
			
		default:
			return null;
	
		}
	}
	
	public void dodajStudenta(String bri, String ime, String prezime, Date datRodj, String adresa, String brt, 
			String email, Date upis, int godina_stud, Status status, double prosek) {

		this.studenti.add(new Student(ime, prezime, datRodj, adresa, brt, email, bri, upis, godina_stud, status, prosek));
		this.setTrenutnoStanje();
		
	}
	
	public void izbrisiStudenta(String bri) {
		for(Student s : studenti) {
			if(s.getBri().equals(bri)) {
				studenti.remove(s);
				break;
			}
		}
		for(Student s : filter_Student) {
			if(s.getBri().equals(bri)) {
				filter_Student.remove(s);
				break;
			}
		}	
		this.setTrenutnoStanje();
	}
	
	public void izmeniStudenta(String bri, String ime, String prezime, Date datumr, String adresa, String brt, Date upis, 
			int godina_stud, String email, 
			Status status, double prosek) {
		
		for(Student s : studenti) {
			if(s.getBri().contentEquals(bri)) {
				s.setIme(ime);
				s.setPrezime(prezime);
				s.setGodina_stud(godina_stud);
				s.setEmail(email);
				s.setBr_tel(brt);
				s.setStatus(status);
				s.setProsek(prosek);
				s.setAdresa(adresa);
				s.setDatum_upisa(upis);
				s.setDatumr(datumr);
				
			}
		}

		for(Student s : filter_Student) {
			if(s.getBri().contentEquals(bri)) {
				s.setIme(ime);
				s.setPrezime(prezime);
				s.setGodina_stud(godina_stud);
				s.setEmail(email);
				s.setBr_tel(brt);
				s.setStatus(status);
				s.setProsek(prosek);
				s.setAdresa(adresa);
				s.setDatum_upisa(upis);
				s.setDatumr(datumr);
			}
		}
		
		this.setTrenutnoStanje();
	}
	
	public void pretraziStudenta(String text) {
		if(StudentController.flag == 0) {
			this.tekuca_lista = this.studenti;
			return;
		}
		
		ArrayList<Student> studentiNadjeni = new ArrayList<>();
	
		int i = 0;
		String[] deli = text.split(";");
		String[] kriterijumi = new String[5];
		String[] podaci = new String[5];
		
		for (String s : deli) {
			String[] pom = s.split(":");
			kriterijumi[i] = pom[0];
			podaci[i] = pom[1];
			i++;
		}
		if(!kriterijumi[0].equals("ime") && !kriterijumi[0].equals("prezime") && !kriterijumi[0].equals("indeks") && !kriterijumi[0].equals("status") && !kriterijumi[0].equals("godina")) {
			JOptionPane.showMessageDialog(null, "Kriterijum pretrage je: \n[ime:'Ime'];[prezime'Prezime'];[indeks:'Indeks'];[status:'Status'].", "GRESKA", JOptionPane.ERROR_MESSAGE);
		
		} else {
			boolean isStudent = false;
			
			for (Student s : BazaStudenata.getInstance().getStudenti()) {
				
				for (int j = 0; j < i; j++) {
					if(kriterijumi[j].equals("ime")) {
						if (s.getIme().equals(podaci[j])) {
							isStudent = true;
						}
						else {
							isStudent = false;
							break;
						}
					
					} else if (kriterijumi[j].equals("prezime")) {
						if (s.getPrezime().equals(podaci[j])) {
							isStudent = true;
						} 
						else {
							isStudent = false;
							break;
						}
					} else if (kriterijumi[j].equals("indeks")) {
						if (s.getBri().equals(podaci[j])) {
							isStudent = true;
						} 
						else {
							isStudent = false;
							break;
						}
					} else if (kriterijumi[j].equals("status")) {
						if (s.getStatus().toString().equals(podaci[j]) || s.getStatus().equals(podaci[j])) {
							isStudent = true;
						} 
						else if (podaci[j].equals("Budžet")) {
							if (s.getStatus().toString().equals("B")) {
								isStudent = true;
							} else {
								isStudent = false;
								break;
							}
						} else if (podaci[j].equals("Samofinansiranje")) {
							if (s.getStatus().toString().equals("S")) {
								isStudent = true;
							} else {
								isStudent = false;
								break;
							}
						}
						else {
							isStudent = false;
							break;
						}
					} else if (kriterijumi[j].equals("godina")) {
						if (s.getGodina_stud() == Integer.parseInt(podaci[j])) {
							isStudent = true;
						} else {
							isStudent = false;
						}
					}
					
					
				}
				if (isStudent) 
					studentiNadjeni.add(s);		 
			}
		}
		
		if (studentiNadjeni.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nije pronađen nijedan student datim kriterijumom.", "Neuspešno traženje", JOptionPane.ERROR_MESSAGE);
			StudentController.flag = 0;
		} else {
			this.setFilter_Student(studentiNadjeni);
		}
		
		this.setTrenutnoStanje();

	}
	
	 public List<Student> getTekuca_lista() {
		return tekuca_lista;
	}

	public void setTekuca_lista(List<Student> tekuca_lista) {
		this.tekuca_lista = tekuca_lista;
	}

	public List<Student> getFilter_Student() {
		return filter_Student;
	}

	public void setFilter_Student(List<Student> filter_Student) {
		this.filter_Student = filter_Student;
	}

	public static Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("dd.MM.yyyy.").parse(date);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }	     
	  }
	 
	 public void obrisiPredmet(Predmet p, String bri) {
		 for (Student st : this.studenti) {
			 if (st.getBri().equals(bri)) {
				 for (Predmet pr : st.getPredmeti()) {
					if (pr.getSifra().equals(p.getSifra())) {
						st.getPredmeti().remove(pr);
						break;
					}
				 }
			 }
		 }
		 
		 for (Student st : this.filter_Student) {
			 if (st.getBri().equals(bri)) {
				 for (Predmet pr : st.getPredmeti()) {
					if (pr.getSifra().equals(p.getSifra())) {
						st.getPredmeti().remove(pr);
						break;
					}
				 }
			 }
		 }
	}

	public void dodajPredmet(Student s, Predmet p) {
		for (Student st : this.studenti) {
			if (st.getBri().equals(s.getBri())) {
				st.getPredmeti().add(p);
			}
		}
		this.setTrenutnoStanje();
	}
		 
	 public void serialize() {
		 
			try {
				FileOutputStream fos = new FileOutputStream("data/dataStudents.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(this.studenti);
				oos.close();
				fos.close();
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
	 }
	 
	public void setTrenutnoStanje() {
		if(StudentController.flag == 0) {
			this.tekuca_lista = this.studenti;
		}
		else {
			this.tekuca_lista = this.filter_Student;
		}

			
	}
}
