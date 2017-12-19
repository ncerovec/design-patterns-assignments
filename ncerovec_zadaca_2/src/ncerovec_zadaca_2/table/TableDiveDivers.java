/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_2.table;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_2.AppHelper;
import ncerovec_zadaca_2.model.dive.Dive;
import ncerovec_zadaca_2.model.dive.combinations.DiveCombination;
import ncerovec_zadaca_2.model.diver.Diver;

/**
 * DECORATOR - ConcreteComponent
 * @author nino
 */
public class TableDiveDivers extends TableBasic
{
    Dive dive = null;
    
    public TableDiveDivers(Dive dive, int cellSize)
    {
        super(cellSize);
        this.dive = dive;   
    }
    
    @Override
    public String getCaption()
    {
        return "Popis ronioca za uron: " + AppHelper.formatDateTime(this.dive.getDateTime());
    }

    @Override
    public String[] getColumns()
    {
        String[] columnHeaders = {"Rb.", "Ronioc", "Dubina", "Partneri", "Mjera sig."};
        
        return columnHeaders;
    }
        
    @Override
    public List<String[]> getRows()
    {
        List<String[]> rows = new ArrayList<>();
        
        for(int i=0; i<this.dive.getDivers().size(); i++)
        {
            Diver diver = this.dive.getDivers().get(i);
            DiveCombination diveCmb = this.dive.getDiverCombination(diver);

            List<String> rowValues = new ArrayList<String>();
                        
            if(diveCmb != null)
            {
                rowValues.add(Integer.toString(i+1)+".");
                rowValues.add(diver.getName());
                rowValues.add(diveCmb.checkDiverDepth(diver) + "m");
                rowValues.add(diveCmb.printDiverPartners(diver));
                rowValues.add(Float.toString(diveCmb.calculateCertaintyMeasure()));
            }
            
            rows.add(rowValues.toArray(new String[0]));
        }
        
        return rows;
    }
}