/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_2.model.dive;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import ncerovec_zadaca_2.AppHelper;
import ncerovec_zadaca_2.algorithm.Algorithm;
import ncerovec_zadaca_2.model.Club;
import ncerovec_zadaca_2.model.diver.Diver;

/**
 * BUILDER - Abstract Builder (not abstract anymore)
 * @author nino
 */
public class DiveBuilder
{
    protected Dive dive = null;
    Random randGenerator = null;
    
    public Algorithm getAlgorithm() { return null; }

    public DiveBuilder(int randSeed)
    {
        randGenerator = new Random(randSeed);
    }
    
    public Dive getDive() { return dive; }
    public void setDive(Dive dive) { this.dive = dive; }
    
    public void createNewDive(int maxDepth, int numDivers)
    {
        this.dive = new Dive(maxDepth, numDivers);
    }
    
    public void buildDatetime(String date, String time)
    {
        Date datetime = AppHelper.parseDateTime(date, time);
        
        this.dive.setDateTime(datetime);
    }
    
    public void buildRandomDivers()
    {
        List<Diver> randDivers = null;
        
        int num = this.dive.getNumDivers();
        List<Diver> diverList = Club.getInstance().getDivers();
        
        int totalNum = diverList.size();
        if(num <= 0 || num > totalNum) num = totalNum;
        
        //In case multiple dives in same day (same divers)
        Dive maxDayDive = Club.getInstance().getMaxDiverDayDive(this.dive.getDateTime());
        if(maxDayDive != null)  //already was Dive that day (same divers +/- N)
        {
            randDivers = new ArrayList<>(maxDayDive.getDivers());
            
            int diffDiver = this.dive.getNumDivers() - maxDayDive.getNumDivers();
            if(diffDiver < 0)
            {
                //remove diffDiver (difference) number of divers (diffDiver -> negative number)
                randDivers.subList(randDivers.size() + diffDiver, randDivers.size()).clear();
                num = 0; //prevent from adding more random divers
            }
            else
            {
                //set (difference) number of random divers to add
                num = diffDiver;
            }
        }
        else
        {
            randDivers = new ArrayList<>();
        }
        
        //generate num of random divers and add to randDivers list
        for(int i=0; i < num; i++)
        {
            Diver newRandDiver = null;
            
            do
            {
                int index = randGenerator.nextInt(totalNum);
                newRandDiver = diverList.get(index);
            }
            while(randDivers.contains(newRandDiver));
            
            randDivers.add(newRandDiver);
        }
        
        this.dive.setDivers(randDivers);
    }
    
    //public abstract void buildDiveCombinations();    
    public void buildDiveCombinations()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
