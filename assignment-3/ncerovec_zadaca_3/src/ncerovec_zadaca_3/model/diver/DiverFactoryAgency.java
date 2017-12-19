/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.model.diver;

import ncerovec_zadaca_3.model.Level;
import ncerovec_zadaca_3.model.agency.AgencyBSAC;
import ncerovec_zadaca_3.model.agency.AgencyCMAS;
import ncerovec_zadaca_3.model.agency.AgencyNAUI;
import ncerovec_zadaca_3.model.agency.AgencySSI;

/**
 * FACTORY METHOD - ConcreteCreator
 * @author nino
 */
public class DiverFactoryAgency extends DiverFactory
{
    @Override
    public Diver createDiver(String name, String agency, String lvl, int birthYear)
    {
        Diver newDiver = null;
        Level agencyLevel = null;
        
        switch(agency)
        {
            case "SSI":
                agencyLevel = AgencySSI.getInstance().getLevel(lvl);
                if(agencyLevel != null)
                {
                    newDiver = new DiverSSI(name, lvl, agencyLevel, birthYear);
                }
            break;
            
            case "NAUI":
                agencyLevel = AgencyNAUI.getInstance().getLevel(lvl);
                if(agencyLevel != null)
                {
                    newDiver = new DiverNAUI(name, lvl, agencyLevel, birthYear);
                }
            break;
            
            case "BSAC":
                agencyLevel = AgencyBSAC.getInstance().getLevel(lvl);
                if(agencyLevel != null)
                {
                    newDiver = new DiverBSAC(name, lvl, agencyLevel, birthYear);
                }
            break;
            
            case "CMAS":
                agencyLevel = AgencyCMAS.getInstance().getLevel(lvl);
                if(agencyLevel != null)
                {
                    newDiver = new DiverCMAS(name, lvl, agencyLevel, birthYear);
                }
            break;
        }
        
        return newDiver;
    }   
}
