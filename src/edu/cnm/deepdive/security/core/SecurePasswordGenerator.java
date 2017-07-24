package edu.cnm.deepdive.security.core;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Creates a more secure password.
 * 
 * @author Sky Link
 * @version 1.0
 */
public class SecurePasswordGenerator extends PasswordGenerator {
  
  /**
   * Invokes {@link PasswordGenerator#PasswordGenerator() superclass constructor}
   * but then reassigns the field rng with 
   * a cryptographically secure random number generator.
   */
  public SecurePasswordGenerator() {
    super();
  }
  
  @Override
  protected void setupRng() {
    try {
      setRng(SecureRandom.getInstanceStrong());
    } catch (NoSuchAlgorithmException ex) {
      throw new RuntimeException(ex);
    }
  }

}
