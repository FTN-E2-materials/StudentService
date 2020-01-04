package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.dijalozi.DijalogIzmeniProfesora;

public class DocumentListenerIzmeniProfesora implements DocumentListener {
	 
    public void changedUpdate(DocumentEvent e)
    {
    	DijalogIzmeniProfesora.proveriPopunjenost();
    }
 
    public void insertUpdate(DocumentEvent e)
    {
    	DijalogIzmeniProfesora.proveriPopunjenost();
    }
 
    public void removeUpdate(DocumentEvent e)
    {
    	DijalogIzmeniProfesora.proveriPopunjenost();
    }

}
