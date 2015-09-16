/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mod_tree;
import similarity.*;
import java.util.Comparator;

/**
 *
 * @author SONY
 */
public class ComparatorForQueue implements Comparator<StartAndLen>{
    
    public int compare(StartAndLen ob1,StartAndLen ob2)
    {
        if(ob1.getStart()<ob2.getStart())
        {
            return -1;
        }
        else
        {
            if(ob1.getStart()>ob2.getStart())
            {
            return 1;
            }
            else
                return 0;
        }
            
    }
}
