/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isoui;

import static isoui.UtilString.b2h;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
/**
 *
 * @author arisimam
 */
public class isoControllerXml {
    private List<String> strfile;
    private List<isoModel> model ;
    private String absolutPath;
    public isoControllerXml()
    {
       this.model=     new ArrayList<>();
                    }
    
    public static void main(String[] args) {
    isoControllerXml xmlreader=  new isoControllerXml();
    
    xmlreader.model.clear();
    xmlreader.procees();
    xmlreader.dumpvalue();
  
  } // end of main
  
    public void dumpvalue()
    {
           for (int i=0;i<   model.size();i++)
           {
              System.out.println("id : " + model.get(i).getId());
              System.out.println("length : " + model.get(i).getLength());
	      System.out.println("name : " + model.get(i).getName());
	      System.out.println("data : " + model.get(i).getData1());
              System.out.println("data : " + model.get(i).getData2());
              
              System.out.println("class : " + model.get(i).getClassdata());
             
           }
        
    }
    
    
    public void procees()
    {
        /*
      isoGetFile isogetfile= new isoGetFile();
      isogetfile.setIsimam(Boolean.TRUE);
      isogetfile.proccess();
      
        setStrfile(isogetfile.getResults());
        */
      //strfile.get(0)
        SAXBuilder builder = new SAXBuilder();
	  File xmlFile = new File(getAbsolutPath());// getStrfile().get(0));
    // System.out.println("path "+ xmlFile);
	  try {
        
		Document document = (Document) builder.build(xmlFile);
		Element rootNode = document.getRootElement();
		List list = rootNode.getChildren("isofield");
             
		for (int i = 0; i < list.size(); i++) {
                   isoModel ismodel= new isoModel();
		   Element node = (Element) list.get(i);
                 // System.out.println("name"+  node.getAttributeValue("name"));
                //  System.out.println("getAttribute "+node.getAttributeValue("Attribute", "length") + node.getAttribute("length"));
		//   System.out.println("id : " + node.getAttributeValue("id"));
		 //  System.out.println("length : " + node.getAttributeValue("length"));
		//   System.out.println("name : " + node.getAttributeValue("name"));
	      //	   System.out.println("data : " + node.getAttributeValue("data"));
              //          System.out.println("class : " + node.getAttributeValue("class"));
                   ismodel.setId(Integer.parseInt( node.getAttributeValue("id")));
                   ismodel.setLength(Integer.parseInt( node.getAttributeValue("length")));
		   ismodel.setName(( node.getAttributeValue("name")));
                   ismodel.setData1(( node.getAttributeValue("data")));
                //  ismodel.setData2( b2h( UtilString.hex2byte(ismodel.getData1())));
                   ismodel.setClassdata(( node.getAttributeValue("class")));
                   
		   model.add(i, ismodel);
                }
 
	  } catch (IOException io) {
		System.out.println(io.getMessage());
	  } catch (JDOMException jdomex) {
		System.out.println(jdomex.getMessage());
	  }
	}
    

    /**
     * @return the model
     */
    public List<isoModel> getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(List<isoModel> model) {
        this.model = model;
    }

    /**
     * @return the absolutPath
     */
    public String getAbsolutPath() {
        return absolutPath;
    }

    /**
     * @param absolutPath the absolutPath to set
     */
    public void setAbsolutPath(String absolutPath) {
        this.absolutPath = absolutPath;
    }

    /**
     * @return the strfile
     */
    public List<String> getStrfile() {
        return strfile;
    }

    /**
     * @param strfile the strfile to set
     */
    public void setStrfile(List<String> strfile) {
        this.strfile = strfile;
    }
    
    
}
