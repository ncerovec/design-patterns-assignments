/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.mvc.equipment;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_4.model.Club;
import ncerovec_zadaca_4.model.dive.Dive;
import ncerovec_zadaca_4.model.diver.Diver;
import ncerovec_zadaca_4.model.equipment.EquipmentPart;
import ncerovec_zadaca_4.model.equipment.loan.EquipmentLoan;
import ncerovec_zadaca_4.mvc.Model;

/**
 * MVC - ConcreteModel
 * @author nino
 */
public class EquipmentModel extends Model
{
    private Dive dive = null;
    private List<EquipmentPart> equipmentList = null;

    public List<EquipmentPart> getEquipmentList() { return equipmentList; }
    
    public EquipmentModel(Dive dive)
    {
        this.dive = dive;
        if(dive != null) this.equipmentList = dive.getEquipmentAssignments().get(null);
    }
    
    public void getDiverEquipment(String diverName)
    {
        //alternative (from Diver.equipmentLoan)
        for(Diver diver : Club.getInstance().getDivers())
        {
            if(diver != null && diver.getName().equals(diverName))
            {
                this.equipmentList = new ArrayList();
                
                for(EquipmentLoan equipmentLoan : diver.getEquipmentLoan())
                {
                    this.equipmentList.add(equipmentLoan.getLoanEquipment());
                }
                
                notifyController();
            }
        }
        
        //alternative (from Dive.equipmentAssignments)
        /*
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
        */      
    }
    
    public void getDiveEquipment()
    {
        if(dive != null) this.equipmentList = this.dive.getEquipmentAssignments().get(null);
        notifyController();
    }
}