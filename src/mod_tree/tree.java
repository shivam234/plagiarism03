/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mod_tree;

import java.io.File;

/**
 *
 * @author SONY
 */
public class tree {
    node root;
    node curr_node;
    public tree()
    {
        root=new node("root");
        
    }
    //all edges will be added at the current node
    public node addEdge(File obj,node parent)
    {
        node dest=new node(obj);
        edge ed=new edge(parent,dest);
        parent.addEdgeInMap(ed, obj.getName());
        return dest;
    }
    public void setCurrNode(node n)
    {
        if(n==null)
        curr_node=root;
        else
        {
            
        }
    }
    public node getRootNode()
    {
        return root;
    }
    
    public node FindDirNode(String nameOfDir)
    {
        edge ed=root.findEdge(nameOfDir);
        node selectedDir=ed.getDestNode();
        if(ed==null)
            return null;
        return selectedDir;
    }
    
}
