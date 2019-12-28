package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class PredmetiJTable extends JTable {

	private static final long serialVersionUID = -1404313258591137307L;
	public  static int curr_row;
	public static TableModel table_model;
	public static JTable tabela = null;
	
	public PredmetiJTable() {

		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelPredmeti());
		new StudentiNaPredmetu(this, 5);
		
		tabela = this;
		table_model = tabela.getModel();
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				JTable table = (JTable)e.getComponent();
				if(table.getSelectedRow() != -1) 
					curr_row = table.convertRowIndexToModel(table.getSelectedRow());
			}
		});
		
		TableRowSorter<TableModel> sort = new TableRowSorter<>(this.getModel());
		this.setRowSorter(sort);
		sort.setSortable(5, false);
		//sort();	
		
	}
	
	private void sort() {
		// TODO Auto-generated method stub
		TableRowSorter<TableModel> sort = new TableRowSorter<>();
		tabela.setRowSorter(sort);
		//
	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		if (isRowSelected(row)) {
			c.setBackground(Color.LIGHT_GRAY);
		} else {
			c.setBackground(Color.WHITE);
		}
		return c;
	}
	
	public static void refresh() {
		((AbstractTableModel) table_model).fireTableDataChanged();
	}
}
