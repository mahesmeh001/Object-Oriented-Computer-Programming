
// --== CS400 File Header Information ==--
// Name: Henry Li
// Email: hli779@wisc.edu
// Team: Red
// Role: Frontend
// TA: Keren Chen
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import org.junit.Test;
import org.junit.Assert.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;
import static org.junit.Assert.fail;

public class FrontEndDeveloperTests {
    @Test
    public void testSearchByPrice() throws IOException, DataFormatException {
        Frontend test = new Frontend();
        try {
            test.priceFilter(0, 1000);
        }
        catch (Exception e) {
            fail("The method has not been implemented");
        }
    }
    @Test
    public void testSearchByLocation() throws IOException, DataFormatException {
        Frontend test = new Frontend();
        try {
            test.locationFilter("Chicago", "Toronto");
        }
        catch (Exception e) {
            fail("The method has not been implemented");
        }
    }
    @Test
    public void testSearchByDate() throws IOException, DataFormatException {
        Frontend test = new Frontend();
        try {
            test.dateFilter("11/11/2021");
        }
        catch (Exception e) {
            fail("The method has not been implemented");
        }
    }
    @Test
    public void testDisplayDestinations() throws IOException, DataFormatException {
        Frontend test = new Frontend();
        try {
            test.locationFilter("Chicago", "Toronto");
        }
        catch (Exception e) {
            fail("The method has not been implemented");
        }
    }
    @Test
    public void testDisplayInfo() throws IOException, DataFormatException {
        Frontend test = new Frontend();
        try {
            test.priceFilter(0, 1000);
            test.locationFilter("Chicago", "Toronto");
        } catch (Exception e) {
            fail("The method has not been implemented");
        }
    }
}
