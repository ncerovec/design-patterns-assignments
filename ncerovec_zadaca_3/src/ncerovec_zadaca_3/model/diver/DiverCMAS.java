/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.model.diver;

import ncerovec_zadaca_3.model.Level;
import ncerovec_zadaca_3.model.agency.AgencyCMAS;

/**
 * FACTORY METHOD - ConcreteProduct
 * @author nino
 */
public class DiverCMAS extends Diver
{
    public DiverCMAS(String name, String lvl, Level level, int birthYear)
    {
        super(name, AgencyCMAS.getInstance(), lvl, level, birthYear);
        //this.setAgency(AgencyCMAS.getInstance());
    }
}
