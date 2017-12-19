/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.model.institution;

import java.util.ArrayList;
import java.util.List;
import ncerovec_zadaca_4.helper.AppHelper;
import ncerovec_zadaca_4.model.DiveNotification;
import ncerovec_zadaca_4.notifications.NotificationObserver;
import ncerovec_zadaca_4.visitor.GroupElement;
import ncerovec_zadaca_4.visitor.GroupVisitor;

/**
 * OBSERVER - ConcreteObserver
 * VISITOR - ConcreteElement
 * @author nino
 */
public abstract class Institution implements NotificationObserver, GroupElement
{
    private String name = null;
    
    private List<DiveNotification> memberDives = new ArrayList<>();  //used only for notifications
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    //public List<DiveNotification> getMemberDives() { return memberDives; }
    
    @Override
    public void update(DiveNotification notification)
    {
        System.out.println("    -> Institucija " + this.getName() + " primila obavijest o uronu " + AppHelper.formatDateTime(notification.getDive().getDateTime()) + " za ronioca " + notification.getDiver().getName());
        this.memberDives.add(notification);
    }

    @Override
    public void accept(GroupVisitor visitor)
    {
        visitor.visit(this);
    }
    
    @Override
    public String toString()
    {
        return this.getName();
    }
}
