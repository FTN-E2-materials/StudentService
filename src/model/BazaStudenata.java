package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import controller.StudentController;
import model.Student.Status;
import view.Tabele;
import view.Toolbar;

public class BazaStudenata {
	private static BazaStudenata instance = null;
	
	public static BazaStudenata getInstance() {
		if(instance == null) {
			instance = new BazaStudenata();
		}
		return instance;
	}
	
	private long generator;
	
	public List<Student> studenti;
	private List<String> kolone;
	public List<Student> filter_Student;
	public List<Student> tekuca_lista;
	
	private BazaStudenata() {
		generator = 0;

		//initStudente();
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("Indeks");
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Godina studija");
		this.kolone.add("Status");
		this.kolone.add("Prosek");
		this.kolone.add("Datum rodjenja");
		this.kolone.add("Email");
		this.kolone.add("Broj telefona");
		this.kolone.add("Datum upisa");
		this.kolone.add("Lista predmeta");
		
		this.studenti = new ArrayList<Student>();
		this.tekuca_lista = new ArrayList<Student>();
		this.filter_Student = new ArrayList<Student>();
		
		this.deserialize();
		
		setTekuca_lista(this.studenti);
		for (Student s : getStudenti()) {
			System.out.println(s);
		}
 		
	}
	
	@SuppressWarnings("unchecked")
	public void deserialize() {

		try {
			FileInputStream fis = new FileInputStream("data/dataStudents");
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
/*
	private void initStudente() {
		this.studenti = new ArrayList<Student>();
		this.tekuca_lista = new ArrayList<Student>();
		this.filter_Student = new ArrayList<Student>();
		
		Student s1 = new Student("Jelena", "Vlajkov", parseDate("29.09.1998"), "Novosadskog sajma 33", "0612190090", "vlajkovj31@gmail.com",
				"RA-32-2017",  parseDate("07.07.2017"), 3, Status.B, 9.94);
		
		studenti.add(s1);
		
		Student s2 = new Student("Aleksandra", "Stamenkovic", parseDate("06.12.1998"), "Danila Kisa", "0614684654", "alekstam@gmail.com",
				"RA-123-2017", parseDate("07.07.2017"), 3, Status.B, 9.5);
		
		studenti.add(s2);
		this.tekuca_lista = this.studenti;
	}
*/
	
	public List<Student> getStudenti() {
		return this.studenti;
	}

	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
		this.tekuca_lista = this.studenti; 
	}

	public int getColumnCount() {
		return 11;
	}

	
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	public Student getRow(int rowIndex) {
		return this.tekuca_lista.get(rowIndex);
	}
	
	public String getValueAt(int row, int column) {
		Student student = this.tekuca_lista.get(row);
		
		DateFormat datum = new SimpleDateFormat("dd.MM.yyyy");
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
				return "Budzet";
			else 
				return "Samofinansiranje";
		case 5:
			return Double.toString(student.getProsek());
		case 6:
			return datum.format(student.getDatumr());
		case 7:
			return student.getEmail();
		case 8:
			return student.getBr_tel();
		case 9:
			return datum.format(student.getDatum_upisa());
		default:
			return null;
	
		}
	}
	
	public void dodajStudenta(String bri, String ime, String prezime, Date datRodj, String adresa, String brt, 
			String email, Date upis, int godina_stud, Status status, double prosek) {

		this.studenti.add(new Student(ime, prezime, datRodj, adresa, brt, email, bri, upis, godina_stud, status, prosek));
		
		this.setTrenutnoStanje();
		this.serialize();
		
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
		this.serialize();
		
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

			this.serialize();
			this.setTrenutnoStanje();
	}

		
		if(StudentController.flag == 0) {
			this.tekuca_lista = this.studenti;
		}
		else {
			this.tekuca_lista = this.filter_Student;
		}
	}
	
	public void pretraziStudenta(String text) {
		if(StudentController.flag == 0) {
			this.tekuca_lista = this.studenti;
			return;
		}
		
		ArrayList<Student> studentiNadjeni = new ArrayList<>();
	
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
		if(!kriterijumi[0].equals("ime") && !kriterijumi[0].equals("prezime") && !kriterijumi[0].equals("indeks")) {
			JOptionPane.showMessageDialog(null, "Kriterijum pretrage je: \n[ime:'Ime'];[prezime'Prezime'];[indeks:'Indeks']", "GRESKA", JOptionPane.ERROR_MESSAGE);
		
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
					}
					
				}
				if (isStudent) 
					studentiNadjeni.add(s);		 
			}
		}
		
		if (studentiNadjeni.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nije pronadjen nijedan student datim kriterijumom.", "Neuspesno trazenje", JOptionPane.ERROR_MESSAGE);
			this.setTekuca_lista(this.studenti);
		} else {
			this.setFilter_Student(studentiNadjeni);
			this.setTekuca_lista(this.filter_Student);
		}

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
	         return new SimpleDateFormat("dd.MM.yyyy").parse(date);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }	     
	  }
	 
	 public void obrisiPredmet(Predmet p, Student s) {
		
		p.getStudenti().remove(s);
		s.getPredmeti().remove(p);
		
		BazaPredmeta.getInstance().serialize();
		this.serialize();
		
	}


	public void dodajPredmet(Student s, Predmet p) {
		s.getPredmeti().add(p);
		this.serialize();
		this.setTrenutnoStanje();
	}
		 
	 public void serialize() {
		 
			try {
				FileOutputStream fos = new FileOutputStream("data/dataStudents");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(this.studenti);
				oos.close();
				fos.close();
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
	 }
	 
	private void setTrenutnoStanje() {
		if(StudentController.flag == 0) {
			this.tekuca_lista = this.studenti;
		}
		else {
			this.tekuca_lista = this.filter_Student;
		}

			
	}
}
