package view;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import model.BazaStudenata;

import java.sql.Date;


public class AbstractTableModelStudenti extends AbstractTableModel {

	private static final long serialVersionUID = 7523214924550006969L;
		
	public AbstractTableModelStudenti() {}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex >= 8;
	}
	
	@Override
	public int getColumnCount() {
		return BazaStudenata.getInstance().getColumnCount();
	}

	@Override
	public int getRowCount() {
		return BazaStudenata.getInstance().getTekuca_lista().size();
	}

	@Override
	public String getColumnName(int column) {
		return BazaStudenata.getInstance().getColumnName(column);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex < 8)
			return BazaStudenata.getInstance().getValueAt(rowIndex, columnIndex);
		else if(columnIndex >= 8) {
			JButton button = new JButton("" + rowIndex);
			return button;
		}
		return null;
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		case 4:
			return String.class;
		case 5:
			return Double.class;
		case 6:
			return Date.class;
		case 7: 
			return Date.class;
		case 8:
			return JButton.class;
		case 9:
			return JButton.class;
		default:
			return null;
		}
	}
	
}
