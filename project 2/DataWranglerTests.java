// --== CS400 File Header Information ==--
// Name: Mehul Maheshwari
// Email: mmaheshwari2@wisc.edu
// Team: RED
// Group: KC
// TA: Keren Chen
// Lecturer:Gary Dahl
// Notes to Grader: N/A

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * A Junit Test class for the data wrangler
 */
public class DataWranglerTests {

    public FlightDataReader mdr = null;
    public Scanner br = null;
    public List<FlightInterface> flights = null;
    public FlightInterface testPlane;

    /**
     * Method which initializes a list of flight interfaces to use for testing
     * This method serves as its own test to make sure that the .readDataSet() method does not return an invalid type
     */
    @Before
    public void initialize() {
        // declaring and initializing a MovieDataReader object, BufferedReader, and a List of
        // MovieInterface objects
        try {
            FlightDataReader mdr = new FlightDataReader();
            Scanner br = new Scanner(new FileReader("flights.csv"));
            flights = mdr.readDataSet(br);
        } catch (Exception e) {
            {
                e.printStackTrace();
                Assert.fail("Did not read the file properly");
            }

        }

    }

    /**
     * This method checks whether the flight numbers were read correctly
     * the flight numbers are the first input of data for the flights
     */
    @Test
    public void testFlightNumber() {
        //checks the first flights number
        if (!(flights.get(0).getFlightNumber() == 1 /*first flight number must be changed */))
            Assert.fail("The first flights ID number was wrong");

        //checks the last flights number
        if (!(flights.get(flights.size() - 1).getFlightNumber() == 15 /*change to last flights number*/))
            Assert.fail("The last flights ID number was wrong");
    }

    /**
     * This method checks whether the airline's of the flights were read correctly
     */
    @Test
    public void testAirline() {
        //checks the first flights airline
        if (!(flights.get(0).getAirline().equals("American")  /*first flight airline must be changed */))
            Assert.fail("The first flights airline was wrong");

        //checks the last flights airline
        if (!(flights.get(flights.size() - 1).getAirline().equals("JetBlue") /*change to last flights airline*/))
            Assert.fail("The last flights airline was wrong");
    }

    /**
     * This method checks whether the flights Price per Mile was inputted correctly
     */
    @Test
    public void testPricePerMile() {
        //checks the first flights Price per Mile
        if (!(flights.get(0).getPricePerMile() == 0.3 /*first flight Price per Mile must be changed */))
            Assert.fail("The first flights Price per Mile was wrong");

        //checks the last flights Price per Mile
        if (!(flights.get(flights.size() - 1).getPricePerMile() == 0.25 /*change to last flights Price per Mile*/))
            Assert.fail("The last flights Price per Mile was wrong");
    }

    /**
     * This method checks whether the flight numbers were read correctly
     * the flight times are the last input of data for the flights
     */
    @Test
    public void testTime() {
        //checks the first flights time
        if (!(flights.get(0).getDate().equals("3/25/21") /*first flight time must be changed */))
            Assert.fail("The first flights time was wrong");

        //checks the last flights time
        if (!(flights.get(flights.size() - 1).getDate().equals("3/28/21") /*change to last flights time*/))
            Assert.fail("The last flights time was wrong");
    }

}
