package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.dijalozi.DijalogIzmeniPredmet;


public class DocumentListenerIzmeniPredmet implements DocumentListener {
 
    public void changedUpdate(DocumentEvent e)
    {
    	DijalogIzmeniPredmet.proveriPopunjenost();
    }
 
    public void insertUpdate(DocumentEvent e)
    {
    	DijalogIzmeniPredmet.proveriPopunjenost();
    }
 
    public void removeUpdate(DocumentEvent e)
    {
    	DijalogIzmeniPredmet.proveriPopunjenost();
    }
}
