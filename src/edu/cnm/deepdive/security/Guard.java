/**
 * Guard.java
 */
package edu.cnm.deepdive.security;

import java.util.HashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

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
   HashMap<String, Object> map = getOptions(args);
   String artifact = generateArtifact(map);
   emitArtifact(artifact);
  }
  
  static HashMap<String, Object> getOptions(String[] args) {
    
    try {
      
      Option excludeUpperOption = Option.builder("b").longOpt("exclude-upper")
                                                     .hasArg(false)
                                                     .build();
      Option excludeLowerOption = Option.builder("s").longOpt("exclude-lower")
                                                     .hasArg(false)
                                                     .build();
      Option excludeDigitsOption = Option.builder("n").longOpt("exclude-digits")
                                                      .hasArg(false)
                                                      .build();
      Option excludePunctuationOption = Option.builder("p").longOpt("exclude-punctuation")
                                                           .hasArg(false)
                                                           .build();
      Option includeAmbiguousOption = Option.builder("a").longOpt("include-ambiguous")
                                                         .hasArg(false)
                                                         .build();
      Option lengthOption = Option.builder("L").argName("length")
                                               .hasArg()
                                               .longOpt("length")
                                               .numberOfArgs(1)
                                               .type(Number.class)
                                               .build();
      Option delimiterOption = Option.builder("d").argName("delimiter")
                                                 .hasArg()
                                                 .longOpt("delimiter")
                                                 .numberOfArgs(1)
                                                 .optionalArg(true)
                                                 .type(String.class)
                                                 .build();
      Option wordListOption = Option.builder("w").argName("path-to-list-file")
                                                 .hasArg()
                                                 .longOpt("word-list")
                                                 .numberOfArgs(1)
                                                 .type(String.class)
                                                 .build();
      Options options = new Options().addOption(lengthOption)
                                     .addOption(delimiterOption)
                                     .addOption(wordListOption)
                                     .addOption(excludeUpperOption)
                                     .addOption(excludeLowerOption)
                                     .addOption(excludeDigitsOption)
                                     .addOption(excludePunctuationOption)
                                     .addOption(includeAmbiguousOption);
      DefaultParser parser = new DefaultParser();
      HashMap<String, Object> map = new HashMap<>();
      CommandLine cmdLine = parser.parse(options, args);
      for (Option option : cmdLine.getOptions()) {
        String opt = option.getOpt();
        map.put(opt, cmdLine.getParsedOptionValue(opt));
      }
      return map;
      
    } catch (ParseException ex) {
      // TODO handle this exception with a usage display.
      return null;
    }

  }
  
  static String generateArtifact(HashMap<String, Object> map) {
    return null;// FIXME
  }
  
  static void emitArtifact(String artifact) {
    
  }

}
