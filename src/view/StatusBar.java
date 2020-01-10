package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.Timer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel {

	private static final long serialVersionUID = -7960319300551657388L;

	public StatusBar() {
	super();
	setLayout(new BorderLayout());
	
	this.setBackground(Color.LIGHT_GRAY);

	
    JLabel labelTitle = new JLabel("Studentska služba");
    Date datum = new Date();
    
    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
    JLabel time = new JLabel(formatter.format(datum));
    
    Timer timer = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Date date = new Date();
			time.setText(formatter.format(date));
		}
    	
    });
    
    timer.start();
    
   
	
	this.add(labelTitle, BorderLayout.WEST);
	this.add(time, BorderLayout.EAST);

	
	/*
	 * 
	 * 
	 * label.setHorizontalAlignment( JLabel.RIGHT );
    Container cont = getContentPane();
    cont.add( label, BorderLayout.SOUTH );
    Timer timer = new Timer( 1000, this );
    timer.start();
  }
  public void actionPerformed( ActionEvent ae )
  {
    label.setText( sdf.format( new GregorianCalendar().getTime() ) );
  }
  public static void main(String[] args) 
  {
    TimeFrame tf = new TimeFrame();
    tf.setVisible( true );
  }
}
	 */
	}
}