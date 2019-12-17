package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Student.Status;

public class BazaStudenata {
	private static BazaStudenata instance = null;
	
	public static BazaStudenata getInstance() {
		if(instance == null) {
			instance = new BazaStudenata();
		}
		return instance;
	}
	
	private long generator;
	
	private List<Student> studenti;
	private List<String> kolone;
	
	private BazaStudenata() {
		generator = 0;
		
		initStudente();
		
		this.kolone = new ArrayList<String>();
		this.kolone.add("Indeks");
		this.kolone.add("Ime");
		this.kolone.add("Prezime");
		this.kolone.add("Godina studija");
		this.kolone.add("Status");
		this.kolone.add("Prosek");
	
	}
	
	@SuppressWarnings("deprecation")
	private void initStudente() {
		this.studenti = new ArrayList<Student>();
	
		Student s1 = new Student("Jelena", "Vlajkov", parseDate("29.09.1998"), "Novosadskog sajma 33", "0612190090", "vlajkovj31@gmail.com",
				"RA-32-2017", new Date(2017, 7, 7), 3, Status.B, 9.94);
		
		studenti.add(s1);
		
		Student s2 = new Student("Aleksandra", "Stamenkovic", parseDate("06.12.1998"), "Danila Kisa", "0614684654", "alekstam@gmail.com",
				"RA-120-2017", new Date(2017, 7, 7), 3, Status.B, 9.5);
		
		studenti.add(s2);
	}


	public List<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}

	public int getColumnCount() {
		return 6;
	}

	
	public String getColumnName(int index) {
		return this.kolone.get(index);
	}
	
	public Student getRow(int rowIndex) {
		return this.studenti.get(rowIndex);
	}
	
	public String getValueAt(int row, int column) {
		Student student = this.studenti.get(row);
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
				return "B";
			else 
				return "S";
		case 5:
			return Double.toString(student.getProsek());
	
		default:
			return null;
	
		}
	}
	
	public void dodajStudenta(String bri, String ime, String prezime, Date datRodj, String adresa, String brt, 
			String email, Date upis, int godina_stud, Status status, double prosek) {

		this.studenti.add(new Student(ime, prezime, datRodj, adresa, brt, email, bri, upis, godina_stud, status, prosek));
		
	}
	
	public void izbrisiStudenta(String bri) {
		for(Student s : studenti) {
			if(s.getBri().equals(bri)) {
				studenti.remove(s);
				break;
			}
		}
	}
	
	public void izmeniStudenta(String bri, String ime, String prezime, Date datumr, String adresa, String brt, Date upis, 
			int godina_stud, String email, 
			Status status, double prosek, String br_tel) {
		/*
		 * 	public Student(String ime, String prezime, Date datumr, String adresa, String br_tel, String email,
			String bri, Date datum_upisa, int godina_stud, Status status, double prosek,
			ArrayList<Predmet> predmeti) {
		
		 */
		
		for(Student s : studenti) {
			if(s.getBri().contentEquals(bri)) {
				s.setIme(ime);
				s.setPrezime(prezime);
				s.setGodina_stud(godina_stud);
				s.setEmail(email);
				s.setBr_tel(br_tel);
				s.setStatus(status);
				s.setProsek(prosek);
				s.setAdresa(adresa);
				s.setDatum_upisa(upis);
				s.setDatumr(datumr);
				
			}
		}
	}
	
	
	 public static Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("dd.MM.yyyy").parse(date);
	     } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	     }
	     
	  }
}
