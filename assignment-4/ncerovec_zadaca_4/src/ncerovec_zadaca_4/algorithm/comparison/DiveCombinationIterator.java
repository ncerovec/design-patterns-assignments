/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.algorithm.comparison;

import java.util.List;
import ncerovec_zadaca_4.model.dive.combinations.DiveCombination;

/**
 * ITERATOR - ConcreteIterator
 * @author nino
 */
public class DiveCombinationIterator implements IteratorInterface
{
    private int index = -1;
    private List<DiveCombination> diveCmbs = null;
    private DiveCombination currentDiveCmb = null;
    
    public DiveCombinationIterator(List<DiveCombination> diveCmbs) { this.diveCmbs = diveCmbs; }
    
    @Override
    public void first() { this.index = 0;  next(); }
    
    @Override
    public boolean isDone() { return currentDiveCmb == null; }
    
    @Override
    public DiveCombination currentItem() { return currentDiveCmb; }
    
    @Override
    public void next()
    {
        try { this.currentDiveCmb = this.diveCmbs.get(this.index++); }
        catch (IndexOutOfBoundsException ex) { this.currentDiveCmb = null; }
    }  
}
