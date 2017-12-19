/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.table;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_4.model.equipment.EquipmentPart;

/**
 * DECORATOR - ConcreteComponent
 * @author nino
 */
public class TableEquipment extends TableBasic
{
    List<EquipmentPart> equipmentList = null;
    
    public TableEquipment(List<EquipmentPart> equipmentList, int tableWidth)
    {
        super(tableWidth);
        this.equipmentList = equipmentList;
    }

    @Override
    public String getCaption()
    {
        return "Popis opreme";
    }
    
    @Override
    public String[] getColumns()
    {
        String[] columnHeaders = {"Id", "Naziv", "Količina", "Temp. vode", "Kapuljača", "Pododijelo", "Noćni uron", "Snimanje"};
        
        return columnHeaders;
    }
        
    @Override
    public List<String[]> getRows()
    {
        List<String[]> rows = new ArrayList<>();
        
        for(int i=0; i<equipmentList.size(); i++)
        {
            EquipmentPart equipment = equipmentList.get(i);
            
            List<String> rowValues = new ArrayList<String>();
                        
            rowValues.add(equipment.getId());
            rowValues.add(equipment.getName());
            rowValues.add(Integer.toString(equipment.getQty()));
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