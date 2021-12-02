// --== CS400 File Header Information ==--
// Name: Andrew Le
// Email: ale24@wisc.edu
// Team: red
// Role: Backend Developer
// TA: Karen Chen
// Lecturer: Gary Dahl


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class BackEndCode implements BackendInterface{
  
  
  private String start;
  private String end;
  private Double minPrice;
  private Double maxPrice;
  private Double minDistance;
  private Double maxDistance;
  private String newDate;
  private List<String> startingPoints;
  private List<Double> flightDistances;
  private List<String> airlines;
  private List<FlightInterface> openFlights;
  private List<FlightInterface> prices;
  private List<FlightInterface> departures;
  private List<FlightInterface> destinations;
  private List<String> dates;
  
  /**
   * Constructor for BackEnd part
   * @param file is the file that is to be taken in
   */
  public BackEndCode(String file) throws IOException, DataFormatException {
    // uses data wrangler's code
    FlightDataReader reader = new FlightDataReader();
    // reads in file
    Scanner reader1 = new Scanner(new FileReader(file));
    openFlights = reader.readDataSet(reader1);
  }
  /**
   * Takes in a flight number, airline, pricePerMile, departure, destination, and date, and adds said flight
   * with information into the new FlightInterface that is hidden
   */
  public void addFlights(int flightNumber, String Airline, Double pricePerMile,
      String departure, String destination, String date) {
    openFlights.add(new FlightInterface() {
      /**
      * 
      * gets and returns the flight number
      */
      @Override
      public int getFlightNumber() {
        // TODO Auto-generated method stub
        return flightNumber;
      }
      /**
      * 
      * gets and returns the Airline
      */
      @Override
      public String getAirline() {
        // TODO Auto-generated method stub
        return Airline;
      }
      /**
      * 
      * gets and returns the price per mile
      */
      @Override
      public double getPricePerMile() {
        // TODO Auto-generated method stub
        return pricePerMile;
      }
      /**
      * finds the destination coordinates and returns it in a type List<Double>
      * @return a List with doubles of destination coordinates based on the name
      */
      @Override
      public List<Double> getStartCoordinates() {
        // TODO Auto-generated method stub
        ArrayList<Double> start = new ArrayList<>();
        if(departure.equals("Chicago")) {
          start.add(41.8781);
          start.add(87.6923);
        }
        else if(departure.equals("Las Vegas")) {
          start.add(36.1699);
          start.add(115.1398);
        }
        else if(departure.equals("New York")) {
          start.add(40.7128);
          start.add(74.006);
        }
        else if(departure.equals("Houston")) {
          start.add(29.7604);
          start.add(95.3698);
        }
        else if(departure.equals("Atlanta")) {
          start.add(33.749);
          start.add(84.388);
        }
        else if(departure.equals("Toronto")) {
          start.add(43.6532);
          start.add(79.3832);
        }


        return start;
      }
      
      /**
      * finds the destination coordinates and returns it in a type List<Double>
      * @return a List with doubles of destination coordinates based on the name
      */
      @Override
      public List<Double> getDestinationCoordinates() {
        // TODO Auto-generated method stub
        ArrayList<Double> end = new ArrayList<>();
        if(destination.equals("Chicago")) {
          end.add(41.8781);
          end.add(87.6923);
        }
        else if(destination.equals("Las Vegas")) {
          end.add(36.1699);
          end.add(115.1398);
        }
        else if(destination.equals("New York")) {
          end.add(40.7128);
          end.add(74.006);
        }
        else if(destination.equals("Houston")) {
          end.add(29.7604);
          end.add(95.3698);
        }
        else if(destination.equals("Atlanta")) {
          end.add(33.749);
          end.add(84.388);
        }
        else if(destination.equals("Toronto")) {
          end.add(43.6532);
          end.add(79.3832);
        }
        return end;
      }

      @Override
      public int getSize() {
        // TODO Auto-generated method stub
        return 0;
      }
      /**
      * 
      * gets and returns the capacity
      */
      @Override
      public int getCapacity() {
        // TODO Auto-generated method stub
        return this.getCapacity();
      }
      /**
      * 
      * gets and returns the Starting point
      */
      @Override
      public String getStart() {
        // TODO Auto-generated method stub
        return departure;
      }
      /**
      * 
      * gets and returns the Destination
      */
      @Override
      public String getDestination() {
        // TODO Auto-generated method stub
        return destination;
      }
      /**
      * 
      * gets and returns the date
      */
      @Override
      public String getDate() {
        // TODO Auto-generated method stub
        return date;
      }
      /**
      * compares flight number to another object and returns a negative number if the flight number is less than that of the parameter, 
      * 0 if they are the same, and 1 if the flight number is greater than that of its parameter
      * 
      */
      @Override
      public int compareTo(FlightInterface o) {
        if(this.getFlightNumber() < ((FlightInterface) o).getFlightNumber()) {
          return -1;
        }
        else if(this.getFlightNumber() == ((FlightInterface) o).getFlightNumber()) {
          return 0;
        }
        return 1;
      }
      
    });
  }
      /**
      * 
      * Sets the starting point to whatever is inside the parameter
      */
  @Override
  public void setStart(String start) {
    // TODO Auto-generated method stub
    this.start = start;
    
  }
      /**
      * 
      * takes in 2 latitude and longitude values, and returns the resulting distance between the 2 points
      */
  public double getDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
    if((latitude1 == latitude2) && (longitude1 == longitude2)) {
      return 0;
    }
    else {
      double theta = longitude1 - longitude2;
      double distance = Math.sin(Math.toRadians(latitude1)) * Math.sin(Math.toRadians(latitude2)) + 
          Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) * Math.cos(Math.toRadians(theta));
      distance = Math.acos(distance);
      distance = Math.toDegrees(distance);
      distance = distance * 60 * 1.1515;
      return distance;
    }
    
  }
     /**
      * 
      * takes in the distance and pricePerMile and gets the price and returns it.
      */
  public double getPrice(double distance, double pricePerMile) {
    return distance * pricePerMile;
  }
  /**
      * 
      * sets the destination to whatever is in the parameter
      */
  @Override
  public void setDestination(String destination) {
    // TODO Auto-generated method stub
    this.end = destination;
    
    
  }
  /**
      * 
      * sets the minimum price to whatever is inside the parameter
      */
  @Override
  public void setMinPrice(double price) {
    // TODO Auto-generated method stub
    this.minPrice = price;
    
  }
    /**
      * 
      * sets the maximum price to whatever is inside the parameter
      */
  @Override
  public void setMaxPrice(double price) {
    // TODO Auto-generated method stub
    this.maxPrice = price;
  }
    /**
      * 
      * sets the minimum distance to whatever is inside the parameter
      */
  @Override
  public void setMinDistance(double distance) {
    // TODO Auto-generated method stub
    this.minDistance = distance;
    
  }
    /**
      * 
      * sets the maximum distance to whatever is inside the parameter
      */
  @Override
  public void setMaxDistance(double distance) {
    // TODO Auto-generated method stub
    this.maxDistance = distance;
  }
    /**
      * 
      * sets the newDate variable to whatever is inside the parameter
      */
  @Override
  public void addDate(String date) {
    // TODO Auto-generated method stub
    this.newDate = date;
  }
    /**
      * 
      * returns the minumum price
      */
  @Override
  public double getMinPrice() {
    // TODO Auto-generated method stub
    return this.minPrice;
  }
    /**
      * 
      * returns the maximum price
      */
  @Override
  public double getMaxPrice() {
    // TODO Auto-generated method stub
    return this.maxPrice;
  }
    /**
      * 
      * returns the minumum distance
      */
  @Override
  public double getMinDistance() {
    // TODO Auto-generated method stub
    return this.minDistance;
  }
    /**
      * 
      * returns the maximum distance
      */
  @Override
  public double getMaxDistance() {
    // TODO Auto-generated method stub
    return this.maxDistance;
  }
    /**
      * 
      * returns the start
      */
  @Override
  public String getStart() {
    // TODO Auto-generated method stub
    return this.start;
  }
    /**
      * 
      * returns the Destination
      */
  @Override
  public String getDestination() {
    // TODO Auto-generated method stub
    return this.end;
  }
    /**
      * search through red black trees and find matches
      * take in departure, destination, and date and return all flights that match this and filter by price
      * 
      */
  @Override
  public List<FlightInterface> getAllFlights() {
    // TODO Auto-generated method stub
    
    RedBlackTree<FlightInterface> departure = new RedBlackTree<>();
    ArrayList<FlightInterface> temp = new ArrayList<>();
    RedBlackTree<FlightInterface> destination = new RedBlackTree<>();
    ArrayList<FlightInterface> temp1 = new ArrayList<>();
    RedBlackTree<FlightInterface> date = new RedBlackTree<>();
    ArrayList<FlightInterface> temp2 = new ArrayList<>();
    ArrayList<FlightInterface> matches = new ArrayList<>();
    for(int i = 0; i < openFlights.size(); i++) {
      departure.insert(openFlights.get(i));
      destination.insert(openFlights.get(i));
      date.insert(openFlights.get(i));
    }
    Iterator Traverse = departure.iterator();
    while(Traverse.hasNext()) {
      FlightInterface next = (FlightInterface) Traverse.next();
     if ((next.getStart().equals(getStart()))) {
       temp.add(next);
     }
    }
    Iterator Traverse1 = destination.iterator();
    while (Traverse1.hasNext()) {
      FlightInterface next = (FlightInterface) Traverse1.next();
      if (next.getDestination().equals(getDestination())) {
        temp1.add(next);
      }
    }
    Iterator Traverse2 = date.iterator();
    while(Traverse2.hasNext()) {
      FlightInterface next = (FlightInterface) Traverse2.next();
      if (next.getDate().equals(getDate())) {
        temp2.add(next);
      }
    }

    for (int n = 0; n < temp1.size(); n++) {
      if (temp.contains(temp1.get(n))) {
        for (int j = 0; j < temp2.size(); j++) {
          if (temp1.get(n).equals(temp2.get(j))) {
            if (minPrice != null && maxPrice != null) {
              if (getPrice(getDistance(temp2.get(j).getStartCoordinates().get(0),
                  temp2.get(j).getStartCoordinates().get(1),temp2.get(j).getDestinationCoordinates().get(0),
                  temp2.get(j).getDestinationCoordinates().get(1)),temp2.get(j).getPricePerMile()) > minPrice
                  && getPrice(getDistance(temp2.get(j).getStartCoordinates().get(0),
                  temp2.get(j).getStartCoordinates().get(1),temp2.get(j).getDestinationCoordinates().get(0),
                  temp2.get(j).getDestinationCoordinates().get(1)),temp2.get(j).getPricePerMile()) < maxPrice)
              matches.add(temp2.get(j));
            }
            else {
              matches.add(temp2.get(j));
            }
          }
        }
      }
    }

    return matches;
  }
    /**
      * 
      * returns the variable in newDate
      */
  private String getDate() {
    return newDate;
  }
    /**
      * 
      * returns the list of dates
      */
  @Override
  public List<String> getDates() {
    // TODO Auto-generated method stub
    return this.dates;
  }

}
