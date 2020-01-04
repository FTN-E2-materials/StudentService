package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.dijalozi.DijalogIzmeniS;

public class DocumentListenerIzmeniStudenta implements DocumentListener {
 
    public void changedUpdate(DocumentEvent e)
    {
    	DijalogIzmeniS.proveriPopunjenost();
    }
 
    public void insertUpdate(DocumentEvent e)
    {
    	DijalogIzmeniS.proveriPopunjenost();
    }
 
    public void removeUpdate(DocumentEvent e)
    {
    	DijalogIzmeniS.proveriPopunjenost();
    }
}
