/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_1.model.dive.combinations;

import ncerovec_zadaca_1.model.diver.Diver;

/**
 *
 * @author nino
 */
public abstract class DiveCombination
{
    protected int totalMaxDepth = 0;
    
    public abstract boolean addDiver(Diver diver);  //method not used (useful for combination algorithms)
    
    public abstract void calculateCombDepths(int depthLimit);
    
    public abstract int checkDiverDepth(Diver diver);
}
