package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.dijalozi.DodajStudentaNaPredmet;

public class DocumentListenerDodajStudentaNaPredmet implements DocumentListener {
    public void changedUpdate(DocumentEvent e)
    {
    	DodajStudentaNaPredmet.proveriPopunjenost();
    }
 
    public void insertUpdate(DocumentEvent e)
    {
    	DodajStudentaNaPredmet.proveriPopunjenost();
    }
 
    public void removeUpdate(DocumentEvent e)
    {
    	DodajStudentaNaPredmet.proveriPopunjenost();
    }

}
