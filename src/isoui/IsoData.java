/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package isoui;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jpos.iso.ISODate;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

/**
 *
 * @author arisimam
 */
public class IsoData {
    
    private ISOMsg isomsg;
    
    public IsoData(ISOMsg isom) {
        this.isomsg=isom;
    }

    /**
     * @return the isomsg
     */
    public ISOMsg getIsomsg() {
        return isomsg;
    }

    /**
     * @param isomsg the isomsg to set
     */
    public void setIsomsg(ISOMsg isomsg) {
        this.isomsg = isomsg;
    }
    
    
    public void IsoTemplateEchoASCII()
    {
        try {
            this.isomsg.setMTI("0800");
            this.isomsg.set(7, ISODate.getDateTime(new Date()));
	    this.isomsg.set(11, String.valueOf(System.currentTimeMillis() % 1000000));
            
            this.isomsg.set(33, "881");
            this.isomsg.set(70, "301");
           
        } catch (ISOException ex) {
           
            System.out.println("ISO execption");
        }
    }
    
    public void IsoTemplateEcho()
    {
        try {
            this.isomsg.setMTI("0800");
            this.isomsg.set(3, "990000");
            this.isomsg.set(11,"132");
            this.isomsg.set(24, "003");
            this.isomsg.set(41, "11111111");
            this.isomsg.set(42, "11111111098f8f");
        } catch (ISOException ex) {
           
            System.out.println("ISO execption");
        }
    }
    
    
}
