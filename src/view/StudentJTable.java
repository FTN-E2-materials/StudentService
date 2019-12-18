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

public class StudentJTable extends JTable {


	private static final long serialVersionUID = 6561723438237241247L;
	public static int curr_row;
	public static TableModel table_model;
	public static JTable tabela = null;
	
	public StudentJTable() {
	
		// pravljenje tabele
	
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelStudenti());
		this.getTableHeader().setReorderingAllowed(false);

		table_model = this.getModel();
		tabela = this;
	
		sort();
		// trenutni selektovani red (polje je public static
		// moze mu se pristupiti u bilo kom trenutku
		
	    this.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseReleased(MouseEvent e) {
	        	JTable tabela = (JTable)e.getComponent();


	        	if(tabela.getSelectedRow() != -1)
	        		curr_row = tabela.convertRowIndexToModel(tabela.getSelectedRow());
	        }
	    });
	
	}
	
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
			// azuriranje prikaza
			((AbstractTableModel) table_model).fireTableDataChanged();
		}

		public void sort() {
			TableRowSorter<TableModel> sort = new TableRowSorter<>(table_model);
			this.setRowSorter(sort);
			
		}


}

