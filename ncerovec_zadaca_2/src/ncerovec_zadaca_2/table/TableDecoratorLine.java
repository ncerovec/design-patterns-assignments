/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_2.table;

import ncerovec_zadaca_2.StringUtils;

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
        return "\n" + "|" + StringUtils.padCenter("", (getCellSize()+1)*getColumns().length-1, this.lineElement) + "|";
    }
}