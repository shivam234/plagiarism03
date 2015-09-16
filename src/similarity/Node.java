/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package similarity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//import plagiarism02.Edge;

/**
 *
 * @author SONY
 */
public class Node {
   
    Map<String,Edge> MapOfEdges;
    SuffixLink ln=null;
   String label;
    Node()
    {
        
        MapOfEdges=new HashMap();
    }
    Node(String v)
    {
        label=v;
        MapOfEdges=new HashMap();
    }

    Edge search(String s, int index) {
        if(MapOfEdges.containsKey(s)==true)
        {
            return MapOfEdges.get(s);
        }
            return null;
    }
    void add(String s, Edge e)
    {
       MapOfEdges.put(s, e);
    }
    void assignSuffixLink(SuffixLink ob)
    {
        ln=ob;
    }
    SuffixLink getSuffixLink()
    {
        return ln;
    }
    void  printSuffixes(String st,String[] doc)
   {
       Set<Map.Entry<String,Edge>> set=MapOfEdges.entrySet();
       for(Map.Entry<String,Edge> me:set)
       {
           
           Edge ed=me.getValue();
           String x=st;
           int start=ed.getStartIndex();
           int end=ed.getEndIndex();
           while(start<=end)
           {
               x=x+" "+doc[start];
               start++;
           }
           if((ed.getDestNode()).noOfEdges()>0)
           {
               (ed.getDestNode()).printSuffixes(x, doc);
           }
           else
               System.out.println("---SUFFIX-------"+x+"---SUFFIX-------");
       }
   }

     int noOfEdges() {
     return MapOfEdges.size();
     }

   StartAndLen  search(int index, String[] test,String[] doc,int len,StartAndLen obj) {
    
        if(index<test.length && MapOfEdges.containsKey(test[index]))
        {
            Edge ed=MapOfEdges.get(test[index]);
         //   System.out.println("Matching edge found "+ed.label+" for word "+test[index]);
            int start=ed.getStartIndex();
            int end=ed.getEndIndex();
            int no=end-start+1;
            int flag=0;
            if(len==0)
            obj.setStart(start);
        //    System.out.println("found edge has start "+start+" and has end "+end+" no of wods are "+no);
            for(int j=index,k=start;j<test.length && j<index+no && k<doc.length;j++,k++)
            {
          //      System.out.println("comparing index "+j+" of text file "+" and "+" index "+k+" of query file");
                if(test[j].equalsIgnoreCase(doc[k]))
                {
          //          System.out.println(test[j]+" matches with "+doc[k]);
                    obj.incLen(1);
                    len++;
                }
                else
                {
                    flag=1;
                    break;
                }
            }
            if(flag==1)
            {
                //complete edge is not matching
         //       System.out.println("complete edge is not matching....len is "+len);
                return obj;
            }
            else
            {
                Node dest=ed.getDestNode();
       //         System.out.println("going to node "+dest.label);
                return dest.search(index+len, test, doc, len,obj);
            }
            
          
        }
        else
            return obj;
    }
}
