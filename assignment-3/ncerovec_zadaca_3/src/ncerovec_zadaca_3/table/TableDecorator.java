/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.table;

import java.util.List;

/**
 * DECORATOR - Decorator
 * @author nino
 */
public abstract class TableDecorator implements Table
{
    protected Table decoratedTable; // the Table being decorated
    
    @Override
    public int getTableWidth() { return this.decoratedTable.getTableWidth(); }
    
    @Override
    public String getCaption() { return this.decoratedTable.getCaption(); }

    @Override
    public String[] getColumns() { return this.decoratedTable.getColumns(); }

    @Override
    public List<String[]> getRows() { return this.decoratedTable.getRows(); }
    
    public TableDecorator(Table decoratedTable) { this.decoratedTable = decoratedTable; }
    
    @Override
    public String draw() { return this.decoratedTable.draw(); }
}
