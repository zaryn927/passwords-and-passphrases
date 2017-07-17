package edu.cnm.deepdive.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
/**
 * Gets a random subset of words from a list.
 * 
 * @author Sky Link
 * @version 1.0
 */
public class WordList {
  
  /** The default value used for  the phrase length, if none is specified. */
  public static final int DEFAULT_NUM_WORDS = 5;
  
  private static final String PROPERTIES_FILE = "resources/text.properties";
  private static final String WORD_LIST_FILE = "resources/eff_large_wordlist.txt";
  
  private static String usageMessage;
  private static String errorMessage;
  private static String warningMessage;
  
  /**
   * Loads a file and selects a subset of random words to print out.
   * The number of words in the subset is determined by the command line argument.
   * If no arguments are specified, {@link #DEFAULT_NUM_WORDS DEFAULT_NUM_WORDS} is used.
   * 
   * @param args Specifies the number of words to be used in the phrase.
   */
  public static void main(String[] args) {
    
    int phraseLength;
    
    try{
      loadResources();
      if (args.length > 0) {
        phraseLength = Integer.parseInt(args[0]);
      } else {
        phraseLength = DEFAULT_NUM_WORDS;
      }
      if (phraseLength <= 0) {
        throw new IllegalArgumentException(errorMessage);
      } else if (phraseLength < DEFAULT_NUM_WORDS) {
        System.out.println(warningMessage);
      }
      String[] wordList = getWordList(WORD_LIST_FILE);
      String[] selectedWords = getRandomWords(phraseLength, wordList);
      System.out.println(getJoinedString(selectedWords));
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
  
  /**
   * Loads the word list from a text file specified as an argument.
   * 
   * @param listPath Takes the file path to the list text file as a String.
   * @return Returns the word list text file as a String Array.
   * @throws IOException Happens when word list can't be read.
   */
  public static String[] getWordList(String listPath) throws IOException{
    try(BufferedReader reader 
        = new BufferedReader(new InputStreamReader(WordList.class.getClassLoader().getResourceAsStream(listPath)))){
      ArrayList<String> words = new ArrayList<>();
      for (String line = reader.readLine(); line != null; line = reader.readLine()){
        words.add(line.split("\\s+")[1]);
      }
      return words.toArray(new String[]{});
    }
    
  }
  
  /**
   * Takes a list of words and returns a random subset of that list of the specified size.
   * 
   * @param numWords The number of words desired for the random phrase.
   * @param wordList A list of words in the form of a String Array.
   * @return Returns another String Array that is a random subset of the second argument.
   */
  public static String[] getRandomWords(int numWords, String[] wordList) {
    String[] selection = new String[numWords];
    Random rng = new Random();
    for (int i = 0; i < selection.length; i++){
      int selectedPosition = rng.nextInt(wordList.length);
      selection[i] = wordList[selectedPosition];
    }
    return selection;
  }
  
  private static String getJoinedString(String[] source) {
    StringBuilder builder = new StringBuilder();
    for(String item : source) {
      builder.append(item);
      builder.append(" ");
    }
    return builder.toString().trim();
  }

}
