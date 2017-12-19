/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_2.table;

import java.util.List;
import ncerovec_zadaca_2.StringUtils;

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
        
        for(String column : this.getColumns())
        {
            columns.append('|').append(StringUtils.padCenter(column, this.getCellSize()));
        }

        columns.append('|');
        
        return columns.toString();
    }
}