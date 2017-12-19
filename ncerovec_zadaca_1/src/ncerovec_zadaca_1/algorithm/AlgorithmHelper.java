/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_1.algorithm;

import java.util.List;
import ncerovec_zadaca_1.model.dive.combinations.DiveCombination;
import ncerovec_zadaca_1.model.dive.combinations.DivePair;
import ncerovec_zadaca_1.model.dive.combinations.DiveTroika;
import ncerovec_zadaca_1.model.diver.Diver;

/**
 *
 * @author nino
 */
public class AlgorithmHelper
{
    public static List<DiveCombination> makePairs(List<DiveCombination> diveCmbs, List<Diver> groupDivers, int depthLimit, List<Diver> leftoverDivers)
    {
        int itr = groupDivers.size()/2;
        for(int i=0; i < itr; i++)
        {
            DivePair pair = new DivePair();
            
            Diver diver1 = groupDivers.get(i*2);
            Diver diver2 = groupDivers.get(i*2+1);
            
            pair.setDiver1(diver1);
            //list10mDivers.remove(diver1);
            pair.setDiver2(diver2);
            //list10mDivers.remove(diver2);
            
            pair.calculateCombDepths(depthLimit);
            diveCmbs.add(pair);    //add created pair to combination list
        }
        
        //if any - add last (odd) Diver to listAnyDivers group
        if(groupDivers.size()%2 > 0)
            leftoverDivers.add(groupDivers.get(groupDivers.size()-1));

        //clear list when last iteration
        groupDivers.clear();
        
        return diveCmbs;
    }

    public static List<DiveCombination> makeTroikaOrPairs(List<DiveCombination> diveCmbs, List<Diver> groupDivers, int depthLimit, List<Diver> leftoverDivers)
    {
        int initGroupSize = groupDivers.size();
        if(initGroupSize%2 > 0)
        {
            DiveTroika troikas = new DiveTroika();
            
            Diver diver1 = groupDivers.get(initGroupSize-1);
            Diver diver2 = groupDivers.get(initGroupSize-2);
            Diver diver3 = groupDivers.get(initGroupSize-3);
            
            troikas.setDiver1(diver1);
            groupDivers.remove(diver1);
            troikas.setDiver2(diver2);
            groupDivers.remove(diver2);
            troikas.setDiver3(diver3);
            groupDivers.remove(diver3);
            
            troikas.calculateCombDepths(depthLimit);
            diveCmbs.add(troikas);    //add created troika to combination list
        }
        
        if(groupDivers.size()%2 == 0)   //algorithm is sure that group has even number of divers (all pairs)
        {
            //prevents check if all divers are included in combinations (clears groupDivers list at end)
            diveCmbs = makePairs(diveCmbs, groupDivers, depthLimit, leftoverDivers);
        }
        
        return diveCmbs;
    }
}
