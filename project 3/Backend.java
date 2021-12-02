// --== CS400 File Header Information ==--
// Name: Henry Li
// Email: hli779@wisc.edu
// Team: Red
// Role: Frontend
// TA: Keren Chen
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;

public class Backend implements BackendInterface {

    public CS400Graph<String> Network;
    public CS400Graph.Vertex start;
    public CS400Graph.Vertex end;
    public CS400Graph.Vertex current;

    /**
     * Constructor that takes a file name, passes it to the WebsiteDataReader to retrieve usable
     * data to create a graph.
     *
     * @param file string of the file name that contains all the data.
     * @throws IOException if the file being read is invalid in any way.
     */
    public Backend(String file) throws IOException, DataFormatException {
        // uses data wrangler's code
        WebsiteDataReader reader = new WebsiteDataReader();
        // reads in file
        BufferedReader reader1 = new BufferedReader(new FileReader(file));
        // Places all data into list
        Network = new CS400Graph<>();
        Network = reader.createGraph(reader1);
    }

    /**
     * Constructor used for testing the implemented methods of the backend.
     *
     * @param graph example that is being taken in from tester class.
     */
    public Backend(CS400Graph<String> graph) {
        Network = graph;
    }

    /**
     * Method to set the current vertex depending on the input of the user.
     *
     * @param input string that is received by user and frontend.
     */
    public void setCurrent(String input) {
        current = Network.vertices.get(input);
    }

    /**
     * Getter method to return the website title of current.
     *
     * @return the string of the website title.
     */
    public String getCurrentWebsite() {
        return ((String) current.data);
    }

    /**
     * Determines the website titles linked to the current vertex and returns them in a list of
     * strings.
     *
     * @return list of strings of connected websites.
     */
    public List<String> displayCurrentLinked() {
        List<String> links = new ArrayList<>();
        for (int i = 0; i < current.edgesLeaving.size(); i++) {
            links.add((String) ((CS400Graph.Edge) current.edgesLeaving.get(i)).target.data);
        }
        return links;
    }

    /**
     * Determines if the game is over by checking if current vertex equals end vertex.
     *
     * @return a boolean depending on if game is over or not.
     */
    public boolean gameOver() {
        return current.equals(end);
    }

    /**
     * Method that checks if a path exists depending on the start and end vertices.
     *
     * @return true if path exists and false if path does not exist.
     */
    public boolean doesPathExist() {
        if (start == null || end == null)
            return false;
        try {
            Network.dijkstrasShortestPath((String) start.data, (String) end.data);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Determines if a user inputted string exists in the graph and returns as a truth value.
     *
     * @param input string that user entered
     * @return true if string exists in the graph, false otherwise.
     */
    public boolean existsInGraph(String input) {
        for (CS400Graph.Vertex v : Network.vertices.values()) {
            if (v.data.equals(input))
                return true;
        }
        return false;
    }

    /**
     * Method that inserts a node into the network of graphs.
     *
     * @param node        string input of title of website.
     * @param linkedSites list of strings of websites that the node is connected to.
     */
    @Override public void addNode(String node, List<String> linkedSites) {
        Network.insertVertex(node);
        for (int i = 0; i < linkedSites.size(); i++) {
            Network.insertEdge(node, linkedSites.get(i), 1);
        }
    }

    /**
     * Method that returns all websites in the graph into a list of strings.
     *
     * @return list of strings with all website titles.
     */
    @Override public List<String> getAllWebsites() {
        List<String> webNames = new ArrayList<>();
        for (CS400Graph.Vertex v : Network.vertices.values()) {
            webNames.add((String) v.data);
        }
        return webNames;
    }

    /**
     * Getter method that retrieves the title of the starting website.
     *
     * @return string of title of start vertex.
     */
    @Override public String getStartWebsite() {
        return ((String) start.data);
    }

    /**
     * Method that sets the starting website either 1. randomly if the input from user was 'r' or 2.
     * the choice of the user.
     *
     * @param input a string of a title of a website by the user and frontend.
     */
    @Override public void setStartWebsite(String input) {
        if (input.equalsIgnoreCase("r")) {
            Random r = new Random();
            List<String> available = new ArrayList<>();
            for (CS400Graph.Vertex v : Network.vertices.values())
                available.add((String) v.data);
            start = Network.vertices.get(available.get(r.nextInt(available.size())));
            current = start;
        } else {
            start = Network.vertices.get(input);
            current = start;
        }
    }

    /**
     * Getter method that retrieves the title of the end website.
     *
     * @return string of title of end vertex.
     */
    @Override public String getEndWebsite() {
        return ((String) end.data);
    }

    /**
     * Method that sets the ending website either 1. randomly if the input from user was 'r' or 2.
     * the choice of the user.
     *
     * @param input a string of a title of a website by the user and frontend.
     */
    @Override public void setEndWebsite(String input) {
        if (input.equalsIgnoreCase("r")) {
            Random r = new Random();
            List<String> available = new ArrayList<>();
            for (CS400Graph.Vertex v : Network.vertices.values())
                available.add((String) v.data);
            while (!doesPathExist() || end == null || end.equals(start)) {
                end = Network.vertices.get(available.get(r.nextInt(available.size())));
            }
        } else {
            end = Network.vertices.get(input);
        }
    }

    /**
     * Utilizes Dijkstras shortest path algorithm to return the cost of the shortest path from
     * start to end.
     *
     * @return an integer that is the shortest number of connections from start to end
     */
    @Override public int getShortestPath() {
        return Network.getPathCost((String) start.data, (String) end.data);
    }

    /**
     * Utilizes Dijkstras shortest path algorithm to get all individual nodes of the shortest path
     * from start to end.
     *
     * @return a list of strings of each node from the start to end vertices.
     */
    @Override public List<String> getShortestPathNodes() {
        return Network.shortestPath((String) start.data, (String) end.data);
    }
}
