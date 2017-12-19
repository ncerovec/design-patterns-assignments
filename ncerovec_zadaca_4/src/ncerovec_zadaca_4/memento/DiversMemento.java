/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.memento;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_4.model.diver.Diver;

/**
 * MEMENTO - Memento
 * @author nino
 */
public class DiversMemento
{
    private List<Diver> diverList;
    
    public DiversMemento(List<Diver> stateToSave) { diverList = new ArrayList(stateToSave); }
    
    public List<Diver> getSavedState() { return diverList; }
}
