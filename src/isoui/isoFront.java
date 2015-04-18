/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isoui;

import static isoui.UtilString.h2b;
import static isoui.Utils.isNullorEmptyStringPasser;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;

/**
 *
 * @author arisimam
 */
public class isoFront  extends JFrame {
  // start attributes
   private  JTextArea JTextlog= new JTextArea();
  private JTextField jTxtHost= new JTextField();
  private JLabel jLabel1 = new JLabel();
  private JTextField jTxtPort = new JTextField();
  private JLabel jLabel2 = new JLabel();
  private JButton jBtConnecting = new JButton();
    private JButton jBtRefresh = new JButton();
  private JComboBox jCboxIsoType = new JComboBox();
  private JComboBox jCboxFilename = new JComboBox();
   private JComboBox jCboxChannel = new JComboBox();
    private DefaultComboBoxModel jComboBox1Model = new DefaultComboBoxModel();
    private DefaultComboBoxModel jComboBox2Model = new DefaultComboBoxModel();
  private DefaultComboBoxModel jComboBox1Mode3 = new DefaultComboBoxModel();
    
    private JLabel jLabel3 = new JLabel();
   private JLabel jLabel4 = new JLabel();
  private JButton jBtSend = new JButton();
  private JTable jTable1 = new JTable(130, 3);
    private DefaultTableModel jTable1Model = (DefaultTableModel) jTable1.getModel();
    private JScrollPane jTable1ScrollPane = new JScrollPane(jTable1);
  private  JScrollPane scrollPane = new JScrollPane(JTextlog); 
    // utility
    private JLabel jLblAscii = new JLabel();
    private JLabel jLblBcd = new JLabel();
    private JButton jBtnConvertAscii = new JButton();
    private JButton jBtnConvertBCD = new JButton();
    private JTextField jTxtAscii = new JTextField();
    private JTextField jTxtBcd = new JTextField();
    
    
    
  // end attributes
  
     IsoSender isoSender;
         ISOMsg isomsg;
        IsoData isoData;
    
    public void showMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message ,"Message", JOptionPane.PLAIN_MESSAGE);
    }
    public void switchbt(boolean bolbt)
    {
         if (bolbt)
         {jBtConnecting.setVisible(true);
          jBtSend.setVisible(false);
         }
         else
         {jBtConnecting.setVisible(!true);
          jBtSend.setVisible(!false);
         }
    }
    public void recrerateTable()
    {
         JTable jTable1temp =jTable1;
          jTable1temp.removeAll();
    
         
           for (int j=0;j<=128;j++ )
        {
            try {
                String deFields= String.format("%s", jTable1.getModel().getValueAt(j, 0));//.get .getValueAt(j, 0));// .setValueAt(xmlreader.getModel().get(i).getId(), i, 0);
                String deData=String.format("%s",jTable1.getModel().getValueAt(j, 1));//.get .getValueAt(j, 0));// .setValueAt(xmlreader.getModel().get(i).getId(), i, 0);
                
                if (!isNullorEmptyStringPasser(deFields) && (j!=1))
                        {
                
                           
                            int deid=Integer.valueOf(deFields);
                            if (!isNullorEmptyStringPasser(deData) && (deid==0))
                            {   isomsg.set(deid,deData);
                           //  System.out.println("i"+deFields +":"+deData);
                            }
                            
                            if (!isNullorEmptyStringPasser(deData) && (deid>1))
                            {
                                
                              
                                    
                                    isomsg.set(deid, settingdata(deData));
                                 //    System.out.println("len i"+deFields +":"+deData.length()+""+deData);
                                     
                            }
                        }
            }
            // jTable1.getColumnModel().getColumn(0).setHeaderValue("DE");
            // jTable1.getRowCount();
            catch (ISOException ex) {
                isoSender.setError("ErrorConvert");
               // result=-89;
            }

    }
    }
    public void getSendRecv()
    {
           JTextlog.removeAll();
           //PrintStream ps = new PrintStream(System.out);
           PrintStream ps = new PrintStream(new CustomOutputStream(JTextlog));
           System.setOut(ps);
           if ( isoSender.sendReceive()<0)
           {
               showMessage(isoSender.getError());
           }
           else
           {
               int row=0;
               //   isoSender.getRecvMsg().hasField(WIDTH)
               for (int i=0;i< 128;i++)
               {
                   if ( isoSender.getRecvMsg().hasField(i))
                   {  jTable1.getModel().setValueAt( isoSender.getRecvMsg().getString(i), i, 2);
                  // System.out.println("i"+row +":"+isoSender.getRecvMsg().getString(i));
                   }
                   
               }
               showMessage(isoSender.getRecvMsg().getString(39));
               
           }
           // JTextlog.setWrapStyleWord(true);
           //JTextlog.setText(null);
         /* byte[]send= isoSender.getSendmsg().pack();
          byte[]receive= isoSender.getRecvMsg().pack();
          ps.write("->>".getBytes(), 0, 3);
          ps.write(send, 0, send.length);
          ps.write("<<-".getBytes(), 0, 3);
          ps.write(receive, 0, receive.length);
          */
           isoSender.getSendmsg().dump(ps, "request");
           isoSender.getRecvMsg().dump(ps, "Respond");
           
           //  ps.printf(Locale.ENGLISH, "This is a %s application", s);
           ps.flush();
           switchbt(true);
       
    }
    
    
    public byte[] settingdata(String data)
    {
        if (   0==  jCboxChannel.getSelectedIndex())// binary
        {  // string data input must be BCD format
          return   h2b(data);
             
        }
       
            return data.getBytes();
       
       
    }
    public int  connvertTableToIso()
    {
       int result =0;
        int rows =jTable1.getRowCount();
        
        for (int j=0;j<=128;j++ )
        {
            try {
                String deFields= String.format("%s", jTable1.getModel().getValueAt(j, 0));//.get .getValueAt(j, 0));// .setValueAt(xmlreader.getModel().get(i).getId(), i, 0);
                String deData=String.format("%s",jTable1.getModel().getValueAt(j, 1));//.get .getValueAt(j, 0));// .setValueAt(xmlreader.getModel().get(i).getId(), i, 0);
                
                if (!isNullorEmptyStringPasser(deFields) && (j!=1))
                        {
                
                           
                            int deid=Integer.valueOf(deFields);
                            if (!isNullorEmptyStringPasser(deData) && (deid==0))
                            {   isomsg.set(deid,deData);
                            // System.out.println("i"+deFields +":"+deData);
                            }
                            
                            if (!isNullorEmptyStringPasser(deData) && (deid>1))
                            {
                                
                              
                                    
                                    isomsg.set(deid, settingdata(deData));
                                 //    System.out.println("len i"+deFields +":"+deData.length()+""+deData);
                                     
                            }
                        }
            }
            // jTable1.getColumnModel().getColumn(0).setHeaderValue("DE");
            // jTable1.getRowCount();
            catch (ISOException ex) {
                isoSender.setError("ErrorConvert");
                result=-89;
            }

          
          
          
        }
        
     return result;   
    }
            
    
    public void getConnect()
    {
        int result;
        isomsg= new ISOMsg();
         isoSender = new IsoSender(isomsg,jTxtHost.getText(), Integer.valueOf( jTxtPort.getText()), 1, "");
        if ( isoSender.sendConnect()<0)
        {  switchbt(true);
          showMessage(isoSender.getError());
        }
        else
        {
           
           if ( connvertTableToIso()<0)
           { showMessage(isoSender.getError());
                switchbt(true);
                 return ;
           }
             showMessage("Connected");
             switchbt(false); 
        }
        // result= isoSender.sendReceive();
        
        
      
        
    }
    
  public isoFront(String title) { 
    // Frame-Init
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 1000;//590; 
    int frameHeight =700;// 592;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // start components
    
    jTxtHost.setBounds(96, 24, 150, 20);
    jTxtHost.setText("my.flashiz.co.id");
    cp.add(jTxtHost);
    jLabel1.setBounds(16, 24, 54, 20);
    jLabel1.setText("IP Host");
    cp.add(jLabel1);
    jTxtPort.setBounds(96, 48, 75, 20);
    jTxtPort.setText("9013");
    cp.add(jTxtPort);
    jLabel2.setBounds(16, 48, 75, 20);
    jLabel2.setText("Port");
    cp.add(jLabel2);
    
   
    jBtRefresh.setBounds(288, 72, 75, 45);
    jBtRefresh.setText("Refresh");
    jBtRefresh.setMargin(new Insets(2, 2, 2, 2));
    jBtRefresh.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
           switchbt(true);
           
          isoViewer isoviewer= new isoViewer();
           jCboxIsoType.removeAllItems();
          isoviewer.getDataiso();
          
           for (int i=0;i<isoviewer.getJboxfile().getItemCount(); i++)
              jCboxIsoType.addItem(isoviewer.getJboxfile().getItemAt(i));
          
         /// file name  
        jCboxFilename.removeAllItems();
           isoviewer= new isoViewer();
          isoviewer.getData();
          for (int i=0;i<isoviewer.getJboxfile().getItemCount(); i++)
              jCboxFilename.addItem(isoviewer.getJboxfile().getItemAt(i));
          
      }
    });
    cp.add(jBtRefresh);
    jBtConnecting.setBounds(376, 72, 75, 45);
    //jButton1.setBounds(288, 72, 75, 45);
    jBtConnecting.setText("Connect");
    jBtConnecting.setMargin(new Insets(2, 2, 2, 2));
    jBtConnecting.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton1_ActionPerformed(evt);
       //   jBtSend.setVisible(true);
          switchbt(false);
          getConnect();
      }
    });
    cp.add(jBtConnecting);
    jCboxIsoType.setModel(jComboBox2Model);
    jCboxIsoType.setBounds(96, 72, 150, 20);
  // jCboxIsoType.addItem("iso87Binary");
   //jCboxIsoType.addItem("iso87Ascii");
   
    cp.add(jCboxIsoType);
    jCboxFilename.setModel(jComboBox1Model);
    jCboxFilename.setBounds(96, 95, 150, 20);
    jCboxFilename.addItemListener(new ItemChangeListener());
    cp.add(jCboxFilename);
   
     jCboxChannel.setModel(jComboBox1Mode3);
    // jTxtHost.setBounds(96, 24, 150, 20);
    jCboxChannel.setBounds(250, 24, 150, 20);
   // jCboxChannel.addItemListener(new ItemChangeListener());
    jCboxChannel.addItem("Binary");
    jCboxChannel.addItem("asci");
    
    cp.add(jCboxChannel);
   
    
    jLabel3.setBounds(16, 72, 70, 20);
    jLabel3.setText("Type ISO");
    cp.add(jLabel3);
    jLabel4.setBounds(16, 95, 70, 20);
    jLabel4.setText("FileName");
    cp.add(jLabel4);
    jBtSend.setBounds(470, 72, 75, 45);
    jBtSend.setText("Send");
    jBtSend.setMargin(new Insets(2, 2, 2, 2));
    jBtSend.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton2_ActionPerformed(evt);
          
         switchbt(true);
         getSendRecv();
            
                 
      }
    });
    cp.add(jBtSend);
    jTable1ScrollPane.setBounds(8, 120, 570, 450);
    jTable1.getColumnModel().getColumn(0).setPreferredWidth(7);
    jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
    jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);

    jTable1.getColumnModel().getColumn(0).setHeaderValue("DE");
    jTable1.getColumnModel().getColumn(1).setHeaderValue("Request");
    jTable1.getColumnModel().getColumn(2).setHeaderValue("Respond");
    cp.add(jTable1ScrollPane);
   
    scrollPane.setBounds(590, 120, 400, 450);
     cp.add(scrollPane);
            
    
    /*utility */
  //   jTable1ScrollPane.setBounds(8, 120, 540, 450);
    jLblAscii.setBounds(10, 440, 570, 310);
    jLblAscii.setText("Ascii");
    cp.add(jLblAscii);   
    jLblBcd.setBounds(10, 470, 570, 320);
    jLblBcd.setText("Bcd");
    cp.add(jLblBcd);   
   //  jButton2.setBounds(376, 72, 75, 45);
    jBtnConvertAscii.setBounds(500, 580, 80, 30);
    jBtnConvertAscii.setText("Convert");
    jBtnConvertAscii.setMargin(new Insets(2, 2, 2, 2));
    jBtnConvertAscii.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
       // jButton3_ActionPerformed(evt);
          if (!Utils.isNullorEmptyStringPasser(jTxtAscii.getText()))
          {
              // 1234
              String strascii=jTxtAscii.getText();
              //x31x32x33x34
              byte []asciibytes=strascii.getBytes();
             String bcdascii= UtilString.b2h(asciibytes);
             //"3132334"
             jTxtBcd.setText(bcdascii);
              
              
          }
                  
          
      }
    });
    cp.add(jBtnConvertAscii);
    
    
    jBtnConvertBCD.setBounds(500, 615, 80, 30);
    jBtnConvertBCD.setText("Convert");
    jBtnConvertBCD.setMargin(new Insets(2, 2, 2, 2));
    jBtnConvertBCD.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
       // jButton3_ActionPerformed(evt);
           
           if (!Utils.isNullorEmptyStringPasser(jTxtBcd.getText()))
          {  
              if ((jTxtBcd.getText().length()%2)==0)
              {
              // "31323334"
              String strascii=jTxtBcd.getText();
              //x31x32x33x34
            String bcdascii=  UtilString.convertBCDToString(strascii);
            //  byte []asciibytes=strascii.getBytes();
              //"1234"
           //  String bcdascii= UtilString.b2h(asciibytes);
             jTxtAscii.setText(bcdascii);
            }
              else
              {
                  jTxtAscii.setText("bcd must even length"); 
              }
              
          }
      }
    });
    cp.add(jBtnConvertBCD);
    
     jTxtAscii.setBounds(45, 585, 450, 20);
    cp.add(jTxtAscii);
    jTxtBcd.setBounds(45, 620, 450, 20);
    cp.add(jTxtBcd);
    /* utility End */

// end components
     switchbt(true);
    setVisible(true);
  } // end of public simulatorUI
  
  // start methods
  
  public  void resetTable()
  {
       for (int i=0;i<= 128; i++)
       {
          jTable1.getModel().setValueAt("", i, 0);
         jTable1.getModel().setValueAt("", i, 1);
         jTable1.getModel().setValueAt("", i, 2);
               
       }
  }
  
  
  public static void main(String[] args) {
    new isoFront("IsoFront");
  } // end of main
  
  public void jButton1_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
  } // end of jButton1_ActionPerformed

  public void jButton2_ActionPerformed(ActionEvent evt) {
    // TODO add your code here
  } // end of jButton2_ActionPerformed

  // end methods
 // end of class simulatorUI

class ItemChangeListener implements ItemListener{
    @Override
    public void itemStateChanged(ItemEvent event) {
       if (event.getStateChange() == ItemEvent.SELECTED) {
          Object item = event.getItem();
          resetTable();

          jTable1.removeAll();
          // do something with object
          String pathabsoulte=(String) event.getItem();
         //  jTable1.getColumnModel().getColumn(0).setHeaderValue("DE");
           
            isoControllerXml xmlreader=  new isoControllerXml();
            xmlreader.setAbsolutPath(pathabsoulte);
            xmlreader.procees();
            // setjTable1(new JTable(xmlreader.getModel().size(),3));
           // int index=0;
            for (int i=0;i<= 128; i++)
                   // xmlreader.getModel().size();i++)
           {
                jTable1.getModel().setValueAt(i, i, 0);
                
                for (int j=0;j<xmlreader.getModel().size();j++)
                    
                {
                    if (i==xmlreader.getModel().get(j).getId())
                       jTable1.getModel().setValueAt(xmlreader.getModel().get(j).getData1(), i, 1);
            
                }
               
           }
  
       }
    }       
}
}