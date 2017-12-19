/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_2.model.dive;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_2.algorithm.comparison.AlgorithmComparator;

/**
 * BUILDER - Director
 * @author nino
 */
public class DiveManager    //DiveSelector
{
    private DiveBuilder diveBuilder = null;
    private List<DiveBuilder> alternativeDiveBuilders = new ArrayList<>();

    public void setDiveBuilder(DiveBuilder diveBuilder) { this.diveBuilder = diveBuilder; }
    public void addDiveBuilder(DiveBuilder diveBuilder) { this.alternativeDiveBuilders.add(diveBuilder); }
    
    public void createDive(String date, String time, int maxDepth, int numDiver)
    {
        if(this.alternativeDiveBuilders.size() > 0)
        {
            diveBuilder.createNewDive(maxDepth, numDiver);  //equal for every diveBuilder type
            diveBuilder.buildDatetime(date, time);          //equal for every diveBuilder type
            diveBuilder.buildRandomDivers();                //equal for every diveBuilder type

            //find optimal algorithm for building dive combinations
            AlgorithmComparator diveComparator = new AlgorithmComparator(this.getDive(), this.alternativeDiveBuilders);
            this.setDiveBuilder(diveComparator.getOptimalDiveBuilder());

            diveBuilder.buildDiveCombinations();  //override dive combinations built inside AlgorithmComparator
        }
    }
    
    public Dive getDive() { return diveBuilder.getDive(); }
}
