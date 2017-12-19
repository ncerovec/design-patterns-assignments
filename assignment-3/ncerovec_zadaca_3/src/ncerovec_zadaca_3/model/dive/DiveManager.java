/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.model.dive;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ncerovec_zadaca_3.algorithm.comparison.AlgorithmComparator;
import ncerovec_zadaca_3.strategy.divers.DiverSetStrategy;

/**
 * BUILDER - Director, STRATEGY - Context
 * @author nino
 */
public class DiveManager    //DiveSelector
{
    private DiveBuilder diveBuilder = null;
    private DiverSetStrategy strategy = null;
    private List<DiveBuilder> alternativeDiveBuilders = new ArrayList<>();

    public void setStrategy(DiverSetStrategy strategy) { this.strategy = strategy; }
    
    public void setDiveBuilder(DiveBuilder diveBuilder) { this.diveBuilder = diveBuilder; }
    public void addDiveBuilder(DiveBuilder diveBuilder) { this.alternativeDiveBuilders.add(diveBuilder); }
    
    public void createDive(String date, String time, int maxDepth, int numDiver)    //standard dive
    {
        diveBuilder.createNewDive();
        diveBuilder.buildMaxDepth(maxDepth);
        diveBuilder.buildNumDivers(numDiver);
        diveBuilder.buildDatetime(date, time);
        this.arrangeDivers();
    }
    
    public void createDive(int maxDepth, float tempWater, boolean nightDive, int numCameras)    //qualified dive
    {
        diveBuilder.createNewDive();
        this.getDive().setDateTime(new Date());
        diveBuilder.buildMaxDepth(maxDepth);
        diveBuilder.buildTempWater(tempWater);
        diveBuilder.buildNightDive(nightDive);
        diveBuilder.buildNumCameras(numCameras);
        diveBuilder.buildAvailableEquipment();
        this.arrangeDivers();
    }
    
    private void arrangeDivers()
    {
        if(this.getDive() != null && this.strategy != null)
        {
            diveBuilder.buildDivers(this.strategy);

            if(this.alternativeDiveBuilders.size() > 0 && this.getDive().getDivers().size() > 1)
            {
                //find optimal algorithm for building dive combinations
                AlgorithmComparator diveComparator = new AlgorithmComparator(this.getDive(), this.alternativeDiveBuilders);
                this.setDiveBuilder(diveComparator.getOptimalDiveBuilder());

                if(diveBuilder != null && diveBuilder.getClass() != DiveBuilder.class)
                {
                    diveBuilder.buildDiveCombinations();  //override dive combinations built inside AlgorithmComparator
                }
            }
        }
    }
    
    public Dive getDive() { return diveBuilder.getDive(); }
}