package Lab1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jcpatac on 1/26/2017.
 */
public class IndexedSequential {

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> theIndices = new ArrayList<>();

    public static void main(String[] args) {
        IndexedSequential is = new IndexedSequential();
        is.reading();
        System.out.println(is.searchIt());
    }

    /*
    This method simply reads two files. The input and the indices
     */
    private void reading() {
        BufferedReader index;
        BufferedReader data;

        try {
            String indices;
            index = new BufferedReader(new FileReader("C:\\Users\\acer\\Desktop\\CMSC\\CMSC127\\src\\Lab1\\Indices.txt"));

            String lines;
            data = new BufferedReader(new FileReader("C:\\Users\\acer\\Desktop\\CMSC\\CMSC127\\src\\Lab1\\Input.txt"));

            while ((indices = index.readLine()) != null) {
                theIndices.add(indices);
            }

            while ((lines = data.readLine()) != null) {
                names.add(lines);
            }

            index.close();
            data.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    This method searches the List of indices, for the index needed, using the first letter of the given input.
    It then returns the line number where the certain letter was found.
     */
    private int indexGetter(String input) {
        String firstLetter = "" + input.charAt(0);
        String result = "-1";
        for (int i = 0; i < theIndices.size(); i++) {
            if (firstLetter.equalsIgnoreCase(theIndices.get(i).charAt(0) + "")) {
                result = theIndices.get(i).substring(theIndices.get(i).lastIndexOf(" ") + 1);
                break;
            }
        }
        return Integer.parseInt(result);
    }

    /*
    This method asks the user to search something. This returns a String.
    this function determines whether the searched word was found or not.
     */
    private String searchIt() {
        System.out.println("Search:");
        Scanner searchThis = new Scanner(System.in);
        String thisItem = searchThis.next();
        String result = thisItem + " not found!";
        int index = indexGetter(thisItem);

        if (index < 0) {
            return result;
        }

        for (int i = index; i < names.size(); i++) {
            if (thisItem.equals(names.get(i))) {
                result = names.get(i) + " is at line " + i;
                break;
            }
            if (thisItem.charAt(0) < names.get(i).charAt(0)) {
                break;
            }
        }
        System.out.println("");
        return result;
    }

}

/**
 * There are two ways of writing a error-free code; only the third one works. - Alan J. Perlis
 */
