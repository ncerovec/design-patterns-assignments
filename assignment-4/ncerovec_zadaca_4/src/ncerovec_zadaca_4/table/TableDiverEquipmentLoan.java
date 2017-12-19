/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.table;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_4.helper.AppHelper;
import ncerovec_zadaca_4.model.diver.Diver;
import ncerovec_zadaca_4.model.equipment.loan.EquipmentLoan;

/**
 * DECORATOR - ConcreteComponent
 * @author nino
 */
public class TableDiverEquipmentLoan extends TableBasic
{
    Diver diver = null;
    
    public TableDiverEquipmentLoan(Diver diver, int tableWidth)
    {
        super(tableWidth);
        this.diver = diver;   
    }

    @Override
    public String getCaption()
    {
        return "Popis posuđene opreme za ronioca: " + this.diver.getName();
    }
    
    @Override
    public String[] getColumns()
    {
        String[] columnHeaders = {"Id", "Naziv", "Količina", "Temp. vode", "Posudba početak", "Posudba kraj", "Broj dana"};
        
        return columnHeaders;
    }
        
    @Override
    public List<String[]> getRows()
    {
        List<String[]> rows = new ArrayList<>();
        
        List<EquipmentLoan> equipmentLoans = this.diver.getEquipmentLoan();
        
        for(int i=0; i<equipmentLoans.size(); i++)
        {
            EquipmentLoan equipmentLoan = equipmentLoans.get(i);
            
            List<String> rowValues = new ArrayList<String>();
                        
            rowValues.add(equipmentLoan.getLoanEquipment().getId());
            rowValues.add(equipmentLoan.getLoanEquipment().getName());
            rowValues.add(Integer.toString(equipmentLoan.getLoanEquipment().getQty()));
            rowValues.add(equipmentLoan.getLoanEquipment().getTemp());
            rowValues.add(AppHelper.formatDateTime(equipmentLoan.getLoanStart()));
            rowValues.add(AppHelper.formatDateTime(equipmentLoan.getLoanEnd()));
            rowValues.add(Integer.toString(equipmentLoan.getLoanDays()));
            
            rows.add(rowValues.toArray(new String[0]));
        }
        
        return rows;
    }
}