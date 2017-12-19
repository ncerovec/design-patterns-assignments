/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.algorithm.comparison;

/**
 * ITERATOR - Iterator
 * @author nino
 */
public interface IteratorInterface
{
    public void first();
    
    public boolean isDone();
    
    public Object currentItem();
    
    public void next();
}
