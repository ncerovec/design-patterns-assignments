/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.model.diver;

import ncerovec_zadaca_4.model.Level;
import ncerovec_zadaca_4.model.agency.AgencyNAUI;

/**
 * FACTORY METHOD - ConcreteProduct
 * @author nino
 */
public class DiverNAUI extends Diver
{
    public DiverNAUI(String name, String lvl, Level level, int birthYear)
    {
        super(name, AgencyNAUI.getInstance(), lvl, level, birthYear);
        //this.setAgency(AgencyNAUI.getInstance());
    }
}