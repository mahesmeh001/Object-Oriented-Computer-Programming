// --== CS400 File Header Information ==--
// Name: Andrew Le
// Email: ale24@wisc.edu
// Team: red
// Role: Backend Developer
// TA: Karen Chen
// Lecturer: Gary Dahl

import java.io.*;
import java.util.List;
import java.util.Scanner;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;

public class DataWranglerTests {
  
  public static void main(String[] args) {
    testVertexCount();
    testEdgeCount();
    testgetVertices();
    testgetEdges();
    testURLDecoder();
    testisEmpty();
  }
  /**
   * Tests to see if the vertex count returned is correct.
   * @Return true if it is, false otherwise.
   */
  @Test
  public static boolean testVertexCount() {
    
    try {
      Reader br = (new FileReader("links.tsv"));
      WebsiteDataReader test = new WebsiteDataReader();
      CS400Graph<String> graph = test.createGraph(br);
      int count = graph.getVertexCount();
      if(count != 4604) {
        Assert.fail("Test for vertex count fails");
        return false;
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    System.out.println("Test for vertex count passed.");
    
    return true;
    
  }
  /**
   * Tests to see if the edge count returned is correct.
   * @Return true if it is, false otherwise.
   */
  @Test
  public static boolean testEdgeCount() {
    try {
      Reader br = (new FileReader("links.tsv"));
      WebsiteDataReader test = new WebsiteDataReader();
      CS400Graph<String> graph = test.createGraph(br);
      int count = graph.getEdgeCount();
      if(count != 119882) {
        Assert.fail("Test for edge count fails");
        return false;
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    System.out.println("Test for edge count passed.");
    return true;
  }
  /**
   * Tests to see if some vertices are in the graph
   * @Return true if they are, false otherwise.
   */
  @Test
  public static boolean testgetVertices() {
    try {
      Reader br = (new FileReader("links.tsv"));
      WebsiteDataReader test = new WebsiteDataReader();
      CS400Graph<String> graph = test.createGraph(br);
      if(!(graph.containsVertex("England") || (graph.containsVertex("Austria")) || 
          (graph.containsVertex("Belgium")))) {
        Assert.fail("Test to see if England, Austria, and Belgium were vertices failed.");
        return false;
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    System.out.println("Test for Vertices check passed.");
    return true;
  }
  /**
   * Tests to see if some edges are in the graph
   * @Return true if they are, false otherwise.
   */
  @Test
  public static boolean testgetEdges() {
    try {
      Reader br = (new FileReader("links.tsv"));
      WebsiteDataReader test = new WebsiteDataReader();
      CS400Graph<String> graph = test.createGraph(br);
      if(!(graph.containsEdge("€2_commemorative_coins", "Belgium"))) {
        Assert.fail("Test to see if England, Austria, and Belgium were vertices failed.");
        return false;
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    System.out.println("Test for Edges check passed.");
    return true;
  }
  /**
   * Tests to see if the URL decoder works
   * @Return true if it does, false otherwise.
   */
  public static boolean testURLDecoder() {
    try {
      Reader br = (new FileReader("links.tsv"));
      WebsiteDataReader test = new WebsiteDataReader();
      CS400Graph<String> graph = test.createGraph(br);
      if(!(graph.containsVertex("€2_commemorative_coins") ||
          graph.containsVertex("Áedán_mac_Gabráin"))) {
        Assert.fail("Test to see if URL Decoded properly and stored as vertex failed.");
        return false;
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("Test for URL Decoder passed.");
    return false;
  }
  /**
   * Tests to see if isEmpty() works
   * @Return true if it does, false otherwise
   */
  public static boolean testisEmpty() {
    try {
      Reader br = (new FileReader("links.tsv"));
      WebsiteDataReader test = new WebsiteDataReader();
      CS400Graph<String> graph = test.createGraph(br);
      if(graph.isEmpty()) {
        Assert.fail("Test to see if isEmpty failed.");
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("Test for isEmpty passed.");
    return true;
  }
  
}
