package view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import controller.ControllerEntiteta;

public class MenuBar extends JMenuBar {
	
private static final long serialVersionUID = -4199568253533821789L;

public MenuBar() {
	JMenu newMenu = new JMenu("File");
	JMenu edit = new JMenu("Edit");
	JMenu help = new JMenu("Help");

	newMenu.setMnemonic(KeyEvent.VK_N);
	edit.setMnemonic(KeyEvent.VK_E);
	help.setMnemonic(KeyEvent.VK_H);
	
	JMenuItem newItem = new JMenuItem("New");
	JMenuItem closeItem = new JMenuItem("Close");
	newItem.setIcon(setIkonice("images/student_add.png"));
	newItem.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			ControllerEntiteta.getInstance().dodajEntitet();
		}
		
	});
	
	newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
	closeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
	
	
	closeItem.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int code = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da zatvorite?",
					"Zatvaranje aplikacije?", JOptionPane.YES_NO_OPTION);
			if (code != JOptionPane.YES_OPTION) {
				
			} else {
				System.exit(1);
			}
		}
		
	});
	
	newMenu.add(newItem);
	newMenu.addSeparator();
	newMenu.add(closeItem);
	
	JMenuItem editItem = new JMenuItem("Edit");
	JMenuItem deleteItem = new JMenuItem("Delete");
	
	editItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
	deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
	
	editItem.setIcon(setIkonice("images/edit.png"));
	deleteItem.setIcon(setIkonice("images/delete.png"));
	editItem.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			ControllerEntiteta.getInstance().izmeniEntitet();
		}	
	});
	
	deleteItem.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			ControllerEntiteta.getInstance().obrisiEntitet();
		}
		
	});
	
	edit.add(editItem);
	edit.addSeparator();
	edit.add(deleteItem);

	JMenuItem helpItem = new JMenuItem("Help");
	JMenuItem aboutItem = new JMenuItem("About");
	helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
	aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
	helpItem.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ControllerEntiteta.getInstance().pokaziHelp();
		}
		
	});
	
	aboutItem.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ControllerEntiteta.getInstance().pokaziAbout();
			
		}
		
	});
	help.add(helpItem);
	help.addSeparator();
	help.add(aboutItem);

	add(newMenu);
	add(edit);
	add(help);

	}

private ImageIcon setIkonice(String txt) {
	ImageIcon icon = new ImageIcon(txt);
	Image img = icon.getImage() ;  
	Image newimg3 = img.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH ) ;  
    icon = new ImageIcon(newimg3);
    return icon;
}
}
