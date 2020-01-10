package view.dijalozi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import view.MainFrame;

public class AboutDialog extends JDialog {

	private static final long serialVersionUID = -7094331571093004098L;

	public AboutDialog(JFrame parent) {
		super(parent, "About", null);
		this.setSize(MainFrame.width*3/4, MainFrame.height*3/4);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setModal(true);
		JPanel panel1 = new JPanel();
		TitledBorder title;
		title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "ABOUT", javax.swing.border.
			      TitledBorder.CENTER, javax.swing.border.
			      TitledBorder.CENTER, null, java.awt.Color.black);
		
		panel1.setBorder(title);
		
		JLabel oApp = new JLabel();
		oApp.setText("<html>Aplikacija služi za čuvanje podataka  o studentima, profesoriama i predmetima na fakultetu.<br/>O tome kako se aplikacija koristi, pogledajte detaljnije u odeljku Help (prečica: CTRL + H).</html>");
		panel1.add(oApp);
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel picLabel = new JLabel();
		JLabel picLabel2 = new JLabel();
		JPanel student1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel student2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		try {
			BufferedImage jelena = ImageIO.read(new File("images/jelena.png"));
			BufferedImage aleksandra = ImageIO.read(new File("images/aleksandra.png"));
			Image dimg1 = jelena.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			Image dimg2 = aleksandra.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			picLabel.setIcon(new ImageIcon(dimg1));
			picLabel2.setIcon(new ImageIcon(dimg2));
			student1.add(picLabel);
			student1.add(Box.createHorizontalStrut(50));
			student2.add(picLabel2);
			student2.add(Box.createHorizontalStrut(50));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		BoxLayout layout = new BoxLayout(panel2, BoxLayout.Y_AXIS);
		panel2.setLayout(layout);
		TitledBorder titleS1;
		titleS1 = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Student 1");
		TitledBorder titleS2;
		titleS2 = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Student 2");
		student2.setBorder(titleS2);
		student1.setBorder(titleS1);
		
		JLabel oJeleni = new JLabel();
		JLabel oAleksandri = new JLabel();
		oJeleni.setText("<html>Jelena Vlajkov, rođena 29.09.1998. u Kikindi,<br/> završila je srednju školu Gimnazija Dušan Vasiljev u Kikindi.<br/>"
				+ "Trenutno je student treće godine smera Računarstvo i automatika.<br/>U slobodno vreme voli da istražuje novu muziku, <br/>da se igra sa svojim mačkom Shonetom</br> i druži sa svojim drugarima.</html>");
		student1.add(oJeleni);
		oAleksandri.setText("");
		student1.add(oJeleni);
	
		student2.add(oAleksandri);
		
		panel2.add(student1);
		panel2.add(student2);
		
		//this.add(studenti, BorderLayout.CENTER);
		this.add(panel2, BorderLayout.CENTER);
		this.add(panel1, BorderLayout.NORTH);
		
		
		this.setVisible(true);
	}
}
