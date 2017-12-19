/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.mvc.equipment;

import ncerovec_zadaca_4.Diving;
import ncerovec_zadaca_4.mvc.ConsoleController;

/**
 * MVC - ConcreteController
 * @author nino
 */
public class EquipmentController extends ConsoleController
{
    private EquipmentModel equipmentModel = null;
    private EquipmentView equipmentView = null;

    public EquipmentController(EquipmentModel equipmentModel, EquipmentView equipmentView)
    {
        this.equipmentModel = equipmentModel;
        this.equipmentView = equipmentView;
    }

    @Override
    public void showMessage(String message) { this.equipmentView.showMessage(message); }
    
    @Override
    public void enableInput() { this.equipmentView.requestAction("Unesite akciju (V/G/D/O/N/Q/<Ime>): "); }
    
    @Override
    public void actionPerformed(String action)
    {
        boolean repeat = true;
        
        if(action.length() == 1)
        {
            switch(action.charAt(0))
            {
                case 'V':
                    this.equipmentView.showMessage("Nije podržano!");
                break;

                case 'G':
                    this.equipmentView.previousConsole();
                break;

                case 'D':
                    this.equipmentView.nextConsole();
                break;

                case 'O':
                    this.equipmentModel.getDiveEquipment();
                break;

                case 'N':
                    repeat = false;
                break;

                case 'Q':
                    Diving.quitProgram();
                break;

                default:
                    this.equipmentView.showMessage("Kriva naredba!");
                break;
            }
        }
        else
        {
            if(action.length() > 1) this.equipmentModel.getDiverEquipment(action);
        }
        
        if(repeat) this.enableInput();    //loop action request
    }

    @Override
    public void updateView()
    {
        this.equipmentView.showEquipmentView(this.equipmentModel.getEquipmentList());
    }
}
