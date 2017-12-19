/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_2.model.agency;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_2.AppHelper;
import ncerovec_zadaca_2.model.DiveNotification;
import ncerovec_zadaca_2.notifications.NotificationObserver;
import ncerovec_zadaca_2.visitor.GroupElement;
import ncerovec_zadaca_2.visitor.GroupVisitor;

/**
 * OBSERVER - ConcreteObserver, VISITOR - ConcreteElement
 * @author nino
 */
public class AssociationHRS implements NotificationObserver, GroupElement 
{
    private static volatile AssociationHRS INSTANCE = null;
    
    public static final String name = "HRS";
    
    private List<DiveNotification> memberDives = new ArrayList<>(); //used only for notifications
    
    public String getName() { return name; }
    
    private AssociationHRS() { }
    
    public static AssociationHRS getInstance() //lazy loading instantiation
    {
        if(INSTANCE == null)
        {
            synchronized(AssociationHRS.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = new AssociationHRS();
                }
            }
        }
        
        return INSTANCE;
    }
    
    @Override
    public void update(DiveNotification notification)
    {
        System.out.println("    -> HRS primio obavijest o uronu " + AppHelper.formatDateTime(notification.getDive().getDateTime()) + " za ronioca " + notification.getDiver().getName());
        this.memberDives.add(notification);
    }

    @Override
    public void accept(GroupVisitor visitor)
    {
        visitor.visit(this);
    }
}
