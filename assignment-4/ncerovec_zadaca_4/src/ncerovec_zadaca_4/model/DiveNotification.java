/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.model;

import ncerovec_zadaca_4.model.dive.Dive;
import ncerovec_zadaca_4.model.diver.Diver;

/**
 * OBSERVER - Message
 * @author nino
 */
public class DiveNotification
{
    private Diver diver = null;
    private Dive dive = null;

    public Diver getDiver() { return diver; }
    public Dive getDive() { return dive; }
    
    public DiveNotification(Diver diver, Dive dive)
    {
        this.diver = diver;
        this.dive = dive;
    }
}
