/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_1;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author nino
 */
public class AppHelper
{
    public static int findMax(int... vals)
    {
        int max = Integer.MIN_VALUE;

        for(int i : vals)
        {
           if(i > max) max = i;
        }

        return max;
    }
    
    public static boolean checkSameDay(Date date1, Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                          cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        
        return sameDay;
    }
}
