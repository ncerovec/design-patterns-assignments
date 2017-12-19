/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_2.table;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_2.model.Club;
import ncerovec_zadaca_2.model.diver.Diver;

/**
 * DECORATOR - ConcreteComponent
 * @author nino
 */
public class TableDivers extends TableBasic
{
    List<Diver> divers = null;
    boolean details = false;
    
    public TableDivers(List<Diver> divers, int cellSize, boolean details)
    {
        super(cellSize, (short) 0);
        this.divers = divers;
        this.details = details;
    }

    @Override
    public String getCaption()
    {
        return "Popis ronioca";
    }
    
    @Override
    public String[] getColumns()
    {
        String[] columnHeaders = {"Rb.", "Ime", "God. rođ.", "Agencija", "Level"};
        
        return columnHeaders;
    }
    
    @Override
    public List<String[]> getRows()
    {
        List<String[]> rows = new ArrayList<>();
        
        for(int i=0; i<this.divers.size(); i++)
        {
            Diver diver = this.divers.get(i);
            
            List<String> rowValues = new ArrayList<String>();
            
            rowValues.add(Integer.toString(i+1)+ ". RONIOC");
            rowValues.add(diver.getName());
            rowValues.add(Integer.toString(diver.getBirthYear()));
            rowValues.add(diver.getAgency().getName());
            rowValues.add(diver.getLevelString());
            
            rows.add(rowValues.toArray(new String[0]));
            
            if(this.details && Club.getInstance().getDiverDives(diver).size() > 0)    //add diver-dives table nested
            {
                TableDiverDives tableDiverDives = new TableDiverDives(diver, 20);
                Table decoratedDiverDivesTable = new TableDecoratorLineBelow(new TableDecoratorLineAbove(new TableDecoratorColumns(tableDiverDives)));
                String decoratedTableDraw = decoratedDiverDivesTable.draw();        //get decorated table
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