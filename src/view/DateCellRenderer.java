package view;

import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class DateCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 6927885642081252372L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		String strDate = df.format((Date) value);
		this.setText(strDate);
		return this;
	}
}
