package fake;

import java.io.*;
import java.util.*;
public class prob1 {
    public static void main(String[] args)
    {
        try {
            occurance("files//prob1//input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void occurance(String input) throws IOException {
        try {
            InputStream input1 = new FileInputStream(input);
            byte[] buffer = new byte[100];
            input1.read(buffer);
            String data = new String(buffer);
            Map<String, Integer> hashMap = new HashMap<>();
            String[] words = data.split(" ");
            for (String word : words) {
                Integer integer = hashMap.get(word);
                if (integer == null)
                    hashMap.put(word, 1);
                else {
                    hashMap.put(word, integer + 1);
                }
            }
// Sort the map entries by value (word counts) in descending order
            List<Map.Entry<String, Integer>> list = new
                    LinkedList<>(hashMap.entrySet());
            list.sort((o1, o2) ->
                    (o2.getValue()).compareTo(o1.getValue()));
// Take user input for N
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the value of N: ");
            int N = scanner.nextInt();
// Display the top N most frequent words
            System.out.println("Top " + N + " most frequent words:");
            for (int i = 0; i < N && i < list.size(); i++) {
                Map.Entry<String, Integer> entry = list.get(i);
                System.out.println(entry.getKey() + ": " +
                        entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
