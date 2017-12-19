/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.table.decorator;

import ncerovec_zadaca_4.helper.StringUtils;
import ncerovec_zadaca_4.table.Table;

/**
 * DECORATOR - ConcreteDecorator
 * @author nino
 */
public class TableDecoratorColumns extends TableDecorator
{        
    public TableDecoratorColumns(Table decoratedTable) { super(decoratedTable); }
    
    @Override
    public String draw()
    {
        if(this.getColumns() != null)
        {
            return drawColumns() + this.decoratedTable.draw();
        }
        else
        {
            return this.decoratedTable.draw();
        }
    }
    
    private String drawColumns()
    {
        StringBuilder columns = new StringBuilder("\n");
        
        int cellSize = this.getTableWidth()/this.getColumns().length;
        //int cellSize = (this.getTableWidth()-(this.getColumns().length+1))/this.getColumns().length;
        for(String column : this.getColumns())
        {
            columns.append('|').append(StringUtils.padCenter(column, cellSize));
        }

        columns.append('|');
        
        return columns.toString();
    }
}