/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_1.model.diver;

/**
 * FACTORY METHOD - Creator
 * @author nino
 */
public abstract class DiverFactory
{
    public DiverFactory() { }
    public abstract Diver createDiver(String name, String agency, String lvl, int birthYear);
}
