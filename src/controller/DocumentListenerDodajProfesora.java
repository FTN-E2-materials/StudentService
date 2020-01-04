package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.dijalozi.DijalogDodajProfesora;

public class DocumentListenerDodajProfesora implements DocumentListener {
		 
	    public void changedUpdate(DocumentEvent e)
	    {
	    	DijalogDodajProfesora.proveriPopunjenost();
	    }
	 
	    public void insertUpdate(DocumentEvent e)
	    {
	    	DijalogDodajProfesora.proveriPopunjenost();
	    }
	 
	    public void removeUpdate(DocumentEvent e)
	    {
	    	DijalogDodajProfesora.proveriPopunjenost();
	    }

}
