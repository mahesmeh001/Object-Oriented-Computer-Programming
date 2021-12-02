import java.util.List;

public interface BackendInterface {

    public void setStart(String start);
    public void setDestination(String destination);
    public void setMinPrice(double price);
    public void setMaxPrice(double price);
    public void setMinDistance(double distance);
    public void setMaxDistance(double distance);
    public void addDate(String date);
    public double getMinPrice();
    public double getMaxPrice();
    public double getMinDistance();
    public double getMaxDistance();
    public String getStart();
    public String getDestination();
    public List<FlightInterface> getAllFlights();
    public List<String> getDates();


}
