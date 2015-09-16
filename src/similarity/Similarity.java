/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package similarity;
import java.util.*;

/**
 *
 * @author SONY
 */
public class Similarity {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<String> st=new ArrayList<>();
        st.add("a");
        st.add("b");
        st.add("c");
        st.add("a");
        st.add("b");
        st.add("x");
        st.add("a");
        st.add("b");
        st.add("c");
        st.add("d");
        SuffixTree ob=new SuffixTree(st);
        String[] n=new String[st.size()];
        n=st.toArray(n);
        int i=0;
        for(i=0;i<n.length;i++)
            ob.insert(n[i], i);
        System.out.println("FINALLY SUFFIXES IN TREE ARE");
        int a=n.length-1;
        ob.printSuffixes(a);
        int k=9;
        
    }
    
}

