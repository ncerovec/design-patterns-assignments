/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_1.model.diver;

import ncerovec_zadaca_1.model.Level;
import ncerovec_zadaca_1.model.agency.AgencyBSAC;

/**
 * FACTORY METHOD - ConcreteProduct
 * @author nino
 */
public class DiverBSAC extends Diver
{
    public DiverBSAC(String name, String lvl, Level level, int birthYear)
    {
        super(name, AgencyBSAC.getInstance(), lvl, level, birthYear);
        //this.setAgency(AgencyBSAC.getInstance());
    }
}
