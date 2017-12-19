/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.model;

/**
 *
 * @author nino
 */
public class Level
{
    private String label = null;
    private String title = null;
    private int maxDepth = 0;
    private int absLevel = 0;

    public String getLabel() { return label; }    
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public int getMaxDepth() { return maxDepth; }
    
    public int getPotentialMaxDepth()  //depth if diving in combination with divers of higher level
    {
        if(this.maxDepth > 0) return maxDepth+10;
        else return maxDepth;
    }
    
    public int getAbsLevel() { return absLevel; }    
    
    public Level(String label, String title, int maxDepth, int absLevel)
    {
        this.label = label;
        this.title = title;
        this.maxDepth = maxDepth;
        this.absLevel = absLevel;
    }

    @Override
    public String toString()
    {
        String levelPrint = this.getLabel() + ":" + "[" + this.getAbsLevel() + "]" + ((this.getTitle() != null) ? this.getTitle() : "No Level Equivalence");
        return levelPrint;
    }
}
