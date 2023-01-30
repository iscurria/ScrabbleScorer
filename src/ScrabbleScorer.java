import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Scrabble Scoring class that takes a word and scores it
 * @author 24scurria
 * @version 1/26/22
 */
public class ScrabbleScorer {
    private String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int[] values = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    private ArrayList<ArrayList<String>> dictionary;

    /**
     * scrabble scorer method
     */
    public ScrabbleScorer() {
        dictionary = new ArrayList<>();
        for(int i = 0; i < 26; i++)
            dictionary.add(new ArrayList<String>());
        buildDictionary();
    }

    /**
     * builds dictionary of playable scrabble words
     */
    public void buildDictionary() {
        try {
            Scanner in = new Scanner(new File("SCRABBLE_WORDS.txt"));
            while(in.hasNext()) {
                String word = in.nextLine();
                int index = alpha.indexOf(word.substring(0,1));
                dictionary.get(index).add(word);
            }
            in.close();
            for(int i = 0; i < dictionary.size(); i ++) {
                Collections.sort(dictionary.get(i));
            }
        }
        catch(Exception e) {
            System.out.println("Error here: " + e);
        }
    }

    /**
     * Determines if the user's word is a valid word
     * @param word
     * @return false if not a valid word, true if it is
     */
    public boolean isValidWord(String word) {
        try {
            if (Collections.binarySearch(dictionary.get(alpha.indexOf(word.substring(0, 1))), word) < 0)
                return false;
            else
                return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    /**
     * Calculates the word score
     * @param word
     * @return score
     */
    public int getWordScore(String word) {
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            int index = alpha.indexOf(word.substring(i, i + 1));
            score += values[index];
        }
        return score;
    }
    /**
     * Main method for class Scrabble Scorer
     * @param args command-line arguments if needed
     */
    public static void main (String[] args) {
        ScrabbleScorer app = new ScrabbleScorer();
        System.out.println("* Welcome to the Scrabble Word Scorer app *");
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.print("Enter a word to score or 0 to quit: ");
            String word = in.nextLine().toUpperCase();
            if(word.equals("0")) {
                break;
            }
            if(app.isValidWord(word)) {
                System.out.println(word + " = " + app.getWordScore(word) + " points!");
            }
            else
                System.out.println(word + " is not a valid word in the dictionary");
        }
        System.out.println("Exiting the program, thanks for playing!");
    }
}
