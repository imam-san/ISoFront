/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isoui;

import org.jpos.iso.ISOMsg;

/**
 *
 * @author arisimam
 */
public class isoTest {
    
    String hoststr="my.flashiz.co.id";//203.176.181.108";
    int port=3000;
    IsoSender isoSender;
    ISOMsg isomsg;
    IsoData isoData;
    
    public void echoAscii()
    {
         int result;
        isomsg= new ISOMsg();
        isoData = new IsoData(isomsg);
        isoData.IsoTemplateEchoASCII();
         isomsg= isoData.getIsomsg();
         isoSender = new IsoSender(isomsg,hoststr,  port, 2, "");
        result= isoSender.sendReceive();
        // isoSender.getRecvMsg();
         System.out.println("Result "+ result); 
      //   System.out.println("DE 39" + isoSender.getRecvMsg().getString(39));
    }
    
    public void echoTest()
    { 
        int result;
        isomsg= new ISOMsg();
        isoData = new IsoData(isomsg);
        isoData.IsoTemplateEcho();
         isomsg= isoData.getIsomsg();
         isoSender = new IsoSender(isomsg,hoststr,  port, 1, "");
        result= isoSender.sendReceive();
        // isoSender.getRecvMsg();
         System.out.println("Result "+ result); 
      //   System.out.println("DE 39" + isoSender.getRecvMsg().getString(39));
         
    }
    
    
}
