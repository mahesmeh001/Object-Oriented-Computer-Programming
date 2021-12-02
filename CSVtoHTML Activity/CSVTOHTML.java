import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVTOHTML {

    public static void main(String[] args) {
        try {
            System.out.println("<html>");

            //css head
            System.out.println("<head><style>");
            System.out.println("table {\n" +
                    "    border-collapse: collapse;\n" +
                    "    margin: 40px 30px;\n" +
                    "    font-size: 1em;\n" +
                    "    font-family: sans-serif;\n" +
                    "    min-width: 400px;\n" +
                    "    box-shadow: 0 0 20px #00000040;\n" +
                    "}\n" +
                    "th, td {\n" +
                    "    padding: 12px 15px;\n" +
                    "}\n" +
                    "tr {\n" +
                    "    border-bottom: 1px solid #dddddd;\n" +
                    "}\n" +
                    "tr:nth-of-type(even) {\n" +
                    "    background-color: #f3f3f3;\n" +
                    "}\n" +
                    "tr:last-of-type {\n" +
                    "    border-bottom: none;\n" +
                    "}");
            System.out.println("</style></head>");

            System.out.println();
            System.out.println("<body><p>");

            //TODO: write Java code that generates an HTML table from your csv file"
            List<String[]> output = readAll(new File("data.csv"));

            System.out.println("<table>");
            for (int i = 0; i < output.size(); i++) {
                System.out.println("<tr>");
                for (int e = 0; e < output.get(i).length; e++) {
                    if (i == 0) {
                        System.out.print("<th> " + output.get(i)[e] + " </th>");
                    } else {
                        System.out.print("<td> " + output.get(i)[e] + " </td>");
                    }
                }
                System.out.println("</tr>");
            }
            System.out.println("</table>");

            System.out.println("</p></body></html>");
        } catch (Exception e) {
            System.out.println("<html><body><pre>");
            System.out.println("Ooops, something went wrong. There was an exception in the Java program:");
            e.printStackTrace(System.out);
            System.out.println("</pre></body></html>");
        }
    }

    public static List<String[]> readAll(File csvFile) throws Exception {
        List<String[]> output = new ArrayList<>();
        try (Scanner fin = new Scanner(csvFile)) {
            while (fin.hasNextLine()) {
                output.add(fin.nextLine().split(","));
            }
        }
        return output;
    }

}

