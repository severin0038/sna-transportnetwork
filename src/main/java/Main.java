import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws IOException, URISyntaxException, ParseException {
        Application application = new Application();
        application.sbbDataSetToSnaGraph();

    }
}
