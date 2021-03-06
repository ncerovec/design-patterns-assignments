/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_1.algorithm;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_1.model.dive.combinations.DiveCombination;
import ncerovec_zadaca_1.model.diver.Diver;

/**
 *
 * @author nino
 */
public class AlgorithmRandom implements Algorithm
{
    public static final String algName = "AlgoritamSlucajno";
        
    private List<DiveCombination> diveCmbs = null;
    
    private List<Diver> listLeftoverDivers = null;
    private List<Diver> listRandomDivers = null;
    
    @Override
    public List<DiveCombination> makeDiveCombinations(List<Diver> diveDivers, int depthLimit)
    {
        this.diveCmbs = new ArrayList<>();
        
        this.listLeftoverDivers = new ArrayList<>();
        this.listRandomDivers = new ArrayList<>(diveDivers);
        
        this.diveCmbs = AlgorithmHelper.makeTroikaOrPairs(this.diveCmbs, this.listRandomDivers, depthLimit, this.listLeftoverDivers);
        
        if(!this.listRandomDivers.isEmpty() || !this.listLeftoverDivers.isEmpty())
        {
            System.out.println("Greška u algoritmu, nisu svi ronioci uključeni!");
        }
        
        return this.diveCmbs;
    }
}
