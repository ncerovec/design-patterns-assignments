/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.strategy.divers;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_3.DiversContext;
import ncerovec_zadaca_3.EquipmentContext;
import ncerovec_zadaca_3.model.Club;
import ncerovec_zadaca_3.model.dive.Dive;
import ncerovec_zadaca_3.model.diver.Diver;
import ncerovec_zadaca_3.model.diver.specialty.SpecialtyDrySuit;
import ncerovec_zadaca_3.model.diver.specialty.SpecialtyNightDive;
import ncerovec_zadaca_3.model.diver.specialty.SpecialtyUnderwaterPhoto;
import ncerovec_zadaca_3.mvc.DiversController;
import ncerovec_zadaca_3.mvc.DiversModel;
import ncerovec_zadaca_3.mvc.EquipmentController;
import ncerovec_zadaca_3.mvc.EquipmentModel;
import ncerovec_zadaca_3.strategy.equipment.EquipmentAssignment;

/**
 * STRATEGY - ConcreteStrategy
 * @author nino
 */
public class QualifiedDiverSetStrategy implements DiverSetStrategy
{
    @Override
    public List<Diver> generateDivers(Dive dive)
    {
        List<Diver> qualifiedDivers = new ArrayList<>();
        
        EquipmentAssignment ea = new EquipmentAssignment(dive);
        
        int requiredCameras = dive.getNumCameras();
        boolean applicableDrySuit = dive.getAvailableEquipment(SpecialtyDrySuit.requiredEquipmentCategory).size() > 0;

        List<Diver> diverList = Club.getInstance().getDivers();
        for(Diver diverCandidate : diverList)
        {
            if(diverCandidate.getLevel().getPotentialMaxDepth() < dive.getMaxDepth()) continue;            
            if(dive.isNightDive() && !diverCandidate.hasSpecialty(SpecialtyNightDive.title)) continue;
            if(applicableDrySuit && !diverCandidate.hasSpecialty(SpecialtyDrySuit.title)) continue;            
            if(requiredCameras > 0 && diverCandidate.hasSpecialty(SpecialtyUnderwaterPhoto.title)) { requiredCameras--; }
            
            qualifiedDivers.add(diverCandidate);    //diver has passed all requirement filters and is qualified diver
        }
        
        if(requiredCameras > 0)
        {
            qualifiedDivers.clear();
            System.out.println("Nedovoljan broj kvalificiranih ronioca za snimanje!");
        }
        
        DiversModel diversModel = new DiversModel(qualifiedDivers);
        DiversContext.diversController = new DiversController(diversModel, DiversContext.diversView);
        diversModel.setController(DiversContext.diversController);
        DiversContext.diversView.setController(DiversContext.diversController);
        DiversContext.diversController.updateView();
        DiversContext.diversController.enableInput();
        
        qualifiedDivers = diversModel.getDiverList();   //choosen divers for dive
        for(Diver qualifiedDiver : qualifiedDivers)
        {
            if(ea.assignEquipment(qualifiedDiver))
            {
                //add diver property - full/partial equipment
            }
        }
        
        EquipmentModel equipmentModel = new EquipmentModel(dive);
        EquipmentContext.equipmentController = new EquipmentController(equipmentModel, EquipmentContext.equipmentView);
        equipmentModel.setController(EquipmentContext.equipmentController);
        EquipmentContext.equipmentView.setController(EquipmentContext.equipmentController);
        EquipmentContext.equipmentController.updateView();
        EquipmentContext.equipmentController.enableInput();
        
        return qualifiedDivers;
    }
}