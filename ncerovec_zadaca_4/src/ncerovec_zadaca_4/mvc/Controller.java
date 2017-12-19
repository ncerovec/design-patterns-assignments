/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.mvc;

/**
 * MVC - Controller
 * @author nino
 */
public interface Controller
{
    public void updateView();
    public void showMessage(String message);
    public void enableInput();
    public void actionPerformed(String action);
}
