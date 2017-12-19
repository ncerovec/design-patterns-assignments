/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.mvc;

import java.util.List;
import ncerovec_zadaca_3.model.equipment.EquipmentObject;
import ncerovec_zadaca_3.table.Table;
import ncerovec_zadaca_3.table.TableDecoratorFooter;
import ncerovec_zadaca_3.table.TableDecoratorHeader;
import ncerovec_zadaca_3.table.TableEquipment;

/**
 * MVC - ConcreteView
 * @author nino
 */
public class EquipmentView extends ConsoleView
{
    List<EquipmentObject> equipmentList = null;

    public EquipmentView(int numRows, int numCols, int numRowsBuffer)
    {
        super(numRows, numCols, numRowsBuffer);
        //this.diverList = diverList;
    }

    public void showEquipmentView(List<EquipmentObject> equipmentList)
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
