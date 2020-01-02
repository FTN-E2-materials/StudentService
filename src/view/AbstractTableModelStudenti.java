package view;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import controller.StudentController;
import model.BazaStudenata;


import java.util.ArrayList;


public class AbstractTableModelStudenti extends AbstractTableModel {

	private static final long serialVersionUID = 7523214924550006969L;
		
	public AbstractTableModelStudenti() {}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 10;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return BazaStudenata.getInstance().getColumnCount();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if (StudentController.flag == 0)
			return BazaStudenata.getInstance().getStudenti().size();
		else 
			return BazaStudenata.getInstance().getFilter_Student().size();
	}

	@Override
	public String getColumnName(int column) {
		return BazaStudenata.getInstance().getColumnName(column);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if(columnIndex < 10)
			return BazaStudenata.getInstance().getValueAt(rowIndex, columnIndex);
		else if(columnIndex == 10) {
			JButton button = new JButton("" + rowIndex);
			return button;
		}
		return null;
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
			return JButton.class;
		default:
			return null;
		}
	}
	
}
