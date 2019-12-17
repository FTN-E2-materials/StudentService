
import model.BazaProfesora;
import model.BazaStudenata;
import view.MainFrame;

public class StudentskaSluzba {

	public static void main(String[] args) {

		BazaStudenata.getInstance();
		BazaProfesora.getInstance();
		MainFrame.getInstance();
	}

}
