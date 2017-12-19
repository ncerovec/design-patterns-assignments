/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.model.agency;

/**
 * SINGLETON - Class
 * @author nino
 */
public class AgencyBSAC extends Agency
{
    private static volatile AgencyBSAC INSTANCE = null;
    
    public static final String name = "BSAC";
    
    private String[] recreationalLevels =   {
                                                null,
                                                "Ocean Diver",
                                                "Ocean Diver",
                                                "Sports Diver",
                                                "Sports Diver",
                                                null
                                            };
    
    private String[] proffesionalLevels =   {
                                                "Dive Leader",
                                                null,
                                                "Assistant Open Water Instructor",
                                                "Open Water Instructor",
                                                "Advanced Instructor",
                                                null,
                                                null,
                                                null
                                            };
    
    private AgencyBSAC()
    {
        this.setName(AgencyBSAC.name);
        this.setRecreationalLevels(recreationalLevels);
        this.setProffesionalLevels(proffesionalLevels);
    }
    
    public static AgencyBSAC getInstance() //lazy loading instantiation
    {
        if(INSTANCE == null)
        {
            synchronized(AgencyBSAC.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = new AgencyBSAC();
                }
            }
        }
        
        return INSTANCE;
    }
}
