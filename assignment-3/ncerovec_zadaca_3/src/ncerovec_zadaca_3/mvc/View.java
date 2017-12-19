/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.mvc;

/**
 * MVC - View
 * @author nino
 */
public interface View
{
    public void showView();
    public void requestAction(String message);
    public void showMessage(String message);
}
