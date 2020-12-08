import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws IOException, URISyntaxException {
        Application application = new Application();


        System.out.println("bahnhöfe: " + application.trainstations.toString());
        System.out.println("anzahl items: " + application.items.size());

        Map<String, List<Item>> itemsGroupedByLinienId = application.groupItemsByLinienId(application.items);
        application.groupedItemsToConnections(itemsGroupedByLinienId);

        System.out.println(application.connections.size());

    }
}
