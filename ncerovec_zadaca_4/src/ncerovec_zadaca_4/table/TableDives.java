/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.table;

import ncerovec_zadaca_4.table.decorator.TableDecoratorLineAbove;
import ncerovec_zadaca_4.table.decorator.TableDecoratorLineBelow;
import ncerovec_zadaca_4.table.decorator.TableDecoratorColumns;
import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_4.helper.AppHelper;
import ncerovec_zadaca_4.model.dive.Dive;

/**
 * DECORATOR - ConcreteComponent
 * @author nino
 */
public class TableDives extends TableBasic
{
    List<Dive> dives = null;
    boolean details = false;
    
    public TableDives(List<Dive> dives, int tableWidth, boolean details)
    {
        super(tableWidth, (short) 0);
        this.dives = dives;
        this.details = details;
    }

    @Override
    public String getCaption()
    {
        return "Popis urona";
    }
    
    @Override
    public String[] getColumns()
    {
        String[] columnHeaders = {"Rb.", "Datum i vrijeme", "Max. dubina", "Broj ronioca", "Algoritam", "Mjera sig."};
        
        return columnHeaders;
    }
    
    @Override
    public List<String[]> getRows()
    {
        List<String[]> rows = new ArrayList<>();
        
        for(int i=0; i<this.dives.size(); i++)
        {
            Dive dive = this.dives.get(i);
            
            List<String> rowValues = new ArrayList<>();
            
            rowValues.add(Integer.toString(i+1)+". URON");
            rowValues.add(AppHelper.formatDateTime(dive.getDateTime()));
            rowValues.add(Integer.toString(dive.getMaxDepth())+"m");
            rowValues.add(Integer.toString(dive.getNumDivers()));
            rowValues.add(dive.getUsedAlgorithm().getAlgName());
            rowValues.add(Float.toString(dive.getTotalCertaintyMeasure()));
            
            rows.add(rowValues.toArray(new String[0]));
            
            if(this.details)    //add dive-divers table nested
            {
                
                TableDiveDivers tableDiveDivers = new TableDiveDivers(dive, ((int)(tableWidth*0.8)), this.details);
                Table decoratedDiveDiversTable = new TableDecoratorLineBelow(new TableDecoratorLineAbove(new TableDecoratorColumns(tableDiveDivers)));
                String decoratedTableDraw = decoratedDiveDiversTable.draw();        //get decorated table
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