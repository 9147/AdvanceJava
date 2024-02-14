package fake;

import java.io.*;
import java.util.Scanner;
public class prob2 {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String inputFileName = "input.txt"; // Assuming input file is named "input.txt"
        String destinationFileName;
// Accepting the destination file name from the user
        System.out.print("Enter the destination file name: ");
        destinationFileName = scanner.nextLine();
// Menu-driven program
        System.out.println("Menu:");
        System.out.println("1. Transfer contents from input file to destination file");
                System.out.println("2. Display contents of destination file");
        System.out.println("3. Exit");
        int choice;
        do {
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    transferContents(inputFileName, destinationFileName);
                    break;
                case 2:
                    displayContents(destinationFileName);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (choice != 3);
        scanner.close();
    }
    // Transfer contents from input file to destination file
    private static void transferContents(String inputFileName, String
            destinationFileName) {
        try (InputStream inputStream = new FileInputStream(inputFileName);
             ByteArrayOutputStream outputStream = new
                     ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
// Using ByteArrayInputStream to read from the byte array
            try (ByteArrayInputStream byteArrayInputStream = new
                    ByteArrayInputStream(outputStream.toByteArray());
                 OutputStream fileOutputStream = new
                         FileOutputStream(destinationFileName)) {
                buffer = new byte[1024];
                while ((bytesRead = byteArrayInputStream.read(buffer)) != -
                        1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
            }
            System.out.println("Contents transferred successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Display contents of the destination file
    private static void displayContents(String destinationFileName) {
        try (InputStream inputStream = new
                FileInputStream(destinationFileName);
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            System.out.println("Contents of " + destinationFileName + ":");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
