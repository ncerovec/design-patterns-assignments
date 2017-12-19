/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.mvc;

/**
 * MVC - Model
 * @author nino
 */
public abstract class Model
{
    private Controller controller = null;
    public void setController(Controller controller) { this.controller = controller; }
    
    protected void notifyController()
    {
        if(this.controller != null) this.controller.updateView();
    }
}
