/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.strategy.equipment;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_3.model.dive.Dive;
import ncerovec_zadaca_3.model.diver.Diver;
import ncerovec_zadaca_3.model.diver.specialty.SpecialtyDrySuit;
import ncerovec_zadaca_3.model.diver.specialty.SpecialtyNightDive;
import ncerovec_zadaca_3.model.diver.specialty.SpecialtyUnderwaterPhoto;
import ncerovec_zadaca_3.model.equipment.EquipmentObject;

/**
 * 
 * @author nino
 */
public class EquipmentAssignment
{
    Dive dive = null;

    public EquipmentAssignment(Dive dive)
    {
        this.dive = dive;
    }
        
    public boolean assignEquipment(Diver diver)
    {
        boolean diverFullEquipment = true;
        
        List<EquipmentObject> availableMasks = dive.getAvailableEquipment("0.1");
        if(!assignEquipment(diver, availableMasks)) diverFullEquipment = false;
        
        List<EquipmentObject> availableSnorkels = dive.getAvailableEquipment("0.2");
        assignEquipment(diver, availableSnorkels);     //not mandatory to be assigned
        
        List<EquipmentObject> availableFins = dive.getAvailableEquipment("0.3");
        if(!assignEquipment(diver, availableFins)) diverFullEquipment = false;
        
        List<EquipmentObject> availableWetSuits = dive.getAvailableEquipment("1.1.1");
        List<EquipmentObject> availableSemiDrySuits = dive.getAvailableEquipment("1.1.2");
        List<EquipmentObject> availableDrySuits = dive.getAvailableEquipment(SpecialtyDrySuit.requiredEquipmentCategory);

        if(diver.hasSpecialty(SpecialtyDrySuit.title) && !assignEquipment(diver, availableDrySuits))
        {
            if(!assignEquipment(diver, availableSemiDrySuits))
            {
                if(!assignEquipment(diver, availableWetSuits))
                {
                    diverFullEquipment = false;
                }
            }   
        }
        
        List<EquipmentObject> availableGloves = dive.getAvailableEquipment("1.3");
        if(!assignEquipment(diver, availableGloves)) diverFullEquipment = false;
        
        List<EquipmentObject> availableBoots = dive.getAvailableEquipment("1.5");
        if(!assignEquipment(diver, availableBoots)) diverFullEquipment = false;

        List<EquipmentObject> availableDivingTanks = dive.getAvailableEquipment("2.2");
        if(!assignEquipment(diver, availableDivingTanks)) diverFullEquipment = false;
        
        List<EquipmentObject> availableRegulators = dive.getAvailableEquipment("2.1");
        assignEquipment(diver, availableRegulators);    //not mandatory to be assigned
        
        if(dive.isNightDive() && diver.hasSpecialty(SpecialtyNightDive.title))
        {
            List<EquipmentObject> availableNightEquipment = dive.getAvailableEquipment(SpecialtyNightDive.requiredEquipmentCategory);
            if(!assignEquipment(diver, availableNightEquipment)) diverFullEquipment = false;
        }
        
        List<EquipmentObject> availableKnifes = dive.getAvailableEquipment("5.2");
        assignEquipment(diver, availableKnifes);    //not mandatory to be assigned
        
        List<EquipmentObject> availableBuoys = dive.getAvailableEquipment("5.3");
        assignEquipment(diver, availableBuoys);    //not mandatory to be assigned
        
        if(dive.getNumCameras() > 0 && diver.hasSpecialty(SpecialtyUnderwaterPhoto.title))
        {
            List<EquipmentObject> availableCamEquipment = dive.getAvailableEquipment(SpecialtyUnderwaterPhoto.requiredEquipmentCategory);
            if(!assignEquipment(diver, availableCamEquipment)) diverFullEquipment = false;
        }
        
        return diverFullEquipment;
    }
    
    private boolean assignEquipment(Diver diver, List<EquipmentObject> availableEquipment)
    {
        if(availableEquipment.size() > 0)
        {        
            EquipmentObject equipment = findSuitableEquipment(availableEquipment);
            if(equipment != null)
            {
                List<EquipmentObject> diverEquipment = this.dive.getEquipmentAssignments().get(diver);

                if(diverEquipment == null)
                {
                    diverEquipment = new ArrayList();
                    this.dive.getEquipmentAssignments().put(diver, diverEquipment);
                }

                //assign dependency equipment
                List<String> dependencyEquipmentIds = equipment.getDependencyEquipment();            
                for(String id : dependencyEquipmentIds)
                {
                    if(!equipment.getId().startsWith(id))   //dependency not from same category (infinite-loop)
                    {
                        List<EquipmentObject> dependencyEquipment = dive.getAvailableEquipment(id);
                        if(assignEquipment(diver, dependencyEquipment) == false) return false;
                    }
                }

                diverEquipment.add(equipment);

                //remove one item from stock
                int currQty = equipment.getQty();
                equipment.setQty(--currQty);

                return true;
            }
        }
        
        return false;
    }
    
    private EquipmentObject findSuitableEquipment(List<EquipmentObject> availableEquipment)
    {
        for(EquipmentObject equipment : availableEquipment)
        {
            if(equipment.getQty() > 0) return equipment;
        }
        
        return null;
    }
}
