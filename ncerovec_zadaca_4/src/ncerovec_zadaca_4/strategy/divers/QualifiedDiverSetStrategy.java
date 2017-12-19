/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.strategy.divers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import ncerovec_zadaca_4.context.DiversContext;
import ncerovec_zadaca_4.model.Club;
import ncerovec_zadaca_4.model.dive.Dive;
import ncerovec_zadaca_4.model.diver.Diver;
import ncerovec_zadaca_4.model.diver.specialty.SpecialtyDrySuit;
import ncerovec_zadaca_4.model.diver.specialty.SpecialtyNightDive;
import ncerovec_zadaca_4.model.diver.specialty.SpecialtyUnderwaterPhoto;
import ncerovec_zadaca_4.strategy.equipment.EquipmentAssignment;

/**
 * STRATEGY - ConcreteStrategy
 * @author nino
 */
public class QualifiedDiverSetStrategy implements DiverSetStrategy
{
    Random randGenerator = null;
    
    public QualifiedDiverSetStrategy(int randSeed)
    {
        this.randGenerator = new Random(randSeed);
    }
    
    @Override
    public List<Diver> generateDivers(Dive dive)
    {
        List<Diver> qualifiedDivers = new ArrayList<>();
        
        EquipmentAssignment ea = new EquipmentAssignment(dive, this.randGenerator);
        ea.returnEquipment(dive.getDateTime()); //update returned equipment quantities (Club.equipmentStock)
        
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
        
        //if(requiredCameras > 0) { qualifiedDivers.clear(); }    //not enough qualified divers for underwater photo
        
        if(qualifiedDivers.size() >= dive.getNumDivers() && requiredCameras <= 0)
        {
            qualifiedDivers = filterDivers(qualifiedDivers, dive.getNumDivers());
            
            //MVC - user interface (print divers/equipment/dives)
        
            DiversContext.showDiversView(qualifiedDivers);   //print qualified divers for dive
            qualifiedDivers = DiversContext.diversController.getDiversModel().getDiverList();   //choosen divers for dive

            for(Diver qualifiedDiver : qualifiedDivers)
            {
                //add diver property - full/partial/none equipment
                if(ea.assignEquipment(qualifiedDiver))
                {
                    qualifiedDiver.setEquipmentStatus(Diver.EquipmentStatus.FULL);
                }
                else
                {
                    qualifiedDiver.setEquipmentStatus(Diver.EquipmentStatus.NONE);
                }
            }

            //EquipmentContext.showEquipmentView(dive); //print assigned equipment for dive
            
        }
        else
        {
            //equipment not borrowed if there is not enough qualified divers
            System.out.println("Nedovoljan broj kvalificiranih ronioca za uron!");
        }
        
        //BUG: noćni uron 
        //Ispis ronioca za noćni uron ne funkcionira u ConEmu konzoli. (ronioci pronađeni i ispisuju se u NetBeans konzoli)
        
        return qualifiedDivers;
    }
    
    private List<Diver> filterDivers(List<Diver> qualifiedDivers, int numDivers)
    {
        while(qualifiedDivers.size() > numDivers)
        {
            Diver removeDiver = null;
            int maxNumDives = Integer.MIN_VALUE;
            
            for(Diver diver : qualifiedDivers)
            {
                int divesNum = Club.getInstance().getDiverDives(diver).size();
                
                //remove diver with the most dives (leave the diver with less dives - assimilate)
                if(maxNumDives < divesNum)
                {
                    removeDiver = diver;
                    maxNumDives = divesNum;
                }
                else if(maxNumDives == divesNum)    
                {
                    Date currRecentDive = Club.getInstance().getMostRecentDive(removeDiver);
                    Date diverRecentDive = Club.getInstance().getMostRecentDive(diver);
                    
                    
                        //remove diver with the most recent dive (leave the diver with older most recent dive - renew)
                        if(currRecentDive == null && diverRecentDive != null)
                        {
                            removeDiver = diver;
                        }
                        else if(currRecentDive != null && diverRecentDive != null && currRecentDive.getTime() < diverRecentDive.getTime())
                        {
                            removeDiver = diver;
                        }
                        else if((currRecentDive == null && diverRecentDive == null) || (currRecentDive.getTime() == diverRecentDive.getTime()))
                        {
                            //remove diver with the highest level (leave the diver with lower level - practice)
                            if(diver.getLevel().getAbsLevel() > removeDiver.getLevel().getAbsLevel())
                            {
                                removeDiver = diver;
                            }
                        }
                }
            }
            
            qualifiedDivers.remove(removeDiver);
        }
        
        return qualifiedDivers;
    }
}