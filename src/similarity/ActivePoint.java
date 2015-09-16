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
public class ActivePoint {
    Node node;
    Edge edge;
    int activeLength;
   public  ActivePoint(Node m)
    {
        node=m;
        edge=null;
        activeLength=0;
    }

   public  Node getNode() {
        return node; //To change body of generated methods, choose Tools | Templates.
    }

   public  Edge getEdge() {
        return edge; //To change body of generated methods, choose Tools | Templates.
    }
   public  void changeActivePoint(Edge ed)
    {
        edge=ed;
    }
   public  void incActiveLength()
    {
        activeLength++;
    }
    

   public  int getActiveLength() {
        return activeLength; //To change body of generated methods, choose Tools | Templates.
    }

   public  void decActiveLength() {
        activeLength--; //To change body of generated methods, choose Tools | Templates.
    }

   public void setActiveEdge(Edge ed) {
        edge=ed; //To change body of generated methods, choose Tools | Templates.
    }

   public void setActiveNode(Node destNode) {
    node=destNode;    
    }

  public  void setActiveLength(int i) {
        activeLength=i; //To change body of generated methods, choose Tools | Templates.
    }
    
}
