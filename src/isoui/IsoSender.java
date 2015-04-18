/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isoui;

import static isoui.Utils.isNullorEmptyStringPasser;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.channel.NACChannel;
import org.jpos.iso.packager.GenericPackager;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.iso.packager.ISO87BPackager;

/**
 *
 * @author arisimam
 */
public class IsoSender {
     private  ISOMsg sendmsg;
    private  ISOMsg RecvMsg;
     private String host;
     private int port;
     private int typeChannel ;
     private String TPDU;
     private String pathpackage;
     private String Error;
     
    public IsoSender(ISOMsg send,String host, int port, int channel,String TPDU)
    {
        this.sendmsg=send;
        this.host=host;
        this.port=port;
        this.typeChannel=channel;
        if((TPDU!=null)&&(TPDU.length()>0))
          {
              this.TPDU=TPDU;
          }
          else
          this.TPDU="6001000800";
        
    }
    
    
    public int ActionConneting(ISOChannel channel) 
    {
         int result=0;
         try {
               if (false==channel.isConnected())
                  channel.connect();
                   // channel.send(this.getSendmsg());
                   // Thread.sleep(30);
             
                  //  setRecvMsg(channel.receive());
                 //   System.out.println("DE-> 39 "+ getRecvMsg().getString(39));
               if(channel.isConnected())
                   channel.disconnect();
               
              }/*catch (ISOException e) 
              {
                  Error="ISOException";
                  // System.out.println("ISOException ");
                   result=-2;
               }*//*catch (InterruptedException ioe)
                   
               {  
                    Error="InterruptedException";
                  // System.out.println("InterruptedException ");
                   result=-1;
               }*/catch (IOException er)
               {       setError("IOException");
                 //  System.out.println("IOException ");
                   result =-3;
               }
         
         
          return result;
          
    }
    
    public int ActionSEndrec(ISOChannel channel) 
            
    {   // 
         int result=0;
         try {
               if (false==channel.isConnected())
                  channel.connect();
                    channel.send(this.getSendmsg());
                    //this.getSendmsg().dump(null, host);
                  //  Thread.sleep(30);
                    ISOUtil.sleep(30);
             
                    setRecvMsg(channel.receive());
                   // this.getRecvMsg().dump(null, host);
    
                 //   System.out.println("DE-> 39 "+ getRecvMsg().getString(39));
               if(channel.isConnected())
                   channel.disconnect();
               
              }catch (ISOException e)  {
                   System.out.println("ISOException ");
                   result=1;
               }/*catch (InterruptedException ioe)
                   
               {  System.out.println("InterruptedException ");
                   result=1;
               }*/catch (IOException er)
               {   System.out.println("IOException ");
                   result =1;
               }
         
         
          return result;
          
    }
     public int  sendConnect()
     {
          if (false== isNullorEmptyStringPasser(getPathpackage()))
             { 
                
              try {
                  GenericPackager genpack= new GenericPackager(host);
                  // return ActionSEndrec(channela);
                  
                  switch (this.typeChannel)
                  {
                      case 1:
                          byte TPDUb[] =ISOUtil.str2bcd(this.TPDU, true);
                          ISOChannel  channel=new NACChannel(this.getHost(), this.getPort(), genpack, TPDUb);
                          return ActionConneting(channel);
                      case 2:
                          ISOChannel channela=new ASCIIChannel(this.getHost(), this.getPort(), genpack);
                          return ActionConneting(channela);
                  }
              } catch (ISOException ex) {
                  //Logger.getLogger(IsoSender.class.getName()).log(Level.SEVERE, null, ex);
                  return -9;
              }
                       
             }
          else
              
          {
        //   new ISO87BPackager()
           
                switch (this.typeChannel)
                {
                    case 1:
                        byte TPDUb[] =ISOUtil.str2bcd(this.TPDU, true);
                      ISOChannel  channel=new NACChannel(this.getHost(), this.getPort(), new ISO87BPackager(), TPDUb);
                        return ActionConneting(channel);
                        
                    case 2:
                        
                       ISOChannel channela=new ASCIIChannel(this.getHost(), this.getPort(), new ISO87APackager());
                      return ActionConneting(channela);
                  }
          }
            return 0;
     }
    
    public int  sendReceive()
       {
          if (false== isNullorEmptyStringPasser(getPathpackage()))
             { 
                
              try {
                  GenericPackager genpack= new GenericPackager(host);
                  // return ActionSEndrec(channela);
                  
                  switch (this.typeChannel)
                  {
                      case 1:
                          byte TPDUb[] =ISOUtil.str2bcd(this.TPDU, true);
                          ISOChannel  channel=new NACChannel(this.getHost(), this.getPort(), genpack, TPDUb);
                          return ActionSEndrec(channel);
                      case 2:
                          ISOChannel channela=new ASCIIChannel(this.getHost(), this.getPort(), genpack);
                          return ActionSEndrec(channela);
                  }
              } catch (ISOException ex) {
                  setError("Error ISO");
                  //Logger.getLogger(IsoSender.class.getName()).log(Level.SEVERE, null, ex);
                  return -9;
              }
                       
             }
          else
              
          {
        //   new ISO87BPackager()
           
                switch (this.typeChannel)
                {
                    case 1:
                        byte TPDUb[] =ISOUtil.str2bcd(this.TPDU, true);
                      ISOChannel  channel=new NACChannel(this.getHost(), this.getPort(), new ISO87BPackager(), TPDUb);
                        return ActionSEndrec(channel);
                        
                    case 2:
                        
                       ISOChannel channela=new ASCIIChannel(this.getHost(), this.getPort(), new ISO87APackager());
                      return ActionSEndrec(channela);
                  }
          }
            return 0;
       }
           
    /**
     * @return the TPDU
     */
    public String getTPDU() {
        return TPDU;
    }

    /**
     * @param TPDU the TPDU to set
     */
    public void setTPDU(String TPDU) {
        this.TPDU = TPDU;
    }

    /**
     * @return the typeChannel
     */
    public int getTypeChannel() {
        return typeChannel;
    }

    /**
     * @param typeChannel the typeChannel to set
     */
    public void setTypeChannel(int typeChannel) {
        this.typeChannel = typeChannel;
    }

    /**
     * @return the sendmsg
     */
    public ISOMsg getSendmsg() {
        return sendmsg;
    }

    /**
     * @param sendmsg the sendmsg to set
     */
    public void setSendmsg(ISOMsg sendmsg) {
        this.sendmsg = sendmsg;
    }

    /**
     * @return the RecvMsg
     */
    public ISOMsg getRecvMsg() {
        return RecvMsg;
    }

    /**
     * @param RecvMsg the RecvMsg to set
     */
    public void setRecvMsg(ISOMsg RecvMsg) {
        this.RecvMsg = RecvMsg;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the pathpackage
     */
    public String getPathpackage() {
        return pathpackage;
    }

    /**
     * @param pathpackage the pathpackage to set
     */
    public void setPathpackage(String pathpackage) {
        this.pathpackage = pathpackage;
    }

    /**
     * @return the Error
     */
    public String getError() {
        return Error;
    }

    /**
     * @param Error the Error to set
     */
    public void setError(String Error) {
        this.Error = Error;
    }
            
    
}
