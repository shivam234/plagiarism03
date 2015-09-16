/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package similarity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author SONY
 */
public class LeafEdge extends Edge {

    static int current;
    
    LeafEdge(int a,int b,Node s,Node d)
    {
       super(a,b,s,d);
    }
    
    public static void setCurrent(int curr)
    {
        current=curr;
    }

    
}
class Key
{
    Node node;
    String startWord;
    Key(Node a,String word)
    {
        node=a;
        startWord=word;
    }
}
