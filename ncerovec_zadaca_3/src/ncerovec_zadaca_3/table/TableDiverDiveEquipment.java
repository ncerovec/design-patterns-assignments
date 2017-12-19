/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.table;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_3.AppHelper;
import ncerovec_zadaca_3.model.dive.Dive;
import ncerovec_zadaca_3.model.diver.Diver;
import ncerovec_zadaca_3.model.equipment.EquipmentObject;

/**
 * DECORATOR - ConcreteComponent
 * @author nino
 */
public class TableDiverDiveEquipment extends TableBasic
{
    Dive dive = null;
    Diver diver = null;
    
    public TableDiverDiveEquipment(Diver diver, Dive dive, int tableWidth)
    {
        super(tableWidth);
        this.dive = dive;
        this.diver = diver;   
    }

    @Override
    public String getCaption()
    {
        return "Popis opreme za ronioca: " + this.diver.getName() + " - uron " + AppHelper.formatDateTime(this.dive.getDateTime());
    }
    
    @Override
    public String[] getColumns()
    {
        String[] columnHeaders = {"Id", "Naziv", "Temp. vode", "Kapuljača", "Pododijelo", "Noćni uron", "Snimanje"};
        
        return columnHeaders;
    }
        
    @Override
    public List<String[]> getRows()
    {
        List<String[]> rows = new ArrayList<>();
        
        List<EquipmentObject> equipmentList = this.dive.getEquipmentAssignments().get(this.diver);
        
        for(int i=0; i<equipmentList.size(); i++)
        {
            EquipmentObject equipment = equipmentList.get(i);
            
            List<String> rowValues = new ArrayList<String>();
                        
            rowValues.add(equipment.getId());
            rowValues.add(equipment.getName());
            rowValues.add(equipment.getTemp());
            rowValues.add(equipment.getHood());
            rowValues.add(equipment.getUnderSuit());
            rowValues.add(equipment.getNightDive());
            rowValues.add(equipment.getPhoto());
            
            rows.add(rowValues.toArray(new String[0]));
        }
        
        return rows;
    }
}