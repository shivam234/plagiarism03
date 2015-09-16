/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plagiarism03;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import similarity.*;
import mod_tree.*;

/**
 *
 * @author SONY
 */
public class computingSimilarity {
    tree FilesAndDirectories;
    double MinPercentSimilarity;
    int MinWords;
    File fileToBeCompared;
    ArrayList<String> Arrlist_fileToBeCompared=new ArrayList<String>();
    String[] Arr_fileToBeCompared;
    SuffixTree SuffixTree_fileToBeCompared;
    
    public computingSimilarity(tree obj,double minPercent,int min_words,File file)
    {
        FilesAndDirectories=obj;
        MinPercentSimilarity=minPercent;
        MinWords=min_words;        
        fileToBeCompared=file;
    }
    
    public void CreateSuffixTree() throws FileNotFoundException
    {
        Arrlist_fileToBeCompared=convertFileToArrayList(fileToBeCompared);
        //creating array
        
        Arr_fileToBeCompared=convertArrayListToArray(Arrlist_fileToBeCompared);
        //creating suffixtree
        SuffixTree_fileToBeCompared=new SuffixTree(Arrlist_fileToBeCompared);
        InsertStringsIntoSuffixTree();
    }
    
    public void InsertStringsIntoSuffixTree()
    {
        int i;
        for(i=0;i<Arr_fileToBeCompared.length;i++)
        {
            SuffixTree_fileToBeCompared.insert(Arr_fileToBeCompared[i], i);
        }
        SuffixTree_fileToBeCompared.updateLeafEdges(i-1);
        SuffixTree_fileToBeCompared.printSuffixes(i-1);
    }
    
    public ArrayList<String> convertFileToArrayList(File obj) throws FileNotFoundException
    {
        Scanner s=new Scanner(obj);
        ArrayList<String> list=new ArrayList<String>();
        while(s.hasNext())
        {
            list.add(s.next());
        }
        s.close();
        return list;
    }
    public String[] convertArrayListToArray(ArrayList<String> list)
    {
        String[] arr=new String[list.size()];
        arr=list.toArray(arr);
        return arr;
    }
    
    public void iterateOverTreeContainingFileAndDir() throws FileNotFoundException
    {
        node root=FilesAndDirectories.getRootNode();
        Map<String,edge> MapOfRootNode=root.getMapOfEdges();
        
        for (Map.Entry<String, edge> entry : MapOfRootNode.entrySet())
        {
            edge EdgeInConsideration=entry.getValue();
            node destNode_EdgeInConsideration=EdgeInConsideration.getDestNode();
            //we will iterate over this dest node
            Map<String,edge> MapOfDirNode=destNode_EdgeInConsideration.getMapOfEdges();
           //we will iterate over this map
            for (Map.Entry<String, edge> entry_DirNode : MapOfDirNode.entrySet())
            {
                edge EdgeLeadingToFileNode=entry_DirNode.getValue();
                node nodeContainingFile=EdgeLeadingToFileNode.getDestNode();
                calculateSimilarity(nodeContainingFile);
            }
        }

    }

      {
        
        File fileInsideDirectory=nodeContainingFile.getFile();
        ArrayList<String> arrlist_FileInsideDirectory=convertFileToArrayList(fileInsideDirectory);
        String[] arr_FileInsideDirectory=convertArrayListToArray(arrlist_FileInsideDirectory);
       int i, no_matchingWords=0;
       System.out.println("---Calculating Similarty between--- "+
               fileInsideDirectory.getName()+" and "+fileToBeCompared.getName());
       for(i=0;i<arr_FileInsideDirectory.length;)
       {
           StartAndLen ob=SuffixTree_fileToBeCompared.search(i,arr_FileInsideDirectory );
           int len_matchingSentence=ob.getLen();
           if(len_matchingSentence>MinWords)
           {
               System.out.println("Matching sentence is from "+
                       arr_FileInsideDirectory[i]+" to "+arr_FileInsideDirectory[len_matchingSentence-1]);
               nodeContainingFile.AddMatchingIndexes(i, ob);
               no_matchingWords+=len_matchingSentence;
               i=i+len_matchingSentence;
               
           }
           else
               i=i+1;
       }
       nodeContainingFile.sortList();
       System.out.println("AFTER SORTING ");
       nodeContainingFile.printQueues();
       double percentSimilarity=(double)no_matchingWords*100/Arr_fileToBeCompared.length;
       nodeContainingFile.setPercentSimilarity(percentSimilarity);
       if(percentSimilarity>MinPercentSimilarity)
       {
       System.out.println("file name is "+nodeContainingFile.getFile().getName());
       System.out.println("percentSimilarity is "+percentSimilarity);
       System.out.println("no of matching words is "+no_matchingWords+" and length of dir file is "+arr_FileInsideDirectory.length);
       System.out.println("no of words in query file are "+Arr_fileToBeCompared.length);
       }
       }
}
