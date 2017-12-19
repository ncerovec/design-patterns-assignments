/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_2.model.dive;

import java.util.List;
import ncerovec_zadaca_2.algorithm.Algorithm;
import ncerovec_zadaca_2.algorithm.AlgorithmMaxLevel;
import ncerovec_zadaca_2.model.dive.combinations.DiveCombination;

/**
 * BUILDER - ConcreteBuilder
 * @author nino
 */
public class DiveBuilderMaxLevel extends DiveBuilder
{
    Algorithm algorithm = new AlgorithmMaxLevel();
    
    @Override
    public Algorithm getAlgorithm() { return algorithm; }
    
    public DiveBuilderMaxLevel(int randSeed) { super(randSeed); }
    
    @Override
    public void buildDiveCombinations()
    {
        List<DiveCombination> diveCmbs = algorithm.makeDiveCombinations(this.dive.getDivers(), this.dive.getMaxDepth());
                
        this.dive.setDiveCmbs(diveCmbs);
    }
}