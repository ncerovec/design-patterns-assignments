/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.table;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_4.model.agency.AgencyBSAC;
import ncerovec_zadaca_4.model.agency.AgencyCMAS;
import ncerovec_zadaca_4.model.agency.AgencyNAUI;
import ncerovec_zadaca_4.model.agency.AgencySSI;
import ncerovec_zadaca_4.model.dive.Dive;
import ncerovec_zadaca_4.model.diver.Diver;

/**
 * DECORATOR - ConcreteComponent
 * @author nino
 */
public class TableDivingStats extends TableBasic
{
    List<Dive> dives = null;
    
    public TableDivingStats(List<Dive> dives, int tableWidth)
    {
        super(tableWidth, (short) 0);
        this.dives = dives;
    }

    @Override
    public String getCaption()
    {
        return "Statistika ronjenja";
    }
    
    @Override
    public String[] getColumns()
    {
        String[] columnHeaders = {"Broj urona", "Prosječna dubina", "HRS-ronioci", "SSI-ronioci", "NAUI-ronioci", "CMAS-ronioci", "BSAC-ronioci"};
        
        return columnHeaders;
    }
    
    @Override
    public List<String[]> getRows()
    {
        List<String[]> rows = new ArrayList<>();
        
        int numDives = this.dives.size();
        int sumDepth = 0;
        List<Diver> diversHRS = new ArrayList<>();
        List<Diver> diversSSI = new ArrayList<>();
        List<Diver> diversNAUI = new ArrayList<>();
        List<Diver> diversCMAS = new ArrayList<>();
        List<Diver> diversBSAC = new ArrayList<>();
        
        for(int i=0; i<numDives; i++)
        {
            Dive dive = this.dives.get(i);
            sumDepth += dive.getMaxDepth();
            
            for(Diver diver : dive.getDivers()) //possible to implement using DiveNotification records in List<DiveNotification> memberDives of club/association/agency
            {
                switch(diver.getAgency().getName())
                {
                    case AgencySSI.name:
                        if(!diversSSI.contains(diver)) diversSSI.add(diver);
                    break;
                    case AgencyCMAS.name:
                        if(!diversCMAS.contains(diver)) diversCMAS.add(diver);
                    break;
                    case AgencyNAUI.name:
                        if(!diversNAUI.contains(diver)) diversNAUI.add(diver);
                    break;
                    case AgencyBSAC.name:
                        if(!diversBSAC.contains(diver)) diversBSAC.add(diver);
                    break;
                }
                
                if(!diversHRS.contains(diver)) diversHRS.add(diver);
            }
        }
        
        float avgDepth = (numDives > 0) ? sumDepth/numDives : 0;
        
        String[] columnValues = {Integer.toString(numDives), Float.toString(avgDepth), Integer.toString(diversHRS.size()), Integer.toString(diversSSI.size()), Integer.toString(diversNAUI.size()), Integer.toString(diversCMAS.size()), Integer.toString(diversBSAC.size())};
        rows.add(columnValues);
        
        return rows;
    }
}