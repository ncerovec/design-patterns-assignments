/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.table;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_3.AppHelper;
import ncerovec_zadaca_3.model.Club;
import ncerovec_zadaca_3.model.dive.Dive;
import ncerovec_zadaca_3.model.dive.combinations.DiveCombination;
import ncerovec_zadaca_3.model.diver.Diver;

/**
 * DECORATOR - ConcreteComponent
 * @author nino
 */
public class TableDiverDives extends TableBasic
{
    Diver diver = null;
    boolean details = false;
    
    public TableDiverDives(Diver diver, int tableWidth, boolean details)
    {
        super(tableWidth);
        this.diver = diver;
        this.details = details;
    }

    @Override
    public String getCaption()
    {
        return "Popis urona za ronioca: " + this.diver.getName();
    }
    
    @Override
    public String[] getColumns()
    {
        String[] columnHeaders = {"Rb.", "Datum i vrijeme", "Max. dubina", "Ronioc dubina", "Broj ronioca", "Partneri", "Mjera sig."};
        
        return columnHeaders;
    }
        
    @Override
    public List<String[]> getRows()
    {
        List<String[]> rows = new ArrayList<>();
        
        List<Dive> diverDives = Club.getInstance().getDiverDives(this.diver);
        
        for(int i=0; i<diverDives.size(); i++)
        {
            Dive dive = diverDives.get(i);
            DiveCombination diveCmb = dive.getDiverCombination(diver);

            List<String> rowValues = new ArrayList<String>();
                        
            if(diveCmb != null)
            {
                rowValues.add(Integer.toString(i+1)+".");
                rowValues.add(AppHelper.formatDateTime(dive.getDateTime()));
                rowValues.add(Integer.toString(dive.getMaxDepth())+"m");
                rowValues.add(Integer.toString(diveCmb.checkDiverDepth(diver))+"m");
                rowValues.add(Integer.toString(dive.getNumDivers()));
                rowValues.add(diveCmb.printDiverPartners(diver));
                rowValues.add(Float.toString(diveCmb.calculateCertaintyMeasure()));
            }
            
            rows.add(rowValues.toArray(new String[0]));
            
            if(this.details)    //add diver-dives table nested
            {
                TableDiverDiveEquipment tableDiverDiveEquipment = new TableDiverDiveEquipment(diver, dive, ((int)(tableWidth*0.8)));
                Table decoratedDiverDiveEquipmentTable = new TableDecoratorLineBelow(new TableDecoratorLineAbove(new TableDecoratorColumns(tableDiverDiveEquipment)));
                String decoratedTableDraw = decoratedDiverDiveEquipmentTable.draw();        //get decorated table
                String[] tableRows = decoratedTableDraw.trim().split("\n");

                for(String tableRow : tableRows)
                {
                    rows.add(new String[]{tableRow});
                }
            }
        }
        
        return rows;
    }
}