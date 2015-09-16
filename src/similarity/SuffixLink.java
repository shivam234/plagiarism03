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
public class SuffixLink {
    Node source;
    Node dest;
    SuffixLink()
    {
        
    }
    SuffixLink(Node a,Node b)
    {
        source=a;
        dest=b;
    }
    Node getDest()
    {
        return dest;
    }
}
