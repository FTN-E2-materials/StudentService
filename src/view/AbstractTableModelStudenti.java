package view;

import javax.swing.table.AbstractTableModel;

import model.BazaStudenata;


import java.util.ArrayList;


public class AbstractTableModelStudenti extends AbstractTableModel {


	private static final long serialVersionUID = 7523214924550006969L;
	
	private ArrayList<Boolean> koJeOtkacen;
	
	public AbstractTableModelStudenti() {
		this.koJeOtkacen = new ArrayList<>();
		for(int i = 0; i < BazaStudenata.getInstance().getStudenti().size(); i++) {
			koJeOtkacen.add(false);
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex >= 6;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return BazaStudenata.getInstance().getColumnCount();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return BazaStudenata.getInstance().getStudenti().size();
	}

	@Override
	public String getColumnName(int column) {
		return BazaStudenata.getInstance().getColumnName(column);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return BazaStudenata.getInstance().getValueAt(rowIndex, columnIndex);
	}
	
	public void studentDodat() {
		this.koJeOtkacen.add(false);
	}
	
	public void studentUklonjen(int rowIndex) {
		this.koJeOtkacen.remove(rowIndex);
	}
	
}
