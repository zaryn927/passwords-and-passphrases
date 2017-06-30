/**
 * Guard.java
 */
package edu.cnm.deepdive.security;

import java.util.HashMap;

/**
 * Entry point for the password/passphrase generation application.
 * 
 * Generation uses a cryptographically secure random number generator
 * to select words from a list or characters from a pool.
 * 
 * @author Sky Link
 */
public class Guard {

  /**
   * Parse command line arguments using the Apache Commons CLI library,
   * and then instantiate and invoke the appropriate classes and methods to 
   * generate the requested artifact.
   * 
   * @param args  Command line arguments, specifying generation options.
   */
  public static void main(String[] args) {
   HashMap<String, Object> map = Options.getOptions(args);
   if (map != null) {
   String artifact = generateArtifact(map);
   emitArtifact(artifact);
   }
  }
    
  static String generateArtifact(HashMap<String, Object> map) {
    // TODO Invoke methods for password or passphrase creation based on options (if statement)
    if (map.containsKey("m")) {
      PasswordGenerator gen = new SecurePasswordGenerator();// move to password creation method everything down to next brace
      // TODO Set fields for all specified options.
      return gen.generate();
    }
    return null;// FIXME
  }
  
  static void emitArtifact(String artifact) {
    // TODO make this smarter.
    System.out.println(artifact);
  }
  // TODO methods for password and passphrase creation.

}
