
import model.BazaPredmeta;
import model.BazaProfesora;
import model.BazaStudenata;
import view.MainFrame;

public class StudentskaSluzba {

	public static void main(String[] args) {

		BazaStudenata.getInstance();
		BazaProfesora.getInstance();
		BazaPredmeta.getInstance();
		MainFrame.getInstance();
	}

}
