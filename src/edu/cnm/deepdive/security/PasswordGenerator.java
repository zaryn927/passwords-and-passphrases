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

  /** Basic random number generator */
  protected Random rng = new Random();
  
  private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String LOWERCASE = UPPERCASE.toLowerCase();
  private static final String NUMBERS = "0123456789";
  private static final String PUNCTUATION = "!@#$%&*,.";
  private static final String AMBIGUOUS = "[Ol]";
  
  private char[] pool = null;
  private int minLength = 6;
  private int maxLength = 12;
  private boolean includeUpperCase = true;
  private boolean includeLowerCase = true;
  private boolean includeNumbers = true;
  private boolean includePunctuation = false;
  private boolean excludeAmbiguous = true;

  /**
   * Instantiate PasswordGenerator class for testing password generation.
   * 
   * @param args Command-line parameters for password generation options.
   * NOT CURRENTLY IMPLEMENTED
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }
  
  /**
   * Default constructor where fields are initialized to default values.
   */
  public PasswordGenerator() {
    
  }

  /**
   * Constructor that allows user to specify the minimum and maximum length of password.
   * 
   * @param minLength The minimum length of the password.
   * @param maxLength The maximum lenght of the password.
   */
  public PasswordGenerator(int minLength, int maxLength) {
    this();
    this.minLength = minLength;
    this.maxLength = maxLength;
  }

  /**
   * Constructor that allows user to specify values of all instance fields of the class.
   * 
   * @param minLength The minimum length of the password.
   * @param maxLength The maximum length of the password.
   * @param includeUpperCase Allow upper case in the password?
   * @param includeLowerCase Allow lower case in the password?
   * @param includeNumbers Allow numbers in the password?
   * @param includePunctuation Allow punctuation in the password?
   * @param excludeAmbiguous Exclude lower case "l" and upper case "O"?
   */
  public PasswordGenerator(int minLength, int maxLength, boolean includeUpperCase,
      boolean includeLowerCase, boolean includeNumbers, boolean includePunctuation,
      boolean excludeAmbiguous) {
    this(minLength, maxLength);
    this.includeUpperCase = includeUpperCase;
    this.includeLowerCase = includeLowerCase;
    this.includeNumbers = includeNumbers;
    this.includePunctuation = includePunctuation;
    this.excludeAmbiguous = excludeAmbiguous;
    System.out.println("in another overloaded constructor");
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
      if (includeLowerCase) {
        builder.append(LOWERCASE);
      }
      if (includeUpperCase) {
        builder.append(UPPERCASE);
      }
      if (includeNumbers) {
        builder.append(NUMBERS);
      }
      if (includePunctuation) {
        builder.append(PUNCTUATION);
      }
      String work = builder.toString();
      if (excludeAmbiguous) {
        work.replaceAll(AMBIGUOUS, "");
      }
      pool = work.toCharArray();
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
    int passwordLength = minLength + rng.nextInt(maxLength - minLength + 1);
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < passwordLength; i++) {
      char selection = pool[rng.nextInt(pool.length)];
      builder.append(selection);
    }
    return builder.toString();
  }

}
