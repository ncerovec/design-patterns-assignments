/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_2.model.dive.combinations;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_2.AppHelper;
import ncerovec_zadaca_2.model.diver.Diver;

/**
 * ITERATOR - ConcreteAggregate
 * @author nino
 */
public class DivePair extends DiveCombination
{
    private Diver diver1 = null;
    private int depthDiver1 = 0;
    
    private Diver diver2 = null;
    private int depthDiver2 = 0;

    public Diver getDiver1() { return diver1; }
    private void setDiver1(Diver diver1) { this.diver1 = diver1; }

    public int getDepthDiver1() { return depthDiver1; }
    public void setDepthDiver1(int depthDiver1) { this.depthDiver1 = depthDiver1; }
    
    public Diver getDiver2() { return diver2; }
    private void setDiver2(Diver diver2) { this.diver2 = diver2; }

    public int getDepthDiver2() { return depthDiver2; }
    public void setDepthDiver2(int depthDiver2) { this.depthDiver2 = depthDiver2; }

    public DivePair(Diver diver1, Diver diver2)
    {
        this.setDiver1(diver1);
        this.setDiver2(diver2);
    }
    
    @Override
    public boolean addDiver(Diver diver)    //hint: check if Pair is full - addDiver(null)
    {
        //if possible to add diver: true
        if(getDiver1() == null)
        {
            this.setDiver1(diver);
            return true;
        }
        else if(getDiver2() == null)
        {
            this.setDiver2(diver);
            return true;
        }
        else    //if impossible to add diver: false
        {
            return false;
        }
    }
    
    @Override
    public void calculateCombDepths(int depthLimit)
    {
        //calculate pair depths
        setDepthDiver1(getDiver1().getLevel().getMaxDepth());
        setDepthDiver2(getDiver2().getLevel().getMaxDepth());
        
        this.totalMaxDepth = AppHelper.findMax(getDepthDiver1(), getDepthDiver2());
        
        if(getDepthDiver1() > 0 && this.totalMaxDepth > getDepthDiver1())
        {
            setDepthDiver1(getDepthDiver1()+10);
        }
        
        if(getDepthDiver2() > 0 && this.totalMaxDepth > getDepthDiver2())
        {
            setDepthDiver2(getDepthDiver2()+10);
        }
        
        if(getDepthDiver1() > depthLimit)
        {
            setDepthDiver1(depthLimit);
        }
        
        if(getDepthDiver2() > depthLimit)
        {
            setDepthDiver2(depthLimit);
        }
        
        //set totalMaxDepth of combination regarding (considering) dive depth limit
        //this.totalMaxDepth = AppHelper.findMax(getDepthDiver1(), getDepthDiver2());
    }
    
    @Override
    public int checkDiverDepth(Diver diver)
    {
        if(diver.equals(getDiver1()))
        {
            return getDepthDiver1();
        }
        else if(diver.equals(getDiver2()))
        {
            return getDepthDiver2();
        }
        else
        {
            return -1;
        }
    }

    @Override
    public float calculateCertaintyMeasure()
    {
        return this.totalMaxDepth/(Math.abs(getDiver1().getLevel().getAbsLevel()-getDiver2().getLevel().getAbsLevel())+1);
    }

    @Override
    public List<Diver> getDiverPartners(Diver diver)
    {
        List<Diver> diverPartners = new ArrayList<>();
        
        if(!diver.equals(this.getDiver1())) diverPartners.add(this.getDiver1());
        if(!diver.equals(this.getDiver2())) diverPartners.add(this.getDiver2());
        
        return diverPartners;
    }
    
    @Override
    public String toString()
    {
        return "max " + this.totalMaxDepth + "m  => "
             + getDiver1().getLvl() + "[" + getDiver1().getLevel().getMaxDepth() + "m->" + getDepthDiver1() + "m]:" + getDiver1().getName()
     + " - " + getDiver2().getLvl() + "[" + getDiver2().getLevel().getMaxDepth() + "m->" + getDepthDiver2() + "m]:" + getDiver2().getName();
    }
}