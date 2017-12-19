/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.notifications;

import ncerovec_zadaca_4.model.DiveNotification;

/**
 * OBSERVER - Observer
 * @author nino
 */
public interface NotificationObserver
{
    public void update(DiveNotification notification);
}
