package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.dijalozi.DijalogDodajPredmet;
// DocumentListeneri za enablovanje dugmeta Potvrdi

public class DocumentListenerDodajPredmet implements DocumentListener {
    public void changedUpdate(DocumentEvent e)
    {
    	DijalogDodajPredmet.proveriPopunjenost();
    }
 
    public void insertUpdate(DocumentEvent e)
    {
    	DijalogDodajPredmet.proveriPopunjenost();
    }
 
    public void removeUpdate(DocumentEvent e)
    {
    	DijalogDodajPredmet.proveriPopunjenost();
    }
}
