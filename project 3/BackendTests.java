// --== CS400 File Header Information ==--
// Name: Henry Li
// Email: hli779@wisc.edu
// Team: Red
// Role: Frontend
// TA: Keren Chen
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class BackendTests {
    CS400Graph<String> test;

    @BeforeEach
    public void setGraph() {
        test = new CS400Graph<>();
        test.insertVertex("A");
        test.insertVertex("B");
        test.insertVertex("C");
        test.insertVertex("D");
        test.insertVertex("E");
        test.insertVertex("F");
        test.insertEdge("A","B",1);
        test.insertEdge("B","D",1);
        test.insertEdge("A","C",1);
        test.insertEdge("C","E",1);
        test.insertEdge("C","D",1);
        test.insertEdge("D","E",1);
        test.insertEdge("B","F",1);
        test.insertEdge("E","F",1);
        test.insertEdge("F","A",1);
    }

    /**
     * Test to determine if the setStart and setEnd methods work as intended
     */
    @Test
    public void testSetRandom() {
        Backend bTest = new Backend(test);
        bTest.setStartWebsite("r");
        bTest.setEndWebsite("r");
        System.out.println(bTest.start.data);
        System.out.println(bTest.end.data);
        if (bTest.start == bTest.end) {
            fail();
        }
    }

    /**
     * Test to determine if the DisplayCurrentLinked method works as intended
     */
    @Test
    public void testDisplayCurrentLinked() {
        Backend bTest = new Backend(test);
        bTest.setStartWebsite("A");
        bTest.setEndWebsite("F");
        List<String> testlist = bTest.displayCurrentLinked();
        if (!testlist.get(0).equals("B") || !testlist.get(1).equals("C"))
            fail();
    }

    /**
     * Test to determine if the DoesPathExist method works as intended
     */
    @Test
    public void testDoesPathExist() {
        Backend bTest = new Backend(test);
            bTest.setStartWebsite("F");
            bTest.setEndWebsite("A");
            if (!bTest.doesPathExist())
            fail();
    }

    /**
     * Test to see if adding nodes works through the AddNode method
     */
    @Test
    public void testAddNode() {
        Backend bTest = new Backend(test);
        List<String> temp = new ArrayList<>();
        temp.add("E");
        bTest.addNode("T",temp);
        bTest.setStartWebsite("T");
        bTest.setEndWebsite("F");
        if (bTest.getShortestPath() != 2)
            fail();
    }

    /**
     * Test to see if GetShortestPath works as intended
     */
    @Test
    public void testGetShortestPath() {
        Backend bTest = new Backend(test);
        bTest.setStartWebsite("F");
        bTest.setEndWebsite("E");
        if (bTest.getShortestPath() != 3)
            fail();
    }
}
