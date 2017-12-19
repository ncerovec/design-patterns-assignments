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
public class TableDecoratorCaption extends TableDecorator
{    
    public TableDecoratorCaption(Table decoratedTable) { super(decoratedTable); }
    
    @Override
    public String draw()
    {
        if(this.getCaption() != null)
        {
            return drawCaption() + this.decoratedTable.draw();
        }
        else
        {
            return this.decoratedTable.draw();
        }
    }
    
    private String drawCaption()
    {
        StringBuilder caption = new StringBuilder();
        
        int cellSize = this.getTableWidth()/this.getColumns().length;
        caption.append("\n").append('|').append(StringUtils.padCenter(this.getCaption(), this.getColumns().length*(cellSize+1)-1)).append('|');
        //caption.append("\n").append('|').append(StringUtils.padCenter(this.getCaption(), this.getTableWidth()-this.getColumns().length-1)).append('|');
        
        return caption.toString();
    }
}