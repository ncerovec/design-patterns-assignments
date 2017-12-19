/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.mvc;

import ncerovec_zadaca_3.Diving;

/**
 * MVC - ConcreteController
 * @author nino
 */
public class DiversController extends ConsoleController
{
    DiversModel diversModel = null;
    private DiversView diversView = null;

    public DiversController(DiversModel diversModel, DiversView diversView)
    {
        this.diversModel = diversModel;
        this.diversView = diversView;
    }

    @Override
    public void enableInput() { this.diversView.requestAction("Unesite akciju (V/G/D/P/N/Q/N <Ime>/<Ime>): "); }
    
    @Override
    public void actionPerformed(String action)
    {
        boolean repeat = true;
        
        if(action.length() == 1)
        {
            switch(action.charAt(0))
            {
                case 'V':
                    this.diversModel.getInitialDivers();
                break;

                case 'G':
                    this.diversView.previousConsole();
                break;

                case 'D':
                    this.diversView.nextConsole();
                break;

                case 'P':
                    this.diversModel.getAllDivers();
                break;

                case 'N':
                    repeat = false;
                break;

                case 'Q':
                    Diving.quitProgram();
                break;

                default:
                    this.diversView.showMessage("Kriva naredba!");
                break;
            }
        }
        else
        {
            if(action.length() > 1)
            {
                if(action.startsWith("N ")) this.diversView.showMessage("Nije podržano!");
                else this.diversModel.removeDiver(action);
            }
        }
        
        if(repeat) this.enableInput();    //loop action request
    }

    @Override
    public void updateView()
    {
        this.diversView.showDiversView(this.diversModel.getDiverList());
    }
}
