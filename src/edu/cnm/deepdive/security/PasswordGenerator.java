package edu.cnm.deepdive.security;

import java.util.Random;

/**
 * Implementation of a simple password generator. This includes support for elementary
 * character-set-based rules, but not (yet) regular expressions.
 * 
 * @author Sky Link
 * @version 1.0
 */
public class PasswordGenerator {
  
  /** Punctuation characters that may optionally be included in the password. */
  public static final String PUNCTUATION = "!@#$%&*,.";
  /** Ambiguous characters that may optionally be excluded from the password. */
  public static final String AMBIGUOUS = "[Ol]";
  
  /** */
  public static final int DEFAULT_PASSWORD_LENGTH = 12;
  
  private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String LOWERCASE = UPPERCASE.toLowerCase();
  private static final String NUMBERS = "0123456789";
  
  /** Basic random number generator */
  private Random rng = null;
  private char[] pool = null;
  private int minLength = DEFAULT_PASSWORD_LENGTH ;
  private int maxLength = DEFAULT_PASSWORD_LENGTH ;
  private boolean upperCaseIncluded = true;
  private boolean lowerCaseIncluded = true;
  private boolean numbersIncluded = true;
  private boolean punctuationIncluded = true;
  private boolean ambiguousExcluded = true;
  private String delimiter = "";

  /**
   * Default constructor where fields are initialized to default values.
   */
  public PasswordGenerator() {
    
  }

  /**
   * Gives access to the maxLength field.
   * 
   * @return maxLength The maximum length of the password.
   */
  public int getMaxLength() {
    return maxLength;
  }

  /**
   * Mutates the field maxLength.
   * 
   * @param maxLength The value to change the maxLength field to.
   */
  protected void setMaxLength(int maxLength) {
    this.maxLength = maxLength;
  }

  /**
   * Gives access to the minLength field.
   * 
   * @return minLength The minimum length of the password.
   */
  public int getMinLength() {
    return minLength;
  }

  /**
   * Mutates the field minLength.
   * 
   * @param minLength The value to change the minLength field to.
   */
  protected void setMinLength(int minLength) {
    this.minLength = minLength;
  }

  private void setupPool() {
    if (pool == null) {
      StringBuilder builder = new StringBuilder();
      if (isLowerCaseIncluded()) {
        builder.append(LOWERCASE);
      }
      if (isUpperCaseIncluded()) {
        builder.append(UPPERCASE);
      }
      if (isNumbersIncluded()) {
        builder.append(NUMBERS);
      }
      if (isPunctuationIncluded()) {
        builder.append(PUNCTUATION);
      }
      String work = builder.toString();
      if (isAmbiguousExcluded()) {
        work.replaceAll(AMBIGUOUS, "");
      }
      pool = work.toCharArray();
    }
  }
  
  /**
   * 
   */
  protected void setupRng() {
    if (rng == null) {
      rng = new Random();
    }
  }

  /**
   * Establishes a pool of characters to be chosen from and 
   * then creates a password that consists of random characters 
   * from the pool and is between the minimum and maximum length.
   * 
   * @return Returns generated password.
   */
  public String generate() {
    setupPool();
    setupRng();
    int passwordLength = minLength + getRng().nextInt(maxLength - minLength + 1);
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < passwordLength; i++) {
      char selection = pool[getRng().nextInt(pool.length)];
      builder.append(selection);
    }
    return builder.toString();
  }

  /**
   * @return the upperCaseIncluded
   */
  public boolean isUpperCaseIncluded() {
    return upperCaseIncluded;
  }

  /**
   * @param upperCaseIncluded the upperCaseIncluded to set
   */
  public void setUpperCaseIncluded(boolean upperCaseIncluded) {
    this.upperCaseIncluded = upperCaseIncluded;
  }

  /**
   * @return the lowerCaseIncluded
   */
  public boolean isLowerCaseIncluded() {
    return lowerCaseIncluded;
  }

  /**
   * @param lowerCaseIncluded the lowerCaseIncluded to set
   */
  public void setLowerCaseIncluded(boolean lowerCaseIncluded) {
    this.lowerCaseIncluded = lowerCaseIncluded;
  }

  /**
   * @return the numbersIncluded
   */
  public boolean isNumbersIncluded() {
    return numbersIncluded;
  }

  /**
   * @param numbersIncluded the numbersIncluded to set
   */
  public void setNumbersIncluded(boolean numbersIncluded) {
    this.numbersIncluded = numbersIncluded;
  }

  /**
   * @return the punctuationIncluded
   */
  public boolean isPunctuationIncluded() {
    return punctuationIncluded;
  }

  /**
   * @param punctuationIncluded the punctuationIncluded to set
   */
  public void setPunctuationIncluded(boolean punctuationIncluded) {
    this.punctuationIncluded = punctuationIncluded;
  }

  /**
   * @return the ambiguousExcluded
   */
  public boolean isAmbiguousExcluded() {
    return ambiguousExcluded;
  }

  /**
   * @param ambiguousExcluded the ambiguousExcluded to set
   */
  public void setAmbiguousExcluded(boolean ambiguousExcluded) {
    this.ambiguousExcluded = ambiguousExcluded;
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
   * @return the rng
   */
  protected Random getRng() {
    return rng;
  }

  /**
   * @param rng the rng to set
   */
  protected void setRng(Random rng) {
    this.rng = rng;
  }

}
