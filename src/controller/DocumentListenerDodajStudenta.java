package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.dijalozi.DijalogStudent;

public class DocumentListenerDodajStudenta implements DocumentListener {
 
    public void changedUpdate(DocumentEvent e)
    {
    	DijalogStudent.proveriPopunjenost();
    }
 
    public void insertUpdate(DocumentEvent e)
    {
    	DijalogStudent.proveriPopunjenost();
    }
 
    public void removeUpdate(DocumentEvent e)
    {
    	DijalogStudent.proveriPopunjenost();
    }

}
