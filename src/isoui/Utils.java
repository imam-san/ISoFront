
package isoui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

//import org.springframework.security.core.context.SecurityContextHolder;

//import com.flashiz.HsmGateway.util.Constant;
//import com.flashiz.HsmGateway.util.Constant;

public class Utils {

	public static boolean isNullorEmptyString(String value) {
		return value == null || value.length() == 0 || value == "";
	}
	public static boolean isNullorEmptyStringPasser(String value) {
		if ( value == null || value.length() == 0 || "".equals(value) || value.equals("null"))
			return true;
		return false;
	}

	 /**
     * Format String to currency ( #,###,###.00)
     * <p/>
     * sample:10000 = 10,000.00
     */
	
	
	public static String generateOTP(){    		
        int len = 8;
        String values = "0123456789abcdefghijklmnopqrstuvwxyz";
        
        Random rnd = new Random((new Date()).getTime());
        StringBuilder sb = new StringBuilder(len);
        
        for( int i = 0; i < len; i++ ) 
            sb.append(values.charAt( rnd.nextInt(values.length())));
        
        return sb.toString();
    }
	
	public static String generateTempPass(){    		
        int len = 11;
        String values = "ABCDEFGHIZKLMNOPQRSTUVWXYZ!@#$%^&*_=+-/0123456789abcdefghijklmnopqrstuvwxyz";
        
        Random rnd = new Random((new Date()).getTime());
        StringBuilder sb = new StringBuilder(len);
        
        for( int i = 0; i < len; i++ ) 
            sb.append(values.charAt( rnd.nextInt(values.length())));
        
        return sb.toString();
    }
	
	public static String sendOTP(String mdn, String sOTP, String otp_link) {   
		HttpURLConnection con = null;
		try{
			String sURL = otp_link;
			sURL = sURL + "shortcode=" + "" + "&to=" + mdn + "&message=your%20SMS%20Token%20is%20" + sOTP;
			
			URL url = new URL(sURL);
			System.out.println(">>>> " + url + " <<< ");
			con = (HttpURLConnection) url.openConnection();
	 		
			con.setRequestMethod("GET");
			con.setDoOutput(true);
			con.setReadTimeout(10000);                 
			con.connect();
			
			BufferedReader rd  = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
	       
			String line = null;
	        while ((line = rd.readLine()) != null) {
	             sb.append(line + '\n');
	        }
	       
	        System.out.println(sb.toString());
			
			return "success";
		}
		catch (MalformedURLException e) {
	          e.printStackTrace();
	          return "fail";
	    }
		catch (ProtocolException e) {
	          e.printStackTrace();
	          return "fail";
	    }
		catch (IOException e) {
	          e.printStackTrace();
	          return "fail";
	    }
	    finally {
	          //close the connection, set all objects to null
	          con.disconnect();
	    }
	}
		
	
    public static String convertStringtoCurrency(String value) {
        Double result = 0.0;
        if (value != null) {
            NumberFormat nf = new DecimalFormat("#,###,###.00");
            result = Double.parseDouble(value);
            value = nf.format(result);
        }
        return value;
    }
    
    public static Date getCurrentDate(){
    	Date date = new Date();
    	return date;
    }
    
    public static String getPreviousDate(String formatDate){
    	DateFormat dateFormat = new SimpleDateFormat(formatDate);
		// Use the Calendar class to subtract one day
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(Utils.getCurrentDate());
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date previousDate = calendar.getTime();
		String prevDate = dateFormat.format(previousDate);
		return prevDate;
    }
    
    public static String getCurrentDate(String format) {
        DateFormat df = new SimpleDateFormat(format);
        String formDate = "";
        Date newDate = new Date();
        formDate = df.format(newDate);
        return formDate;
    }
    
    public static void main(String[]args){
    	System.out.println(2%4);
    }
    
    public static StringWriter printStackTraceToString(Exception e){
    	StringWriter stack = new StringWriter();
		e.printStackTrace(new PrintWriter(stack));
		return stack;
    }
    
    
    public static boolean isDriverAvailable(String classsStr) {    
        boolean driverAvailable = true;

        try {
            // Load any class that should be present if driver's available
            Class.forName(classsStr);
        } catch (ClassNotFoundException e) {
            // Driver is not available
            driverAvailable = false; 
        }

        return driverAvailable;
    }
    
}
