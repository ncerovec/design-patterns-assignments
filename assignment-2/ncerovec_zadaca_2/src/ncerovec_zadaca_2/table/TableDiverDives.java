/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_2.table;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_2.AppHelper;
import ncerovec_zadaca_2.model.Club;
import ncerovec_zadaca_2.model.dive.Dive;
import ncerovec_zadaca_2.model.dive.combinations.DiveCombination;
import ncerovec_zadaca_2.model.diver.Diver;

/**
 * DECORATOR - ConcreteComponent
 * @author nino
 */
public class TableDiverDives extends TableBasic
{
    Diver diver = null;
    
    public TableDiverDives(Diver diver, int cellSize)
    {
        super(cellSize);
        this.diver = diver;   
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
        }
        
        return rows;
    }
}