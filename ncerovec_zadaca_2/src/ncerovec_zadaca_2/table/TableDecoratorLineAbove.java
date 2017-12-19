/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_2.table;

/**
 * DECORATOR - ConcreteDecorator
 * @author nino
 */
public class TableDecoratorLineAbove extends TableDecoratorLine
{
    public TableDecoratorLineAbove(Table decoratedTable) { super(decoratedTable); }
    
    public TableDecoratorLineAbove(Table decoratedTable, char lineElement) { super(decoratedTable); this.lineElement = lineElement; }
    
    @Override
    public String draw()
    {
        return this.drawLine() + this.decoratedTable.draw();
    }
}