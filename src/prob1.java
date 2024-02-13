/*Write a Java Program that reads a large text document, processes the data using Java streams
and file handling, and provides Word Frequency Analysis. The program must : Read the
text file using Java streams , Tokenize the text into words, use streams to count the
frequency of each word &amp; display the top N most frequent words.
 */

import java.io.*;
import java.util.*;

public class prob1 {
    public static void main(String args[]){
        int i,j=0;
        char[] currentWord=new char[100];
        Map<String, Integer> dictionary = new HashMap<>();
        String filepath = "files//prob1_input.txt";

        try(FileInputStream fin = new FileInputStream(filepath)){

            byte buffer[] = new byte[fin.available()];
            fin.read(buffer);
            String str = new String(buffer);
            String words[] = str.replace("\n"," ").split(" ");
            for(String word: words){
                dictionary.put(word,dictionary.getOrDefault(word,0)+1);
            }

        } catch(IOException ex){
            System.out.println("Error: "+ex);
        }

        // Get the top N most frequent words
        int N = 5; // Change N to the desired number
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(dictionary.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        System.out.println("Top " + N + " most frequent words:");
        for(int k = 0; k < Math.min(N, entryList.size()); k++) {
            Map.Entry<String, Integer> entry = entryList.get(k);
            System.out.println(entry.getKey() + ": " + entry.getValue() + " occurrences");
        }


    }
}
