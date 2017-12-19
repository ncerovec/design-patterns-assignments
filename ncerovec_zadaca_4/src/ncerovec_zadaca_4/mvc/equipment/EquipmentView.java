/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.mvc.equipment;

import java.util.List;
import ncerovec_zadaca_4.model.equipment.EquipmentPart;
import ncerovec_zadaca_4.mvc.ConsoleView;
import ncerovec_zadaca_4.table.Table;
import ncerovec_zadaca_4.table.decorator.TableDecoratorFooter;
import ncerovec_zadaca_4.table.decorator.TableDecoratorHeader;
import ncerovec_zadaca_4.table.TableEquipment;

/**
 * MVC - ConcreteView
 * @author nino
 */
public class EquipmentView extends ConsoleView
{
    private List<EquipmentPart> equipmentList = null;

    public EquipmentView(int numRows, int numCols, int numRowsBuffer)
    {
        super(numRows, numCols, numRowsBuffer);
        //this.diverList = diverList;
    }

    public void showEquipmentView(List<EquipmentPart> equipmentList)
    {
        this.equipmentList = equipmentList;
        this.showView();
    }

    @Override
    public void showView()
    {
        if(this.equipmentList != null)
        {
            TableEquipment tableEquipment = new TableEquipment(this.equipmentList, this.getNumCols()-10);
            Table decoratedEquipmentTable = new TableDecoratorFooter(new TableDecoratorHeader(tableEquipment));
            String decoratedTableDraw = decoratedEquipmentTable.draw();
            this.printConsole(decoratedTableDraw);    //System.out.println(decoratedTableDraw);
        }
        else
        {
            this.printConsole("Ne postoji oprema za prikaz!");
        }
    }
}
