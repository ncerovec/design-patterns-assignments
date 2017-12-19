/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_3;

/**
 *
 * @author nino
 */
public class StringUtils
{
    public static String padCenter(String s, int size)
    {
        return padCenter(s, size, ' ');
    }

    public static String padCenter(String s, int size, char pad)
    {
        if (s == null || size <= s.length())
        {
            return s;
        }

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++)
        {
            sb.append(pad);
        }
        
        sb.append(s);
        
        while (sb.length() < size)
        {
            sb.append(pad);
        }
        
        return sb.toString();
    }
    
    public static String padRight(String s, int n)
    {
        return String.format("%1$-" + n + "s", s);  
    }

    public static String padLeft(String s, int n)
    {
        return String.format("%1$" + n + "s", s);
    }
    
    public static String addBothBorder(String s, char border)
    {
        StringBuilder sb = new StringBuilder(s);
        sb.insert(0, border);
        sb.append(border);
        
        return sb.toString();
    }
    
    public static String addLeftBorder(String s, char border)
    {
        StringBuilder sb = new StringBuilder(s);
        sb.insert(0, border);
        
        return sb.toString();
    }
    
    public static String addRightBorder(String s, char border)
    {
        StringBuilder sb = new StringBuilder(s);
        sb.append(border);
        
        return sb.toString();
    }
}