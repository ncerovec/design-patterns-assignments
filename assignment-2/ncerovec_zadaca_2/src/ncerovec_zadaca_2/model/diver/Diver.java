/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_2.model.diver;

import ncerovec_zadaca_2.model.Level;
import ncerovec_zadaca_2.model.agency.Agency;

/**
 * FACTORY METHOD - Product
 * @author nino
 */
public abstract class Diver
{
    private String name = null;
    private Agency agency = null;
    private String lvl = null;
    private Level level = null;
    private int birthYear = 0;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Agency getAgency() { return agency; }
    public void setAgency(Agency agency) { this.agency = agency; }

    public String getLvl() { return lvl; }
    public void setLvl(String lvl) { this.lvl = lvl; }
    
    public Level getLevel() { return level; }
    public void setLevel(Level level) { this.level = level; }

    public int getBirthYear() { return birthYear; }
    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }

    public Diver(String name, Agency agency, String lvl, Level level, int birthYear)
    {
        this.name = name;
        this.agency = agency;
        this.lvl = lvl;
        this.level = level;
        this.birthYear = birthYear;
    }
    
    /*
    //Get level from agency
    public String getLevelTitle()
    {
        String levelTitle = this.getAgency().getLevel(this.getLvl()).getTitle();
        
        if(levelTitle != null)
        {
            return levelTitle;
        }
        else
        {
            return "Nema tečaj";
        }
    }
    */

    public String getLevelString()
    {
        String levelString = this.getLvl() + "->" + this.getLevel().getLabel() + ":" + "[" + this.getLevel().getAbsLevel() + "]" + ((this.getLevel().getTitle() != null) ? this.getLevel().getTitle() : "Nema tečaj");
        return levelString;
    }
    
    @Override
    public String toString()
    {
        return this.getName() + " (" + this.getAgency() + " / " + this.getLevelString() + " / " + this.getBirthYear() + ")";
    }
}
