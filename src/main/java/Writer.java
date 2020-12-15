import connection.GroupConnection;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {

    public void writeCSV(ArrayList<GroupConnection> connections, String filename, String header) throws IOException {

        FileWriter csvWriter = new FileWriter(filename);
        csvWriter.append(header + "\n");

        for(int i=0;i<connections.size();i++) {
            csvWriter.append(connections.get(i).toCSVString() + "\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }
}
