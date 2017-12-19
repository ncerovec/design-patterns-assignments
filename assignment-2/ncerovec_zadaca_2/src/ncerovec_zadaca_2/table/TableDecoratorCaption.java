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
        
        caption.append("\n").append('|').append(StringUtils.padCenter(this.getCaption(), this.getColumns().length*(this.getCellSize()+1)-1)).append('|');
        
        return caption.toString();
    }
}