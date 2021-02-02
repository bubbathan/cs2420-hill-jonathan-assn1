import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class LadderGame {
    public ArrayList<ArrayList<String>> dictionary;
    public ArrayList<ArrayList<String>> dictionaryClone = new ArrayList<>();
    public LadderGame(String dictionaryFile) {
        this.dictionary = readDictionary(dictionaryFile);
        for (int i = 0; i < this.dictionary.size(); i++) {
            this.dictionaryClone.add((ArrayList<String>) this.dictionary.get(i).clone());
        }
    }

    public void play(String start, String end) {
        // TODO: Write some good stuff here
        if (start.length() != end.length()) {
            System.out.printf("%s and %s are not the same length", start, end);
        } else if (!this.dictionary.get(start.length()).contains(start) || !this.dictionary.get(end.length()).contains(end)) {
            System.out.printf("%s and/or %s are not in the dictionary", start, end);
        }


        for (int i = 0; i < this.dictionary.size(); i++) {
            this.dictionaryClone.add((ArrayList<String>) this.dictionary.get(i).clone());
        }

        int enqueues = 0;
        WordInfo partialSolution = new WordInfo(start, 0, start);
        Queue<WordInfo> partialSolutionQueue = new Queue<>();
        partialSolutionQueue.enqueue(partialSolution);
        enqueues++;

        boolean ladderComplete = false;
        while (!partialSolutionQueue.isEmpty() && !ladderComplete) {
            WordInfo dequeueSolution = partialSolutionQueue.dequeue();
            ArrayList<String> solutionList = oneAway(dequeueSolution.getWord(), false);
            if (solutionList.size() > 0) {
                for (String word : solutionList) {
                    String stringHistory = dequeueSolution.getHistory() + " " + word;
                    int newMoves = dequeueSolution.getMoves() + 1;
                    WordInfo newPartialSolution = new WordInfo(word, newMoves, stringHistory);
                    if (newPartialSolution.getWord().compareTo(end) == 0) {
                        System.out.printf("%s => %s : %d Moves [%s] total enqueues %d\n", start, end,
                                newPartialSolution.getMoves(), newPartialSolution.getHistory(), enqueues);
                        partialSolutionQueue.clearQueue();
                        ladderComplete = true;
                        partialSolutionQueue.isEmpty();
                    } else {
                        partialSolutionQueue.enqueue(newPartialSolution);
                        enqueues++;
                    }
                }

            }
            partialSolutionQueue.isEmpty();
        }
        this.dictionary.removeAll(dictionary);
        for (int i = 0; i < dictionaryClone.size(); i++) {
            this.dictionary.add((ArrayList<String>) dictionaryClone.get(i).clone());
        }


    }

    public ArrayList<String> oneAway(String word, boolean withRemoval) {
        ArrayList<String> words = new ArrayList<>();

        while (!withRemoval) {
            for (String wordTwo : this.dictionary.get(word.length())) {
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
        this.dictionary.get(word.length()).removeAll(words);
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