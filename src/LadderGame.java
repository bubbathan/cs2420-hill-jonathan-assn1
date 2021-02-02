import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class LadderGame {
    public ArrayList<ArrayList<String>> dictionary;
    public LadderGame(String dictionaryFile) {
        this.dictionary = readDictionary(dictionaryFile);
    }

    public void play(String start, String end) {
        // TODO: Write some good stuff here

    }

    public ArrayList<String> oneAway(String word, boolean withRemoval) {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> wordLengthList = this.dictionary.get(word.length());

        while (!withRemoval) {
            for (String wordTwo : wordLengthList) {
                int wordDifference = 0;
                for (int j = 0; j < word.length(); j++) {
                    char charOne = word.charAt(j);
                    char charTwo = wordTwo.charAt(j);

                    if (charOne == charTwo) {
                        wordDifference++;
                    }
                }
                if (wordDifference == word.length() - 1) {
                    words.add(wordTwo);
                }
            }
            withRemoval = true;
        }
        

        return words;
    }

    public void listWords(int length, int howMany) {
        for (int i = 0; i < howMany; i++) {
            System.out.println(this.dictionary.get(length).get(i));
        }
    }

    /*
        Reads a list of words from a file, putting all words of the same length into the same array.
     */
    private ArrayList<ArrayList<String>> readDictionary(String dictionaryFile) {
        File file = new File(dictionaryFile);
        ArrayList<String> allWords = new ArrayList<>();

        //
        // Track the longest word, because that tells us how big to make the array.
        int longestWord = 0;
        try (Scanner input = new Scanner(file)) {
            //
            // Start by reading all the words into memory.
            while (input.hasNextLine()) {
                String word = input.nextLine().toLowerCase();
                allWords.add(word);
                longestWord = Math.max(longestWord, word.length());
            }
            
            ArrayList<ArrayList<String>> wordLists = new ArrayList<>();
            for(int i = 0; wordLists.size() <= longestWord; i++) {
                wordLists.add(new ArrayList<String>());
            }
            for (String word : allWords) {
                wordLists.get(word.length()).add(word);
            }
            return wordLists;
        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the dictionary: " + ex);
        }
        return null;
    }
}