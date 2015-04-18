/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isoui;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arisimam
 */
public class isoGetFile {
    
    String pathdirectory;
    private List<String> results;
    private Boolean isimam;
    
    
    public isoGetFile()
    {
        
    }
    public void dump()

    {
        for (int i=0;i<   getResults().size();i++)
          System.out.println(" "+ i+ ":"+ getResults().get(i));
    }
    
    public void proccess()
    {
         getPath();
         getListFile();
      //   dump();
    }
    
    public void getPath()
    {
        String addpath=System.getProperty("file.separator")+"cfgiso";
        pathdirectory=    Paths.get(".").toAbsolutePath().normalize().toString()+addpath;
    }
    
    public void getListFile()
    {
        results = new ArrayList<String>();
      File f = new File(pathdirectory);
    
      if (f.exists() && f.isDirectory()) {
         File[] files = new File(pathdirectory).listFiles();
         for (File file : files) 
         {
            if (file.isFile()) {
                String minefile=file.getName();
                if (getIsimam())
                {
                    if ( file.getName().startsWith("imam") && file.getName().endsWith(".xml"))
                        getResults().add(pathdirectory+System.getProperty("file.separator")+minefile);
                     
                }
                else
                {
                     if ((! file.getName().startsWith("imam")) && file.getName().endsWith(".xml"))
                 
                    
                        getResults().add(pathdirectory+System.getProperty("file.separator")+minefile);
                }
                
            }
         }
       }
     
       
    }
    
    
     public static void main(String[] args) {
       
       isoGetFile isogetfile= new isoGetFile();
       isogetfile.setIsimam(Boolean.TRUE);
       isogetfile.proccess();
    
    }

    /**
     * @return the isimam
     */
    public Boolean getIsimam() {
        return isimam;
    }

    /**
     * @param isimam the isimam to set
     */
    public void setIsimam(Boolean isimam) {
        this.isimam = isimam;
    }

    /**
     * @return the results
     */
    public List<String> getResults() {
        return results;
    }
    
}
