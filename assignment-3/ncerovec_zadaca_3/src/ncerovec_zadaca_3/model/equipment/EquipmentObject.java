/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.model.equipment;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_3.model.diver.specialty.Specialty;

/**
 *
 * @author nino
 */
public class EquipmentObject extends Equipment
{
    private int qty = 0;
    
    private String temp = null;
    private String hood = null;
    private String underSuit = null;
    private String nightDive = null;
    private String photo = null;
    
    //private List<Specialty> requiredSpecialty = new ArrayList();
    
    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }
    
    public String getTemp() { return temp; }
    public void setTemp(String temp) { this.temp = temp; }

    public String getHood() { return hood; }
    public void setHood(String hood) { this.hood = hood; }

    public String getUnderSuit() { return underSuit; }
    public void setUnderSuit(String underSuit) { this.underSuit = underSuit; }

    public String getNightDive() { return nightDive; }
    public void setNightDive(String nightDive) { this.nightDive = nightDive; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }
    
    public EquipmentObject(String id, String name, int qty)
    {
        super(id, name);
        this.qty = qty;
    }
    
    public List<String> getDependencyEquipment()
    {
        List<String> dependencyEquipment = new ArrayList();
        
        if(this.getHood().equals("+")) { dependencyEquipment.add("1.4"); }
        
        //if(this.getUnderSuit().equals("+")) { dependencyEquipment.add("1.2"); }
        
        if(this.getNightDive().equals("*")) { dependencyEquipment.add("5.1"); }
        
        if(this.getPhoto().equals("*")) { dependencyEquipment.add("6.2"); }
            
        return dependencyEquipment;
    }
}