package edu.cnm.deepdive.security;

import java.security.SecureRandom;

/**
 * Creates a more secure password.
 * 
 * @author Sky Link
 */
public class SecurePasswordGenerator extends PasswordGenerator {
  
  /**
   * Invokes {@link PasswordGenerator#PasswordGenerator() superclass constructor}
   * but then reassigns the field rng with 
   * a cryptographically secure random number generator.
   */
  public SecurePasswordGenerator() {
    super();
    rng = new SecureRandom();
    // TODO Auto-generated constructor stub
  }

  /**
   * Invokes {@link PasswordGenerator#PasswordGenerator(int, int) superclass constructor}.
   * 
   * @param minLength
   * @param maxLength
   */
  public SecurePasswordGenerator(int minLength, int maxLength) {
    super(minLength, maxLength);
    // TODO Auto-generated constructor stub
  }

  /**
   * Invokes {@link PasswordGenerator#PasswordGenerator(int, int, boolean, boolean, boolean, boolean, boolean) superclass constructor}.
   * 
   * @param minLength
   * @param maxLength
   * @param includeUpperCase
   * @param includeLowerCase
   * @param includeNumbers
   * @param includePunctuation
   * @param excludeAmbiguous
   */
  public SecurePasswordGenerator(int minLength, int maxLength, boolean includeUpperCase,
      boolean includeLowerCase, boolean includeNumbers, boolean includePunctuation,
      boolean excludeAmbiguous) {
    super(minLength, maxLength, includeUpperCase, includeLowerCase, includeNumbers,
        includePunctuation, excludeAmbiguous);
    // TODO Auto-generated constructor stub
  }

}
