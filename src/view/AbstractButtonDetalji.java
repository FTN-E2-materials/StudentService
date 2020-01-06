package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class AbstractButtonDetalji extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, MouseListener{

	private static final long serialVersionUID = 5585163159493689269L;
	private JButton renderer;
	private JButton editor;
	private JTable tabela;
	private boolean activeEditor = false;
	
	public AbstractButtonDetalji(JTable table, int column) {
		this.tabela = table;
		this.tabela.getColumnModel().getColumn(column).setCellEditor(this);
		this.tabela.getColumnModel().getColumn(column).setCellRenderer(this);
		this.tabela.addMouseListener(this);
		
	
	
		this.renderer = new JButton("Detalji");
		this.editor = new JButton("Detalji");
		
		this.editor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireEditingStopped();
				int ind = 0;
				ind = tabela.convertRowIndexToModel(tabela.getSelectedRow());
				Detalji det = new Detalji(new JFrame(), ind);
			}
			
		});
		
		this.activeEditor = false;
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		// TODO Auto-generated method stub
		return new PredmetiTablePanel(this.renderer, 50, 50);
	}
	

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component getTableCellEditorComponent(JTable talbe, Object value, boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		return new PredmetiTablePanel(this.editor, 50, 50);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		if(tabela.isEditing() && tabela.getCellEditor() == this) {
			this.activeEditor = true;
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(activeEditor && tabela.isEditing()) {
			tabela.getCellEditor().stopCellEditing();
		}
		activeEditor = false;
	}
	

}
