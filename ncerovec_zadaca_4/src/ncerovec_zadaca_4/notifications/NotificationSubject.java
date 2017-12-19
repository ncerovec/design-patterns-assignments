/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.notifications;

import java.util.List;
import ncerovec_zadaca_4.model.DiveNotification;
import ncerovec_zadaca_4.model.dive.Dive;

/**
 * OBSERVER - Subject
 * @author nino
 */
public interface NotificationSubject
{
    public void addObserver(NotificationObserver observer);
    public void removeObserver(NotificationObserver observer);
    public List<DiveNotification> getState();
    public void setState(Dive state);
}
