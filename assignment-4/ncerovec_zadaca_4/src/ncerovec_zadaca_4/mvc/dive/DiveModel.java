/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.mvc.dive;

import ncerovec_zadaca_4.model.dive.Dive;
import ncerovec_zadaca_4.mvc.Model;

/**
 * MVC - ConcreteModel
 * @author nino
 */
public class DiveModel extends Model
{
    private Dive dive = null;

    public Dive getDive() { return dive; }

    public void setDive(Dive dive)
    {
        this.dive = dive;
        notifyController();
    }
    
    public DiveModel(Dive dive)
    {
        this.dive = dive;
    }
}