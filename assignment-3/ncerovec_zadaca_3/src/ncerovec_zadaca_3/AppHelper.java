/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ncerovec_zadaca_3.model.dive.DiveBuilder;

/**
 *
 * @author nino
 */
public class AppHelper
{
    public static int findMin(int... vals)
    {
        int min = Integer.MAX_VALUE;

        for(int i : vals)
        {
           if(i < min) min = i;
        }

        return min;
    }
    
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
    
    public static boolean checkStringsUnique(String[] strings)
    {
        for(int i=0; i < strings.length; i++)
        {
            String curString = strings[i];
            
            for(int j=(i+1); j < strings.length; j++)
            {
                if(curString.equals(strings[j])) return false;
            }
        }
        
        return true;
    }
    
    public static String formatDateTime(Date datetime)
    {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        
        return df.format(datetime);
    }
    
    public static Date parseDateTime(String date, String time)
    {
        Date result = null;
        
        String datetime = date + "/" + time;
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd/HH:mm");
        
        try { result = df.parse(datetime); }
        catch (ParseException ex) { Logger.getLogger(DiveBuilder.class.getName()).log(Level.SEVERE, null, ex); }
        
        return result;
    }
    
    public static class LimitedQueue<E> extends LinkedList<E>   //full implemented (all adding methods)
    {
        private int limit;

        public LimitedQueue(int limit) { this.limit = limit; }

        @Override
        public boolean add(E e)
        {
            boolean added = super.add(e);
            while (added && size() > limit) { super.remove(); }
            return added;
        }

        @Override
        public void addLast(E e)
        {
            super.addLast(e);
            while (size() > limit) { super.removeFirst(); }
        }

        @Override
        public void addFirst(E e)
        {
            super.addFirst(e);
            while (size() > limit) { super.removeLast(); }
        }

        @Override
        public boolean addAll(Collection<? extends E> c)
        {
            boolean added = super.addAll(c);
            while (added && size() > limit) { super.remove(); }
            return added;
        }

        @Override
        public void add(int i, E e)
        {
            super.add(i, e);
            while (size() > limit) { super.remove(++i); }
        }
        
        @Override
        public boolean addAll(int i, Collection<? extends E> c)
        {
            boolean added = super.addAll(i, c);
            while (added && size() > limit) { super.remove(c.size()+(++i)); }
            return added;
        }
    }
}
