/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package similarity;

/**
 *
 * @author SONY
 */
public class StartAndLen {
    int start;
    int len;
    
    public StartAndLen(int st)
    {
        start=st;
        len=0;
    }
    public StartAndLen(int st,int length)
    {
        start=st;
        len=length;
    }
    public void incLen(int fac)
    {
        len=len+fac;
        
    }
    public int getStart()
    {
        return start;
    }
    public int getLen()
    {
        return len;
    }
    public void setStart(int st)
    {
         start=st;
    }
}
