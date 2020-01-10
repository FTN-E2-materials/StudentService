package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.dijalozi.DodajProfesoraNaPredmet;


public class DocumentListenerDodajProfesoraNaPredmet implements DocumentListener {
 
    public void changedUpdate(DocumentEvent e)
    {
    	DodajProfesoraNaPredmet.proveriPopunjenost();
    }
 
    public void insertUpdate(DocumentEvent e)
    {
    	DodajProfesoraNaPredmet.proveriPopunjenost();
    }
 
    public void removeUpdate(DocumentEvent e)
    {
    	DodajProfesoraNaPredmet.proveriPopunjenost();
    }
}
