// --== CS400 File Header Information ==--
// Name: Andrew Le
// Email: ale24@wisc.edu
// Team: red
// Role: Backend Developer
// TA: Karen Chen
// Lecturer: Gary Dahl

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class WebsiteDataReader implements WebsiteDataReaderInterface {
    // creates the graph
    public CS400Graph<String> createGraph(Reader r) throws IOException {
        CS400Graph<String> graph = new CS400Graph<String>();
        int data;
        StringBuffer web = new StringBuffer();
        String firstString = "";
        String secondString = "";

        // reads the dataset
        while ((data = r.read()) != -1) {
            char character = (char) data;

            //determines what vertex is used
            if (character == '\t') {
                firstString =
                    java.net.URLDecoder.decode(web.toString(), StandardCharsets.UTF_8.name());
                if (!graph.vertices.contains(firstString)) {
                    graph.insertVertex(firstString);
                    web.setLength(0);
                }

            } else if (character == '\n') {
                secondString =
                    java.net.URLDecoder.decode(web.toString(), StandardCharsets.UTF_8.name());
                if (!graph.vertices.contains(secondString)) {
                    graph.insertVertex(secondString);
                    if (!firstString.equals("") && !secondString.equals("")) {
                        graph.insertEdge(firstString, secondString, 1);
                    }
                    web.setLength(0);
                }
            } else if (character == '\r') {
                // Do nothing
            } else {
                web.append(character);
            }
        }

        return graph;
    }

}
