/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package similarity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author SONY
 */
public class SuffixTree {
    Node root;
    ActivePoint act_point;
    Queue<suffix> suffixes=new LinkedList<suffix>();
    int remainder=1;
    Set<LeafEdge> leafEdges=new HashSet<LeafEdge>();
    String[] doc; //contains the query file
    Node link=null;
    int Nid=1;
    int Eid=1;
 public   SuffixTree(ArrayList<String> file)
    {
        root=new Node();
        root.label="root";
        act_point=new ActivePoint(root);
        doc=new String[file.size()];
        doc=file.toArray(doc);
    }
    public void insert(String s,int index)
    {
        //change the current index of remaining suffixes
        //System.out.println(s+" being added in the tree------------------------");
        //System.out.println(" index= "+index);
        suffix ob=new suffix(index);
        suffix.setCurrent(index);
        suffixes.add(ob);
        //change the current index of all leafedges
        //updateLeafEdges(index);
            LeafEdge.setCurrent(index);
        //insert the given suffix at the active point
            while(suffixes.isEmpty()==false)
            {
                
        Node activeNode=act_point.getNode();
        Edge activeEdge=act_point.getEdge();
        int act_len=act_point.getActiveLength();
        //System.out.println("Active POINT is:::::::");
        //System.out.println("active node is "+activeNode.label);
       // if(activeEdge==null)
        //System.out.println("active Edge is null ");
        //else
        //System.out.println("active Edge is "+activeEdge.label);    
        //System.out.println("active length is "+act_len);
        //search at the active node for the given word that is to be inserted
        Edge EdgeOfWord=activeNode.search(s,index);
            if(activeEdge==null)
            {
                if(EdgeOfWord==null)
                {
                //insertion at the root.jst directly add the edge with the given string in the tree at the root;
                //this edge will be a leaf edge
                Node dest=new Node(s);
                dest.label="Node"+Nid;
                Nid++;
                LeafEdge ed=new LeafEdge(index,index,activeNode,dest);
                ed.label="Edge"+Eid;
                Eid++;
              //this edge will be added to the set of leafedges
                leafEdges.add(ed);
                //add  this edge to the map of edges in active node
                activeNode.add(s, ed);
                //remove the suffix inserted
                //change the current of suffixes
                //suffixes.remove();
          //      System.out.println(s+" added in tree");
                suffix ob1=suffixes.remove();  
        //        printLeafEdges();
                }
                else
                {
                //given word is present at the active node
                //update the active point
                //active node remains same
                //active edge is changed
                //active length is incremented
                //remainder is incremented
                act_point.setActiveEdge(EdgeOfWord);
                act_point.incActiveLength();
                remainder++;
                checkWhetherActEdgeEndsOnNode();
            //    System.out.println(s+" already presnt in tree");
             //   System.out.println("activeLength is "+act_point.activeLength);
               // printLeafEdges();
                break;
                }
            }
            else
            {
                //check whether active edge contains the word to be inserted
                //retrieve the word present at the active length in the active edge
                int pos=activeEdge.getTrueIndex(act_len);
               // System.out.println("check whether active edge contains the "+ s +" to be inserted");
                //System.out.println("comparing "+s +"and positon "+pos +" in array doc");
                if(s.equalsIgnoreCase(doc[pos]))
                {
                    act_point.incActiveLength();
                  //  System.out.println(" active edge contains  "+ s +" to be inserted");
                    //System.out.println("activeLength is "+act_point.activeLength);
                    remainder++;  
                    checkWhetherActEdgeEndsOnNode();
                    //printLeafEdges();
                    break;
                }
                else
                {
                    //split the activeedge and create one new edge
                    //set the end of active edge
                    //System.out.println("at active node "+activeNode.label);
                    //System.out.println("splitting activeEdge which stats with"+ doc[(act_point.getEdge()).getStartIndex()]);
                    activeEdge.setEnd(index);
                    //System.out.println("set end of activeEdge to "+doc[(act_point.getEdge()).getEndIndex()]);
                    
                    Node n1=new Node();
                    n1.label="Node"+Nid;
                    Nid++;
                    Node n2=new Node();
                    n2.label="Node"+Nid;
                    Nid++;
                    Node source=activeEdge.getDestNode();
                    
                    int start=activeEdge.getStartIndex();
                    int end=activeEdge.getEndIndex();
                    //create 2 new leaf edges
                    LeafEdge e1=new LeafEdge(start+act_len,end,source,n1);
                    e1.label="Edge"+Eid;
                    Eid++;
                    LeafEdge e2=new LeafEdge(end,end,source,n2);
                    e2.label="Edge"+Eid;
                    Eid++;

                    //System.out.println("create one new edge stating with  "+doc[e1.getStartIndex()]+" and ending with "+doc[e1.getEndIndex()]);
                    //System.out.println("create second new edge stating with  "+doc[e2.getStartIndex()]+" and ending with "+doc[e2.getEndIndex()]);
                    //add these leafedges to their respective nodes
                    source.add(doc[start+act_len], e1);
                    source.add(s, e2);
                    //update end of active edge
                    activeEdge.setEnd(start+act_len-1);
                    //System.out.println("set end of activeEdge to "+doc[(act_point.getEdge()).getEndIndex()]);
                    //remove active edge fom the set of leafedges
                    leafEdges.remove(activeEdge);
                    //add above 2 newly created leaf edges into the set of leafedges
                    leafEdges.add(e1);
                    leafEdges.add(e2);
                    
                    remainder--;
                    addLink(source);
                    
                    suffix ob1=suffixes.remove();
                    changeActivePoint();
                    //System.out.println("activeLength is "+act_point.activeLength);
                    //printLeafEdges();
                }
            }
            
            
            
        }
            if(suffixes.isEmpty()==true)
            {
            suffix.setCurrent(-1);
            link=null;
            }
            //printSuffixes();
    }
   
      public  void printSuffixes(int lastIndex)
        {
            updateLeafEdges(lastIndex);
            System.out.println("TOTAL suffixes are"+leafEdges.size());
            root.printSuffixes("", doc);
        }
    
    private void changeActivePoint() {
     Node activeNode=act_point.getNode();
     if(activeNode==root)
     {
         suffix ob=suffixes.peek();
         if(ob==null)
             return;
         String st=doc[ob.start];
         Edge ed=activeNode.search(st, remainder);
         act_point.setActiveEdge(ed);
         act_point.decActiveLength();
     }
     else
     {
         if(activeNode.getSuffixLink()!=null)
         {
          /*   System.out.println("suffix link is not null ");
             System.out.println("previous active node was  "+act_point.node.label);
             System.out.println("previous active edge  was  "+act_point.edge.label);
             System.out.println("active len was "+act_point.activeLength);*/
             int a=(act_point.getEdge()).getStartIndex();
            // System.out.println("starting of previous active edge  was at index "+a+" with value "+doc[a]);
             act_point.setActiveNode((activeNode.getSuffixLink()).getDest());
             activeNode=act_point.getNode();
            // System.out.println("active node is now  "+act_point.node.label);
             String st=doc[a];
            Edge ed=activeNode.search(st, remainder);
            act_point.setActiveEdge(ed);
          /*   System.out.println(" active edge is now "+act_point.edge.label);
             System.out.println("active len is now "+act_point.activeLength);*/
         }
         else
         {
             
             act_point.setActiveNode(root);
             activeNode=act_point.getNode();
             suffix ob=suffixes.peek();
         if(ob==null)
             return;
         String st=doc[ob.start];
         Edge ed=activeNode.search(st, remainder);
         act_point.setActiveEdge(ed);
         //act_point.decActiveLength();
             
         }
     }
    }

    private void addLink(Node newlyCreated) {
        if(link==null)
        {
            link=newlyCreated;
        }
        else
        {
            SuffixLink ob=new SuffixLink(link,newlyCreated);
            link.assignSuffixLink(ob);
            link=newlyCreated;
        }
    }

    private void checkWhetherActEdgeEndsOnNode() {
       Edge activeEdge=act_point.getEdge();
       int activeLength=act_point.getActiveLength();
       if(activeEdge!=null && leafEdges.contains(activeEdge)==false && (activeEdge.getEndIndex()+1)==activeLength)
       {
           //activeEdge ends on a node
           //change activeNode to the dest node pointed by activeEdge
           act_point.setActiveNode(activeEdge.getDestNode());
           //change activeEdge to null
           act_point.setActiveEdge(null);
           //set activeLength to zero
           act_point.setActiveLength(0);
       }
    }

    public void updateLeafEdges(int lastIndex) {
   
        for(LeafEdge ed:leafEdges)
        {
            ed.setEnd(lastIndex);
        }
    }
    void printLeafEdges()
    {
        System.out.println("LEAF EDGES ARE::::::::");
        for(LeafEdge ed:leafEdges)
        {
            System.out.println("label of leaf edge is"+ed.label);
            System.out.println("leaf edge stating with  "+doc[ed.getStartIndex()]+" and ending with "+doc[ed.getEndIndex()]);
            System.out.println("source node is "+(ed.source).label+" and dest node is "+(ed.getDestNode()).label);
        }
    }
  public  StartAndLen search(int index,String[] test)
    {
        StartAndLen obj=new StartAndLen(0);
      return root.search(index,test,doc,0,obj);
    }
}

