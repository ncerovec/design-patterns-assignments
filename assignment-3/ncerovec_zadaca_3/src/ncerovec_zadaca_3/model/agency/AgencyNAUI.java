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
public class AgencyNAUI extends Agency
{
    private static volatile AgencyNAUI INSTANCE = null;
    
    public static final String name = "NAUI";
    
    private String[] recreationalLevels =   {
                                                null,
                                                "Scuba Diver",
                                                "Advanced Scuba Diver",
                                                "Scuba Rescue Diver",
                                                "Master Scuba Diver",
                                                null
                                            };
    
    private String[] proffesionalLevels =   {
                                                "Assistant Instructor",
                                                null,
                                                "Divemaster",
                                                "Instructor",
                                                null,
                                                null,
                                                null,
                                                "Instructor Trainer"
                                            };
    
    private AgencyNAUI()
    {
        this.setName(AgencyNAUI.name);
        this.setRecreationalLevels(recreationalLevels);
        this.setProffesionalLevels(proffesionalLevels);
    }
    
    public static AgencyNAUI getInstance() //lazy loading instantiation
    {
        if(INSTANCE == null)
        {
            synchronized(AgencyNAUI.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = new AgencyNAUI();
                }
            }
        }
        
        return INSTANCE;
    }
}
