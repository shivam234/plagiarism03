/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mod_tree;

/**
 *
 * @author SONY
 */
public class edge {
    node src;
    node dest;
    String name;
   
    public edge(node a,node b)
    {
        src=a;
        dest=b;
        
    }
    
    public node getDestNode()
    {
        return dest;
    }
}
