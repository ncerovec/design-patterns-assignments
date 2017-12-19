/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_1.model.dive;

import java.util.List;
import ncerovec_zadaca_1.algorithm.Algorithm;
import ncerovec_zadaca_1.algorithm.AlgorithmRandom;
import ncerovec_zadaca_1.model.dive.combinations.DiveCombination;

/**
 * BUILDER - ConcreteBuilder
 * @author nino
 */
public class DiveBuilderRandom extends DiveBuilder
{
    Algorithm algorithm = new AlgorithmRandom();

    public DiveBuilderRandom(int randSeed) { super(randSeed); }
    
    @Override
    public void buildDiveCombinations()
    {
        List<DiveCombination> diveCmbs = algorithm.makeDiveCombinations(this.dive.getDivers(), this.dive.getMaxDepth());
                
        this.dive.setDiveCmbs(diveCmbs);
    }    
}
