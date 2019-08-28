/*
 * Program makes use of javaIO in order to write to and read from a .txt file
 * when prompted to by the user.
 *
 * @author Stewart
 * @version JAVA 8, 8/3/2019
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class TalesOfTwoCities {
    public static void main(String[] args){

        /* Test Cases */
        sendTextToFile("Charles Dickens\n", "Tales.txt", false);
        sendTextToFile("A Tale of Two Cities\n", "Tales.txt", true);
        sendTextToFile("It was the best of times, it was the worst of times, it was the age of wisdom, it was the age of foolishness, it was the epoch of belief, it was the epoch of incredulity, it was the season of Light, it was the season of Darkness, it was the spring of hope, it was the winter of despair, we had everything before us, we had nothing before us, we were all going direct to Heaven, we were all going direct the other way -in short, the period was so far like the present period, that some of its noisiest authorities insisted on its being received, for good or for evil, in the superlative degree of comparison only.", "Tales.txt", true);

        printFileToConsole("Tales.txt");
    }

    /**
     * Method: sendTextToFile
     * Arguments: String text, String filename, boolean append
     * 
     * Method takes 'text' and adds it to a file named, 'filename'. If append is
     * 'true', the information in 'text' will be added to the end of 'filename' if
     * it is 'false' then the contents of 'text' will overwrite the contents of 'filename'.
     */
    public static void sendTextToFile(String text, String filename, boolean append){
        /* Try/Catch to prevent crashing */
        try {
            /*
             * Identifies the file being created/modified and specifies if the file
             * will be overwriten or added to.
             */
            FileWriter fileWrite = new FileWriter(filename, append);
            PrintWriter printWrite = new PrintWriter(fileWrite);

            /* 'str' will contain each line as it is input into the file */
            String str = "";
            /* 
             * Splits the inputed text on spaces so that the lines can be measured
             * and input in a controlled manner.
             */
            String[] parts = text.split("((?<=\\s+)|(?=\\s+))");

            /* Loops through 'parts' and adds each index to 'str' */
            for(int i = 0; i < parts.length; i++){
                str += parts[i];

                /*
                 * Once 'str' is more than 80 characters long, the line should be
                 * added to the file and a new line should be started.
                 */
                if(str.length() > 80){
                    printWrite.println(str);
                    /* clears 'str' for reuse */
                    str = "";
                    /*
                     * Skips the space after the last word (if there is one)
                     * to prevent unwanted indentions.
                     */
                    if(parts[i + 1].equals(" ")){
                        i++;
                    }
                }
            }
            /*
             * Adds any leftover words from string that do not amount to more than
             * 80 characters to the file.
             */
            if(str.length() > 0){
                printWrite.println(str);
            }

            /* Closes and saves the information in the file */
            printWrite.close();
        } catch(IOException e){
            System.out.println("ERROR: " + e.getMessage());
            // System.out.println(e);
        }
    }

    /**
     * Method: printFileToConsole
     * Arguments: String filename
     *
     * Method takes the contents of 'filename' and then prints it to console line
     * by line.
     */
    public static void printFileToConsole(String filename){
        // File fileName;
        try {
            File fileName = new File(filename);
            Scanner scan = new Scanner(fileName);
            while(scan.hasNextLine()){
                System.out.println(scan.nextLine());
            }
            scan.close();
        } catch(IOException e){
            System.out.println("ERROR: " + e.getMessage());
        }

    }
}
