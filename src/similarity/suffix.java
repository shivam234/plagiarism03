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
public class suffix {
    int start;
    static int current;
    suffix(int index)
    {
        start=index;
    }
    public static void setCurrent(int curr)
    {
        current=curr;
    }
    
}
