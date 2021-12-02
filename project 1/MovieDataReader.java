// --== CS400 File Header Information ==--
// Name: Brian Blinder
// Email: bblinder@wisc.edu
// Team: RED
// Group: KC
// TA: Keren Chen
// Lecturer:Gary Dahl
// Notes to Grader: N/A

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.NotDirectoryException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;


public class MovieDataReader implements MovieDataReaderInterface {

  /**
   * Takes a .csv file with movies and their info and seperates their info a list useable for back
   * end code devlopment
   * 
   * @param inputFileReader a Reader that is to be used as a holder for the incoming .csv file
   * @return returns a List that contains MovieInterface classes (the movie and its information)
   * @throws FileNotFoundException,        when a specified file is not found
   * @throws IOException,         when the file cannot be opened for reading or some error with the
   *                              file
   * @throws DataFormatException, when there is an error because the file does not have the correct
   *                              format
   */
  public List<MovieInterface> readDataSet(Reader inputFileReader)
      throws FileNotFoundException, IOException, DataFormatException {

    // declaring and initializing a List of MovieInterfaces, String line, and a List of String
    // representing lines
    ArrayList<MovieInterface> movies = new ArrayList<MovieInterface>();
    String line = "";
    List<String> lines = new ArrayList<>();
    // keeps a while loop going until the line read is null (aka the end of the file)
    while ((line = ((BufferedReader) inputFileReader).readLine()) != null) // returns a Boolean
                                                                           // value
    {
      // checks if the line from the file is formatted correctly, throws a DataFormatException if it
      // isnt
      if (line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1).length != 13) {
        throw new DataFormatException();
      }
      // if format is correct, the line is added to the list of Strings
      lines.add(line);
    }
    // System.out.println(lines.get(0));

    // for loop to make MovieInterface classes using the String's held in the lines List
    for (int i = 0; i < lines.size(); i++) {
      String[] temp = lines.get(i).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
      movies.add(new MovieInterface() {

        @Override
        public String getTitle() {
          return temp[0];
        }

        @Override
        public Integer getYear() {
          return Integer.parseInt(temp[2]);
        }

        @Override
        public List<String> getGenres() {
          temp[3] = temp[3].replace('\"', ' ');
          temp[3] = temp[3].replaceAll("\\s", "");
          return Arrays.asList(temp[3].split(","));
        }

        @Override
        public String getDirector() {
          return temp[7];
        }

        @Override
        public String getDescription() {
          return temp[11];
        }

        @Override
        public Float getAvgVote() {
          try {
            return Float.parseFloat(temp[12]);
          }
          catch (NumberFormatException e) {
            return null;
          }
        }

        // an override
        // compares two MovieInterfaces (movies) by ratings;
        // returns 0 if the movies have equal ratings, 1 if the other movie has higher ratings, 1-
        // if this movie has higher ratings
        @Override
        public int compareTo(MovieInterface otherMovie) {
          if (this.getTitle().equals(otherMovie.getTitle())) {
            return 0;
            // sort by rating
          } else if (this.getAvgVote() < otherMovie.getAvgVote()) {
            return +1;
          } else {
            return -1;
          }
        }

      });
    }
    // TODO Auto-generated method stub
    return movies;
  }

}
