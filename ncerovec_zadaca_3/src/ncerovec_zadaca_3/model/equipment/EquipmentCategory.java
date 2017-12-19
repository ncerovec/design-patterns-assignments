/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.model.equipment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nino
 */
public class EquipmentCategory extends Equipment
{
    private List<EquipmentObject> equipment = new ArrayList();

    public List<EquipmentObject> getEquipment() { return equipment; }
    public void setEquipment(List<EquipmentObject> equipment) { this.equipment = equipment; }
    
    public EquipmentCategory(String id, String name) { super(id, name); }
}