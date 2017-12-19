/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ncerovec_zadaca_3.AppHelper;
import ncerovec_zadaca_3.model.agency.AgencyBSAC;
import ncerovec_zadaca_3.model.agency.AgencyCMAS;
import ncerovec_zadaca_3.model.agency.AgencyNAUI;
import ncerovec_zadaca_3.model.agency.AgencySSI;
import ncerovec_zadaca_3.model.institution.AssociationHRS;
import ncerovec_zadaca_3.model.dive.Dive;
import ncerovec_zadaca_3.model.dive.combinations.DiveCombination;
import ncerovec_zadaca_3.model.diver.Diver;
import ncerovec_zadaca_3.model.equipment.EquipmentCategory;
import ncerovec_zadaca_3.model.equipment.EquipmentObject;
import ncerovec_zadaca_3.notifications.NotificationObserver;
import ncerovec_zadaca_3.notifications.NotificationSubject;
import ncerovec_zadaca_3.table.Table;
import ncerovec_zadaca_3.table.TableDecoratorFooter;
import ncerovec_zadaca_3.table.TableDecoratorHeader;
import ncerovec_zadaca_3.table.TableDivers;
import ncerovec_zadaca_3.table.TableDives;
import ncerovec_zadaca_3.table.TableDivingStats;

/**
 * SINGLETON - Class, OBSERVER - ConcreteSubject
 * @author nino
 */
public class Club implements NotificationSubject
{
    private static volatile Club INSTANCE = null;
    
    private List<Diver> divers = new ArrayList();
    private List<Dive> dives = new ArrayList();
    private List<EquipmentCategory> equipment =  new ArrayList();
    
    private List<NotificationObserver> observers = new ArrayList();
    private List<DiveNotification> memberDives = new ArrayList(); //used only for notifications
        
    private Club() {}
    
    public static Club getInstance() //lazy loading instantiation
    {
        if(INSTANCE == null)
        {
            synchronized(Club.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = new Club();
                }
            }
        }
        
        return INSTANCE;
    }

    public List<Diver> getDivers() { return divers; }
    public void setDivers(List<Diver> divers) { this.divers = divers; }

    public List<Dive> getDives() { return dives; }
    public void setDives(List<Dive> dives) { this.dives = dives; }

    public List<EquipmentCategory> getEquipment() { return equipment; }
    public void setEquipment(List<EquipmentCategory> equipment) { this.equipment = equipment; }
    
    @Override
    public void addObserver(NotificationObserver observer) { this.observers.add(observer); }

    @Override
    public void removeObserver(NotificationObserver observer) { this.observers.remove(observer); }

    @Override
    public List<DiveNotification> getState()
    {
        return this.memberDives;
    }

    @Override
    public void setState(Dive newDive)
    {
        for(Diver diver : newDive.getDivers())
        {
            DiveNotification newNotification = new DiveNotification(diver, newDive);
            this.memberDives.add(newNotification);
            notifyObservers(newNotification);
        }
    }
    
    private void notifyObservers(DiveNotification newNotification)
    {
        switch(newNotification.getDiver().getAgency().getName())
        {
            case AgencySSI.name:
                if(this.observers.contains(AgencySSI.getInstance())) AgencySSI.getInstance().update(newNotification);
            break;
            case AgencyCMAS.name:
                if(this.observers.contains(AgencyCMAS.getInstance())) AgencyCMAS.getInstance().update(newNotification);
            break;
            case AgencyNAUI.name:
                if(this.observers.contains(AgencyNAUI.getInstance())) AgencyNAUI.getInstance().update(newNotification);
            break;
            case AgencyBSAC.name:
                if(this.observers.contains(AgencyBSAC.getInstance())) AgencyBSAC.getInstance().update(newNotification);
            break;
        }
        
        if(this.observers.contains(AssociationHRS.getInstance())) AssociationHRS.getInstance().update(newNotification);
    }
    
    //Method returns the Dive of the day (Date) with most of the Divers
    public Dive getMaxDiverDayDive(Date date)
    {
        Dive maxDiverDayDive = null;
        
        if(this.getDives() != null)
        {
            for(Dive dive : this.getDives())
            {
                if(AppHelper.checkSameDay(dive.getDateTime(), date))
                {
                    if(maxDiverDayDive == null || maxDiverDayDive.getNumDivers() < dive.getNumDivers())
                    {
                        maxDiverDayDive = dive;
                    }
                }
            }
        }
        
        return maxDiverDayDive;
    }
    
    public List<Dive> getDiverDives(Diver diver)
    {
        List<Dive> diverDives = new ArrayList<>();
        
        for(Dive dive : this.getDives())
        {
            if(dive.getDivers().contains(diver))
            {
                diverDives.add(dive);
            }
        }
        
        return diverDives;
    }
    
    
    //output Club stats
    
    public String printStatsTable()
    {
        TableDivingStats tableStats = new TableDivingStats(this.getDives(), 120);
        Table decoratedStatsTable = new TableDecoratorFooter(new TableDecoratorHeader(tableStats));
        String decoratedTableDraw = decoratedStatsTable.draw();        //get decorated table
        return decoratedTableDraw;
    }
    
    public String printDiversTable() { return printDiversTable(false); }
    public String printDetailDiversTable() { return printDiversTable(true); }
    public String printDiversTable(boolean details)
    {
        TableDivers tableDivers = new TableDivers(this.getDivers(), 150, details);
        Table decoratedDiversTable = new TableDecoratorFooter(new TableDecoratorHeader(tableDivers));
        String decoratedTableDraw = decoratedDiversTable.draw();        //get decorated table
        return decoratedTableDraw;
    }
    
    public String printDivesTable() { return printDivesTable(false); }
    public String printDetailDivesTable() { return printDivesTable(true); }
    public String printDivesTable(boolean details)
    {
        TableDives tableDives = new TableDives(this.getDives(), 100, details);
        Table decoratedDivesTable = new TableDecoratorFooter(new TableDecoratorHeader(tableDives));
        String decoratedTableDraw = decoratedDivesTable.draw();        //get decorated table
        return decoratedTableDraw;
    }
    
    public String printDivers()
    {
        String text = "\n -- Popis ronioca --";
        
        for(Diver diver : this.getDivers())
        {
            text += "\n" + diver;
        }
        
        return text;
    }
    
    public String printDives()
    {
        String text = "\n -- Popis urona --";
        
        for(Dive dive : this.getDives())
        {
            text += "\n" + dive;
        }
        
        return text;
    }
    
    public String printDiverDives(Diver diver)
    {
        String text = "\n --> Popis urona za ronioca: ";
        
        text += diver;
        for(Dive dive : this.getDives())
        {
            DiveCombination diveCmb = dive.getDiverCombination(diver);

            if(diveCmb != null)
            {
                text += "\n" + "URON " + "(" + dive.getDateTime() + "): max " + dive.getMaxDepth() + "m";
                text += "\n" + " -> Dubina ronioca: " + diveCmb.checkDiverDepth(diver) + "m";
                text += "\n" + " -> Kombinacija-partneri: " + diveCmb;                    
            }
        }
        
        return text;
    }
    
    public String printAllDiverDives()
    {
        String text = "\n -- Popis ronioca i urona za pojedinog ronioca --";
        
        for(Diver diver : this.getDivers())
        {
            text += "\n" + printDiverDives(diver);
        }
        
        return text;
    }
}
