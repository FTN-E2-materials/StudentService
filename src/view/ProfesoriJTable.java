package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ProfesoriJTable extends JTable {

	private static final long serialVersionUID = 2432377729858813772L;
	
	
	public static int curr_row = -1;
	public static TableModel table_model;
	public static JTable tabela = null;
	
	
	public ProfesoriJTable() {
		
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelProfesori());
		new PredmetiKodProfesora(this, 10);
		this.getTableHeader().setReorderingAllowed(false);
		this.getColumnModel().getColumn(7).setCellRenderer(new DateCellRenderer());
		
		table_model = this.getModel();
		
		// soritranje
		
		TableRowSorter<TableModel> sort = new TableRowSorter<>(table_model);
		this.setRowSorter(sort);
		sort.setSortable(10, false);
		
		tabela = this;

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		this.setDefaultRenderer(String.class, centerRenderer);
		
		// trenutni selektovani red (polje je public static
		// moze mu se pristupiti u bilo kom trenutku
		
	    this.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mousePressed(MouseEvent e) {
	    		JTable tabela = (JTable)e.getComponent();
	        	curr_row = tabela.convertRowIndexToModel(tabela.getSelectedRow());
	    	}
	        @Override
	        public void mouseReleased(MouseEvent e) {
	        	JTable tabela = (JTable)e.getComponent();
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
			// objasnjenje za vracanje na -1 u tabeli StudentJTable
			
			curr_row = -1;
			((AbstractTableModel) table_model).fireTableDataChanged();
		}

}
