// --== CS400 File Header Information ==--
// Name: Henry Li
// Email: hli779@wisc.edu
// Team: Red
// Role: Frontend
// TA: Keren Chen
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.util.List;

public interface BackendInterface {
    public void setStartWebsite(String input);
    public void setEndWebsite(String input);
    public void addNode(String node, List<String> linkedSites);
    public List<String> getAllWebsites();
    public String getStartWebsite();
    public String getEndWebsite();
    public int getShortestPath();
    public List<String> getShortestPathNodes();
}
