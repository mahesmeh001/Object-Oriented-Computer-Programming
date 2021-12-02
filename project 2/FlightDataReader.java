// --== CS400 File Header Information ==--
// Name: Mehul Maheshwari
// Email: mmaheshwari2@wisc.edu
// Team: RED
// Group: KC
// TA: Keren Chen
// Lecturer:Gary Dahl
// Notes to Grader: N/A


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class FlightDataReader{




        public List<FlightInterface> readDataSet(Scanner br) throws DataFormatException {
                //will hold all the flight interfaces
                List<FlightInterface> Flights = new ArrayList<>();

                //since the file is comma separated, we will parse in respect to commas
                br.useDelimiter(",");

                //will only execute when there is a next line
                //stores data in an Arraylist of strings
                ArrayList<String> totalLines = new ArrayList<>();
                while(br.hasNext()) {
                        totalLines.add(br.nextLine());
                }

                for (String totalLine : totalLines) {
                        String[] temp = totalLine.split(",");
                        //check to make sure everything was formatted correctly
                        if (temp.length != 12) {
                                throw new DataFormatException("wrong format for string line");
                        }

                        Flights.add(new FlightInterface() {

                                @Override
                                public int getFlightNumber() {
                                        return Integer.parseInt(temp[0]);
                                }

                                @Override
                                public String getAirline() {
                                        return temp[1];
                                }

                                @Override
                                public double getPricePerMile() {
                                        return Double.parseDouble(temp[2]);
                                }

                                @Override
                                public List<Double> getStartCoordinates() {
                                        List<Double> coordinates = new ArrayList<>();
                                        coordinates.add(Double.parseDouble(temp[3]));
                                        coordinates.add(Double.parseDouble(temp[4]));
                                        return coordinates;
                                }

                                @Override
                                public List<Double> getDestinationCoordinates() {
                                        List<Double> coordinates = new ArrayList<>();
                                        coordinates.add(Double.parseDouble(temp[5]));
                                        coordinates.add(Double.parseDouble(temp[6]));
                                        return coordinates;
                                }

                                @Override
                                public int getSize() {
                                        return Integer.parseInt(temp[7]);
                                }

                                @Override
                                public int getCapacity() {
                                        return Integer.parseInt(temp[8]);
                                }

                                @Override
                                public String getStart() {
                                        return temp[9];
                                }

                                @Override
                                public String getDestination() {
                                        return temp[10];
                                }

                                @Override
                                public String getDate() {
                                        return temp[11];
                                }

                                /*
                                Compares via flight numbers.

                                If this flight number is greater that what it is
                                being compared against, the method will return 1.

                                If the flight numbers are equal, the method will
                                return 0.

                                If the other flight has a greater flight number,
                                the method will return -1.
                                 */
                                @Override
                                public int compareTo(FlightInterface o) {
                                        return Integer.compare(this.getFlightNumber(), o.getFlightNumber());
                                }
                        });
                }//end for loop


                return Flights;
        }

}
