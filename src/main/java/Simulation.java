import entity.Network;
import entity.Packet;
import entity.Port;
import entity.routing.RipAlgorithm;
import entity.Router;
import generator.*;

import java.io.IOException;
import java.util.*;

public class Simulation {

    //5 mins
    private static final long DEFAULT_TTL = 300000;
    private static final long DEFAULT_STOP = 300;
    private static IRandomGenerator generator;
    private static List<Router> routers;

    public static void main(String[] args) throws IOException {
        Network network = new Network();
        routers = new ArrayList<>();
        network.getNodes().forEach(node -> routers.add((Router) node));
        List<Router> copy = new ArrayList<>(routers);
        int stop = 0;
        boolean active = true;
        while (active) {
            if (stop > DEFAULT_STOP){
                active = false;
            }
            Packet packet = new Packet("Message", DEFAULT_TTL);
            Collections.shuffle(copy);
            network.route(packet, copy.get(0), copy.get(1));
            stop++;
        }
    }

    private static Router getRouterById(int id){
        return routers.stream().filter(router -> router.getId().equals(id)).findAny().get();
    }

}
