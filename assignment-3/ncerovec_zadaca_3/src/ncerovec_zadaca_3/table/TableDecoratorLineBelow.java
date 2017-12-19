/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.table;

/**
 * DECORATOR - ConcreteDecorator
 * @author nino
 */
public class TableDecoratorLineBelow extends TableDecoratorLine
{
    public TableDecoratorLineBelow(Table decoratedTable) { super(decoratedTable); }
    
    public TableDecoratorLineBelow(Table decoratedTable, char lineElement) { super(decoratedTable); this.lineElement = lineElement; }
    
    @Override
    public String draw()
    {
        return this.decoratedTable.draw() + this.drawLine();
    }
}