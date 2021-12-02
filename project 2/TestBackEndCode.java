import static org.junit.Assert.fail;
import java.io.IOException;
import java.util.zip.DataFormatException;

public class TestBackEndCode {

  public static void main(String[] args) {
    testGetDistance();
    testGetMinDistance();
    testGetMinPrice();
    testGetStart();
    testGetDestination();
  }
  
  public static boolean testGetDistance()  {
    BackEndCode test = null;
    try {
      test = new BackEndCode("flights.csv");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (DataFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    double distance = test.getDistance(51, 117, 45, 113);
    if(distance != 453.7449783720575) { 
      fail("getDistance() method failed");
    }
    System.out.println("Test 1 passed");
    return true;
    
  }
  
  public static boolean testGetMinDistance() {
    BackEndCode test = null;
    try {
      test = new BackEndCode("flights.csv");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (DataFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    test.setMinDistance(95.5);
    test.getMinDistance();
    if(test.getMinDistance() != 95.5) {
      fail("getMinDistance() method failed");
    }
    System.out.println("Test 2 passed");
    
    return true;
  }
  
  public static boolean testGetMinPrice() {
    BackEndCode test = null;
    try {
      test = new BackEndCode("flights.csv");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (DataFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    test.setMinPrice(55.5);
    test.getMinPrice();
    if(test.getMinPrice() != 55.5) {
      fail("getMinPrice() method failed");
    }
    System.out.println("Test 3 passed");
    
    return true;
  }
  
  public static boolean testGetStart() {
    BackEndCode test = null;
    try {
      test = new BackEndCode("flights.csv");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (DataFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    test.setStart("Atlanta");
    test.getStart();
    if(test.getStart() != "Atlanta") {
      fail("getStart() method failed");
    }
    System.out.println("Test 4 passed");
    
    return true;
  }
  
  public static boolean testGetDestination() {
    BackEndCode test = null;
    try {
      test = new BackEndCode("flights.csv");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (DataFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    test.setDestination("Las Vegas");
    test.getDestination();
    if(test.getDestination() != "Las Vegas") {
      fail("getStart() method failed");
    }
    System.out.println("Test 5 passed");
    
    return true;
  }

}
