/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plagiarism03;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.tree.DefaultMutableTreeNode;
import mod_tree.*;
import similarity.StartAndLen;
/**
 *
 * @author SONY
 */
public class initail2 extends javax.swing.JFrame {
    
    tree FilesAndDirectories;
    int MinWords;
    double MinPercentSimilarity;
    File fileToBeCompared;
    Object[][] files=new Object[1000][3];
    String colnames[]={"Name of File","Location","Percent Similarity"};
    DefaultTableModel tableModel;
    node SelectedDirectory;
    TextPane pane1=new TextPane();
    TextPane pane2=new TextPane();
    /**
     * Creates new form initail2
     */
    
    public initail2(tree obj,double MinPercentSimilarity,int MinWords,File fileToBeCompared) {
        initComponents();
        jScrollPane3.setViewportView(pane1);
        jScrollPane4.setViewportView(pane2);
        FilesAndDirectories=obj;
        this.MinPercentSimilarity=MinPercentSimilarity;
        this.MinWords=MinWords;
        this.fileToBeCompared=fileToBeCompared;
        expandTree();
    }
    public initail2()
    {
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Repositories");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButton1.setText("Generate Report");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))))
                .addGap(27, 27, 27)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
        // TODO add your handling code here:
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                       jTree1.getLastSelectedPathComponent();
        if(node.isRoot()==true)
            return;
        else
        {
            String DirectorySelected=node.toString();
            try {
                fillTable(DirectorySelected);
            } catch (IOException ex) {
                Logger.getLogger(initail2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jTree1MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            // TODO add your handling code here:
            int row_select = jTable1.rowAtPoint(evt.getPoint());
            File p1=new File((String)tableModel.getValueAt(row_select, 0));
            edge edgeLeadingToFile=SelectedDirectory.findEdge(p1.getName());
            node NodeContainingFile=edgeLeadingToFile.getDestNode();
            pane1.setText("");
            pane2.setText("");
            writeContentsOfFile(NodeContainingFile,1);
            writeContentsOfFile(NodeContainingFile,0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(initail2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(initail2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(initail2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(initail2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(initail2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new initail2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

    private void expandTree() {
    node root=FilesAndDirectories.getRootNode();
    Map<String,edge> edges=root.getMapOfEdges();
    DefaultMutableTreeNode jTree_root=(DefaultMutableTreeNode)jTree1.getModel().getRoot();
    for (Map.Entry<String, edge> entry : edges.entrySet())
    {
        DefaultMutableTreeNode child=new DefaultMutableTreeNode(entry.getKey());
        jTree_root.add(child);
    }
    
    }

    private void fillTable(String DirectorySelected) throws IOException {
        int row=0;
         SelectedDirectory=FilesAndDirectories.FindDirNode(DirectorySelected);
         System.out.println("SELECTED DIR IS:::::: "+SelectedDirectory.getFile().getName());
        Map<String,edge> MapOfDirNode=SelectedDirectory.getMapOfEdges();
        for (Map.Entry<String, edge> entry_DirNode : MapOfDirNode.entrySet())
            {
                edge EdgeLeadingToFileNode=entry_DirNode.getValue();
                node nodeContainingFile=EdgeLeadingToFileNode.getDestNode();
                if(nodeContainingFile.getPercentSimilarity()>=MinPercentSimilarity)
                {
                    files[row][0]=nodeContainingFile.getFile().getName();
                    files[row][1]=nodeContainingFile.getFile().getCanonicalPath();
                    files[row][2]=nodeContainingFile.getPercentSimilarity();
                    row++;
                } 
            }
        tableModel=new DefaultTableModel(files,colnames);
         jTable1.setModel(tableModel);
         
        
    }

    private void writeContentsOfFile(node NodeContainingFile, int i) throws FileNotFoundException {
        
        File fileToBeWritten;
        ArrayList<StartAndLen> matchingIndexes;
        TextPane pane;
        if(i==0)
        {
             fileToBeWritten=NodeContainingFile.getFile();
             matchingIndexes=new ArrayList(NodeContainingFile.getList(i));
             pane=pane1;
        }
        else
        {
            fileToBeWritten=fileToBeCompared;
            matchingIndexes=new ArrayList(NodeContainingFile.getList(i));
            pane=pane2;
        }
        
        String[] arr_fileToBeWritten=convertFileToArray(fileToBeWritten);
        int k;
        System.out.println("printing file "+fileToBeWritten.getName());
        int list_index=0;
        for(k=0;k<arr_fileToBeWritten.length;)
        {
            StartAndLen head;
            
            if(list_index<matchingIndexes.size())
            head=matchingIndexes.get(list_index);
            else
                head=null;
            if(head!=null)
            {  
            System.out.println("head of queue is "+arr_fileToBeWritten[head.getStart()]);
            System.out.println("index of head of queue is "+head.getStart()
            +" and index of loop variable is "+k);
            }
            else
            System.out.println("head of queue is null "+" and index of loop variable is "+k);
            if(head!=null && head.getStart()==k)
            {
                int j;
                String x="";
                    for(j=k;j<k+head.getLen();j++)
                         x=x+" "+arr_fileToBeWritten[j];
                pane.append(Color.red,x+" ");
                System.out.println(x+" appended in pane in red ");
                k=k+head.getLen();
                list_index++;
                
            }
            else
            {
                pane.append(Color.black,arr_fileToBeWritten[k]+" ");
                System.out.println(arr_fileToBeWritten[k]+" appended in pane in black ");
                k++;
            }
            while(list_index<matchingIndexes.size() && k>matchingIndexes.get(list_index).getStart())
                 list_index++;
        }
    }
    public String[] convertFileToArray(File ob) throws FileNotFoundException
    {
        Scanner s=new Scanner(ob);
        ArrayList<String> list=new ArrayList<String>();
        while(s.hasNext())
        {
            list.add(s.next());
        }
        s.close();
        String[] arr=new String[list.size()];
        arr=list.toArray(arr);
        return arr;
    }
}


class TextPane extends javax.swing.JTextPane
{
    public void append(String s) {
   try {
      Document doc = this.getDocument();
      doc.insertString(doc.getLength(), s, null);
   } catch(BadLocationException exc) {
      exc.printStackTrace();
   }
    }
    public void append(Color c, String s) { // better implementation--uses
                      // StyleContext
    StyleContext sc = StyleContext.getDefaultStyleContext();
    AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
        StyleConstants.Foreground, c);

    int len = getDocument().getLength(); // same value as
                       // getText().length();
    setCaretPosition(len); // place caret at the end (with no selection)
    setCharacterAttributes(aset, false);
    replaceSelection(s); // there is no selection, so inserts at caret
  }
}