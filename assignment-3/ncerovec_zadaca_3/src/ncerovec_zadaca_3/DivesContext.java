/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ncerovec_zadaca_3.algorithm.AlgorithmMaxDepth;
import ncerovec_zadaca_3.algorithm.AlgorithmMaxLevel;
import ncerovec_zadaca_3.algorithm.AlgorithmRandom;
import ncerovec_zadaca_3.model.Club;
import ncerovec_zadaca_3.model.dive.Dive;
import ncerovec_zadaca_3.model.dive.DiveBuilder;
import ncerovec_zadaca_3.model.dive.DiveBuilderMaxDepth;
import ncerovec_zadaca_3.model.dive.DiveBuilderMaxLevel;
import ncerovec_zadaca_3.model.dive.DiveBuilderRandom;
import ncerovec_zadaca_3.model.dive.DiveManager;

/**
 *
 * @author nino
 */
public class DivesContext
{
    private DiveManager diveSelector = new DiveManager();

    public DivesContext(DiveManager diveSelector)
    {
        this.diveSelector = diveSelector;
    }
    
    /**
     * Read dives file and create dives
     * @param divesFile
     * @return 
     */
    public List<Dive> readDives(String divesFile)
    {
        System.out.println("\n -- Čitanje urona --");
        
        List<Dive> dives = Club.getInstance().getDives();
        
        try
        {
            BufferedReader inDives = new BufferedReader(new FileReader(divesFile));                

            String dive = null;
            while((dive = inDives.readLine()) != null)
            {
                System.out.println("Pročitan redak: " + dive);
                String[] diveProps = dive.split(";");
                
                if(diveProps.length >= 4)
                {
                    String diveDate = diveProps[0];
                    String diveTime = diveProps[1];
                    int diveMaxDepth = Integer.parseInt(diveProps[2]);
                    int diveNumDiver = Integer.parseInt(diveProps[3]);
                    
                    if(diveNumDiver > 1)
                    {
                        diveSelector.createDive(diveDate, diveTime, diveMaxDepth, diveNumDiver);
                        Dive newDive = diveSelector.getDive();
                        if(newDive != null) dives.add(newDive);

                        System.out.println(" -> uron kreiran");
                        Club.getInstance().setState(newDive);   //send notifications about new dive
                    }
                    else
                    {
                        System.out.println(" -> nedovoljan broj ronioca za uron!");
                    }
                }
                else
                {
                    System.out.println(" -> neispravan redak");
                }
            }
            
            inDives.close();
        }
        catch (IOException ex) { Logger.getLogger(Diving.class.getName()).log(Level.SEVERE, null, ex); }
        
        return dives;
    }
    
    /**
     * Read algorithms and pass them to DiveManager
     * @param algorithm 
     */
    public void readAlgorithms(String[] alternativeAlgorithms)
    {
        diveSelector.setDiveBuilder(new DiveBuilder());
        
        for(String algorithm : alternativeAlgorithms)
        {
            switch(algorithm)
            {
                case AlgorithmMaxDepth.algName:
                    diveSelector.addDiveBuilder(new DiveBuilderMaxDepth());
                break;

                case AlgorithmMaxLevel.algName:
                    diveSelector.addDiveBuilder(new DiveBuilderMaxLevel());
                break;

                case AlgorithmRandom.algName:
                    diveSelector.addDiveBuilder(new DiveBuilderRandom());
                break;                
            }
        }
    }
}
