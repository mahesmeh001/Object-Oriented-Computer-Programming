import java.io.IOException;
import java.util.List;
import java.io.Reader;
import java.util.zip.DataFormatException;

public interface FlightDataReaderInterface{
	public List<FlightInterface> readDataSet(Reader inputFileReader) throws IOException, DataFormatException;
}

