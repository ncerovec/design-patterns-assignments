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
import ncerovec_zadaca_4.model.dive.combinations.DiveCombination;
import ncerovec_zadaca_4.model.diver.Diver;

/**
 * DECORATOR - ConcreteComponent
 * @author nino
 */
public class TableDiveDivers extends TableBasic
{
    Dive dive = null;
    boolean details = false;
    
    public TableDiveDivers(Dive dive, int tableWidth, boolean details)
    {
        super(tableWidth);
        this.dive = dive;
        this.details = details;
    }
    
    @Override
    public String getCaption()
    {
        return "Popis ronioca za uron: " + AppHelper.formatDateTime(this.dive.getDateTime());
    }

    @Override
    public String[] getColumns()
    {
        String[] columnHeaders = {"Rb.", "Ronioc", "Dubina", "Partneri", "Mjera sig.", "Oprema"};
        
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
                rowValues.add(diver.getEquipmentStatus());
            }
            
            rows.add(rowValues.toArray(new String[0]));
            
            if(this.details)    //add diver-equipment-loans table nested
            {
                TableDiverEquipmentLoan tableDiverEquipmentLoan = new TableDiverEquipmentLoan(diver, ((int)(tableWidth*0.8)));
                Table decoratedDiverEquipmentLoanTable = new TableDecoratorLineBelow(new TableDecoratorLineAbove(new TableDecoratorColumns(tableDiverEquipmentLoan)));
                String decoratedTableDraw = decoratedDiverEquipmentLoanTable.draw();        //get decorated table
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