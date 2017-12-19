/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.model.dive.combinations;

import java.util.List;
import ncerovec_zadaca_3.model.diver.Diver;

/**
 * ITERATOR - Aggregate
 * @author nino
 */
public abstract class DiveCombination
{
    protected int totalMaxDepth = 0;
    
    public abstract boolean addDiver(Diver diver);  //method not used (useful for combination algorithms)
    
    public abstract void calculateCombDepths(int depthLimit);
    
    public abstract int checkDiverDepth(Diver diver);
    
    public abstract float calculateCertaintyMeasure();
    
    public abstract List<Diver> getDiverPartners(Diver diver);
    
    public String printDiverPartners(Diver diver)
    {
        StringBuilder partnerDivers = new StringBuilder();
        List<Diver> diverPartners = this.getDiverPartners(diver);
        for(Diver partnerDiver : diverPartners)
        {
            partnerDivers.append(partnerDiver.getName()).append(',');
        }
        partnerDivers.deleteCharAt(partnerDivers.length()-1); //remove last comma (,)
        
        return partnerDivers.toString();
    }
}
