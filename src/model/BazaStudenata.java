package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	private long generator;
	
	public ArrayList<Student> studenti;
	private List<String> kolone;
	public ArrayList<Student> filter_Student;
	public List<Student> tekuca_lista;
	
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
		this.tekuca_lista = new ArrayList<Student>();
	
		Student s1 = new Student("Jelena", "Vlajkov", parseDate("29.09.1998"), "Novosadskog sajma 33", "0612190090", "vlajkovj31@gmail.com",
				"RA-32-2017",  parseDate("07.07.2017"), 3, Status.B, 9.94);
		
		studenti.add(s1);
		
		Student s2 = new Student("Aleksandra", "Stamenkovic", parseDate("06.12.1998"), "Danila Kisa", "0614684654", "alekstam@gmail.com",
				"RA-123-2017", parseDate("07.07.2017"), 3, Status.B, 9.5);
		
		studenti.add(s2);
		this.tekuca_lista = this.studenti;
	}


	public List<Student> getStudenti() {
		return tekuca_lista;
	}

	public void setStudenti(List<Student> studenti) {
		this.tekuca_lista = studenti;
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
		this.tekuca_lista = this.studenti;
		
	}
	
	public void izbrisiStudenta(String bri) {
		for(Student s : studenti) {
			if(s.getBri().equals(bri)) {
				studenti.remove(s);
				break;
			}
		}
		this.tekuca_lista = this.studenti;
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
		this.tekuca_lista = this.studenti;
	}
	
	public void pretraziStudenta(String s) {
		if(StudentController.flag == 0) {
			this.tekuca_lista = this.studenti;
			return;
		}
		String[] kriterijumi = s.split(";");
		String ime;
		String prezime;
		String bri;
		
		String[] pom = kriterijumi[0].split(":");
		ime = pom[1];

		String[] pom2 = kriterijumi[1].split(":");

		prezime = pom2[1];

		String[] pom3 = kriterijumi[2].split(":");
		bri = pom3[1];
		filter_Student = new ArrayList<Student>();
		System.out.println(bri);
		for (Student student : studenti ) {
			if (student.getBri().equals(bri) && student.getIme().equals(ime) && student.getPrezime().equals(prezime))
				filter_Student.add(student);
			System.out.println(student);
		}
		this.tekuca_lista = filter_Student;
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
