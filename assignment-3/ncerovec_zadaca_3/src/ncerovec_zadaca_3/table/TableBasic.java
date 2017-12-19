/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.table;

import java.util.List;
import ncerovec_zadaca_3.StringUtils;

/**
 * DECORATOR - ConcreteComponent
 * @author nino
 */
public class TableBasic implements Table
{
    protected int align = -1;   //-1=left, 0=center, 1=right
    protected int tableWidth = 20;
    
    private String caption = null;
    private String[] columns = null;
    private List<String[]> rows = null;
    
    @Override
    public int getTableWidth() { return tableWidth; }
    
    @Override
    public String getCaption() { return caption; }
    protected void setCaption(String caption) { this.caption = caption; }

    @Override
    public String[] getColumns() { return columns; }
    protected void setColumns(String[] columns) { this.columns = columns; }

    @Override
    public List<String[]> getRows() { return rows; }
    protected void setRows(List<String[]> rows) { this.rows = rows; }

    public TableBasic(int tableWidth) { if(tableWidth != 0) this.tableWidth = tableWidth; }
    
    public TableBasic(int tableWidth, short align)
    {
        if(tableWidth != 0) this.tableWidth = tableWidth;
        this.align = align;
    }
    
    public TableBasic(String caption, String[] columns, List<String[]> rows)
    {
        this.caption = caption;
        this.columns = columns;
        this.rows = rows;
    }
        
    @Override
    public String draw()
    {
        StringBuilder body = new StringBuilder();
        
        for(String[] rowValues : this.getRows())
        {
            StringBuilder row = new StringBuilder();
            
            int cellSize = this.getTableWidth()/this.getColumns().length;
                        
            for(String columnValue : rowValues)
            {
                if(rowValues.length == 1) align = 0;    //align nested tables in center
                    
                int rowCellSize = ((cellSize+1)*getColumns().length-1)/rowValues.length;
                //int rowCellSize = (this.getTableWidth()-(rowValues.length+1))/rowValues.length;
                
                if(columnValue.length() > rowCellSize) columnValue = columnValue.substring(0, rowCellSize-3) + "...";
                
                switch(align)
                {
                    case 1:
                        row.append('|').append(StringUtils.padLeft(columnValue, rowCellSize));
                    break;
                    
                    case 0:
                        row.append('|').append(StringUtils.padCenter(columnValue, rowCellSize));
                    break;
                    
                    default:    //case -1:
                        row.append('|').append(StringUtils.padRight(columnValue, rowCellSize));
                    break;
                }
            }            
            
            body.append("\n").append(row).append('|');
        }
        
        return body.toString();
    }
}