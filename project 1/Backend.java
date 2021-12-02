// --== CS400 File Header Information ==--
// Name: Mehul Maheshwari
// Email: mmaheshwari2@wisc.edu
// Team: Red
// Role: Backend
// TA: Keren
// Lecturer: Mr. Florian
// Notes to Grader: <optional extra notes>

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;


/*
This class works with data from movie interfaces that the front end can use.
 */
public class Backend implements BackendInterface {
    //hashtable for ratings
    private HashTableMap<Integer, List<MovieInterface>> ratingsTable = new HashTableMap<Integer, List<MovieInterface>>();
    //list of user selected genres
    private List<String> selectedGenreList = new ArrayList<String>();
    //list of full movie interfaces
    private List<MovieInterface> movies;
    //list of ratings the user selected
    private List<String> selectedRatingsList = new ArrayList<String>();
    //all of the genres from data wrangler
    private List<String> allGenreList = new ArrayList<String>();
    //use allGenresList and ratingsList to find top 3 candidates
    //have to return a MovieInterface instead of the genres and ratings, which is what is currently happening.
    //list of filtered movies
    private List<MovieInterface> filterMovieList = new ArrayList<MovieInterface>();

    //value has to be list of movies that have the same genre
    private HashTableMap<String, List<MovieInterface>> genreTable = new HashTableMap<String, List<MovieInterface>>();

    /**
     * This constructor instantiates a backend with the command line arguments
     * passed to the app when started by the user. The arguments are expected
     * to contain the path to the data file that the backend will read in.
     *
     * @param args list of command line arguments passed to front end
     */
    public Backend(String[] args) throws IOException, DataFormatException {

        File newfile = new File("AGoodNameForAFile");

        FileWriter writer = new FileWriter("AGoodNameForAFile");
        for(int i = 0; i<13;i++){
            if(i==12){
                writer.write(args[i]+"\n");
            }else{
                writer.write(args[i]+",");
            }
        }

        BufferedReader reader = new BufferedReader(new FileReader("AGoodNameForAFile"));

        new Backend(reader);
    }

    /**
     * A constructor that will create a backend from data that can be read in
     * with a reader object.
     *
     * @param file A reader that contains the data in CSV format.
     */
    public Backend(Reader file) throws IOException, DataFormatException {
        //uses data wrangler's code
        MovieDataReader reader = new MovieDataReader();
        //reads in file
        BufferedReader reader1 = new BufferedReader(file);
        movies = reader.readDataSet(reader1);

        //assign data to arrays
        //call functions which will put data into hashtable
        createGenreTable();
        createRatingsTable();
        //creates a filtered list of selected movies
        createFilteredList();
    }

    /*
This method creates the genre hashtable.
It stores genres as the key and a list of the movies which are associated with that genre as the value
*/
    private void createGenreTable() {
        //create list of all genres
        for (int d = 0; d < movies.size(); d++) {
            for (int s = 0; s < movies.get(d).getGenres().size(); s++) {
                if (!allGenreList.contains(movies.get(d).getGenres().get(s))) {
                    allGenreList.add(movies.get(d).getGenres().get(s));
                }
            }
        }
        //uses list of all genres to hash
        for (int i = 0; i < getAllGenres().size(); i++) {
            //make list of specific genre's
            List<MovieInterface> ofGenres = new ArrayList<MovieInterface>();
            for (int e = 0; e < movies.size(); e++) {
                for (int f = 0; f < movies.get(e).getGenres().size(); f++) {
                    if (movies.get(e).getGenres().get(f).equals(getAllGenres().get(i))) {
                        //adds all movies of a specific genre to the list
                        //avoids duplicates
                        if(!ofGenres.contains(movies.get(e))){
                            ofGenres.add(movies.get(e));
                        }
                    }
                }
            }
            //for each of these genres, put into hashtable
            genreTable.put(getAllGenres().get(i), ofGenres);
        }
    }

    /*
This method creates the ratings hashtable
It stores ratings as the key and a list of the movies which are associated with that genre as the value
*/
    private void createRatingsTable() {

        for (int i = 0; i < movies.size(); i++) {

            //turn into an int, lose the decimal
            int intRating;
            //make list of ratings
            List<MovieInterface> ofRatingsList = new ArrayList<MovieInterface>();

            //add to list
            //ofRatingsList.add(movies.get(i));
            //add others that are equal to list
            for (int e = 0; e < movies.size(); e++) {
                if (movies.get(i).getAvgVote() != null) {
                    intRating = movies.get(i).getAvgVote().intValue();
                    if (movies.get(e).getAvgVote() != null)
                    if (intRating == (movies.get(e).getAvgVote().intValue())) {
                        //avoids duplicates
                        if (!ofRatingsList.contains(movies.get(e))) {
                            ofRatingsList.add(movies.get(e));
                        }
                    }
                }
            }
            //put list into hashtable
            //conversion into int because when user selects 6, 6.9 rating should be accounted for as well
            //which means the decimal is detrimental

            if (movies.get(i).getAvgVote() != null)
            ratingsTable.put(movies.get(i).getAvgVote().intValue(), ofRatingsList);
        }

    }


    /**
     * Method to add a genre that the user selected. It appends the genre to the selectedGenreList
     */
    @Override
    public void addGenre(String genre) {
        selectedGenreList.add(genre);
    }

    /**
     * Method to add a AvgRating that the user selected. It appends the genre to the selectedRatingsList
     */
    @Override
    public void addAvgRating(String rating) {
        selectedRatingsList.add(rating);
    }

    /**
     * Method to remove a genre that the user selected. It will output but not remove the
     * genre passed to it from the backend object.
     */
    @Override
    public void removeGenre(String genre) {
        selectedGenreList.remove(genre);
    }

    /**
     * Method to remove a rating that the user selected. It will output but not remove the
     * genre passed to it from the backend object.
     */
    @Override
    public void removeAvgRating(String rating) {
        selectedRatingsList.remove(rating);
    }

    /**
     * Return the genres added to this backend object. The dummy implementation always returns
     * the same list of genres for testing.
     */
    @Override
    public List<String> getGenres() {
        return selectedGenreList;
    }

    /**
     * Return the ratings added to this backend object.
     * gets the average ratings of the selected set
     */
    @Override
    public List<String> getAvgRatings() {
        return selectedRatingsList;
    }

    /*
    This method helps you create a filtered list of the selected
     */
    private List<MovieInterface> createFilteredList() {
        
        List<MovieInterface> tempSelectedGenreList = new ArrayList<MovieInterface>();

        //get all in genre
        for (int i = 0; i < selectedGenreList.size(); i++) {
            List<MovieInterface> temp = new ArrayList<MovieInterface>();
            temp = genreTable.get(selectedGenreList.get(i));
            for (int e = 0; e < temp.size(); e++) {
                if (!tempSelectedGenreList.contains(temp.get(e))) {
                    tempSelectedGenreList.add(temp.get(e));
                }
            }
        }

        /*if(selectedGenreList.size()==1) {

        } else {
            //get list of List<Movieinterfaces> per each genre
            List<List<MovieInterface>> temp = new ArrayList<List<MovieInterface>>();
            for(int i = 0; i<selectedGenreList.size();i++){
                if(!temp.contains(genreTable.get(selectedGenreList.get(i))))
                    temp.add(genreTable.get(selectedGenreList.get(i)));
            }

            //compare which elements come up multiple times
            //iterate through List<List<Movie>>

            for(int s = 0; s<temp.size();s++){
                for(int s1 = 1; s1<temp.size();s1++){
                    for(int s2 = 0;s2<temp.get(s1).size();s2++){
                        if(temp.get(s).contains(temp.get(s1).get(s2))){
                            if(!tempSelectedGenreList.isEmpty()){
                                tempSelectedGenreList.add(temp.get(s1).get(s2));
                            }
                            else{
                                tempSelectedGenreList.add(temp.get(s1).get(s2));
                            }
                        }
                    }

                }
            }

        }*/

        List<MovieInterface> tempSelectedRatingsList = new ArrayList<MovieInterface>();
        //get all in the same rating
        for (int d = 0; d < selectedRatingsList.size(); d++) {
            List<MovieInterface> temp = new ArrayList<MovieInterface>();
            temp = ratingsTable.get(Integer.parseInt((selectedRatingsList.get(d))));
            for (int f = 0; f < temp.size(); f++) {
                if (!tempSelectedRatingsList.contains(temp.get(f))) {
                    tempSelectedRatingsList.add(temp.get(f));
                }
            }
        }


        //cross compare so that if its in both, its id
        if (!tempSelectedGenreList.isEmpty() && !tempSelectedRatingsList.isEmpty()) {
            for (int c = 0; c < tempSelectedGenreList.size(); c++) {
                for (int s = 0; s < tempSelectedRatingsList.size(); s++) {
                    if (tempSelectedGenreList.get(c).equals(tempSelectedRatingsList.get(s))) {
                        //doesn't allow duplicates
                        if(!filterMovieList.contains(tempSelectedGenreList.get(c))){
                            filterMovieList.add(tempSelectedGenreList.get(c));
                        }
                    }
                }
            }
        } else if (tempSelectedGenreList.isEmpty()&&!tempSelectedRatingsList.isEmpty()) {
            filterMovieList = tempSelectedRatingsList;
        } else if (!tempSelectedGenreList.isEmpty()) {
            filterMovieList = tempSelectedGenreList;
        } else {
            return filterMovieList;
        }


        //sort in descending order by rating
        int n = filterMovieList.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (filterMovieList.get(j).getAvgVote() < filterMovieList.get(j + 1).getAvgVote()) {
                    // swap arr[j+1] and arr[j]
                    MovieInterface temp = filterMovieList.get(j);
                    filterMovieList.set(j, filterMovieList.get(j + 1));
                    filterMovieList.set(j + 1, temp);
                }

        return filterMovieList;
    }

    /**
     * Returns the number of movies.
     * gets the number of movies which match the requirements of the selected set
     */
    @Override
    public int getNumberOfMovies() {
        createFilteredList();
        if(filterMovieList.isEmpty()){
            return movies.size();
        } else {
        return filterMovieList.size();
        }
    }

    /**
     * Returns all genres in the dataset.
     */
    @Override
    public List<String> getAllGenres() {
        return allGenreList;
    }

    /**
     * Returns the movies that match the ratings and genres.
     * Make sure it is in decreasing order
     */
    @Override
    public List<MovieInterface> getThreeMovies(int startingIndex) {
        createFilteredList();
        List<MovieInterface> Output = new ArrayList<MovieInterface>();
        //from starting index, get three movies
        if (startingIndex + 2 < filterMovieList.size()) {
            //do 3
            Output.add(filterMovieList.get(startingIndex));
            Output.add(filterMovieList.get(startingIndex + 1));
            Output.add(filterMovieList.get(startingIndex + 2));
        } else if (startingIndex + 1 < filterMovieList.size()) {
            //do 2
            Output.add(filterMovieList.get(startingIndex));
            Output.add(filterMovieList.get(startingIndex + 1));
        } else if (startingIndex < filterMovieList.size()) {
            //do 1
            Output.add(filterMovieList.get(startingIndex));
        } else {
            //if the starting index is out of range
        }

        return Output;
    }


}
