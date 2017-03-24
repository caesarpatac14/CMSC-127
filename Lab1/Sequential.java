package Lab1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by jcpatac on 1/26/2017.
 */

public class Sequential {

    private ArrayList<String> names = new ArrayList<>(); // serves as the storage of the names (takes up more space?)

    public static void main(String[] args) {
        Sequential seq = new Sequential(); // create a new instance of the class
        seq.reading();
        seq.addName(); // add a certain name
        seq.printResult(); // print/write the result
    }


    /*
    This method reads an input file which may or may not have any contents. It stores the contents into an ArrayList
     */
    private void reading() {
        BufferedReader readData = null;

        try {
            String line;
            readData = new BufferedReader(new FileReader("C:\\Users\\acer\\Desktop\\CMSC\\CMSC127\\src\\Lab1\\Input.txt"));

            while ((line = readData.readLine()) != null) {
                names.add(line);
                Collections.sort(names); // the original names from the file (just in case)
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (readData != null) {
                    readData.close(); // must close the file
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /*
    This function simply gets the minimum size of two strings
     */
    private int min(String str1, String str2) {
        if (str1.length() < str2.length()) {
            return str1.length();
        }
        return str2.length();
    }


    /*
    Function that adds/inserts the name given by the user. The time complexity of this function
    is O(n^2), because of nested for loop, which is not so good I think. hahaha

    The reason that I did not use the '.compareTo' function provided by JAVA is that, it could not properly sort
    the names like 'John' and 'Joh'. In this case, 'Joh' must appear first but it doesn't seems to be the case.
     */
    private void addName() {
        System.out.println("Enter name to insert:");
        Scanner sc = new Scanner(System.in);

        String input = sc.next();
        String curr;

        for (int i = 0; i < names.size(); i++) {
            curr = names.get(i);
            boolean added = false;

            if (input.charAt(0) == curr.charAt(0)) {
                int minimumSize = min(input, curr);
                for (int j = 1; j < minimumSize; j++) {

                    if (input.charAt(j) > curr.charAt(j)) {
                        if (input.charAt(0) < names.get(i + 1).charAt(0)) {
                            names.add(i + 1, input);
                            added = true;
                        }
                        break;
                    }else if (input.charAt(j) < curr.charAt(j)) {
                        if (input.charAt(0) < names.get(i + 1).charAt(0)) {
                            names.add(i, input);
                            added = true;
                        }
                        break;
                    }else if (j == input.length() - 1) {
                        if (input.length() < curr.length()) {
                            names.add(i, input);
                        }else {
                            names.add(i + 1, input);
                        }
                        added = true;
//                        break;
                    }
                }

                if (added) {
                    break;
                }

            }else if (input.charAt(0) < curr.charAt(0)) {
                names.add(i, input);
                break;
            }else if (i == names.size() - 1) {
                names.add(names.size(), input);
                break;
            }
        }
    }


    /*
    This method prints/writes the results to a certain file in a certain directory.
     */
    private void printResult() {

        try {

            File file = new File("C:\\Users\\acer\\Desktop\\CMSC\\CMSC127\\src\\Lab1\\Output.txt");

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            for (int i = 0; i < names.size(); i++) {
                writer.write(names.get(i));
                writer.newLine();
            }
            writer.close(); // must close the file

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}

/**
 * There are two ways of writing an error-free code; only the third one works. - Alan J. Perlis
 */
