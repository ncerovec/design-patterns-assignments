/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_2.algorithm;

import java.util.List;
import ncerovec_zadaca_2.model.dive.combinations.DiveCombination;
import ncerovec_zadaca_2.model.diver.Diver;

/**
 *
 * @author nino
 */
public interface Algorithm
{
    String getAlgName();
    
    List<DiveCombination> makeDiveCombinations(List<Diver> diveDivers, int depthLimit);
}
