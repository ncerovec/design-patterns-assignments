/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.visitor;

import java.util.List;
import ncerovec_zadaca_3.model.agency.Agency;
import ncerovec_zadaca_3.model.institution.Institution;

/**
 * VISITOR - Visitor
 * @author nino
 */
public interface GroupVisitor
{
    void visit(Agency agency);
    void visit(Institution institution);
    void visit(List<GroupElement> groups);
}
