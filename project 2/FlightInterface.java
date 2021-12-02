import java.util.List;
public interface FlightInterface extends Comparable<FlightInterface> {
public int getFlightNumber();
public String getAirline();
public double getPricePerMile();
public List<Double> getStartCoordinates();
public List<Double> getDestinationCoordinates();
public int getSize(); //number of people in the flight
public int getCapacity();
public String getStart(); //starting location
public String getDestination();
public String getDate();
}
