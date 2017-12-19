/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.mvc.divers;

import java.util.List;
import ncerovec_zadaca_4.model.diver.Diver;
import ncerovec_zadaca_4.mvc.ConsoleView;
import ncerovec_zadaca_4.table.Table;
import ncerovec_zadaca_4.table.decorator.TableDecoratorFooter;
import ncerovec_zadaca_4.table.decorator.TableDecoratorHeader;
import ncerovec_zadaca_4.table.TableDivers;

/**
 * MVC - ConcreteView
 * @author nino
 */
public class DiversView extends ConsoleView
{
    private List<Diver> diverList = null;

    public DiversView(int numRows, int numCols, int numRowsBuffer)
    {
        super(numRows, numCols, numRowsBuffer);
        //this.diverList = diverList;
    }
    
    public void showDiversView(List<Diver> diverList)
    {
        this.diverList = diverList;
        this.showView();
    }

    @Override
    public void showView()
    {
        if(this.diverList != null)
        {
            TableDivers tableDivers = new TableDivers(this.diverList, this.getNumCols()-10, false);
            Table decoratedDiversTable = new TableDecoratorFooter(new TableDecoratorHeader(tableDivers));
            String decoratedTableDraw = decoratedDiversTable.draw();
            this.printConsole(decoratedTableDraw);    //System.out.println(decoratedTableDraw);
        }
        else
        {
            this.printConsole("Ne postoje ronioci za prikaz!");
        }
    }
}
