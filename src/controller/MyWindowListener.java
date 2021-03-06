package controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import model.BazaPredmeta;
import model.BazaProfesora;
import model.BazaStudenata;

public class MyWindowListener implements WindowListener {

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		JFrame frame = (JFrame) arg0.getComponent();
		UIManager.put("OptionPane.yesButtonText", "Da");
		UIManager.put("OptionPane.noButtonText", "Nе");
		int code = JOptionPane.showConfirmDialog(frame, "Da li ste sigurni da želite da zatvorite?",
				"Zatvaranje aplikacije?", JOptionPane.YES_NO_OPTION);
		if (code != JOptionPane.YES_OPTION) {
			frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		} else {
			// Serijalizacija prilikom zatvaranja aplikacije 
			BazaStudenata.getInstance().serialize();
			BazaPredmeta.getInstance().serialize();
			BazaProfesora.getInstance().serialize();
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

}
