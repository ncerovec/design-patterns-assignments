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
public abstract class TableDecoratorLine extends TableDecorator
{
    char lineElement = '-';
    
    public TableDecoratorLine(Table decoratedTable) { super(decoratedTable); }
    
    protected String drawLine()
    {
        int cellSize = this.getTableWidth()/this.getColumns().length;
        return "\n" + "|" + StringUtils.padCenter("", (cellSize+1)*getColumns().length-1, this.lineElement) + "|";
        //return "\n" + "|" + StringUtils.padCenter("", this.getTableWidth()-this.getColumns().length-1, this.lineElement) + "|";
    }
}