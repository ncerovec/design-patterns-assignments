/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.model.equipment;

import java.util.ArrayList;
import java.util.List;

/**
 * PROTOTYPE - ConcretePrototype
 * @author nino
 */
public class EquipmentCategory extends Equipment
{
    private List<EquipmentPart> equipment = new ArrayList();

    public List<EquipmentPart> getEquipment() { return equipment; }
    public void setEquipment(List<EquipmentPart> equipment) { this.equipment = equipment; }
    
    public EquipmentCategory(String id, String name) { super(id, name); }

    @Override
    public Equipment clone()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public static class Id
    {
        public static final String MASK = "0.1";
        public static final String SNORKEL = "0.2";
        public static final String FINS = "0.3";
        public static final String WET_SUIT = "1.1.1";
        public static final String SEMI_DRY_SUIT = "1.1.2";
        public static final String DRY_SUIT = "1.1.3";
        public static final String GLOVES = "1.3";
        public static final String BOOTS = "1.5";
        public static final String REGULATOR = "2.1";
        public static final String DIVE_TANK = "2.2";
        public static final String BCD = "3.1";
        public static final String LEAD = "3.2";
        public static final String PDA = "4.1";
        public static final String COMPASS = "4.2";
        public static final String LIGHT = "5.1";
        public static final String KNIFE = "5.2";
        public static final String BUOY = "5.3";
        public static final String CAMERA = "6.1";
    }
}