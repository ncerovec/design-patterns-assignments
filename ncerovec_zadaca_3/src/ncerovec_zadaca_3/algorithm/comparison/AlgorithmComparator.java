/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.algorithm.comparison;

import java.util.List;
import ncerovec_zadaca_3.model.dive.Dive;
import ncerovec_zadaca_3.model.dive.DiveBuilder;

/**
 *
 * @author nino
 */
public class AlgorithmComparator
{
    private Dive optimizingDive = null;
    private List<DiveBuilder> alternativeDiveBuilders = null;
    
    public AlgorithmComparator(Dive dive, List<DiveBuilder> diveBuilders)
    {
        this.optimizingDive = dive;
        this.alternativeDiveBuilders = diveBuilders;
    }
       
    public DiveBuilder getOptimalDiveBuilder()
    {
        DiveBuilder optimalDiveBuilder = null;
     
        float optimalTotalCertaintyMeasure = Float.MAX_VALUE;
        for(DiveBuilder diveBuilder : this.alternativeDiveBuilders)
        {
            diveBuilder.setDive(this.optimizingDive);
            diveBuilder.buildDiveCombinations();
            
            float diveTotalCertaintyMeasure = 0;
            DiveCombinationIterator diveCmbIterator = new DiveCombinationIterator(this.optimizingDive.getDiveCmbs());
            
            for(diveCmbIterator.first(); !diveCmbIterator.isDone(); diveCmbIterator.next())
            {
                float combinationCertaintyMeasure = diveCmbIterator.currentItem().calculateCertaintyMeasure();
                diveTotalCertaintyMeasure += combinationCertaintyMeasure;
            }
            
            System.out.println(" -> Mjera sigurnosti za " + diveBuilder.getAlgorithm().getAlgName() + ": " + diveTotalCertaintyMeasure);
                        
            if(optimalDiveBuilder == null || diveTotalCertaintyMeasure < optimalTotalCertaintyMeasure)
            {
                optimalTotalCertaintyMeasure = diveTotalCertaintyMeasure;
                optimalDiveBuilder = diveBuilder;
            }
        }
        
        System.out.println(" -> Odabrani algoritam: " + optimalDiveBuilder.getAlgorithm().getAlgName());
        
        this.optimizingDive.setUsedAlgorithm(optimalDiveBuilder.getAlgorithm());
        this.optimizingDive.setTotalCertaintyMeasure(optimalTotalCertaintyMeasure);
        
        return optimalDiveBuilder;
    }   
}
