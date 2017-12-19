/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.model.diver;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_4.model.Level;
import ncerovec_zadaca_4.model.agency.Agency;
import ncerovec_zadaca_4.model.diver.specialty.Specialty;
import ncerovec_zadaca_4.model.equipment.EquipmentPart;
import ncerovec_zadaca_4.model.equipment.loan.EquipmentLoan;

/**
 * FACTORY METHOD - Product
 * LEASING - Holder
 * @author nino
 */
public abstract class Diver
{
    private String name = null;
    private Agency agency = null;
    private String lvl = null;
    private Level level = null;
    private int birthYear = 0;
    
    private List<Specialty> specialties = new ArrayList();
    
    private String equipmentStatus = Diver.EquipmentStatus.NONE;
    private List<EquipmentLoan> equipmentLoan = new ArrayList();

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

    
    public List<Specialty> getSpecialties() { return specialties; }
    public void setSpecialties(List<Specialty> specialties) { this.specialties = specialties; }

    
    public String getEquipmentStatus() { return equipmentStatus; }
    public void setEquipmentStatus(String equipmentStatus) { this.equipmentStatus = equipmentStatus; }
    
    public List<EquipmentLoan> getEquipmentLoan()
    {
        /*
        List<EquipmentLoan> activeEquipmentLoan = new ArrayList();
        
        for(EquipmentLoan equipmentLoan : this.equipmentLoan)
        {
            if(equipmentLoan.getLoanEquipment().getQty() > 0) activeEquipmentLoan.add(equipmentLoan);
        }
        
        return activeEquipmentLoan;
        */
        
        return equipmentLoan;
    }
    public void setEquipmentLoan(List<EquipmentLoan> equipmentLoan) { this.equipmentLoan = equipmentLoan; }
    
    public List<EquipmentPart> getBorrowedEquipment()
    {
        List<EquipmentPart> borrowedEquipmentList = new ArrayList();
        
        for(EquipmentLoan borrowedEquipment : this.getEquipmentLoan())
        {
            borrowedEquipmentList.add(borrowedEquipment.getLoanEquipment());
        }
        
        return borrowedEquipmentList;
    }
    
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
    
    public boolean hasSpecialty(String requiredSpecialtyTitle)
    {
        for(Specialty specialty : this.specialties)
        {
            if(specialty.getTitle().equals(requiredSpecialtyTitle)) return true;
        }
        
        return false;
    }            

    public boolean hasEquipment(String categoryId)
    {
        for(EquipmentPart equipment : this.getBorrowedEquipment())
        {
            if(equipment.getId().startsWith(categoryId) && equipment.getQty() > 0)
            {
                return true;
            }
        }
        
        return false;
    }
    
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
    
    
    public static class EquipmentStatus
    {
        public static final String NONE = "Bez opreme";
        public static final String PARTIAL = "Djelomična oprema";
        public static final String FULL = "Potpuna oprema";
    }
}
