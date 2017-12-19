/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.table;

import java.util.List;

/**
 * DECORATOR - Component
 * @author nino
 */
public interface Table
{
    public int getTableWidth();
    public String getCaption();
    public String[] getColumns();
    public List<String[]> getRows();
    
    public String draw();
}
