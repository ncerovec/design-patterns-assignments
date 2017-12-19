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
public class AgencyCMAS extends Agency
{
    private static volatile AgencyCMAS INSTANCE = null;
 
    public static final String name = "CMAS";
    
    private String[] recreationalLevels =   {
                                                null,
                                                "One Star Diver",
                                                "One Star Diver",
                                                "Two Star Diver",
                                                "Two Star Diver",
                                                null
                                            };
    
    private String[] proffesionalLevels =   {
                                                "Three Star Diver",
                                                null,
                                                "One Star Instructor",
                                                "Two Star Instructor",
                                                null,
                                                null,
                                                null,
                                                null
                                            };
    
    private AgencyCMAS()
    {
        this.setName(AgencyCMAS.name);
        this.setRecreationalLevels(recreationalLevels);
        this.setProffesionalLevels(proffesionalLevels);
    }
    
    public static AgencyCMAS getInstance() //lazy loading instantiation
    {
        if(INSTANCE == null)
        {
            synchronized(AgencyCMAS.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = new AgencyCMAS();
                }
            }
        }
        
        return INSTANCE;
    }
}
