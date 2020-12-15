import data.GroupConnection;
import data.TrainStation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Writer {

    void writeConnectionCSV(ArrayList<GroupConnection> connections, String filename, String header) throws IOException {

        FileWriter csvWriter = new FileWriter(filename);
        csvWriter.append(header).append("\n");

        for (GroupConnection connection : connections) {
            csvWriter.append(connection.toCSVString(";")).append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    void writeTrainstationCSV(ArrayList<TrainStation> trainStations, String filename, String header) throws IOException {

        FileWriter csvWriter = new FileWriter(filename);
        csvWriter.append(header).append("\n");

        for (TrainStation trainstation : trainStations) {
            csvWriter.append(trainstation.toCSVString(";")).append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }
}
