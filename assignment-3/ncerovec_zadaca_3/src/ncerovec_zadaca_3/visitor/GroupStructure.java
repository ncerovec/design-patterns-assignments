/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.visitor;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_3.model.agency.AgencyBSAC;
import ncerovec_zadaca_3.model.agency.AgencyCMAS;
import ncerovec_zadaca_3.model.agency.AgencyNAUI;
import ncerovec_zadaca_3.model.agency.AgencySSI;
import ncerovec_zadaca_3.model.institution.AssociationHRS;

/**
 * VISITOR - ObjectStructure
 * @author nino
 */
public class GroupStructure
{
    private static List<GroupElement> diverGroups = new ArrayList<>();
    
    static
    {
        diverGroups.add(AgencySSI.getInstance());
        diverGroups.add(AgencyBSAC.getInstance());
        diverGroups.add(AgencyCMAS.getInstance());
        diverGroups.add(AgencyNAUI.getInstance());
        diverGroups.add(AssociationHRS.getInstance());
    }

    public static List<GroupElement> getDiverGroups() { return diverGroups; }
}
