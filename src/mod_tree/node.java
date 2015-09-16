/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mod_tree;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import similarity.*;
/**
 *
 * @author SONY
 */
public class node {
  Map<String,edge> edges;
  String name;
  File file;
  double percentSimilarity;
  Comparator<StartAndLen> ob=new comp();
  ArrayList<StartAndLen> indexes_thisFile=new ArrayList<StartAndLen>();
 ArrayList<StartAndLen> indexes_FileToBeCompared=new ArrayList<StartAndLen>();
  public node(String name)
  {
      this.name=name;
      edges=new HashMap();
  }
  public node(File obj)
  {
      file=obj;
      name=obj.getName();
      edges=new HashMap();
  }
  
  public void addEdgeInMap(edge ed,String st)
  {
      edges.put(st, ed);
  }
  public Map getMapOfEdges()
  {
      return edges;
  }
  public File getFile()
  {
      return file;
  }
  public void AddMatchingIndexes(int index_currFile,StartAndLen obj)
  {
      StartAndLen ob=new StartAndLen(index_currFile,obj.getLen());
      indexes_thisFile.add(ob);
      indexes_FileToBeCompared.add(obj);
      printQueues();
  }
  public void setPercentSimilarity(double percent)
  {
      percentSimilarity=percent;
  }
  public edge findEdge(String nameOfDir)
  {
      if(edges.containsKey(nameOfDir))
      {
          return edges.get(nameOfDir);
      }
      return null;
  }
  public double getPercentSimilarity()
  {
      return percentSimilarity;
  }
  public ArrayList<StartAndLen> getList(int i)
  {
      if(i==0)
          return indexes_thisFile; 
       else
          return indexes_FileToBeCompared;
  }
  public void sortList()
  {
      Comparator<StartAndLen> ob=new comp();
      Collections.sort(indexes_thisFile, ob);
      Collections.sort(indexes_FileToBeCompared, ob);
  }

    public void printQueues() {
    Iterator<StartAndLen> it1=indexes_thisFile.iterator();
    Iterator<StartAndLen> it2=indexes_FileToBeCompared.iterator();
    System.out.println("printing queue of file "+file.getName());
    while(it1.hasNext())
    {
        StartAndLen ob=it1.next();
        System.out.println(ob.getStart()+"  "+ob.getLen());
    }
    System.out.println("printing queue of queury file ");
    while(it2.hasNext())
    {
        StartAndLen ob=it2.next();
        System.out.println(ob.getStart()+"  "+ob.getLen());
    }
    }
}

class comp implements Comparator<StartAndLen>
{
    public int compare(StartAndLen ob1,StartAndLen ob2)
    {
        if(ob1.getStart()<ob2.getStart())
        {return -1;}
        else{if(ob1.getStart()>ob2.getStart())
            {return 1;}
            else
                return 0;}
    }
}

 