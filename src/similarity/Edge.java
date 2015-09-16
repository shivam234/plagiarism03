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
public class Edge {
    int start;
    private int end;
    Node source;
    Node dest;
    String label;
   Edge()
   {
      start=-1; 
      end=-1;
      source=null;
      dest=null;
   }
   Edge(int s,int e,Node a,Node b)
   {
      start=s; 
      end=e;
      source=a;
      dest=b; 
   }
   int getTrueIndex(int factor)
   {
       return factor+start;
   }

    Node getDestNode() {
        return dest; //To change body of generated methods, choose Tools | Templates.
    }

    int getStartIndex() {
        return start; //To change body of generated methods, choose Tools | Templates.
    }

    int getEndIndex() {
        return end ; //To change body of generated methods, choose Tools | Templates.
    }

   
    void setEnd(int index) {
        end=index; //To change body of generated methods, choose Tools | Templates.
    }
  }
