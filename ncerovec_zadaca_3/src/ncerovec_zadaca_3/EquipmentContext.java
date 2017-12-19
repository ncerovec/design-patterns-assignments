/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ncerovec_zadaca_3.model.Club;
import ncerovec_zadaca_3.model.equipment.EquipmentCategory;
import ncerovec_zadaca_3.model.equipment.EquipmentObject;
import ncerovec_zadaca_3.mvc.EquipmentController;
import ncerovec_zadaca_3.mvc.EquipmentView;

/**
 * Interpreter - Context
 * @author nino
 */
public class EquipmentContext
{
    public static EquipmentView equipmentView = null;
    public static EquipmentController equipmentController = null;
    
    /**
     * Read equipment file and create equipment
     * @param equipmentFile
     * @return 
     */
    public List<EquipmentCategory> readEquipment(String equipmentFile)
    {
        System.out.println("\n -- Čitanje opreme --");
        
        List<EquipmentCategory> equipmentStock = Club.getInstance().getEquipment();
        
        try
        {           
            BufferedReader inEquipment = new BufferedReader(new FileReader(equipmentFile));                

            EquipmentCategory currEquipmentCategory = null;
            
            String equipmentLine = null;
            while((equipmentLine = inEquipment.readLine()) != null)
            {
                System.out.println("Pročitan redak: " + equipmentLine);
                String[] equipmentProps = equipmentLine.split(";");
                
                if(equipmentProps.length == 2)
                {
                    String categoryId = equipmentProps[0];
                    String categoryTitle = equipmentProps[1];
                    currEquipmentCategory = new EquipmentCategory(categoryId, categoryTitle);                    
                    if(currEquipmentCategory != null) equipmentStock.add(currEquipmentCategory);
                    
                    System.out.println(" -> kategorija opreme kreirana");
                }
                else if(equipmentProps.length == 8)
                {   
                    String equipmentId = equipmentProps[0];
                    String equipmentName = equipmentProps[1];
                    String equipmentTemp = equipmentProps[2];
                    String equipmentHood = equipmentProps[3];
                    String equipmentUnderSuit = equipmentProps[4];
                    String equipmentNightDive = equipmentProps[5];
                    String equipmentPhoto = equipmentProps[6];
                    int equipmentQty = Integer.parseInt(equipmentProps[7]);
                    
                    EquipmentObject newEquipment = new EquipmentObject(equipmentId, equipmentName, equipmentQty);                    
                    newEquipment.setTemp(equipmentTemp);
                    newEquipment.setHood(equipmentHood);
                    newEquipment.setUnderSuit(equipmentUnderSuit);
                    newEquipment.setNightDive(equipmentNightDive);
                    newEquipment.setPhoto(equipmentPhoto);
                    
                    newEquipment.setFileRecord(equipmentLine);
                    
                    if(currEquipmentCategory != null) currEquipmentCategory.getEquipment().add(newEquipment);
                    
                    System.out.println(" -> oprema kreirana");
                }
                else
                {
                    System.out.println(" -> neispravan redak");
                }
            }
            
            inEquipment.close();
        }
        catch (IOException ex) { Logger.getLogger(Diving.class.getName()).log(Level.SEVERE, null, ex); }
        
        return equipmentStock;
    }
}
