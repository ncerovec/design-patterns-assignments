/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.strategy.equipment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import ncerovec_zadaca_4.context.EquipmentContext;
import ncerovec_zadaca_4.model.Club;
import ncerovec_zadaca_4.model.dive.Dive;
import ncerovec_zadaca_4.model.diver.Diver;
import ncerovec_zadaca_4.model.diver.specialty.SpecialtyUnderwaterPhoto;
import ncerovec_zadaca_4.model.equipment.EquipmentCategory;
import ncerovec_zadaca_4.model.equipment.EquipmentPart;
import ncerovec_zadaca_4.model.equipment.loan.EquipmentLoan;

/**
 * LEASING - Grantor
 * @author nino
 */
public class EquipmentAssignment
{
    Dive dive = null;
    Club club = null;
    
    Random randGenerator = null;

    public EquipmentAssignment(Dive dive, Random randGenerator)
    {
        this.dive = dive;
        this.club = Club.getInstance();
        this.randGenerator = randGenerator;
    }
            
    public boolean assignEquipment(Diver diver)
    {
        boolean diverFullEquipment = true;

        //add diver to equipment assignments list (initialize with currently borrowed equipment)
        this.dive.getEquipmentAssignments().put(diver, new ArrayList(diver.getBorrowedEquipment()));
                
        //ASSIGN EQUIPMENT PARTS
        
        if(!diver.hasEquipment(EquipmentCategory.Id.MASK))
        {
            List<EquipmentPart> availableMasks = dive.getAvailableEquipment(EquipmentCategory.Id.MASK);
            if(!assignEquipment(diver, availableMasks)) diverFullEquipment = false;
        }
        
        if(!diver.hasEquipment(EquipmentCategory.Id.SNORKEL))
        {
            List<EquipmentPart> availableSnorkels = dive.getAvailableEquipment(EquipmentCategory.Id.SNORKEL);
            assignEquipment(diver, availableSnorkels);     //not mandatory to be assigned
        }
        
        if(!diver.hasEquipment(EquipmentCategory.Id.FINS))
        {
            List<EquipmentPart> availableFins = dive.getAvailableEquipment(EquipmentCategory.Id.FINS);
            if(!assignEquipment(diver, availableFins)) diverFullEquipment = false;
        }
        
        List<EquipmentPart> availableWetSuits = dive.getAvailableEquipment(EquipmentCategory.Id.WET_SUIT);
        List<EquipmentPart> availableSemiDrySuits = dive.getAvailableEquipment(EquipmentCategory.Id.SEMI_DRY_SUIT);
        List<EquipmentPart> availableDrySuits = dive.getAvailableEquipment(EquipmentCategory.Id.DRY_SUIT);

        if(availableDrySuits.size() > 0)
        {
            if(!diver.hasEquipment(EquipmentCategory.Id.DRY_SUIT))
            {
                if(!assignEquipment(diver, availableDrySuits))  diverFullEquipment = false;
            }
        }
        else if(availableSemiDrySuits.size() > 0)
        {
            if(!diver.hasEquipment(EquipmentCategory.Id.SEMI_DRY_SUIT))
            {
                if(!assignEquipment(diver, availableSemiDrySuits))  diverFullEquipment = false;
            }
        }
        else if(availableWetSuits.size() > 0)
        {
            if(!diver.hasEquipment(EquipmentCategory.Id.WET_SUIT))
            {
                if(!assignEquipment(diver, availableWetSuits))  diverFullEquipment = false;
            }
        }
        else
        {
            diverFullEquipment = false;
        }
                
        if(!diver.hasEquipment(EquipmentCategory.Id.GLOVES))
        {
            List<EquipmentPart> availableGloves = dive.getAvailableEquipment(EquipmentCategory.Id.GLOVES);
            if(!assignEquipment(diver, availableGloves)) diverFullEquipment = false;
        }
        
        if(!diver.hasEquipment(EquipmentCategory.Id.BOOTS))
        {
            List<EquipmentPart> availableBoots = dive.getAvailableEquipment(EquipmentCategory.Id.BOOTS);
            if(!assignEquipment(diver, availableBoots)) diverFullEquipment = false;
        }

        if(!diver.hasEquipment(EquipmentCategory.Id.REGULATOR))
        {
            List<EquipmentPart> availableRegulators = dive.getAvailableEquipment(EquipmentCategory.Id.REGULATOR);
            assignEquipment(diver, availableRegulators);    //not mandatory to be assigned
        }
        
        if(!diver.hasEquipment(EquipmentCategory.Id.DIVE_TANK))
        {
            List<EquipmentPart> availableDivingTanks = dive.getAvailableEquipment(EquipmentCategory.Id.DIVE_TANK);
            if(!assignEquipment(diver, availableDivingTanks)) diverFullEquipment = false;
        }
        
        if(!diver.hasEquipment(EquipmentCategory.Id.BCD))
        {
            List<EquipmentPart> availableBCDs = dive.getAvailableEquipment(EquipmentCategory.Id.BCD);
            assignEquipment(diver, availableBCDs);    //not mandatory to be assigned
        }
        
        if(!diver.hasEquipment(EquipmentCategory.Id.LEAD))
        {
            List<EquipmentPart> availableLeads = dive.getAvailableEquipment(EquipmentCategory.Id.LEAD);
            assignEquipment(diver, availableLeads);    //not mandatory to be assigned
        }
        
        if(!diver.hasEquipment(EquipmentCategory.Id.COMPASS))
        {
            List<EquipmentPart> availableCompasses = dive.getAvailableEquipment(EquipmentCategory.Id.COMPASS);
            assignEquipment(diver, availableCompasses);    //not mandatory to be assigned
        }
        
        if(dive.isNightDive() && !diver.hasEquipment(EquipmentCategory.Id.LIGHT))
        {
            List<EquipmentPart> availableNightEquipment = dive.getAvailableEquipment(EquipmentCategory.Id.LIGHT);
            if(!assignEquipment(diver, availableNightEquipment)) diverFullEquipment = false;
        }
        
        if(!diver.hasEquipment(EquipmentCategory.Id.KNIFE))
        {
            List<EquipmentPart> availableKnifes = dive.getAvailableEquipment(EquipmentCategory.Id.KNIFE);
            assignEquipment(diver, availableKnifes);    //not mandatory to be assigned
        }
        
        if(!diver.hasEquipment(EquipmentCategory.Id.BUOY))
        {
            List<EquipmentPart> availableBuoys = dive.getAvailableEquipment(EquipmentCategory.Id.BUOY);
            assignEquipment(diver, availableBuoys);    //not mandatory to be assigned
        }
        
        if(dive.getNumCameras() > 0 && diver.hasSpecialty(SpecialtyUnderwaterPhoto.title) && !diver.hasEquipment(EquipmentCategory.Id.CAMERA))
        {
            List<EquipmentPart> availableCamEquipment = dive.getAvailableEquipment(EquipmentCategory.Id.CAMERA);
            assignEquipment(diver, availableCamEquipment);  //not mandatory to be assigned
        }
        
        return diverFullEquipment;
    }
    
    private boolean assignEquipment(Diver diver, List<EquipmentPart> availableEquipment)
    {
        List<EquipmentPart> diverEquipment = this.dive.getEquipmentAssignments().get(diver);            
        
        if(availableEquipment.size() > 0 && diverEquipment != null)
        {        
            EquipmentPart stockEquipment = findSuitableEquipment(availableEquipment);
            
            if(stockEquipment != null)
            {
                //assign dependency equipment
                List<String> dependencyEquipmentIds = stockEquipment.getDependencyEquipment();            
                for(String id : dependencyEquipmentIds)
                {
                    if(!stockEquipment.getId().startsWith(id))   //dependency not from same category (infinite-loop)
                    {
                        List<EquipmentPart> dependencyEquipment = dive.getAvailableEquipment(id);
                        if(assignEquipment(diver, dependencyEquipment) == false) return false;
                    }
                }

                //clone equipment to have new instance (single equipment part) - assing/loan single equipment part to diver
                EquipmentPart equipment = (EquipmentPart) stockEquipment.clone();   
                equipment.setQty(1);                
                
                //assign to diver in Dive.equipmentAssignments
                diverEquipment.add(equipment);
                //loan to diver in Diver.equipmentLoan
                diver.getEquipmentLoan().add(new EquipmentLoan(equipment, this.dive.getDateTime(), this.randGenerator.nextInt(EquipmentContext.maxLoanDays)+1));

                //remove one item from 'dive stock' (Dive.equipmentAssignments) and 'club stock' (Club.equipment): double evidention - same instance
                int currQty = stockEquipment.getQty()-1;
                stockEquipment.setQty(currQty);

                return true;
            }
        }
        
        return false;
    }
            
    private EquipmentPart findSuitableEquipment(List<EquipmentPart> availableEquipment)
    {
        for(EquipmentPart equipment : availableEquipment)
        {
            if(equipment.getQty() > 0) return equipment;
        }
        
        return null;
    }
    
    public void returnEquipment(Date currDate)
    {
        for(Diver diver : Club.getInstance().getDivers())
        {
            List<EquipmentLoan> diverEquipmentLoans = diver.getEquipmentLoan();
            
            for(int i=diverEquipmentLoans.size()-1; i>=0; i--)
            {
                EquipmentLoan equipmentLoan = diverEquipmentLoans.get(i);
                
                if(equipmentLoan.getLoanEnd().getTime() < currDate.getTime())
                {
                    int returnQty = equipmentLoan.getLoanEquipment().getQty();
                                        
                    EquipmentPart eqStock = Club.getInstance().getEquipment(equipmentLoan.getLoanEquipment().getId());
                    eqStock.setQty(eqStock.getQty() + returnQty);
                    
                    //equipmentLoan.getLoanEquipment().setQty(0);
                    diverEquipmentLoans.remove(equipmentLoan);
                }
            }
        }
    }
}
