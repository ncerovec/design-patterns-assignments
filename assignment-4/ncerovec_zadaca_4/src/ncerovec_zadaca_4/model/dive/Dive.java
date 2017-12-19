/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.model.dive;

import java.util.ArrayList;
import ncerovec_zadaca_4.model.dive.combinations.DiveCombination;
import java.util.Date;
import java.util.List;
import java.util.Map;
import ncerovec_zadaca_4.algorithm.Algorithm;
import ncerovec_zadaca_4.model.diver.Diver;
import ncerovec_zadaca_4.model.equipment.EquipmentPart;

/**
 * BUILDER - Product
 * @author nino
 */
public class Dive
{
    //primary properties
    private Date dateTime = null;
    private int maxDepth = 0;
    private int numDivers = 0;
    
    //additional properties
    private float tempWater = -1;
    private boolean nightDive = false;
    private int numCameras = 0;
    //private String place = null;
    
    //functional properties
    private Algorithm usedAlgorithm = null;
    private float totalCertaintyMeasure = 0;
            
    //aggregation properties
    private List<Diver> divers = null;
    private List<DiveCombination> diveCmbs = null;
    private Map<Diver, List<EquipmentPart>> equipmentAssignments = null;    //[key = null] - dive equipment requirements (list of required equipment parts) WARNING: references to Dive.equipment instances

    public Date getDateTime() { return dateTime; }
    public void setDateTime(Date dateTime) { this.dateTime = dateTime; }

    public int getMaxDepth() { return maxDepth; }
    public void setMaxDepth(int maxDepth) { this.maxDepth = maxDepth; }

    public int getNumDivers() { return numDivers; }
    public void setNumDivers(int numDivers) { this.numDivers = numDivers; }
    

    public float getTempWater() { return tempWater; }
    public void setTempWater(float tempWater) { this.tempWater = tempWater; }

    public boolean isNightDive() { return nightDive; }
    public void setNightDive(boolean nightDive) { this.nightDive = nightDive; }

    public int getNumCameras() { return numCameras; }
    public void setNumCameras(int numCameras) { this.numCameras = numCameras; }
            
    
    public Algorithm getUsedAlgorithm() { return usedAlgorithm; }
    public void setUsedAlgorithm(Algorithm usedAlgorithm) { this.usedAlgorithm = usedAlgorithm; }

    public float getTotalCertaintyMeasure() { return totalCertaintyMeasure; }
    public void setTotalCertaintyMeasure(float totalCertaintyMeasure) { this.totalCertaintyMeasure = totalCertaintyMeasure; }

    
    public List<Diver> getDivers() { return divers; }
    public void setDivers(List<Diver> divers) { this.divers = divers; }

    public List<DiveCombination> getDiveCmbs() { return diveCmbs; }
    public void setDiveCmbs(List<DiveCombination> diveCmbs) { this.diveCmbs = diveCmbs; }

    public Map<Diver, List<EquipmentPart>> getEquipmentAssignments() { return equipmentAssignments; }
    public void setEquipmentAssignments(Map<Diver, List<EquipmentPart>> equipmentAssignments) { this.equipmentAssignments = equipmentAssignments; }
    
    
    public Dive() { }   //constructor
    
    /*
    //standard dive
    public Dive(int maxDepth, int numDivers)
    {
        this.maxDepth = maxDepth;
        this.numDivers = numDivers;
    }
    
    //qualified dive
    public Dive(int maxDepth, float tempWater, boolean nightDive, int numCameras)
    {
        this.maxDepth = maxDepth;
        this.tempWater = tempWater;
        this.nightDive = nightDive;
        this.numCameras = numCameras;
    }
    */

    public DiveCombination getDiverCombination(Diver diver)
    {
        DiveCombination diveCmb = null;
        
        if(this.divers.contains(diver))
        {
            for(DiveCombination cmb : this.diveCmbs)
            {
                if(cmb.checkDiverDepth(diver) >= 0)
                {
                    diveCmb = cmb;
                    break;
                }
            }
        }
        
        return diveCmb;
    }
    
    public List<EquipmentPart> getAvailableEquipment(String categoryId)
    {
        List<EquipmentPart> availableEquipment = new ArrayList();
        
        for(EquipmentPart equipment : this.getEquipmentAssignments().get(null))   //get unassigned (dive specific) equipment
        {
            if(equipment.getId().startsWith(categoryId) && equipment.getQty() > 0)
            {
                availableEquipment.add(equipment);
            }
        }
        
        return availableEquipment;
    }
    
    @Override
    public String toString()
    {
        String text = "\n" + "URON " + "(" + getDateTime() + ")";
        text += "\n" + "Maksimalna dubina: " + getMaxDepth();
        text += "\n" + "Broj ronioca: " + getNumDivers();
        
        text += "\n" + "Ronilačke kombinacije:";
        for(DiveCombination diveCmb : this.diveCmbs)
        {
            text += "\n" + diveCmb;
        }
        
        return text;
    }
}