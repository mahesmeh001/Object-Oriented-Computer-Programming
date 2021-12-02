// --== CS400 File Header Information ==--
// Name: Brian Blinder
// Email: bblinder@wisc.edu
// Team: RED
// Group: KC
// TA: Keren Chen
// Lecturer:Gary Dahl
// Notes to Grader: N/A

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

public class MovieDataReaderTester {

  /**
   * Main method in the tester to run the test method
   * 
   * @param args a String array that is for arguments
   * @throws FileNotFound,        when a specified file is not found
   * @throws IOException,         when the file cannot be opened for reading or some error with the
   *                              file
   * @throws DataFormatException, when there is an error because the file does not have the correct
   *                              format
   */
  public static void main(String[] args)
      throws FileNotFoundException, IOException, DataFormatException {
    // TODO Auto-generated method stub
    System.out.println("PASSED: " + readDataTest());
  }

  /**
   * Tests the MovieDataReader class and its method readDataSet()
   * 
   * @return returns true if the code works properly, false otherwise
   * @throws FileNotFound,        when a specified file is not found
   * @throws IOException,         when the file cannot be opened for reading or some error with the
   *                              file
   * @throws DataFormatException, when there is an error because the file does not have the correct
   *                              format
   */
  public static boolean readDataTest()
      throws FileNotFoundException, IOException, DataFormatException {
    // declaring and initializing a MovieDataReader object, BufferedReader, and a List of
    // MovieInterface objects
    MovieDataReader mdr = new MovieDataReader();
    BufferedReader br = new BufferedReader(new FileReader("movies.csv"));
    List<MovieInterface> test = mdr.readDataSet(br);

    // tests if the title of the first movie in the list is correct, returns false if it isnt
    if (!test.get(1).getTitle().equals("La battaglia di Long Tan")) {
       //System.out.print("1");
      return false;
    }
    // tests if the year the movie was made in is correct when returned, returns false if it isnt
    if (test.get(1).getYear() != 2019) {
      // System.out.print("2");
      return false;

    }
    // tests if the genres of the movie in the list are returned correct, returns false if it isnt
    if (!test.get(1).getGenres().toString().equals("[Action, Drama, War]")) {
       //System.out.print("3");
      return false;
    }

  


    return true;
  }
}
