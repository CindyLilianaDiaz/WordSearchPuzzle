package wordsearch;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
/**
 * Word Search Puzzle - ReadFile Class
 * This class reads the file that contains the puzzle.
 * @author Cindy Diaz
 * @version 1.0
 */
public class ReadFile {
    private String path;
    public ReadFile(String file_path) {
        path = file_path;
    }

    public String[] OpenFile() throws IOException {
        // creates an object fr of type File Reader
        FileReader fr = new FileReader(path);
        // Creates buff stream to store chars
        BufferedReader textReader = new BufferedReader(fr);
        //get the number of lines from the file
        int numberOfLines = readLines();
        //create variable to store individual lines
        String[] textData = new String[numberOfLines];

        //read in each line of code into the buffered stream then save into string array
        for (int i = 0; i < numberOfLines; i++) {
            textData[i] = textReader.readLine();

        }
        //close buffered stream
        textReader.close();
        //return string array to parent method
        return textData;

    }

    int readLines() throws IOException {

        //creates object of  type File Reader
        //open an i/o stream
        FileReader file_to_read = new FileReader(path);
        // Creates buffer i/o stream to store chars
        BufferedReader bf = new BufferedReader(file_to_read);
        //Create string to store lines from file
        String aLine;
        //keeps track of how many lines
        int numberOfLines = 0;
        //loops one line at a time an increments counter of line number
        while ((aLine = bf.readLine()) != null) {
            numberOfLines++;
        }
        //closes the buffer i/o stream
        bf.close();
        //sends number of lines back to parent method
        return numberOfLines;
    }
    public void writeFile1(String filename,String text) throws IOException {
	File fout = new File(filename);
	FileOutputStream fos = new FileOutputStream(fout);
 
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
 
	for (int i = 0; i < 10; i++) {
		bw.write(text);
		bw.newLine();
	}
 
	bw.close();
}
}
