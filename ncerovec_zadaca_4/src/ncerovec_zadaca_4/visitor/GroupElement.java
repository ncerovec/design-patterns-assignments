/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.visitor;

/**
 * VISITOR - Element
 * @author nino
 */
public interface GroupElement
{
    void accept(GroupVisitor visitor); // Agency/Association have to provide accept().
}
