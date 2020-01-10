package view;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import model.BazaProfesora;

import java.sql.Date;

	public class AbstractTableModelProfesori extends AbstractTableModel {

		private static final long serialVersionUID = -7886221481965601776L;
		
		public AbstractTableModelProfesori() {}
		
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return columnIndex == 9;
		}
	
		@Override
		public int getColumnCount() {
			return BazaProfesora.getInstance().getColumnCount();
		}

		@Override
		public int getRowCount() {
			return BazaProfesora.getInstance().getTekuci_profesori().size();
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
				return String.class;
			case 6:
				return Date.class;
			case 7:
				return String.class;
			case 8:
				return String.class;
			case 9:
				return JButton.class;
			default:
				return null;
			}
		}
		@Override
		public String getColumnName(int column) {
			return BazaProfesora.getInstance().getColumnName(column);
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return BazaProfesora.getInstance().getValueAt(rowIndex, columnIndex);
		}
		
}
