package view.dijalozi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import controller.DocumentListenerIzmeniStudenta;
import controller.StudentController;
import model.BazaStudenata;
import model.Student;
import model.Student.Status;
import view.MainFrame;
import view.StudentJTable;

public class DijalogIzmeniS extends JDialog {

	private static final long serialVersionUID = -9189340832633985607L;

	
	public static JTextField imeS;
	public static JTextField przS;
	public static JTextField datRodj;
	public static JTextField adresa;
	public static JTextField brtel;
	public static JTextField briS;
	public static JTextField datumU;
	public static JTextField prosOc;
	public static JTextField email;
	public static JButton okBtn;
	@SuppressWarnings("rawtypes")
	public static JComboBox godStud;
	public static JRadioButton budzet;
	public static JRadioButton samofin;
	public static int flag = 0;
	private DocumentListener documentListener = new DocumentListenerIzmeniStudenta();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DijalogIzmeniS(JFrame parent) {
		super(parent, "Izmena studenta", true);
		
		if(BazaStudenata.getInstance().getStudenti().size()==0) {
			JOptionPane.showMessageDialog(null, "Ne postoji nijedan student", "GREŠKA", JOptionPane.ERROR_MESSAGE );
	        dispose();
		}
		else {
			Student st = new Student();
			st = BazaStudenata.getInstance().getRow(StudentJTable.curr_row);
			
			this.pack();
			this.setSize(MainFrame.width/3, MainFrame.height*3/4);
			
			this.setLayout(new BorderLayout());
			this.setLocationRelativeTo(parent);
			
			JLabel labIme = new JLabel("*Ime:");
			//labIme.setPreferredSize(new Dimension(100, 50));
			labIme.setToolTipText("Unesite ime");
			okBtn  = new JButton("Potvrda");

		    imeS = new JTextField();
			//imeS.setPreferredSize(new Dimension(300, 25));
			imeS.setText(st.getIme());
			        // prezime
			JLabel labPrz = new JLabel("*Prezime:");
			//labPrz.setPreferredSize(new Dimension(100, 50));
			labPrz.setToolTipText("Unesite prezime");
			
			
			przS = new JTextField();
			//przS.setPreferredSize(new Dimension(300, 25));
			przS.setText(st.getPrezime());
			        // datum
			JLabel labDat = new JLabel("*Datum rođenja:");
			//labDat.setPreferredSize(new Dimension(200, 50));
			labDat.setToolTipText("Unesite datum rođenja");
			        
			        
			datRodj = new JTextField();
			//datRodj.setPreferredSize(new Dimension(200, 25));
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.", Locale.ENGLISH);
			
			
			datRodj.setText(dateFormat.format(st.getDatumr()));
			        // adresa
			JLabel labAdresa = new JLabel("*Adresa stanovanja:");
			//labAdresa.setPreferredSize(new Dimension(200, 50));
			labAdresa.setToolTipText("Unesite adresa stanovanja");
			
			adresa = new JTextField();
			//        adresa.setPreferredSize(new Dimension(200, 25));
			
			adresa.setText(st.getAdresa());
			        // br telefona
			JLabel labBrTel = new JLabel("*Broj telefona:");
			//labBrTel.setPreferredSize(new Dimension(100, 50));
			labBrTel.setToolTipText("Unesite broj telefona");
			
			brtel = new JTextField();
			//brtel.setPreferredSize(new Dimension(300, 25));
			brtel.setText(st.getBr_tel());
			        // broj indeksa
			        
			JLabel labBRI = new JLabel("*Broj indeksa:");
			//labBRI.setPreferredSize(new Dimension(100, 50));
			labBRI.setToolTipText("Unesite broj indeksa");
			       
			        
			briS = new JTextField();
			//briS.setPreferredSize(new Dimension(300, 25));
			briS.setText(st.getBri());
			JLabel labGodU = new JLabel("*Datum upisa:");
			//labGodU.setPreferredSize(new Dimension(100, 50));
			labGodU.setToolTipText("Unesite datum upisa");
			       
			        
			datumU = new JTextField();
			//datumU.setPreferredSize(new Dimension(300, 25));
			
			Date date = st.getDatum_upisa();
			
			datumU.setText(dateFormat.format(date));
			 
			// godina studija
			JLabel labGodinaStud = new JLabel("*Trenutna godina studija:");
			//labGodinaStud.setPreferredSize(new Dimension(100, 50));
			        
			JLabel prosecnaOc = new JLabel("*Prosečna ocena:");
			//prosecnaOc.setPreferredSize(new Dimension(100, 50));
			prosecnaOc.setToolTipText("Unesite prosečnu ocenu");
			
			prosOc = new JTextField();
			//prosOc.setPreferredSize(new Dimension(300, 25));
			
			prosOc.setText(String.valueOf(st.getProsek()));
			
			JLabel labEmail = new JLabel("*E-posta: ");
			//labEmail.setPreferredSize(new Dimension(100, 50));
			labEmail.setToolTipText("Unesite email adresu");
			 
			email = new JTextField();
			//email.setPreferredSize(new Dimension(300, 25));
			email.setText(st.getEmail());
			
			String[] godine = { "I (prva)", "II (druga)", "III (treća)", "IV (četvrta)" };
			godStud = new JComboBox(godine);
			//godStud.setPreferredSize(new Dimension(250, 25));
			
			godStud.setSelectedIndex(st.getGodina_stud()-1);
			
			// nacin finansiranja
			budzet = new JRadioButton("Budžet");
			samofin = new JRadioButton("Samofinansiranje");
					// grupa regulise da samo jedan RadioButton može biti čekiran
			ButtonGroup btnGroup1 = new ButtonGroup();
			btnGroup1.add(budzet);
			btnGroup1.add(samofin);
			
			
			if(st.getStatus() == Status.B) {
				budzet.setSelected(true);
			}
			else if(st.getStatus() == Status.S) {
				samofin.setSelected(true);
			} else {
				
			}
			
			okBtn.setBackground(Color.LIGHT_GRAY);
			JButton notokBtn = new JButton("Odustanak");
			notokBtn.setBackground(Color.LIGHT_GRAY);
			
			JPanel up = new JPanel(new GridBagLayout());
			JPanel down = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			
			up.add(labIme, constraintLbl(0, 0));
			up.add(imeS, constraintTF(1, 0));
			up.add(labPrz, constraintLbl(0, 1));
			up.add(przS, constraintTF(1, 1));
			up.add(labDat, constraintLbl(0, 2));
			up.add(datRodj, constraintTF(1, 2));
			up.add(labAdresa, constraintLbl(0, 3));
			up.add(adresa, constraintTF(1, 3));
			up.add(labBrTel, constraintLbl(0, 4));
			up.add(brtel, constraintTF(1, 4));
			up.add(labBRI, constraintLbl(0, 5));
			up.add(briS, constraintTF(1, 5));
			up.add(labGodU, constraintLbl(0, 6));
			up.add(datumU, constraintTF(1, 6));
			up.add(prosecnaOc, constraintLbl(0, 7));
			up.add(prosOc, constraintTF(1, 7));
			up.add(labGodinaStud, constraintLbl(0, 8));
			up.add(godStud, constraintTF(1, 8));
			up.add(labEmail, constraintLbl(0, 9));
			up.add(email, constraintTF(1, 9));
			up.add(budzet, constraintLbl(0, 10));
			up.add(samofin, constraintLbl(0, 11));
			
			imeS.getDocument().addDocumentListener(documentListener);
			przS.getDocument().addDocumentListener(documentListener);
			datRodj.getDocument().addDocumentListener(documentListener);
			adresa.getDocument().addDocumentListener(documentListener);
			brtel.getDocument().addDocumentListener(documentListener);
			briS.getDocument().addDocumentListener(documentListener);
			datumU.getDocument().addDocumentListener(documentListener);
			prosOc.getDocument().addDocumentListener(documentListener);
			email.getDocument().addDocumentListener(documentListener);
		

			okBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {						
					if(StudentController.getInstance().izmeniStudenta()) {
							dispose();
					
					}
				}
			});
			
			notokBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					
				}			
			});
			down.add(notokBtn);
			down.add(okBtn);
			
			this.add(up, BorderLayout.NORTH);
			this.add(down, BorderLayout.SOUTH);
			this.setVisible(true);
					
		}
	}
		private GridBagConstraints constraintLbl(int x,int y) {
			GridBagConstraints gbc= new GridBagConstraints();
			gbc.gridx = x;
			gbc.gridy = y;
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(10, 20, 0, 0);
			return gbc;
		}
		private GridBagConstraints constraintTF(int x,int y) {
			GridBagConstraints gbc= new GridBagConstraints();
			gbc.gridx = x;
			gbc.gridy = y;
			gbc.gridwidth = 3;
			gbc.weightx = 100;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(10, 20, 0, 20);
			return gbc;
		}
		
		public static void proveriPopunjenost() {
			if (imeS.getText().trim().isEmpty() || przS.getText().trim().isEmpty() || briS.getText().trim().isEmpty() ||
					adresa.getText().trim().isEmpty() || brtel.getText().trim().isEmpty() || datumU.getText().trim().isEmpty() 
					|| datRodj.getText().trim().isEmpty() || prosOc.getText().trim().isEmpty())
				okBtn.setEnabled(false);
			else {
				okBtn.setEnabled(true);
			}
		}
			
	}
	

