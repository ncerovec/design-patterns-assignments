/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.mvc.dive;

import ncerovec_zadaca_4.Diving;
import ncerovec_zadaca_4.mvc.ConsoleController;

/**
 * MVC - ConcreteController
 * @author nino
 */
public class DiveController extends ConsoleController
{
    private DiveModel diveModel = null;
    private DiveView diveView = null;

    public DiveController(DiveModel diveModel, DiveView diveView)
    {
        this.diveModel = diveModel;
        this.diveView = diveView;
    }

    @Override
    public void showMessage(String message) { this.diveView.showMessage(message); }
    
    @Override
    public void enableInput() { this.diveView.requestAction("Unesite akciju (G/D/N/Q): "); }
    
    @Override
    public void actionPerformed(String action)
    {
        boolean repeat = true;
        
        if(action.length() == 1)
        {
            switch(action.charAt(0))
            {
                case 'G':
                    this.diveView.previousConsole();
                break;

                case 'D':
                    this.diveView.nextConsole();
                break;

                case 'N':
                    repeat = false;
                break;

                case 'Q':
                    Diving.quitProgram();
                break;

                default:
                    this.diveView.showMessage("Kriva naredba!");
                break;
            }
        }
        else
        {
            this.diveView.showMessage("Kriva naredba!");
        }
        
        if(repeat) this.enableInput();    //loop action request
    }

    @Override
    public void updateView()
    {
        this.diveView.showDiveView(this.diveModel.getDive());
    }
}
