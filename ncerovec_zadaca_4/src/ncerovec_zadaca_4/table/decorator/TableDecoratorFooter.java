/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.table.decorator;

import ncerovec_zadaca_4.table.Table;

/**
 * DECORATOR - ConcreteDecorator
 * @author nino
 */
public class TableDecoratorFooter extends TableDecorator
{
    public TableDecoratorFooter(Table decoratedTable)
    {
        super(new TableDecoratorLineBelow(decoratedTable));
    }
}