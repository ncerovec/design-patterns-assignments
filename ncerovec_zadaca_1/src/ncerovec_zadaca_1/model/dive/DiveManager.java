/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_1.model.dive;

/**
 * BUILDER - Director
 * @author nino
 */
public class DiveManager    //DiveSelector
{
    private DiveBuilder diveBuilder = null;

    public void setDiveBuilder(DiveBuilder diveBuilder) { this.diveBuilder = diveBuilder; }
    
    public void createDive(String date, String time, int maxDepth, int numDiver)
    {
        diveBuilder.createNewDive(maxDepth, numDiver);
        diveBuilder.buildDatetime(date, time);
        diveBuilder.buildRandomDivers();
        diveBuilder.buildDiveCombinations();
    }
    
    public Dive getDive() { return diveBuilder.getDive(); }
}
