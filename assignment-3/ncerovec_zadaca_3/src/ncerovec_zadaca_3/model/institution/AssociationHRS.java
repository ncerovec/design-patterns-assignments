/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.model.institution;

import ncerovec_zadaca_3.model.agency.AgencyBSAC;

/**
 * SINGLETON - Class
 * @author nino
 */
public class AssociationHRS extends Institution
{
    private static volatile AssociationHRS INSTANCE = null;
    
    public static final String name = "HRS";
    
    private AssociationHRS() { this.setName(AssociationHRS.name); }
    
    public static AssociationHRS getInstance() //lazy loading instantiation
    {
        if(INSTANCE == null)
        {
            synchronized(AssociationHRS.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = new AssociationHRS();
                }
            }
        }
        
        return INSTANCE;
    }
}
