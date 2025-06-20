package pe.gob.serfor.osutd.sgd.repository.logic.utils;







public class TripleDES
{
  private final DES desEncryptor = new DES();


  
  private static final int CIPHER = 1;


  
  private static final int DECIPHER = 2;



  
  public String cipher(String data, String key1, String key2, String key3) { return HP_3DES(1, data, key1, key2, key3); }











  
  public String decipher(String data, String key1, String key2, String key3) { return HP_3DES(2, data, key1, key2, key3); }


  
  private String HP_3DES(int tipo, String Data_in, String key1, String key2, String key3) {
    String outputTotal = "";
    String input = "";
    String output = "";
    int posicion = 0, data_in_len = Data_in.length();
    
    try {
      while (posicion < data_in_len) {
        
        if (posicion + 8 <= data_in_len) {
          input = Data_in.substring(posicion, posicion + 8);
        } else {
          input = Data_in.substring(posicion, data_in_len);
        } 
        if (input.length() < 8) for (; input.length() < 8; input = input.concat(" "));

        
        input = toHexString(input);
        
        if (tipo == 1) {

          
          output = this.desEncryptor.cipher(input, key1);

          
          output = this.desEncryptor.decipher(output.toUpperCase(), key2);

          
          output = this.desEncryptor.cipher(output.toUpperCase(), key3);
        } 

        
        if (tipo == 2) {

          
          output = this.desEncryptor.decipher(input, key3);

          
          output = this.desEncryptor.cipher(output.toUpperCase(), key2);

          
          output = this.desEncryptor.decipher(output.toUpperCase(), key1);
        } 
        
        posicion += 8;
        outputTotal = outputTotal + output;
      } 
      outputTotal = toString(outputTotal);
    }
    catch (Exception e) {
      
      System.out.println("ERROR: HP_3DES " + e.getMessage());
    } 
    return outputTotal;
  }

  
  public String toHexString(String data) {
    byte[] buf2 = new byte[data.length()];
    int j = 0;
    int offset = 0;
    
    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    
    for (int i = 0; i < data.length(); i++) {
      buf2[i] = (byte)data.charAt(i);
    }
    int length = buf2.length;
    char[] buf = new char[length * 2];
    
    for (int i = offset; i < offset + length; i++) {
      int k = buf2[i];
      buf[j++] = hexDigits[k >>> 4 & 0xF];
      buf[j++] = hexDigits[k & 0xF];
    } 
    return new String(buf);
  }

  
  public String toString(String s) {
    int index = 0;
    String cadena = "";
    while (index < s.length()) {
      String aux = s.substring(index, index + 2);
      index += 2;
      int b = Integer.parseInt(aux, 16);
      cadena = cadena + (char)b;
    } 
    return cadena;
  }
}
