/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * MEMENTO - Caretaker
 * @author nino
 */
public class Caretaker
{
    private List<Object> savedStates = new ArrayList<Object>();
    
    public void addMemento(Object m) { savedStates.add(m); }
    public Object getMemento(int index) { return savedStates.get(index); }
    public Object getLastMemento() { return savedStates.get(savedStates.size()-1); }
}
