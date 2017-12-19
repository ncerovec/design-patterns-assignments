/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.strategy.divers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ncerovec_zadaca_4.model.Club;
import ncerovec_zadaca_4.model.dive.Dive;
import ncerovec_zadaca_4.model.diver.Diver;

/**
 * STRATEGY - ConcreteStrategy
 * @author nino
 */
public class RandomDiverSetStrategy implements DiverSetStrategy
{
    Random randGenerator = null;

    public RandomDiverSetStrategy(int randSeed)
    {
        randGenerator = new Random(randSeed);
    }
    
    @Override
    public List<Diver> generateDivers(Dive dive)
    {
        List<Diver> randDivers = null;
        
        int num = dive.getNumDivers();
        List<Diver> diverList = Club.getInstance().getDivers();
        
        int totalNum = diverList.size();
        if(num <= 0 || num > totalNum) num = totalNum;
        
        //In case multiple dives in same day (same divers)
        Dive maxDayDive = Club.getInstance().getMaxDiverDayDive(dive.getDateTime());
        if(maxDayDive != null)  //already was Dive that day (same divers +/- N)
        {
            randDivers = new ArrayList<>(maxDayDive.getDivers());
            
            int diffDiver = dive.getNumDivers() - maxDayDive.getNumDivers();
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
                int index = this.randGenerator.nextInt(totalNum);
                newRandDiver = diverList.get(index);
            }
            while(randDivers.contains(newRandDiver));
            
            randDivers.add(newRandDiver);
        }
        
        return randDivers;
    }    
}
