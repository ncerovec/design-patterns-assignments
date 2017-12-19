/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.mvc;

import java.util.List;
import ncerovec_zadaca_3.model.dive.Dive;
import ncerovec_zadaca_3.model.diver.Diver;
import ncerovec_zadaca_3.model.equipment.EquipmentObject;

/**
 * MVC - ConcreteModel
 * @author nino
 */
public class EquipmentModel extends Model
{
    private Dive dive = null;
    private List<EquipmentObject> equipmentList = null;

    public List<EquipmentObject> getEquipmentList() { return equipmentList; }
    
    public EquipmentModel(Dive dive)
    {
        this.dive = dive;
        if(dive != null) this.equipmentList = dive.getEquipmentAssignments().get(null);
    }
    
    public void getDiverEquipment(String diverName)
    {
        Diver ownerDiver = null;
        for(Diver diver : this.dive.getEquipmentAssignments().keySet())
        {
            if(diver != null && diver.getName().equals(diverName))
            {
                ownerDiver = diver;
            }
        }
        
        if(ownerDiver != null)
        {
            this.equipmentList = this.dive.getEquipmentAssignments().get(ownerDiver);
            notifyController();
        }
    }
    
    public void getDiveEquipment()
    {
        if(dive != null) this.equipmentList = this.dive.getEquipmentAssignments().get(null);
        notifyController();
    }
}