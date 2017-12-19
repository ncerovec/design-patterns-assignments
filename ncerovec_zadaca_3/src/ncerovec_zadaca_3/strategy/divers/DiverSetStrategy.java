/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.strategy.divers;

import java.util.List;
import ncerovec_zadaca_3.model.dive.Dive;
import ncerovec_zadaca_3.model.diver.Diver;

/**
 * STRATEGY - Strategy
 * @author nino
 */
public interface DiverSetStrategy
{
    public List<Diver> generateDivers(Dive dive);
}
