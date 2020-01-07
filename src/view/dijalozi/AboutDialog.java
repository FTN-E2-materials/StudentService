package view.dijalozi;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class AboutDialog extends JDialog {

	private static final long serialVersionUID = -7094331571093004098L;

	public AboutDialog(JFrame parent) {
		super(parent, "About", null);
		this.setSize(parent.getWidth()/2, parent.getHeight()/2);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setModal(true);
		JPanel panel1 = new JPanel();
		TitledBorder title;
		title = BorderFactory.createTitledBorder("ABOUT");
		panel1.setBorder(title);
		
		JLabel oApp = new JLabel();
		oApp.setText("<html>Aplikacija služi za čuvanje podataka  o studentima, profesoriama i predmetima na fakultetu.<br/>O tome kako se aplikacija koristi, pogledajte detaljnije u odeljku Help (prečica: CTRL + H).</html>");
		panel1.add(oApp);
		JPanel panel2 = new JPanel();
		JLabel picLabel = null;
		JLabel picLabel2 = null;
		panel2.setLayout(new GridBagLayout());
		
		try {
			BufferedImage jelena = ImageIO.read(new File("images/jelena.png"));
			BufferedImage aleksandra = ImageIO.read(new File("images/aleksandra.png"));
			picLabel = new JLabel(new ImageIcon(jelena));
			picLabel2 = new JLabel(new ImageIcon(aleksandra));
			picLabel.setSize(50, 50);
			picLabel2.setSize(50, 50);
			panel2.add(picLabel, lbl(0, 0));
			panel2.add(picLabel2, lbl(1, 0));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		this.add(panel2, BorderLayout.CENTER);
		this.add(panel1, BorderLayout.NORTH);
		
		
		this.setVisible(true);
	}
	

	private GridBagConstraints lbl(int x,int y) {
		GridBagConstraints gbc= new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 2;

		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(20, 10, 0, 10);
		return gbc;
	}
	
	private GridBagConstraints tf(int x,int y) {
		GridBagConstraints gbc= new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 2;
		gbc.weightx = 10;

		gbc.insets = new Insets(4, 4, 4, 4);
		return gbc;
	}
}
