package wordsearch;
import java.io.IOException;
/**
 * Word Search Puzzle - WordSearch Class
 * This class contains all the logic to solve the puzzle
 * @author Cindy Diaz
 * @version 1.0
 */
public class WordSearch {
    //Declare the char array  letters and letters2 that will store the letters
    static char[][] letters = null;
    static char[][] letters2 = null;
    //Declare the string array word that will store the word list
    static String[] word = null;
    //Declare the variable wordnum that will be used for reference to the number of the word in the words list
    static int wordnum = 0;
    //Declare the variable that will store the percentage of the letters founded
    static double percentage = 0;
    

    public static void main(String args[]) {
        //Read the files that contain the letters and words
        int SIZE = 0;
        String file_name1 = "C:\\Users\\Cindy Diaz\\Documents\\NetBeansProjects\\WordSearchPuzzle\\src\\wordsearch\\letters2.txt";
        String file_name3 = "C:\\Users\\Cindy Diaz\\Documents\\NetBeansProjects\\WordSearchPuzzle\\src\\wordsearch\\words2.txt";
        try {
            //read first file (the one that contains the letters) into string array
            ReadFile file = new ReadFile(file_name1);
            String[] lettersLines = file.OpenFile();

            //store the size of the array
            SIZE = lettersLines.length;
            //create 2D array of predetermined size
            letters = new char[SIZE][SIZE];
            //We need another array that is the same as the first one to store the capitalize letters of the word founded
            letters2 = new char[SIZE][SIZE];
            //convert the lines into a 2D array
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    letters[i][j] = lettersLines[i].charAt(j);
                    letters2[i][j] = letters[i][j];
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            //read second file (the one that contains the words)into string array
            ReadFile file = new ReadFile(file_name3);
            word = file.OpenFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        //Create a for loop to search each word int the string array word[]
        for (wordnum = 0; wordnum < word.length; wordnum++) {
            /*We call each boolean method that searchs in all directions
            *(right, left, up, dowm, right up, right down, left up, and left down)
            *If the method returns false we continue look in the next direction until a method returns true.
            */
            if (!searchRight()) {
                
                if (!searchLeft()) {

                    if (!searchUp()) {

                        if (!searchDown()) {

                            if (!searchRightUp()) {

                                if (!searchRightDown()) {

                                    if (!searchLeftUp()) {

                                        if (!searchLeftDown()) {
                                            /*If we get up to here means that the word is not found;
                                            *but we will print the percenatge of how many letters were found
                                            */
                                            System.out.printf("%.2f%% of the word %s has been found %n", percentage, word[wordnum].toUpperCase());                                           
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            percentage=0;
        }
        //Display the letters2 array, this array contains the capitalize letters
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(letters2[i][j]);
            }
            System.out.println();
        }
    }//End of the Main

    public static boolean searchRight() {
        // Search for the word looking right
        //The boolean variable flag will be our return value, it will tell us if the word is found
        boolean flag = false; 
        //We start looping from row 0 till it is less than the length of the array
        for (int row = 0; row < letters.length; row++) {
            //And column 0 till it is less or eqal than the lenght array letters[row] minus the word lenght, 
            for (int column = 0; column <= (letters[row].length - word[wordnum].length()); column++) {
                int counter = 0; //this variable counter will store how many char are equal
                //We use this to loop through all the char in the word
                for (int i = 0; i < word[wordnum].length(); i++) {
                    /*We compare the char at i of the word to the char in the array
                      we sum i to the column (this make the loop look right)*/
                    if (letters[row][column + i] == word[wordnum].charAt(i)) {
                        //if the char are the same we increment the variable counter
                        counter++;
                    }
                }
                //We compare the varable counter to the length of the word
                if (counter == word[wordnum].length()) {
                    System.out.printf("The word %s is found at (%d,%d) searching right %n", word[wordnum].toUpperCase(), row, column);
                    /*If we are here that means that the word was found
                    so we proceed to capitalize the chars and sotre them in letters2*/
                    for (int i = 0; i < word[wordnum].length(); i++) {
                        letters2[row][column + i] = (char) ((letters[row][column + i]) - 32);
                    }
                    //Since the word was found, the variable flag will be true.
                    flag = true;
                } 
                //If the word is not found we see how many of the char were the samea and sotre the percentage
                else if (counter >= 2 &&  ((double) counter / word[wordnum].length()) * 100 > percentage) {
                    percentage = ((double) counter / word[wordnum].length()) * 100;
                }
            }
        }
        //This is the return value to see if the word is found searching right
        return flag;
    }//End of method searchRight

    public static boolean searchLeft() {
        // Search for the word backwards
        //We will use the same method we did to search right with just a minor modifications
        boolean flag = false;
        //The loop of the row starts at 0 till it is less than the letters array length
        for (int row = 0; row < letters.length; row++) {
            //Now the column will start at the length of the word minus one till it is less than letters[row] length, to make sure the word fits
            for (int column = (word[wordnum].length() - 1); column < letters[row].length; column++) {
                int counter = 0;
                for (int i = 0; i < word[wordnum].length(); i++) {
                    //Since we want to look backwards, we substract i from the column number to compare
                    if (letters[row][column - i] == word[wordnum].charAt(i)) {
                        counter++;
                    }
                }
                if (counter == word[wordnum].length()) {
                    System.out.printf("The word %s is found at (%d,%d) searching left %n", word[wordnum].toUpperCase(), row, column);
                    for (int i = 0; i < word[wordnum].length(); i++) {
                        letters2[row][column - i] = (char) ((letters[row][column - i]) - 32);
                    }
                    flag = true;
                } else if (counter >= 2 &&  ((double) counter / word[wordnum].length()) * 100 > percentage) {
                    percentage = ((double) counter / word[wordnum].length()) * 100;
                }
            }
        }
        return flag;
    }//End of method searchLeft

    public static boolean searchDown() {
        // Search for the word down
        //We will use the same method we did to search right with just a minor modifications
        boolean flag = false;
        //The loop of the row starts at 0 till it is less or equal than the array letters length minus the word length to make sure the word fits
        for (int row = 0; row <= (letters.length - word[wordnum].length()); row++) {
            //The column will start at 0 till it is less than letters[row] length to make sure the word fits
            for (int column = 0; column < (letters[row].length); column++) {
                int counter = 0;
                for (int i = 0; i < word[wordnum].length(); i++) {
                    //Since we want to search down, we add i to the row number to compare
                    if (letters[row + i][column] == word[wordnum].charAt(i)) {
                        counter++;
                    }
                }
                if (counter == word[wordnum].length()) {
                    System.out.printf("The word %s is found at (%d,%d) searching down %n", word[wordnum].toUpperCase(), row, column);
                    for (int i = 0; i < word[wordnum].length(); i++) {
                        letters2[row+i][column] = (char) ((letters[row+i][column]) - 32);
                    }
                    flag = true;
                } else if (counter >= 2 &&  ((double) counter / word[wordnum].length()) * 100 > percentage) {
                    percentage = ((double) counter / word[wordnum].length()) * 100;
                }
            }
        }
        return flag;
    }//End of method searchDown

    public static boolean searchUp() {
        // Search for the word laid up
        //We will use the same method we did to search right with just a minor modifications
        boolean flag = false;
        //The loop of the row starts at the length of the word minus one till it is less than the array letters length to make sure it fits
        for (int row = word[wordnum].length() - 1; row < letters.length; row++) {
            //Column will start at 0 till it is less than the letters[row] length 
            for (int column = 0; column < (letters[row].length); column++) {
                int counter = 0;
                for (int i = 0; i < word[wordnum].length(); i++) {
                    //Since we want to search up we will substract i from the row number
                    if (letters[row - i][column] == word[wordnum].charAt(i)) {
                        counter++;
                    }
                }
                if (counter == word[wordnum].length()) {
                    System.out.printf("The word %s is found at (%d,%d) searching up %n", word[wordnum].toUpperCase(), row, column);
                    for (int i = 0; i < word[wordnum].length(); i++) {
                        letters2[row-i][column] = (char) ((letters[row-i][column]) - 32);
                    }
                    flag = true;
                } else if (counter >= 2 &&  ((double) counter / word[wordnum].length()) * 100 > percentage) {
                    percentage = ((double) counter / word[wordnum].length()) * 100;
                }
            }
        }       
        return flag;
    }//End of method searchUp

    public static boolean searchRightDown() {
        // Search for the words right down
        //We will use the same method we did to search right with just a minor modifications
        boolean flag = false;
        /*To make it search right down, 
        row will start at 0 till array letters length AND  when it is less or equal than array letters length minus word length*/
        for (int row = 0; row < letters.length && row <= (letters.length - word[wordnum].length()); row++) {
            /*Column will start at 0 till array letters[row]length minus word length AND  when is less than array letters [row] length */
            for (int column = 0; column <= (letters[row].length - word[wordnum].length()) && column < (letters[row].length); column++) {
                int counter = 0;
                for (int i = 0; i < word[wordnum].length(); i++) {
                    //Since we are searching right down , we add i to row and i to column to compare 
                    if (letters[row + i][column + i] == word[wordnum].charAt(i)) {
                        counter++;
                    }
                }
                if (counter == word[wordnum].length()) {
                    System.out.printf("The word %s is found at (%d,%d) searching right down %n", word[wordnum].toUpperCase(), row, column);
                    for (int i = 0; i < word[wordnum].length(); i++) {
                        letters2[row+i][column + i] = (char) ((letters[row+i][column + i]) - 32);
                    }
                    flag = true;
                } else if (counter >= 2 &&  ((double) counter / word[wordnum].length()) * 100 > percentage) {
                    percentage = ((double) counter / word[wordnum].length()) * 100;
                }
            }
        }
        return flag;
    }//End of method searchRightDown

    public static boolean searchRightUp() {
        // Search for the words right up
        //We will use the same method we did to search right with just a minor modifications
        boolean flag = false;
        /*To make it search right up, 
        row will start at word length minus 1 till it is less than array letters length */
        for (int row = word[wordnum].length() - 1; row < letters.length; row++) {
            /*Column will start at 0 till it is less or equal than array letters[row]length minus word length AND less than array letters [row] length */
            for (int column = 0; column <= (letters[row].length - word[wordnum].length()) && column < (letters[row].length); column++) {
                int counter = 0;
                for (int i = 0; i < word[wordnum].length(); i++) {
                    //Since we want to search right up we substract i from row AND add i to column
                    if (letters[row - i][column + i] == word[wordnum].charAt(i)) {
                        counter++;
                    }
                }
                if (counter == word[wordnum].length()) {
                    System.out.printf("The word %s is found at (%d,%d) searching right up %n", word[wordnum].toUpperCase(), row, column);
                    for (int i = 0; i < word[wordnum].length(); i++) {
                        letters2[row-i][column + i] = (char) ((letters[row - i][column + i]) - 32);
                    }
                    flag = true;
                } else if (counter >= 2 &&  ((double) counter / word[wordnum].length()) * 100 > percentage) {
                    percentage = ((double) counter / word[wordnum].length()) * 100;
                }
            }
        }
        return flag;
    }//End of method searchRightUp

    public static boolean searchLeftDown() {
        // Search for the word looking down left
        //We will use the same method we did to search right with just a minor modifications
        boolean flag = false;
        /*To make it search left down, 
        row will start at 0 till it is less than array letters length AND less than or equal to the array letters length minus word length*/
        for (int row = 0; row < letters.length && row <= (letters.length - word[wordnum].length()); row++) {
            /*Column will start at word length minus 1 till  it is less than array letters[row]length*/
            for (int column = (word[wordnum].length() - 1); column < letters[row].length; column++) {
                int counter = 0;
                for (int i = 0; i < word[wordnum].length(); i++) {
                    //Since we are seraching left down, add i to row AND substract i to column
                    if (letters[row + i][column - i] == word[wordnum].charAt(i)) {
                        counter++;
                    }
                }
                if (counter == word[wordnum].length()) {
                    System.out.printf("The word %s is found at (%d,%d) searching left down %n", word[wordnum].toUpperCase(), row, column);
                    for (int i = 0; i < word[wordnum].length(); i++) {
                        letters2[row + i][column - i] = (char) ((letters[row + i][column - i]) - 32);
                    }
                    flag = true;
                } else if (counter >= 2 &&  ((double) counter / word[wordnum].length()) * 100 > percentage) {
                    percentage = ((double) counter / word[wordnum].length()) * 100;
                }
            }
        }
        return flag;
    }//End of method searchLeftDown

    public static boolean searchLeftUp() {
        // Search for the word backwards
        //We will use the same method we did to search right with just a minor modifications
        boolean flag = false;
        /*To make it search left up, 
        row will start at word length minus 1 till it is less than array letters length*/
        for (int row = word[wordnum].length() - 1; row < letters.length; row++) {
            /*Column will start at word length minus 1 till  it is less than array letters[row]length*/
            for (int column = (word[wordnum].length() - 1); column < letters[row].length; column++) {
                int counter = 0;
                for (int i = 0; i < word[wordnum].length(); i++) {
                    //Since we are looking left up, sunstract i from row AND substract i from column
                    if (letters[row - i][column - i] == word[wordnum].charAt(i)) {
                        counter++;
                    }
                }
                if (counter == word[wordnum].length()) {
                    System.out.printf("The word %s is found at (%d,%d) searching left up %n", word[wordnum].toUpperCase(), row, column);
                    for (int i = 0; i < word[wordnum].length(); i++) {
                        letters2[row - i][column - i] = (char) ((letters[row - i][column - i]) - 32);
                    }
                    flag = true;
                } else if (counter >= 2 &&  ((double) counter / word[wordnum].length()) * 100 > percentage) {
                    percentage = ((double) counter / word[wordnum].length()) * 100;
                }
            }
        }       
        return flag;
    }//End of method searchLeftUp

}//End of the Class 
