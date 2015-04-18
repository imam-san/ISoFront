/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isoui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author arisimam
 */
public class isoViewer {
    private JComboBox jboxfile;
    private JTable jTable1 ;
    
    
    public void getDataxml(String inputfile)
    { 
       
    //  private DefaultComboBoxModel jComboBox1Model = new DefaultComboBoxModel();
     
         isoControllerXml xmlreader=  new isoControllerXml();
          xmlreader.setAbsolutPath(inputfile);
           xmlreader.procees();
             setjTable1(new JTable(xmlreader.getModel().size(),3));
            for (int i=0;i<   xmlreader.getModel().size();i++)
           {
               
                jTable1.setValueAt(xmlreader.getModel().get(i).getId(), i, 1);
                jTable1.setValueAt(xmlreader.getModel().get(i).getName(), i, 2);
               
           }
           
    }
    
    
    public void getDataiso( )
    {   setJboxfile(new JComboBox());
        isoGetFile isogetfile= new isoGetFile();
       isogetfile.setIsimam(Boolean.FALSE);
       isogetfile.proccess();
       
       for (int i=0;i< isogetfile.getResults().size();i++)
            getJboxfile().addItem(isogetfile.getResults().get(i));
       
       //   System.out.println(" "+ i+ ":"+ isogetfile.getResults().get(i));
    }
    public void getData( )
    {   setJboxfile(new JComboBox());
        isoGetFile isogetfile= new isoGetFile();
       isogetfile.setIsimam(Boolean.TRUE);
       isogetfile.proccess();
       
       for (int i=0;i< isogetfile.getResults().size();i++)
            getJboxfile().addItem(isogetfile.getResults().get(i));
       
       //   System.out.println(" "+ i+ ":"+ isogetfile.getResults().get(i));
    }

    /**
     * @return the jboxfile
     */
    public JComboBox getJboxfile() {
        return jboxfile;
    }

    /**
     * @param jboxfile the jboxfile to set
     */
    public void setJboxfile(JComboBox jboxfile) {
        this.jboxfile = jboxfile;
    }

    /**
     * @return the jTable1
     */
    public JTable getjTable1() {
        return jTable1;
    }

    /**
     * @param jTable1 the jTable1 to set
     */
    public void setjTable1(JTable jTable1) {
        this.jTable1 = jTable1;
    }
    
}
