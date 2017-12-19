/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_1.model.agency;

import ncerovec_zadaca_1.model.Level;

/**
 * 
 * @author nino
 */
public abstract class Agency
{
    private String name = null;
    
    //private Level[] recreationalLevels = null;
    private Level[] recreationalLevels =   {
                                                new Level("R0", null, 0),
                                                new Level("R1", null, 10),
                                                new Level("R2", null, 30),
                                                new Level("R3", null, 40),
                                                new Level("R4", null, 40),
                                                new Level("R5", null, 40)
                                            };
    
    //private Level[] proffesionalLevels = null;
    private Level[] proffesionalLevels =   {
                                                new Level("I1", null, 40),
                                                new Level("I2", null, 40),
                                                new Level("I3", null, 40),
                                                new Level("I4", null, 40),
                                                new Level("I5", null, 40),
                                                new Level("I6", null, 40),
                                                new Level("I7", null, 40),
                                                new Level("I8", null, 40)
                                            };

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Level[] getRecreationalLevels() { return recreationalLevels; }
    public void setRecreationalLevels(String[] recreationalLevels)
    {
        for(int i=0; i < this.recreationalLevels.length; i++)
        {
            Level level = this.recreationalLevels[i];            
            level.setTitle(recreationalLevels[i]);
        }
    }

    public Level[] getProffesionalLevels() { return proffesionalLevels; }
    public void setProffesionalLevels(String[] proffesionalLevels)
    {
        for(int i=0; i < this.proffesionalLevels.length; i++)
        {
            Level level = this.proffesionalLevels[i];            
            level.setTitle(proffesionalLevels[i]);
        }
    }
    
    private Level getRecrLevel(int lvlNum)
    {
        Level recrLevel = null;
        
        if(lvlNum >= 0 && lvlNum <= this.getRecreationalLevels().length-1)
        {
            do
            {
                recrLevel = this.getRecreationalLevels()[lvlNum];
                lvlNum--;
            }
            while(lvlNum >= 0 && recrLevel.getTitle() == null);
        }
        
        return recrLevel;
    }
    
    private Level getProffLevel(int lvlNum)
    {
        Level proffLevel = null;
        
        if(lvlNum > 0 && lvlNum <= this.getProffesionalLevels().length)
        {
            do
            {
                proffLevel = this.getProffesionalLevels()[lvlNum-1];
                lvlNum--;
            }
            while(lvlNum > 0 && proffLevel.getTitle() == null);
        }
        
        return proffLevel;
    }
    
    public Level getLevel(String lvl)
    {
        Level level = null;
        Character firstChar = Character.toUpperCase(lvl.charAt(0));
        int lvlNum = Character.getNumericValue(lvl.charAt(1));
        
        switch(firstChar)
        {
            case 'R':
                level = this.getRecrLevel(lvlNum);
            break;
            
            case 'I':
                level = this.getProffLevel(lvlNum);
            break;
        }
        
        return level;
    }

    @Override
    public String toString()
    {
        return this.getName();
    }
}
