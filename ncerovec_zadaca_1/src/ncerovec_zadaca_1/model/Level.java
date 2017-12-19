/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_1.model;

/**
 *
 * @author nino
 */
public class Level
{
    private String label = null;
    private String title = null;
    private int maxDepth = 0;

    public String getLabel() { return label; }    
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public int getMaxDepth() { return maxDepth; }
    
    public Level(String label, String title, int maxDepth)
    {
        this.label = label;
        this.title = title;
        this.maxDepth = maxDepth;
    }

    @Override
    public String toString()
    {
        String titlePrint = this.getTitle();
        
        if(titlePrint != null)
        {
            return titlePrint;
        }
        else
        {
            return "No Level Equivalence";
        }
    }
}
