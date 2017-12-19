/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.model.agency;

/**
 * SINGLETON - Class
 * @author nino
 */
public class AgencySSI extends Agency
{
    private static volatile AgencySSI INSTANCE = null;
    
    public static final String name = "SSI";
    
    private String[] recreationalLevels =   {
                                                "Scuba Diver",
                                                "Open Water Diver",
                                                "Advanced Adventure",
                                                "Diver Stress & Rescue",
                                                "Advanced Open Water Diver",
                                                "Master Diver"
                                            };
    
    private String[] proffesionalLevels =   {
                                                "Dive Guide",
                                                "Divemaster",
                                                "Dive Control Specialist",
                                                "Open Water Instructor",
                                                "Advanced Open Water Instructor",
                                                "Divemaster Instructor",
                                                "Dive Control Specialist Instructor",
                                                "Instructor Trainer"
                                            };
    
    private AgencySSI()
    {
        this.setName(AgencySSI.name);
        this.setRecreationalLevels(recreationalLevels);
        this.setProffesionalLevels(proffesionalLevels);
    }
    
    public static AgencySSI getInstance() //lazy loading instantiation
    {
        if(INSTANCE == null)
        {
            synchronized(AgencySSI.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = new AgencySSI();
                }
            }
        }
        
        return INSTANCE;
    }
}
