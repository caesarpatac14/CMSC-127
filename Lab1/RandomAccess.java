package Lab1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jcpatac on 1/26/2017.
 */

public class RandomAccess {

    private ArrayList<String> names = new ArrayList<>(); // storage of the file contents
    private ArrayList<Long> hashValues = new ArrayList<>(); // storage of the indices after hashing

    public static void main(String[] args) {
        RandomAccess randomAccess = new RandomAccess();
        randomAccess.reading();
        randomAccess.gatherHashValues(randomAccess.names);
        randomAccess.printing();
    }


    /*
    This hash function takes a string and the size of the array as inputs. It processes the string four bytes
    at a time, and interprets each of the four-byte chunks as a single long integer value. The integer values for the
    four-byte chunks are added together. Finally, the resulting sum is converted to the range 0 - (size - 1) using the
    modulus operator.
     */
    private long hashCode(String string, int size) {
        int strLength = string.length() / 4;
        long sum = 0;
        for (int i = 0; i < strLength; i++) {
            char ch[] = string.substring(i * 4, (i * 4) + 4).toCharArray();
            long multiplier = 1;
            for (int j = 0; j < ch.length; j++) {
                sum += ch[j] * multiplier;
                multiplier *= 256;
            }
        }
        char ch2[] = string.substring(strLength * 4).toCharArray();
        long multiplier2 = 1;
        for (int k = 0; k < ch2.length; k++) {
            sum += ch2[k] * multiplier2;
            multiplier2 *= 256;
        }
        return (Math.abs(sum) % size);
    }

    /*
    This method reads an input file and stores the contents into an ArrayList.
     */
    private void reading() {
        BufferedReader bufferedReader = null;

        try {
            String line;
            bufferedReader = new BufferedReader(new FileReader("C:\\Users\\acer\\Desktop\\CMSC\\CMSC127\\src\\Lab1\\Input.txt"));
            while ((line = bufferedReader.readLine()) != null) {
                names.add(line);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /*
    This method simply gathers the values, which are indices, of the contents after hashing.
     */
    private void gatherHashValues(ArrayList<String> strings) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size:");
        int size = scanner.nextInt();
        for (int i = 0; i < strings.size(); i++) {
            hashValues.add(hashCode(strings.get(i), size));
        }
    }


    /*
    Prints out the equivalent indices of the contents
     */
    private void printing() {
        System.out.println("");
        for (int i = 0; i < hashValues.size(); i++) {
            System.out.println(names.get(i) + " - " + hashValues.get(i));
        }
    }

}

/**
 * There are two ways of writing an error-free code; only the third one works. - Alan J. Perlis
 */
