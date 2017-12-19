/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.mvc;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_3.memento.Caretaker;
import ncerovec_zadaca_3.memento.DiversMemento;
import ncerovec_zadaca_3.model.Club;
import ncerovec_zadaca_3.model.diver.Diver;

/**
 * MVC - ConcreteModel, MEMENTO - Originator
 * @author nino
 */
public class DiversModel extends Model
{
    Caretaker diversCaretaker = new Caretaker();
    
    private List<Diver> diverList = null;
    public List<Diver> getDiverList() { return diverList; }
    
    public DiversModel(List<Diver> diverList)
    {
        this.diverList = diverList;
        diversCaretaker.addMemento(this.saveToMemento());
    }
    
    private Object saveToMemento() { return new DiversMemento(diverList); }
    private void restoreFromMemento(Object memento)
    {
        if(memento instanceof DiversMemento)
        {
            DiversMemento diversMemento = (DiversMemento) memento;
            this.diverList = new ArrayList(diversMemento.getSavedState());
        }
    }

    public void removeDiver(String diverName)
    {
        Diver delDiver = null;
        for(Diver diver : this.diverList)
        {
            if(diver.getName().equals(diverName))
            {
                delDiver = diver;
                break;
            }
        }
        
        if(delDiver != null)
        {
            this.diverList.remove(delDiver);
            notifyController();
        }
    }
    
    public void getInitialDivers()
    {
        this.restoreFromMemento(diversCaretaker.getLastMemento());
        notifyController();
    }

    public void getAllDivers()
    {
        this.diverList = new ArrayList(Club.getInstance().getDivers());
        notifyController();
    }
}