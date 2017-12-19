/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.algorithm;

import java.util.List;
import java.util.ArrayList;
import static ncerovec_zadaca_3.algorithm.AlgorithmMaxDepth.algName;
import ncerovec_zadaca_3.model.dive.combinations.DiveCombination;
import ncerovec_zadaca_3.model.dive.combinations.DivePair;
import ncerovec_zadaca_3.model.dive.combinations.DiveTroika;
import ncerovec_zadaca_3.model.diver.Diver;

/**
 *
 * @author nino
 */
public class AlgorithmMaxLevel implements Algorithm
{
    public static final String algName = "AlgoritamMaksRazina";
    
    @Override
    public String getAlgName() { return algName; }
    
    private List<DiveCombination> diveCmbs = null;
    
    private List<Diver> listAllDivers = new ArrayList<>();
    
    /**
     * Aglorithm makes troika with first combination if possible
     * then pairs all the rest, and at the end combines all unmatched divers
     * @param diveDivers
     * @param depthLimit
     * @return 
     */
    @Override
    public List<DiveCombination> makeDiveCombinations(List<Diver> diveDivers, int depthLimit)
    {
        this.diveCmbs = new ArrayList<>();
        
        this.listAllDivers = new ArrayList<>(diveDivers);
        
        //this.diveCmbs = this.makeTroika(this.diveCmbs, this.listAllDivers, depthLimit);   //prevents 2x2 same level pairs
        this.diveCmbs = this.makePairs(this.diveCmbs, this.listAllDivers, depthLimit);
                
        //combine any unmatched divers
        this.diveCmbs = AlgorithmHelper.makeTroikaOrPairs(this.diveCmbs, this.listAllDivers, depthLimit, this.listAllDivers);
        
        if(!this.listAllDivers.isEmpty())
        {
            System.out.println("Greška u algoritmu, nisu svi ronioci uključeni!");
        }
        
        return this.diveCmbs;
    }
    
    /**
     * Aglorithm makes troika (same level divers) with first combination if possible (same level troika exist)
     * if even number of divers: leaves even number of divers in list to be combined in pairs 
     * if odd number and troika matched/unmatched: leaves even (odd-3) number of divers / leaves odd number of divers
     * @param diveCmbs
     * @param groupDivers
     * @param depthLimit
     * @return 
     */
    public List<DiveCombination> makeTroika(List<DiveCombination> diveCmbs, List<Diver> groupDivers, int depthLimit)
    {
        //try to make same level troika
        int listSize = groupDivers.size();
        if(listSize%2 > 0)
        {
            boolean troikaCombined = false;
            for(int i=listSize-1; i >= 0; i--)
            {
                Diver anchorDiver = groupDivers.get(i);

                for(int j=i-1; j >= 0; j--)
                {
                    Diver secondDiver = groupDivers.get(j);

                    if(anchorDiver.getLvl().equals(secondDiver.getLvl()))
                    {                        
                        for(int k=j-1; k >= 0; k--)
                        {
                            Diver thirdDiver = groupDivers.get(k);
                            
                            if(anchorDiver.getLvl().equals(thirdDiver.getLvl()))
                            {
                                DiveTroika troika = new DiveTroika(anchorDiver, secondDiver, thirdDiver);

                                troika.calculateCombDepths(depthLimit);
                                diveCmbs.add(troika);    //add created pair to combination list

                                groupDivers.remove(i);
                                groupDivers.remove(j);
                                groupDivers.remove(k);
                                i=i-2;    //decrese by 1 (last element removed-anchor and one more removed-pair) //or i=listSize-1
                                j=j-2;    //not neccesery - initialized by i in next parent loop
                                k=k-2;    //not neccesery - initialized by j in next parent loop
                                
                                troikaCombined = true;
                            }
                            
                            if(troikaCombined) break;   //limit troikas to one combination
                        }
                    }
                    
                    if(troikaCombined) break;   //limit troikas to one combination
                }
                
                if(troikaCombined) break;   //limit troikas to one combination
            }
        }
        
        return diveCmbs;
    }
    
    /**
     * Aglorithm makes pairs (same level divers) of all divers if possible (same level pairs exist) 
     * leaves all unmatched (even/odd number) divers in list to be combined any way (randomly)
     * if all pairs matched and odd number of divers: leave last three divers in list to be combined in troika
     * @param diveCmbs
     * @param groupDivers
     * @param depthLimit
     * @return 
     */
    public List<DiveCombination> makePairs(List<DiveCombination> diveCmbs, List<Diver> groupDivers, int depthLimit)
    {        
        //try to make same level pairs
        int listSize = groupDivers.size();
        for(int i=listSize-1; i >= 0; i--)
        {
            Diver anchorDiver = groupDivers.get(i);
            
            if(listSize != 3)   //can't pair 3 divers - combine last divers to Troika
            {
                for(int j=i-1; j >= 0; j--)
                {
                    Diver pairDiver = groupDivers.get(j);

                    if(anchorDiver.getLvl().equals(pairDiver.getLvl()))
                    {
                        DivePair pair = new DivePair(anchorDiver, pairDiver);
                                                
                        pair.calculateCombDepths(depthLimit);
                        diveCmbs.add(pair);    //add created pair to combination list
                        
                        groupDivers.remove(i);
                        groupDivers.remove(j);
                        i--;    //decrese by 1 (last element removed-anchor and one more removed-pair) //or i=listSize-1
                        j--;    //not neccesery - initialized by i in next parent loop
                        break;
                    }
                }
            }
            
            listSize = groupDivers.size();
        }
        
        return diveCmbs;
    }
}
