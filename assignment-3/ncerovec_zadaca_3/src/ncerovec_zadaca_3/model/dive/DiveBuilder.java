/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.model.dive;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ncerovec_zadaca_3.AppHelper;
import ncerovec_zadaca_3.algorithm.Algorithm;
import ncerovec_zadaca_3.model.Club;
import ncerovec_zadaca_3.model.diver.Diver;
import ncerovec_zadaca_3.model.equipment.EquipmentCategory;
import ncerovec_zadaca_3.model.equipment.EquipmentObject;
import ncerovec_zadaca_3.strategy.divers.DiverSetStrategy;

/**
 * BUILDER - Abstract Builder (not abstract anymore)
 * @author nino
 */
public class DiveBuilder
{
    protected Dive dive = null;
        
    public Algorithm getAlgorithm() { return null; }

    public DiveBuilder() { }    //constructor
    
    public Dive getDive() { return dive; }
    public void setDive(Dive dive) { this.dive = dive; }
    
    public void createNewDive()
    {
        this.dive = new Dive();
    }
    
    public void buildMaxDepth(int maxDepth)
    {
        this.dive.setMaxDepth(maxDepth);
    }
    
    public void buildNumDivers(int numDivers)
    {
        this.dive.setNumDivers(numDivers);
    }
    
    public void buildTempWater(float tempWater)
    {
        this.dive.setTempWater(tempWater);
    }
    
    public void buildNightDive(boolean nightDive)
    {
        this.dive.setNightDive(nightDive);
    }
    
    public void buildNumCameras(int numCameras)
    {
        this.dive.setNumCameras(numCameras);
    }
    
    public void buildDatetime(String date, String time)
    {
        Date datetime = AppHelper.parseDateTime(date, time);
        
        this.dive.setDateTime(datetime);
    }
    
    public void buildDivers(DiverSetStrategy strategy)
    {
        this.dive.setDivers(strategy.generateDivers(this.dive));
        this.dive.setNumDivers(this.dive.getDivers().size());   //update number of divers (after divers generated)
    }
    
    public void buildAvailableEquipment()
    {
        Map<Diver, List<EquipmentObject>> equipmentAssignments = new HashMap();
        List<EquipmentObject> availableEquipment = new ArrayList();
        
        for(EquipmentCategory category : Club.getInstance().getEquipment())
        {
            List<EquipmentObject> equipment = category.getEquipment();

            if(equipment != null && equipment.size() > 0)
            {
                for(EquipmentObject equipmentPiece : equipment)
                {
                    int equipmentTemp = Integer.MIN_VALUE;
                    try{ equipmentTemp = Integer.parseInt(equipmentPiece.getTemp()); } catch(NumberFormatException ex) {}
                    if(equipmentTemp == Integer.MIN_VALUE || dive.getTempWater() == equipmentTemp) availableEquipment.add(equipmentPiece);
                }
            }
        }
        
        equipmentAssignments.put(null, availableEquipment);  //available equipment - not assigned (null Diver)
        this.dive.setEquipmentAssignments(equipmentAssignments);
    }
    
    //public abstract void buildDiveCombinations();
    public void buildDiveCombinations()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
