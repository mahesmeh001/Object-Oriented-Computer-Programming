// --== CS400 File Header Information ==--
// Name: Andrew Le
// Email: ale24@wisc.edu
// Team: RED
// Group: KC
// TA: Keren Chen
// Lecturer:Gary Dahl
// Notes to Grader: N/A

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.zip.DataFormatException;



public class FrontEndCode {
  private static Backend bE;
  private static BufferedReader fr;
  private static int index;
  private static boolean[] rselected = new boolean[11];
  private static boolean[] gselected = new boolean[19];
  private static String[] genres = {"","Western","Comedy","Thriller","History","Action","Drama","War","Musical","Adventure","Sci-Fi","Romance","Horror","Animation","Fantasy","Crime","Biography","Mystery","Family"};

  public FrontEndCode() throws IOException, DataFormatException {
    System.out.println("Welcome!");
    index = 0;
    fr = new BufferedReader(new FileReader("movies.csv"));
    bE = new Backend(fr);

    for (int i = 1; i < 10; i++) {
      bE.addAvgRating(ratingSelection(i));
    }
    for (int n = 0; n < 11; n++) {
      rselected[n] = true;
    }
  }
  
  public static void main(String[] args) throws IOException, DataFormatException {
    new FrontEndCode();
    baseMode();
    
  }

  public static void baseMode() {
    System.out.println("Top 3 Recommended Movies: ");
    
    try {
      if (bE.getGenres().size() != 0 && bE.getAvgRatings().size() != 0) {
        //      System.out.println("Hello");
        int temp = bE.getThreeMovies(index).size();
        switch (temp) {
          case 1:
            System.out.println(
                bE.getThreeMovies(index).get(0).getTitle());
            break;
          case 2:
            System.out.println(
                bE.getThreeMovies(index).get(0).getTitle() + ", " + bE.getThreeMovies(index).get(1).getTitle());
            break;
          case 3:
            System.out.println(
                bE.getThreeMovies(index).get(0).getTitle() + ", " + bE.getThreeMovies(index).get(1).getTitle() + ", " + bE.getThreeMovies(index).get(2).getTitle());
            break;
          case 0:
            throw new NoSuchElementException();
          default:
            throw new IllegalStateException("Unexpected value: " + temp);
        }
        System.out.println("There are " + bE.getNumberOfMovies() + " result(s).");
      }
    }
    catch (NoSuchElementException e) {
      System.out.println("No Results");
    }
    System.out.println("Enter g to enter genre selection mode, r to enter rating selection mode, a number to scroll through the movie list, and x to exit.");
    Scanner menu = new Scanner(System.in);
    String selection = menu.next();
    if (selection.equals("x")) {
      System.out.println("Program exiting.");
      System.exit(0);
    }
    else if (isInteger(selection)) {
      index = Integer.parseInt(selection);
      baseMode();
    }
    else if (selection.equals("g")) {
        Scanner genre = new Scanner(System.in);
        String choice = "";
        while(!choice.equals("x")) {
          System.out.println("Genres: ");
          for (int i = 1; i < 19; i++) {
            if (gselected[i])
              System.out.println(i+". "+genres[i] + " " + "[Selected]");
            else {
              System.out.println(i+". "+genres[i]);
            }
          }
          System.out.println("Enter a number to pick a genre to add, or x to exit");
          choice = genre.nextLine();
          if (choice.equals("1")) {
            if (!gselected[1]) {
              gselected[1] = true;
              bE.addGenre(genreSelection(1));
            }
            else {
              gselected[1] = false;
              bE.removeGenre(genreSelection(1));
            }
          } else if (choice.equals("2")) {
            if (!gselected[2]) {
              gselected[2] = true;
              bE.addGenre(genreSelection(2));
            }
            else {
              gselected[2] = false;
              bE.removeGenre(genreSelection(2));
            }
          } else if (choice.equals("3")) {
            if (!gselected[3]) {
              gselected[3] = true;
              bE.addGenre(genreSelection(3));
            }
            else {
              gselected[3] = false;
              bE.removeGenre(genreSelection(3));
            }
          } else if (choice.equals("4")) {
            if (!gselected[4]) {
              gselected[4] = true;
              bE.addGenre(genreSelection(4));
            }
            else {
              gselected[4] = false;
              bE.removeGenre(genreSelection(4));
            }
          } else if (choice.equals("5")) {
            if (!gselected[5]) {
              gselected[5] = true;
              bE.addGenre(genreSelection(5));
            }
            else {
              gselected[5] = false;
              bE.removeGenre(genreSelection(5));
            }
          } else if (choice.equals("6")) {
            if (!gselected[6]) {
              gselected[6] = true;
              bE.addGenre(genreSelection(6));
            }
            else {
              gselected[6] = false;
              bE.removeGenre(genreSelection(6));
            }
          } else if (choice.equals("7")) {
            if (!gselected[7]) {
              gselected[7] = true;
              bE.addGenre(genreSelection(7));
            }
            else {
              gselected[7] = false;
              bE.removeGenre(genreSelection(7));
            }
          } else if (choice.equals("8")) {
            if (!gselected[8]) {
              gselected[8] = true;
              bE.addGenre(genreSelection(8));
            }
            else {
              gselected[8] = false;
              bE.removeGenre(genreSelection(8));
            }
          } else if (choice.equals("9")) {
            if (!gselected[9]) {
              gselected[9] = true;
              bE.addGenre(genreSelection(9));
            }
            else {
              gselected[9] = false;
              bE.removeGenre(genreSelection(9));
            }
          } else if (choice.equals("10")) {
            if (!gselected[10]) {
              gselected[10] = true;
              bE.addGenre(genreSelection(10));
            }
            else {
              gselected[10] = false;
              bE.removeGenre(genreSelection(10));
            }
          } else if (choice.equals("11")) {
            if (!gselected[11]) {
              gselected[11] = true;
              bE.addGenre(genreSelection(11));
            }
            else {
              gselected[11] = false;
              bE.removeGenre(genreSelection(11));
            }
          } else if (choice.equals("12")) {
            if (!gselected[12]) {
              gselected[12] = true;
              bE.addGenre(genreSelection(12));
            }
            else {
              gselected[12] = false;
              bE.removeGenre(genreSelection(12));
            }
          } else if (choice.equals("13")) {
            if (!gselected[13]) {
              gselected[13] = true;
              bE.addGenre(genreSelection(12));
            }
            else {
              gselected[13] = false;
              bE.removeGenre(genreSelection(13));
            }
          } else if (choice.equals("14")) {
            if (!gselected[14]) {
              gselected[14] = true;
              bE.addGenre(genreSelection(14));
            }
            else {
              gselected[14] = false;
              bE.removeGenre(genreSelection(14));
            }
          } else if (choice.equals("15")) {
            if (!gselected[15]) {
              gselected[15] = true;
              bE.addGenre(genreSelection(15));
            }
            else {
              gselected[15] = false;
              bE.removeGenre(genreSelection(15));
            }
          } else if (choice.equals("16")) {
            if (!gselected[16]) {
              gselected[16] = true;
              bE.addGenre(genreSelection(16));
            }
            else {
              gselected[16] = false;
              bE.removeGenre(genreSelection(16));
            }
          } else if (choice.equals("17")) {
            if (!gselected[17]) {
              gselected[17] = true;
              bE.addGenre(genreSelection(17));
            }
            else {
              gselected[17] = false;
              bE.removeGenre(genreSelection(17));
            }
          } else if (choice.equals("18")) {
            if (!gselected[18]) {
              gselected[18] = true;
              bE.addGenre(genreSelection(18));
            }
            else {
              gselected[18] = false;
              bE.removeGenre(genreSelection(18));
            }
          }
          else if (choice.equals("x")) {
            System.out.println("Exiting to base mode");
            index = 0;
            baseMode();
          }
          else {
            System.out.println("You pressed an invalid character.");
          }
        }

    } else if (selection.equals("r")) {
        Scanner rating = new Scanner(System.in);
        String choice = "";
        while (!choice.equals("x")) {
          System.out.println("Ratings: ");
          for (int i = 0; i < 11; i++) {
            if (rselected[i])
              System.out.println(i + " " + "[Selected]");
            else {
              System.out.println(i);
            }
          }
        System.out.println("Enter a number to pick a rating, or x to exit");
        choice = rating.nextLine();
        if (choice.equals("x")) {
          System.out.println("Exiting to base mode");
          index = 0;
          baseMode();
        }
        else if (choice.equals("0")) {
          if (!rselected[0]) {
            rselected[0] = true;
          }
          else {
            rselected[0] = false;
          }
        }else if (choice.equals("1")) {
          if (!rselected[1]) {
            rselected[1] = true;
            bE.addAvgRating(ratingSelection(1));
          }
          else {
            rselected[1] = false;
            bE.removeAvgRating(ratingSelection(1));
          }
        } else if (choice.equals("2")) {
          if (!rselected[2]) {
            rselected[2] = true;
            bE.addAvgRating(ratingSelection(2));
          }
          else {
            rselected[2] = false;
            bE.removeAvgRating(ratingSelection(2));
          }
        } else if (choice.equals("3")) {
          if (!rselected[3]) {
            rselected[3] = true;
            bE.addAvgRating(ratingSelection(3));
          }
          else {
            rselected[3] = false;
            bE.removeAvgRating(ratingSelection(3));
          }
        } else if (choice.equals("4")) {
          if (!rselected[4]) {
            rselected[4] = true;
            bE.addAvgRating(ratingSelection(4));
          }
          else {
            rselected[4] = false;
            bE.removeAvgRating(ratingSelection(4));
          }
        } else if (choice.equals("5")) {
          if (!rselected[5]) {
            rselected[5] = true;
            bE.addAvgRating(ratingSelection(5));
          }
          else {
            rselected[5] = false;
            bE.removeAvgRating(ratingSelection(5));
          }
        } else if (choice.equals("6")) {
          if (!rselected[6]) {
            rselected[6] = true;
            bE.addAvgRating(ratingSelection(6));
          }
          else {
            rselected[6] = false;
            bE.removeAvgRating(ratingSelection(6));
          }
        } else if (choice.equals("7")) {
          if (!rselected[7]) {
            rselected[7] = true;
            bE.addAvgRating(ratingSelection(7));
          }
          else {
            rselected[7] = false;
            bE.removeAvgRating(ratingSelection(7));
          }
        } else if (choice.equals("8")) {
          if (!rselected[8]) {
            rselected[8] = true;
            bE.addAvgRating(ratingSelection(8));
          }
          else {
            rselected[8] = false;
            bE.removeAvgRating(ratingSelection(8));
          }
        } else if (choice.equals("9")) {
          if (!rselected[9]) {
            rselected[9] = true;
            bE.addAvgRating(ratingSelection(9));
          }
          else {
            rselected[9] = false;
            bE.removeAvgRating(ratingSelection(9));
          }
        } else if (choice.equals("10")) {
          if (!rselected[10]) {
            rselected[10] = true;
          }
          else {
            rselected[10] = false;
          }
        }
        else {
          System.out.println("You pressed an invalid character.");
        }
      }
    }
  }
  private static boolean isInteger(String choice) {
    try {
      Integer.parseInt(choice);
      return true;
    }
    catch(Exception e) {
      return false;
    }
  }

  public static String genreSelection(int x) {
    if (x == 1) {
      return "Western";
    } else if (x == 2) {
      return "Comedy";
    } else if (x == 3) {
      return "Thriller";
    } else if (x == 4) {
      return "History";
    } else if (x == 5) {
      return "Action";
    } else if (x == 6) {
      return "Drama";
    } else if (x == 7) {
      return "War";
    } else if (x == 8) {
      return "Musical";
    } else if (x == 9) {
      return "Adventure";
    } else if (x == 10) {
      return "Sci-Fi";
    } else if (x == 11) {
      return "Romance";
    } else if (x == 12) {
      return "Horror";
    } else if (x == 13) {
      return "Animation";
    } else if (x == 14) {
      return "Fantasy";
    } else if (x == 15) {
      return "Crime";
    } else if (x == 16) {
      return "Biography";
    } else if (x == 17) {
      return "Mystery";
    } else if (x == 18) {
      return "Family";
    }
    else {
      System.out.println("You have pressed an incorrect character");
    }

    throw new NoSuchElementException();

  }
  
//  public static boolean selection() {
//    
//  }

  public static String ratingSelection(int x) {

    if (x == 1) {
      return "1";
    } else if (x == 2) {
      return "2";
    } else if (x == 3) {
      return "3";
    } else if (x == 4) {
      return "4";
    } else if (x == 5) {
      return "5";
    } else if (x == 6) {
      return "6";
    } else if (x == 7) {
      return "7";
    } else if (x == 8) {
      return "8";
    } else if (x == 9) {
      return "9";
    } else if (x == 10) {
      return "10";
    }
    else if (x == 0) {
      return "0";
    }
    else {
      System.out.println("You have pressed an incorrect character");
    }

    throw new NoSuchElementException();
  }
}
