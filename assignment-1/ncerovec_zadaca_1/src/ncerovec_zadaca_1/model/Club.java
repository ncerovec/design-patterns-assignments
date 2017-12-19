/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_1.model;

import java.util.Date;
import java.util.List;
import ncerovec_zadaca_1.AppHelper;
import ncerovec_zadaca_1.algorithm.Algorithm;
import ncerovec_zadaca_1.model.dive.Dive;
import ncerovec_zadaca_1.model.dive.combinations.DiveCombination;
import ncerovec_zadaca_1.model.diver.Diver;

/**
 * SINGLETON - Class
 * @author nino
 */
public class Club
{
    private static volatile Club INSTANCE = null;
    
    private List<Diver> divers = null;
    private List<Dive> dives = null;
    
    private Club() {}
    
    public static Club getInstance() //lazy loading instantiation
    {
        if(INSTANCE == null)
        {
            synchronized(Club.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = new Club();
                }
            }
        }
        
        return INSTANCE;
    }

    public List<Diver> getDivers() { return divers; }
    public void setDivers(List<Diver> divers) { this.divers = divers; }

    public List<Dive> getDives() { return dives; }
    public void setDives(List<Dive> dives) { this.dives = dives; }
    
    public Dive getMaxDiverDayDive(Date date)
    {
        Dive maxDiverDayDive = null;
        
        if(this.getDives() != null)
        {
            for(Dive dive : this.getDives())
            {
                if(AppHelper.checkSameDay(dive.getDateTime(), date))
                {
                    if(maxDiverDayDive == null || maxDiverDayDive.getNumDivers() < dive.getNumDivers())
                    {
                        maxDiverDayDive = dive;
                    }
                }
            }
        }
        
        return maxDiverDayDive;
    }
    
    public String printDivers()
    {
        String text = "\n -- Popis ronioca --";
        
        for(Diver diver : this.getDivers())
        {
            text += "\n" + diver;
        }
        
        return text;
    }
    
    public String printDives()
    {
        String text = "\n -- Popis urona --";
        
        for(Dive dive : this.getDives())
        {
            text += "\n" + dive;
        }
        
        return text;
    }
    
    public String printDiverDives(Diver diver)
    {
        String text = "\n --> Popis urona za ronioca: ";
        
        text += diver;
        for(Dive dive : this.getDives())
        {
            DiveCombination diveCmb = dive.getDiverCombination(diver);

            if(diveCmb != null)
            {
                text += "\n" + "URON " + "(" + dive.getDateTime() + "): max " + dive.getMaxDepth() + "m";
                text += "\n" + " -> Dubina ronioca: " + diveCmb.checkDiverDepth(diver) + "m";
                text += "\n" + " -> Kombinacija-partneri: " + diveCmb;                    
            }
        }
        
        return text;
    }
    
    public String printAllDiverDives()
    {
        String text = "\n -- Popis ronioca i urona za pojedinog ronioca --";
        
        for(Diver diver : this.getDivers())
        {
            text += "\n" + printDiverDives(diver);
        }
        
        return text;
    }
}
