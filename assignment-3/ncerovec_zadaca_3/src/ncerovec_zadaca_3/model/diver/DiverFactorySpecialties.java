/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.model.diver;

import java.util.List;
import ncerovec_zadaca_3.model.diver.specialty.Specialty;
import ncerovec_zadaca_3.model.diver.specialty.SpecialtyDrySuit;
import ncerovec_zadaca_3.model.diver.specialty.SpecialtyNightDive;
import ncerovec_zadaca_3.model.diver.specialty.SpecialtyUnderwaterPhoto;

/**
 * FACTORY METHOD - ConcreteCreator
 * @author nino
 */
public class DiverFactorySpecialties extends DiverFactory
{
    List<String> specialtiesAssignmentsList = null;
    DiverFactoryAgency diverFactoryAgency = new DiverFactoryAgency();

    public DiverFactorySpecialties(List<String> specialtiesAssignmentsList)
    {
        this.specialtiesAssignmentsList = specialtiesAssignmentsList;
    }
    
    @Override
    public Diver createDiver(String name, String agency, String lvl, int birthYear)
    {
        Diver newDiver = diverFactoryAgency.createDiver(name, agency, lvl, birthYear);
        
        for(String specialtyAssignment : this.specialtiesAssignmentsList)
        {
            String[] diverAssignment = specialtyAssignment.split(";");
            
            if(diverAssignment.length == 2 && diverAssignment[0].equals(name))
            {
                Specialty diverSpecialty = null;
                String specialtyTitle = diverAssignment[1];
                
                switch(specialtyTitle)
                {
                    case SpecialtyDrySuit.title:
                        diverSpecialty = new SpecialtyDrySuit();
                    break;
                    
                    case SpecialtyNightDive.title:
                        diverSpecialty = new SpecialtyNightDive();
                    break;
                    
                    case SpecialtyUnderwaterPhoto.title:
                        diverSpecialty = new SpecialtyUnderwaterPhoto();
                    break;
                    
                    default:
                        System.out.println(specialtyTitle + " - specijalnost nije pronađena!");
                    break;
                }
                
                if(diverSpecialty != null) newDiver.getSpecialties().add(diverSpecialty);
            }
        }
        
        return newDiver;
    }   
}
