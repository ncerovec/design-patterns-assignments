/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.visitor;

import java.util.List;
import ncerovec_zadaca_3.model.Level;
import ncerovec_zadaca_3.model.agency.Agency;
import ncerovec_zadaca_3.model.institution.AssociationHRS;
import ncerovec_zadaca_3.model.institution.Institution;

/**
 * VISITOR - ConcreteVisitor
 * @author nino
 */
public class GroupLevelsPrintVisitor implements GroupVisitor
{
    @Override
    public void visit(Agency agency)
    {
        System.out.println("Razine agencije " + agency.getName() + ": ");
        System.out.println("    Rekreacijske razine: ");
        
        for(Level level : agency.getRecreationalLevels())
        {
            System.out.println("        - " + level.toString());
        }
        
        System.out.println("    Profesionalne razine: ");
        
        for(Level level : agency.getProffesionalLevels())
        {
            System.out.println("        - " + level.toString());
        }
    }

    @Override
    public void visit(Institution institution)
    {
        System.out.println("Razine institucije " + institution.getName() + ": Savez nema definirane razine!");
    }   

    @Override
    public void visit(List<GroupElement> groups)
    {
        System.out.println("-----------> Grupe i razine <-----------");
        
        for(GroupElement diverGroup : groups)
        {
            diverGroup.accept(this);
        }
        
        System.out.println("----------------------------------------");
    }
}
