package isoui;

import java.net.InetAddress;
import java.util.StringTokenizer;

public class UtilString {
	
	
	 public static String RemoveBlank(String input)
	    {
	        int i=0;
	        StringBuffer st = new StringBuffer();
	        for(i=0;i<input.length();i++)
	        {
	            if ((0!=(int) input.charAt(i))&&(0x20!=(byte) input.charAt(i)))
	            {
	                st.append(input.charAt(i));
	                
	            }
	        
	        }
	        
	         return new String(st);
	    }
	    public static String getvalueJson(String buff,String token)
	    { 
	       
	         String retbuff="";
	       char r=0x22;
	      String t=""+r+"";
	       StringTokenizer Tok = new StringTokenizer(buff,t);
	        boolean istoken=false;
	        String me;
	        while (Tok.hasMoreElements())
	        {  me=Tok.nextElement().toString();
	       
	            if (!istoken)
	            { 
	                if (token.equals(me))
	                { istoken=true;
	                }
	            }
	          else
	            { if (me.length()>2 )
	                { retbuff=me;
	                  break;
	                }
	             
	            } 
	           
	        }
	    return retbuff;
	        
	    }
	    public static String getAStringFormByte(byte[] buffin, int start, int len)
	    {
	         String retString ="";
	         byte buffbyte[]=new byte[len];
	         
	         System.arraycopy(buffin, start, buffbyte, 0, len);
	         retString=new String(buffbyte);
	   return retString;
	    }
	    public String convertStringToHex(String str){

	        char[] chars = str.toCharArray();

	        StringBuffer hex = new StringBuffer();
	        for(int i = 0; i < chars.length; i++){
	            hex.append(Integer.toHexString((int) chars[i]));
	        }

	        return hex.toString();
	    }
	                   //     "303030313031323334FFFFFFFFFFFFFF"
	                    //     000101234
	    public static String convertBCDToString(String hex){

	        StringBuilder sb = new StringBuilder();
	        StringBuilder temp = new StringBuilder();

	        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
	        for( int i=0; i<hex.length()-1; i+=2 ){

	            //grab the hex in pairs
	            String output = hex.substring(i, (i + 2));
	            //convert hex to decimal
	            int decimal = Integer.parseInt(output, 16);
	            //convert the decimal to character
	            sb.append((char)decimal);

	            temp.append(decimal);
	        }
	       // System.out.println("Decimal : " + temp.toString());

	        return sb.toString();
	    }

	    public static byte[] h2b(String hex)
	    {
	        if ((hex.length() & 0x01) == 0x01)
	            throw new IllegalArgumentException();
	        byte[] bytes = new byte[hex.length() / 2];
	        for (int idx = 0; idx < bytes.length; ++idx) {
	            int hi = Character.digit((int) hex.charAt(idx * 2), 16);
	            int lo = Character.digit((int) hex.charAt(idx * 2 + 1), 16);
	            if ((hi < 0) || (lo < 0))
	                throw new IllegalArgumentException();
	            bytes[idx] = (byte) ((hi << 4) | lo);
	        }
	        return bytes;
	    }

	    public static String b2h(byte[] bytes)
	    {
	        char[] hex = new char[bytes.length * 2];
	        for (int idx = 0; idx < bytes.length; ++idx) {
	            int hi = (bytes[idx] & 0xF0) >>> 4;
	            int lo = (bytes[idx] & 0x0F);
	            hex[idx * 2] = (char) (hi < 10 ? '0' + hi : 'A' - 10 + hi);
	            hex[idx * 2 + 1] = (char) (lo < 10 ? '0' + lo : 'A' - 10 + lo);
	        }
	        return new String(hex);
	    }

	    public static String postpadString(int  instr, int leng) {

	        String str=null;
	        //String.valueOf(instr);
	        if (instr>0)
	        str=  String.format("%d", instr);
	             //   String.for
	       // str=""+instr+"";
	         else
	        str="1";
	      //  System.out.println("pad string |"+ str + "|");
	      return   postpadString   (str,leng);
	    }


	    public  static String postpadString(String pString, int lenght) {



	        if (pString != null && !pString.isEmpty()){
	            return getStringAtFixedLength(pString, lenght);
	        }else{
	            return completeWithWhiteSpaces("", lenght);
	        }
	    }

	    private static String getStringAtFixedLength(String pString, int lenght) {
	        if(lenght < pString.length()){
	           // System.out.println("getStringAtFixedLength string |"+ lenght + "|" + pString.length());
	            return pString.substring(0, lenght);
	        }else{
	            return completeWithWhiteSpaces(pString, lenght - pString.length());
	        }
	    }

	    private static String completeWithWhiteSpaces(String pString, int lenght) {
	        for (int i=0; i<lenght; i++)
	            pString += " ";
	        return pString;
	    }

	    public static String padString(String str, int leng) {
	        for (int i = str.length(); i <= leng; i++)
	            str += " ";
	       return  getStringAtFixedLength (str,leng);
	       // return str;
	    }
	    public static String padString(int istr, int leng) {


	        String me=  String.valueOf(istr);//  String.format("%1$",leng," " +istr);
	       // return String.format("%1$"+length+ "s", string);

	       // for (int i = str.length(); i <= leng; i++)
	         //   str += " ";
	        return  getStringAtFixedLength (me,leng);
	        // return str;
	    }


	    public static int strToInt( String str ){
	        int i = 0;
	        int num = 0;
	        boolean isNeg = false;

	       // System.out.println("StrtoInt  "+ str);
	        if (str==null)
	            str="0";
	        //check for negative sign; if it's there, set the isNeg flag
	        if( str.charAt(0) == '-') {
	            isNeg = true;
	            i = 1;
	        }

	        //process each char of the string;
	        while( i < str.length()) {
	            num *= 10;
	            num += str.charAt(i++) - '0'; //minus the ASCII code of '0' to get the value of the charAt(i++)
	        }

	        if (isNeg)
	            num = -num;
	      //  System.out.println("StrtoInt  "+ num);
	        return num;
	    }

	    public static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	    public static byte[] short2byte(short[] paramArrayOfShort)
	    {
	        int i = paramArrayOfShort.length;
	        byte[] arrayOfByte = new byte[i * 2];
	        int j = 0; for (int k = 0; j < i; ) {
	        int m = paramArrayOfShort[(j++)];
	        arrayOfByte[(k++)] = (byte)(m >>> 8 & 0xFF);
	        arrayOfByte[(k++)] = (byte)(m & 0xFF);
	    }
	        return arrayOfByte;
	    }

	    public static short[] byte2short(byte[] paramArrayOfByte)
	    {
	        int i = paramArrayOfByte.length;
	        short[] arrayOfShort = new short[i / 2];
	        int j = 0; for (int k = 0; k < i / 2; ) {
	        arrayOfShort[(k++)] = (short)((paramArrayOfByte[(j++)] & 0xFF) << 8 | paramArrayOfByte[(j++)] & 0xFF);
	    }

	        return arrayOfShort;
	    }

	    public static byte[] int2byte(int[] paramArrayOfInt)
	    {
	        int i = paramArrayOfInt.length;
	        byte[] arrayOfByte = new byte[i * 4];
	        int j = 0; for (int k = 0; j < i; ) {
	        int m = paramArrayOfInt[(j++)];
	        arrayOfByte[(k++)] = (byte)(m >>> 24 & 0xFF);
	        arrayOfByte[(k++)] = (byte)(m >>> 16 & 0xFF);
	        arrayOfByte[(k++)] = (byte)(m >>> 8 & 0xFF);
	        arrayOfByte[(k++)] = (byte)(m & 0xFF);
	    }
	        return arrayOfByte;
	    }

	    public static int[] byte2int(byte[] paramArrayOfByte)
	    {
	        int i = paramArrayOfByte.length;
	        int[] arrayOfInt = new int[i / 4];
	        int j = 0; for (int k = 0; k < i / 4; ) {
	        arrayOfInt[(k++)] = ((paramArrayOfByte[(j++)] & 0xFF) << 24 | (paramArrayOfByte[(j++)] & 0xFF) << 16 | (paramArrayOfByte[(j++)] & 0xFF) << 8 | paramArrayOfByte[(j++)] & 0xFF);
	    }

	        return arrayOfInt;
	    }

	    public static String toHEX(byte[] paramArrayOfByte)
	    {
	        int i = paramArrayOfByte.length;
	        char[] arrayOfChar = new char[i * 3];
	        int j = 0; for (int k = 0; j < i; ) {
	        int m = paramArrayOfByte[(j++)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 4 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m & 0xF)];
	        arrayOfChar[(k++)] = ' ';
	    }
	        return new String(arrayOfChar);
	    }

	    public static String toHEX(short[] paramArrayOfShort)
	    {
	        int i = paramArrayOfShort.length;
	        char[] arrayOfChar = new char[i * 5];
	        int j = 0; for (int k = 0; j < i; ) {
	        int m = paramArrayOfShort[(j++)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 12 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 8 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 4 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m & 0xF)];
	        arrayOfChar[(k++)] = ' ';
	    }
	        return new String(arrayOfChar);
	    }

	    public static String toHEX(int[] paramArrayOfInt)
	    {
	        int i = paramArrayOfInt.length;
	        char[] arrayOfChar = new char[i * 10];
	        int j = 0; for (int k = 0; j < i; ) {
	        int m = paramArrayOfInt[(j++)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 28 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 24 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 20 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 16 & 0xF)];
	        arrayOfChar[(k++)] = ' ';
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 12 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 8 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 4 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m & 0xF)];
	        arrayOfChar[(k++)] = ' ';
	    }
	        return new String(arrayOfChar);
	    }

	    public static String toHEX1(byte paramByte)
	    {
	        char[] arrayOfChar = new char[2];
	        int i = 0;
	        arrayOfChar[(i++)] = HEX_DIGITS[(paramByte >>> 4 & 0xF)];
	        arrayOfChar[(i++)] = HEX_DIGITS[(paramByte & 0xF)];
	        return new String(arrayOfChar);
	    }

	    public static String toHEX1(byte[] paramArrayOfByte)
	    {
	        int i = paramArrayOfByte.length;
	        char[] arrayOfChar = new char[i * 2];
	        int j = 0; for (int k = 0; j < i; ) {
	        int m = paramArrayOfByte[(j++)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 4 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m & 0xF)];
	    }
	        return new String(arrayOfChar);
	    }

	    public static String toHEX1(short[] paramArrayOfShort)
	    {
	        int i = paramArrayOfShort.length;
	        char[] arrayOfChar = new char[i * 4];
	        int j = 0; for (int k = 0; j < i; ) {
	        int m = paramArrayOfShort[(j++)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 12 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 8 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 4 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m & 0xF)];
	    }
	        return new String(arrayOfChar);
	    }

	    public static String toHEX1(int paramInt)
	    {
	        char[] arrayOfChar = new char[8];
	        int i = 0;
	        arrayOfChar[(i++)] = HEX_DIGITS[(paramInt >>> 28 & 0xF)];
	        arrayOfChar[(i++)] = HEX_DIGITS[(paramInt >>> 24 & 0xF)];
	        arrayOfChar[(i++)] = HEX_DIGITS[(paramInt >>> 20 & 0xF)];
	        arrayOfChar[(i++)] = HEX_DIGITS[(paramInt >>> 16 & 0xF)];
	        arrayOfChar[(i++)] = HEX_DIGITS[(paramInt >>> 12 & 0xF)];
	        arrayOfChar[(i++)] = HEX_DIGITS[(paramInt >>> 8 & 0xF)];
	        arrayOfChar[(i++)] = HEX_DIGITS[(paramInt >>> 4 & 0xF)];
	        arrayOfChar[(i++)] = HEX_DIGITS[(paramInt & 0xF)];
	        return new String(arrayOfChar);
	    }

	    public static String toHEX1(int[] paramArrayOfInt)
	    {
	        int i = paramArrayOfInt.length;
	        char[] arrayOfChar = new char[i * 8];
	        int j = 0; for (int k = 0; j < i; ) {
	        int m = paramArrayOfInt[(j++)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 28 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 24 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 20 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 16 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 12 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 8 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m >>> 4 & 0xF)];
	        arrayOfChar[(k++)] = HEX_DIGITS[(m & 0xF)];
	    }
	        return new String(arrayOfChar);
	    }
                     //   
	    public static byte[] hex2byte(String paramString)
	    {
	        int i = paramString.length();
	        byte[] arrayOfByte = new byte[(i + 1) / 2];

	        int j = 0; int k = 0;
	        if (i % 2 == 1) {
	            arrayOfByte[(k++)] = (byte)hexDigit(paramString.charAt(j++));
	        }
	        while (j < i) {
	            arrayOfByte[(k++)] = (byte)(hexDigit(paramString.charAt(j++)) << 4 | hexDigit(paramString.charAt(j++)));
	        }

	        return arrayOfByte;
	    }

	    public static boolean isHex(String paramString)
	    {
	        int i = paramString.length();
	        int j = 0;

	        while (j < i) {
	            int k = paramString.charAt(j++);
	            if (((k < 48) || (k > 57)) && ((k < 65) || (k > 70)) && ((k < 97) || (k > 102)))
	                return false;
	        }
	        return true;
	    }

	    public static int hexDigit(char paramChar)
	    {
	        if ((paramChar >= '0') && (paramChar <= '9'))
	            return paramChar - '0';
	        if ((paramChar >= 'A') && (paramChar <= 'F'))
	            return paramChar - 'A' + 10;
	        if ((paramChar >= 'a') && (paramChar <= 'f')) {
	            return paramChar - 'a' + 10;
	        }
	        return 0;
	    }
	   public static byte[] trimfromtms(String input,int len )
	   {   //byte[]buff=new byte[len];

	       if (input==null)
	           return null;
	       String me=input.trim();
	       if (me.length()>len )
	       {
	           StringBuffer sb = new StringBuffer(me);
	             sb.delete(23, me.length());
	              me=sb.toString();

	       }
	      return  postpadString(me,len).getBytes();

	       //return buff;

	   }

	    /*
	    //     "303030313031323334FFFFFFFFFFFFFF"
	    //     000101234
	    public static String convertHexToString(String hex){

	        StringBuilder sb = new StringBuilder();
	        StringBuilder temp = new StringBuilder();

	        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
	        for( int i=0; i<hex.length()-1; i+=2 ){

	            //grab the hex in pairs
	            String output = hex.substring(i, (i + 2));
	            //convert hex to decimal
	            int decimal = Integer.parseInt(output, 16);
	            //convert the decimal to character
	            sb.append((char)decimal);

	            temp.append(decimal);
	        }
	        // System.out.println("Decimal : " + temp.toString());

	        return sb.toString();
	    }  */
	    public static byte[] StringtoIP(String s)
	    {
	        byte[] bytes=new byte[4];

	        //String s = "77.125.65.201:8099";
	       // String[] a = s.split(":");
	        try{
	        InetAddress ia = InetAddress.getByName(s);//a[0]);

	         bytes = ia.getAddress();
	       // int port = Integer.parseInt(a[1]);
	        }catch(Exception e)
	        {}
	            return bytes;

	    }

	    public static byte [] inttobytePort(int intbuf )
	    {    byte []me2=new byte [2];
	        byte a=0;

	        a =(byte)((intbuf>>8) & 0xFF);
	        me2[0]=(byte)a;
	        a =(byte)(intbuf & 0xFF);
	        me2[1]=a;

	        return me2;

	    }
	    public static void ReserseByte(byte[] array) {
	        if (array == null) {
	            return ;
	        }
	        int i = 0;
	        int j = array.length - 1;
	        byte tmp;
	        while (j > i) {
	            tmp = array[j];
	            array[j] = array[i];
	            array[i] = tmp;
	            j--;
	            i++;
	        }
	         // return array;
	    }

	    public static byte [] Memset(byte []arrayb, byte fill)
	    {
	        for (int i=0;i<arrayb.length;i++)
	           arrayb[i]=fill; 
	        
	        return arrayb;
	    }

}
