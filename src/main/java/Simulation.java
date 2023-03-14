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
    private static final long DEFAULT_STOP = 50;
    private static IRandomGenerator generator;
    private static List<Router> routers;

    public static void main(String[] args) throws IOException, InterruptedException {
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
            Thread.sleep((long) new ErlangRandomGenerator().generate());
            Packet packet = new Packet("Message", DEFAULT_TTL);
            Collections.shuffle(copy);
            network.route(packet, copy.get(0), copy.get(1));
            stop++;
        }
        Thread.sleep(3000);
        Map<Router, Integer> routerLoad = new LinkedHashMap<>();
        for (Router router:routers) {
            routerLoad.put(router,router.getLoad());
        }
        int maxLoad = 0;
        for (int load : routerLoad.values()) {
            if (load > maxLoad) {
                maxLoad = load;
            }
        }
        sortRouterLoadMap(routerLoad);
        for (Router router : routerLoad.keySet()) {
            int load = routerLoad.get(router);
            int barWidth = (int) Math.round((double) load / maxLoad * 20);
            System.out.printf("Router %-10s |%s%n", router.getId(), "*".repeat(barWidth));
        }
    }

    public static void sortRouterLoadMap(Map<Router, Integer> routerLoadMap) {
        List<Map.Entry<Router, Integer>> entryList = new ArrayList<>(routerLoadMap.entrySet());
        Comparator<Map.Entry<Router, Integer>> loadComparator =
                Map.Entry.<Router, Integer>comparingByValue().reversed();
        Collections.sort(entryList, loadComparator);
        LinkedHashMap<Router, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Router, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        routerLoadMap.clear();
        routerLoadMap.putAll(sortedMap);
    }

    private static Router getRouterById(int id){
        return routers.stream().filter(router -> router.getId().equals(id)).findAny().get();
    }

}
