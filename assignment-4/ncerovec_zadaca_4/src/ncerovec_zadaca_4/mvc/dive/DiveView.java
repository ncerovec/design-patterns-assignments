/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.mvc.dive;

import ncerovec_zadaca_4.model.dive.Dive;
import ncerovec_zadaca_4.mvc.ConsoleView;
import ncerovec_zadaca_4.table.Table;
import ncerovec_zadaca_4.table.decorator.TableDecoratorFooter;
import ncerovec_zadaca_4.table.decorator.TableDecoratorHeader;
import ncerovec_zadaca_4.table.TableDiveDivers;

/**
 * MVC - ConcreteView
 * @author nino
 */
public class DiveView extends ConsoleView
{
    private Dive dive = null;

    public DiveView(int numRows, int numCols, int numRowsBuffer)
    {
        super(numRows, numCols, numRowsBuffer);
        //this.diverList = diverList;
    }

    public void showDiveView(Dive dive)
    {
        this.dive = dive;
        this.showView();
    }

    @Override
    public void showView()
    {
        if(this.dive != null)
        {
            TableDiveDivers tableDiveDivers = new TableDiveDivers(this.dive, this.getNumCols()-10, true);
            Table decoratedDiveDiversTable = new TableDecoratorFooter(new TableDecoratorHeader(tableDiveDivers));
            String decoratedTableDraw = decoratedDiveDiversTable.draw();
            this.printConsole(decoratedTableDraw);    //System.out.println(decoratedTableDraw);
        }
        else
        {
            this.printConsole("Ne postoji uron za prikaz!");
        }
    }
}
