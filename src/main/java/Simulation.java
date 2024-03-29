import entity.Destination;
import entity.Network;
import entity.Packet;
import entity.routing.AdvancedRipAlgorithm;
import entity.Router;
import generator.*;

import java.io.IOException;
import java.util.*;

public class Simulation {

    //5 mins
    private static final long DEFAULT_TTL = 300000;
    private static final long DEFAULT_STOP = 5;
    private static IRandomGenerator generator;
    private static List<Router> routers;
    private static Destination staticDestination;

    public static void main(String[] args) throws IOException, InterruptedException {
        //1 phase
        Network network = new Network(7);
        routers = new ArrayList<>();
        network.getNodes().forEach(node -> routers.add((Router) node));
        staticDestination = new Destination(getRouterById(0), getRouterById(4));
        Map<Integer, Destination> destinations = new LinkedHashMap<>();
        startFirstPhaseNetworkSimulation(network, destinations);
        Thread.sleep(3000);
        Map<Router, Integer> routerLoad = new LinkedHashMap<>();
        calculateAndPrintLoad(routerLoad);
        //2 phase
        network.getRoutingProtocols().add(new AdvancedRipAlgorithm(network.getRouterNodes(), routerLoad));
        startSecondPhaseNetworkSimulation(network, destinations);
        Thread.sleep(3000);
        calculateAndPrintLoad(routerLoad);
        //3 phase
        startThirdPhaseNetworkSimulation(network, destinations);
    }

    private static void startFirstPhaseNetworkSimulation(Network network, Map<Integer, Destination> destinations) throws InterruptedException {
        List<Router> copy = new ArrayList<>(routers);
        int stop = 0;
        boolean active = true;
        while (active) {
            if (stop > DEFAULT_STOP){
                active = false;
            }
            Thread.sleep((long) new ErlangRandomGenerator().generate());
            Packet packet = new Packet("01234567", DEFAULT_TTL);
//            Collections.shuffle(copy);
//            network.route(packet, copy.get(0), copy.get(1));
//            destinations.put(stop, new Destination(copy.get(0), copy.get(1)));
            network.route(packet, staticDestination.getSource(), staticDestination.getTarget());
            destinations.put(stop, new Destination(staticDestination.getSource(), staticDestination.getTarget()));
            stop++;
        }
    }

    private static void startSecondPhaseNetworkSimulation(Network network, Map<Integer, Destination> destinations) throws InterruptedException {
        int stop = 0;
        boolean active = true;
        while (active) {
            if (stop > DEFAULT_STOP){
                active = false;
            }
            Thread.sleep((long) new ErlangRandomGenerator().generate());
            Packet packet = new Packet("01234567", DEFAULT_TTL);
            Destination destination = destinations.get(stop);
            network.route(packet, destination.getSource(), destination.getTarget());
            stop++;
        }
    }

    private static void startThirdPhaseNetworkSimulation(Network network, Map<Integer, Destination> destinations) throws InterruptedException {
        Thread.sleep((long) new ErlangRandomGenerator().generate());
        Packet packet = new Packet("01234567012345670123456701234567012345670123456701234567", DEFAULT_TTL);
        Destination destination = destinations.get(0);
        network.route(packet, destination.getSource(), destination.getTarget());
    }

    private static void calculateAndPrintLoad(Map<Router, Integer> routerLoad) {
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
