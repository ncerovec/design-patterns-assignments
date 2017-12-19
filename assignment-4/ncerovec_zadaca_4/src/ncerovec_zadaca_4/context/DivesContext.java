/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.context;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ncerovec_zadaca_4.Diving;
import ncerovec_zadaca_4.algorithm.AlgorithmMaxDepth;
import ncerovec_zadaca_4.algorithm.AlgorithmMaxLevel;
import ncerovec_zadaca_4.algorithm.AlgorithmRandom;
import ncerovec_zadaca_4.model.Club;
import ncerovec_zadaca_4.model.dive.Dive;
import ncerovec_zadaca_4.model.dive.DiveBuilder;
import ncerovec_zadaca_4.model.dive.DiveBuilderMaxDepth;
import ncerovec_zadaca_4.model.dive.DiveBuilderMaxLevel;
import ncerovec_zadaca_4.model.dive.DiveBuilderRandom;
import ncerovec_zadaca_4.model.dive.DiveManager;
import ncerovec_zadaca_4.mvc.dive.DiveController;
import ncerovec_zadaca_4.mvc.dive.DiveModel;
import ncerovec_zadaca_4.mvc.dive.DiveView;

/**
 *
 * @author nino
 */
public class DivesContext
{
    public static DiveView diveView = null;
    public static DiveController diveController = null;
    
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
                    float tempWater = Integer.parseInt(diveProps[4]);
                    boolean nightDive = Integer.parseInt(diveProps[5]) > 0;
                    int numCameras = Integer.parseInt(diveProps[6]);
                    
                    if(diveNumDiver <= 1) System.out.println("Nedovoljan broj ronioca za uron!");
                    else if (diveMaxDepth < 5 || diveMaxDepth > 40) System.out.println("Dubina urona nije valjana (min 5/max 40)!");
                    else if (tempWater < 0 || tempWater > 35) System.out.println("Temperatura vode nije valjana (min 0/max 35)!");
                    else if (numCameras < 0) System.out.println("Broj kamera nije valjan (min 0)!");
                    else
                    {
                        diveSelector.createDive(diveDate, diveTime, diveMaxDepth, diveNumDiver, tempWater, nightDive, numCameras);
                        Dive newDive = diveSelector.getDive();
                        
                        if(newDive != null)
                        {
                            if(newDive.getDivers().size() > 1)
                            {
                                dives.add(newDive);
                                System.out.println(" -> uron kreiran");
                            }
                            else
                            {
                                System.out.println("Nedovoljan broj kvalificiranih ronioca za uron!");
                            }
                        }

                        Club.getInstance().setState(newDive);   //send notifications about new dive
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
    
    public static void showDiveView(Dive dive)
    {
        DiveModel diveModel = new DiveModel(dive);
        DivesContext.diveController = new DiveController(diveModel, DivesContext.diveView);
        diveModel.setController(DivesContext.diveController);
        DivesContext.diveView.setController(DivesContext.diveController);
        DivesContext.diveController.updateView();
        DivesContext.diveController.enableInput();
    } 
}
