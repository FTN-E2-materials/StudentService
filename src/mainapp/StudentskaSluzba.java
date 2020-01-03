package mainapp;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import model.BazaPredmeta;
import model.BazaProfesora;
import model.BazaStudenata;
import view.MainFrame;

public class StudentskaSluzba {

	public static void main(String[] args) {
	
	try {
    	UIManager.setLookAndFeel(
		UIManager.getSystemLookAndFeelClassName());
    } 
    catch (UnsupportedLookAndFeelException e) {
    	System.out.println(e.getMessage());
    }
    catch (ClassNotFoundException e) {
    	System.out.println(e.getMessage());
    }
    catch (InstantiationException e) {
    	System.out.println(e.getMessage());
    }
    catch (IllegalAccessException e) {
       System.out.println(e.getMessage());
    }
		
	
	MainFrame.getInstance();
	
	}

}
