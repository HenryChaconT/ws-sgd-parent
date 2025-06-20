package pe.gob.serfor.osutd.sgd.repository.logic.utils;



import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;













public class Utilidad
{
  private static boolean instanciated = false;
  private static final Pattern numberPattern = Pattern.compile("^\\d+$");




  
  private Random generator = new Random(19580427L); private static Utilidad utilityInstance;
  private TripleDES _3Des = new TripleDES();
  private static final String key1_3DEs = "1234567890ABCDEF";
  
  public static Utilidad getInstancia() {
    if (!instanciated) {
      utilityInstance = new Utilidad();
      instanciated = true;
    } 
    return utilityInstance;
  }
  private static final String key2_3DEs = "ABCDEFGHIJKLMNOP";
  private static final String key3_3DEs = "QRSTUVWXYZ123456";
  
  public Integer nextRandomNumber() { return Integer.valueOf(this.generator.nextInt()); }

  
  public Date getLastDateInYear(Date date) {
    Date newd = null;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    if (date == null) {
      date = new Date();
    }
    try {
      newd = format.parse("31/12/" + format.format(date).substring(6));
    } catch (ParseException e) {
      newd = new Date();
    } 
    
    return newd;
  }
  
  public Date getFirstDateInYear(Date date) {
    Date newd = null;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    if (date == null) {
      date = new Date();
    }
    try {
      newd = format.parse("01/01/" + format.format(date).substring(6));
    } catch (ParseException e) {
      newd = new Date();
    } 
    
    return newd;
  }
  
  public String dateToFormatString(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    return format.format(date);
  }
  
  public String dateToFormatString2(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    return format.format(date);
  }
  public String dateToFormatStringHHmm(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    return format.format(date);
  }
  public String dateToFormatString3(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    return format.format(date);
  }
  
  public String dateToFormatStringYYYY(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy");
    return format.format(date);
  }
  
  public String dateYearString(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    return format.format(date).substring(6);
  }
  
  public String dateYearString() {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    return format.format(new Date()).substring(6);
  }
  
  public String rellenaCerosIzquierda(String str, int length) {
    String retval = "";
    if (str != null) {
      for (int i = 0; i < length - str.length(); i++) {
        retval = retval + "0";
      }
      retval = retval + str;
    } 
    return retval;
  }
  
  public Date getDateHour(String string) throws ParseException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    return dateFormat.parse(string);
  }
  
  public String getDateString(Date date) {
    if (date == null) {
      return "";
    }
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    return dateFormat.format(date);
  }

  
  public long getDateInt() {
    Calendar now = Calendar.getInstance();
    long vsec = now.getTimeInMillis() / 1000L;
    return vsec;
  }

  
  public String cifrar(String sinCifrar, String key) throws Exception {
    byte[] bytes = sinCifrar.getBytes("UTF-8");
    Cipher aes = obtieneCipher(true, key);
    byte[] cifrado = aes.doFinal(bytes);
    return DatatypeConverter.printHexBinary(cifrado);
  }
  
  public String descifrar(String cifrado, String key) throws Exception {
    byte[] bcadena = hexStrToByteArray(cifrado);
    Cipher aes = obtieneCipher(false, key);
    byte[] bytes = aes.doFinal(bcadena);
    String sinCifrar = new String(bytes, "UTF-8");
    return sinCifrar;
  }
  
  private Cipher obtieneCipher(boolean paraCifrar, String keyString) throws Exception {
    MessageDigest digest = MessageDigest.getInstance("SHA");
    digest.update(keyString.getBytes("UTF-8"));
    SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
    
    Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
    if (paraCifrar) {
      aes.init(1, key);
    } else {
      aes.init(2, key);
    } 
    
    return aes;
  }

































  
  public Date restarFechasDias(Date fch, int dias) {
    Calendar cal = new GregorianCalendar();
    cal.setTimeInMillis(fch.getTime());
    cal.add(5, -dias);
    return new Date(cal.getTimeInMillis());
  }


  
  public String codificaAES(String plainMessage, String symKeyHex) {
    byte[] symKeyData = symKeyHex.getBytes();
    byte[] encodedMessage = plainMessage.getBytes(Charset.forName("UTF-8"));
    byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      SecretKeySpec symKey = new SecretKeySpec(symKeyData, "AES");
      IvParameterSpec ivspec = new IvParameterSpec(iv);
      
      cipher.init(1, symKey, ivspec);
      byte[] encryptedMessage = cipher.doFinal(encodedMessage);
      
      String ivAndEncryptedMessageHex = DatatypeConverter.printHexBinary(encryptedMessage);
      
      return ivAndEncryptedMessageHex;
    } catch (InvalidKeyException e) {
      throw new IllegalArgumentException("key argument does not contain a valid AES key");
    } catch (GeneralSecurityException e) {
      throw new IllegalStateException("Unexpected exception during encryption", e);
    } 
  }
  
  public String decodificaAES(String encryptedMessageHex, String symKeyHex) {
    byte[] symKeyData = symKeyHex.getBytes();
    
    byte[] encryptedMessage = DatatypeConverter.parseHexBinary(encryptedMessageHex);
    byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      SecretKeySpec symKey = new SecretKeySpec(symKeyData, "AES");
      IvParameterSpec ivspec = new IvParameterSpec(iv);
      
      cipher.init(2, symKey, ivspec);
      byte[] encodedMessage = cipher.doFinal(encryptedMessage);
      
      String message = new String(encodedMessage, Charset.forName("UTF-8"));
      
      return message;
    } catch (InvalidKeyException e) {
      throw new IllegalArgumentException("key argument does not contain a valid AES key");
    } catch (BadPaddingException e) {
      return null;
    } catch (GeneralSecurityException e) {
      throw new IllegalStateException("Unexpected exception during decryption", e);
    } 
  }
  
  public Date getDateToString(String pfecha) {
    Date date;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    
    try {
      date = formatter.parse(pfecha);

    
    }
    catch (ParseException e) {
      date = null;
    } 
    return date;
  }






  
  public String getStringFechaYYYYMMDDHHmm(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
    return format.format(date);
  }
  
  public String getStringFechaFormat(String pfecha, String formatoFechaReturn, String formatoPfecha) {
    String vResult = "";
    if (pfecha != null && !pfecha.equals("") && formatoFechaReturn != null && !formatoFechaReturn.equals("") && formatoPfecha != null && 
      !formatoPfecha.equals("")) {
      try {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatoPfecha);
        Date date = dateFormat.parse(pfecha);
        dateFormat = new SimpleDateFormat(formatoFechaReturn);
        vResult = dateFormat.format(date);
      } catch (Exception e) {
        e.printStackTrace();
        vResult = "NO_OK";
      } 
    }
    return vResult;
  }

  
  public static boolean validarContraseniaFuerte(String cadena) {
    Pattern pat = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d){8,20}.+$");
    Matcher mat = pat.matcher(cadena);
    return mat.find();
  }

  
  public static boolean isNumber(String nro) { return (nro != null && numberPattern.matcher(nro).matches()); }
  
  public Date getDateFromString(String pfecha) {
    Date date;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    
    try {
      date = formatter.parse(pfecha);
    }
    catch (ParseException e) {
      date = null;
    } 
    return date;
  }


  
  public boolean isPdf(byte[] data) { return (data != null && data.length > 4 && data[0] == 37 && data[1] == 80 && data[2] == 68 && data[3] == 70 && data[4] == 45); }







  
  public String cipher_3Des(String data) { return this._3Des.toHexString(this._3Des.cipher(data, "1234567890ABCDEF", "ABCDEFGHIJKLMNOP", "QRSTUVWXYZ123456")); }


  
  public String decipher_3Des(String data) { return this._3Des.decipher(data, "1234567890ABCDEF", "ABCDEFGHIJKLMNOP", "QRSTUVWXYZ123456"); }
  
  public byte[] hexStrToByteArray(String hex) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream(hex.length() / 2);
    for (int i = 0; i < hex.length(); i += 2) {
      String output = hex.substring(i, i + 2);
      int decimal = Integer.parseInt(output, 16);
      baos.write(decimal);
    } 
    return baos.toByteArray();
  }
}
