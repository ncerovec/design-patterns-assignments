/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_2.visitor;

import java.util.List;
import ncerovec_zadaca_2.model.agency.Agency;
import ncerovec_zadaca_2.model.agency.AssociationHRS;

/**
 * VISITOR - Visitor
 * @author nino
 */
public interface GroupVisitor
{
    void visit(Agency agency);
    void visit(AssociationHRS hrs);
    void visit(List<GroupElement> groups);
}
