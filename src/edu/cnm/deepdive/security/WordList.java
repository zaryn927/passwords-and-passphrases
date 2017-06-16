package edu.cnm.deepdive.security;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WordList {
  
  public static final int DEFAULT_NUM_WORDS = 5;
  
  private static final String PROPERTIES_FILE = "resources/text.properties";
  private static final String WORD_LIST = "resources/eff_large_wordlist.txt";
  
  private static String usageMessage;
  private static String errorMessage;
  private static String warningMessage;

  public static void main(String[] args) {
    
    int numWords;
    
    try{
      loadResources();
      if (args.length > 0) {
        numWords = Integer.parseInt(args[0]);
      } else {
        numWords = DEFAULT_NUM_WORDS;
      }
      if (numWords <= 0) {
        throw new IllegalArgumentException(errorMessage);
      } else if (numWords < DEFAULT_NUM_WORDS) {
        System.out.println(warningMessage);
      }
      loadList();
    }
    catch (NumberFormatException ex) {
      ex.printStackTrace();
      System.out.println(errorMessage);
      System.out.printf(usageMessage, WordList.class.getName());
      System.exit(1);
    }
    catch (IllegalArgumentException ex) {
      ex.printStackTrace();
      System.out.println(errorMessage);
      System.out.printf(usageMessage, WordList.class.getName());
      System.exit(1);
    }
    catch (IOException ex) {
      ex.printStackTrace();
      System.exit(1);
    }

  }
  
  private static void loadResources() throws IOException{
    Properties properties = new Properties();
    try(InputStream input = WordList.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)){
      properties.load(input);
      usageMessage = properties.getProperty("usage.message");
      errorMessage = properties.getProperty("error.message");
      warningMessage = properties.getProperty("warning.message");
    }
  }
  // TODO finish loadList method.
  private static void loadList() throws IOException{
    try(IntputStream input = WordList.class.getClassLoader().getResourceAsStream(name))
  }

}
