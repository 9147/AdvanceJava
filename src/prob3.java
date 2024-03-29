/*
Write a Java Program to demonstrate the implementation of reading and writing binary data
in Java. 1) Read the source and destination file names. 2) Read user defined text to be
written to the source file. 3) Write every alternate byte from the source to the destination
file. 4) Compare the properties of the file.
*/
import java.util.*;
import java.io.*;

public class prob3 {
    public static void main(String args[]) {
        File filesrc=null;
        File filedest=null;
        try (BufferedReader bin = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter the source file name(dont enter the extension):");
            filesrc = new File("files//prob3//" + bin.readLine() + ".txt");
            System.out.println("Enter the destination file name(dont enter the extension):");
            filedest = new File("files//prob3//" + bin.readLine() + ".txt");

        //writing in the source file
            try (FileOutputStream fout = new FileOutputStream(filesrc)){
                System.out.println("Enter the text to be written to the file with . on single line to end.");
                String val = bin.readLine();
                while(!val.equals(".")){
                    fout.write(val.getBytes());
                    fout.write("\n".getBytes());
                    val = bin.readLine();
                }
            }catch(IOException ex){
                System.out.println("Error: "+ex);
            }

            //writing in the destination file
            try(FileInputStream fin=new FileInputStream(filesrc);
                FileOutputStream fout = new FileOutputStream(filedest)){
                int i=1;
                int val=fin.read();
                while(val!=-1){
                    if(i%2!=0) fout.write(val);
                    val=fin.read();
                    i++;
                }

            }catch(IOException ex){
                System.out.println("Error: "+ex);
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }

//        comparing file properties
        System.out.println("File properties of source file:");
        System.out.println("File name: "+filesrc.getName());
        System.out.println("File path: "+filesrc.getPath());
        System.out.println("File size: "+filesrc.length());
        System.out.println("File properties of destination file:");
        System.out.println("File name: "+filedest.getName());
        System.out.println("File path: "+filedest.getPath());
        System.out.println("File size: "+filedest.length());

    }

}
