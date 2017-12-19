/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_2.model.dive;

import ncerovec_zadaca_2.model.dive.combinations.DiveCombination;
import java.util.Date;
import java.util.List;
import ncerovec_zadaca_2.algorithm.Algorithm;
import ncerovec_zadaca_2.model.diver.Diver;

/**
 * BUILDER - Product
 * @author nino
 */
public class Dive
{
    //private String place = null;
    private Date dateTime = null;
    private int maxDepth = 0;
    private int numDivers = 0;
    
    private Algorithm usedAlgorithm = null;
    private float totalCertaintyMeasure = 0;
    
    private List<Diver> divers = null;
    private List<DiveCombination> diveCmbs = null;

    public Date getDateTime() { return dateTime; }
    public void setDateTime(Date dateTime) { this.dateTime = dateTime; }

    public int getMaxDepth() { return maxDepth; }
    public void setMaxDepth(int maxDepth) { this.maxDepth = maxDepth; }

    public int getNumDivers() { return numDivers; }
    public void setNumDivers(int numDivers) { this.numDivers = numDivers; }
    
    public List<Diver> getDivers() { return divers; }
    public void setDivers(List<Diver> divers) { this.divers = divers; }

    public List<DiveCombination> getDiveCmbs() { return diveCmbs; }
    public void setDiveCmbs(List<DiveCombination> diveCmbs) { this.diveCmbs = diveCmbs; }

    public Algorithm getUsedAlgorithm() { return usedAlgorithm; }
    public void setUsedAlgorithm(Algorithm usedAlgorithm) { this.usedAlgorithm = usedAlgorithm; }

    public float getTotalCertaintyMeasure() { return totalCertaintyMeasure; }
    public void setTotalCertaintyMeasure(float totalCertaintyMeasure) { this.totalCertaintyMeasure = totalCertaintyMeasure; }
    
    public Dive(int maxDepth, int numDivers)
    {
        this.maxDepth = maxDepth;
        this.numDivers = numDivers;
    }  
    
    public DiveCombination getDiverCombination(Diver diver)
    {
        DiveCombination diveCmb = null;
        
        if(this.divers.contains(diver))
        {
            for(DiveCombination cmb : this.diveCmbs)
            {
                if(cmb.checkDiverDepth(diver) >= 0)
                {
                    diveCmb = cmb;
                    break;
                }
            }
        }
        
        return diveCmb;
    }
    
    @Override
    public String toString()
    {
        String text = "\n" + "URON " + "(" + getDateTime() + ")";
        text += "\n" + "Maksimalna dubina: " + getMaxDepth();
        text += "\n" + "Broj ronioca: " + getNumDivers();
        
        text += "\n" + "Ronilačke kombinacije:";
        for(DiveCombination diveCmb : this.diveCmbs)
        {
            text += "\n" + diveCmb;
        }
        
        return text;
    }
}