import java.util.List;
import java.io.IOException;
import java.io.Reader;
public interface WebsiteDataReaderInterface{
    public CS400Graph<String> createGraph(Reader r) throws IOException;
}
