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


public class PredmetiJTable extends JTable {

	private static final long serialVersionUID = -1404313258591137307L;
	public  static int curr_row = -1;
	public static TableModel table_model;
	public static JTable tabela = null;
	
	public PredmetiJTable() {
		this.getTableHeader().setReorderingAllowed(false);
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelPredmeti());
		new StudentiNaPredmetu(this, 5);
		
		tabela = this;
		table_model = tabela.getModel();
	
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JTable table = (JTable)e.getComponent();

				curr_row = table.convertRowIndexToModel(table.getSelectedRow());
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				JTable table = (JTable)e.getComponent();

				curr_row = table.convertRowIndexToModel(table.getSelectedRow());
			}
		});
		
		TableRowSorter<TableModel> sort = new TableRowSorter<>(this.getModel());
		this.setRowSorter(sort);
		sort.setSortable(5, false);	
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		this.setDefaultRenderer(String.class, centerRenderer);
		
		
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
		// objasnjenje za vracanje na -1 u tabeli StudentJTable
		
		curr_row = -1;
		((AbstractTableModel) table_model).fireTableDataChanged();
	}
}
