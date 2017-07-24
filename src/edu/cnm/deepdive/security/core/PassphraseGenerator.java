/**
 * 
 */
package edu.cnm.deepdive.security.core;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Creates a passphrase of specified length from a predetermined word list.
 * 
 * @author Sky Link
 * @version 1.0
 */
public class PassphraseGenerator {
  
  /** The word list used if none other is specified */
  public static final String DEFAULT_WORD_LIST = "resources/wordlist";
  /** A space is used if no other delimiter is specified */
  public static final String DEFAULT_DELIMITER = " ";
  /** The default number of words to be used in the passphrase*/
  public static final int DEFAULT_LENGTH = 6;
  
  private String wordList = DEFAULT_WORD_LIST;
  private String delimiter = DEFAULT_DELIMITER;
  private int length = DEFAULT_LENGTH;
  private Random rng = null;
  private ArrayList<String> pool = null;
  
  /**
   * Creates instance. Same functionality as default constructor.
   */
  public PassphraseGenerator() {
    super();
  }
  
  /**
   * Creates a pool of words by adding them to an array list from the word list file.
   */
  protected void setupPool() {
    ResourceBundle bundle = ResourceBundle.getBundle(wordList);
    pool = new ArrayList<>();
    Enumeration<String> keyEnum = bundle.getKeys();
    while (keyEnum.hasMoreElements()) {
      String key = keyEnum.nextElement();
      String word = bundle.getString(key);
      pool.add(word);
    }
  }
  
  /**
   * Creates a secure random number generator
   */
  protected void setupRng() {
    rng = new SecureRandom();
  }
  
  /**
   * Generates a passphrase from a pool of words
   * 
   * @return The passphrase created
   */
  public String generate() {
    if (pool == null) {
      setupPool();
    }
    if (rng == null) {
      setupRng();
    }
    StringBuilder builder = new StringBuilder();
    String word = pool.get(rng.nextInt(pool.size()));
    builder.append(word);
    for (int i = 0; i < length - 1; i++) {
      word = pool.get(rng.nextInt(pool.size()));
      builder.append(delimiter);
      builder.append(word);
    }
    return builder.toString().trim();
  }
  
  /**
   * @return the wordList
   */
  public String getWordList() {
    return wordList;
  }
  /**
   * @param wordList the wordList to set
   */
  public void setWordList(String wordList) {
    this.wordList = wordList;
  }
  /**
   * @return the delimiter
   */
  public String getDelimiter() {
    return delimiter;
  }
  /**
   * @param delimiter the delimiter to set
   */
  public void setDelimiter(String delimiter) {
    this.delimiter = delimiter;
  }
  /**
   * @return the length
   */
  public int getLength() {
    return length;
  }
  /**
   * @param length the length to set
   */
  public void setLength(int length) {
    this.length = length;
  }

}
