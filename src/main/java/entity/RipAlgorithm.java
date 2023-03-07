package entity;

import java.util.*;
import java.util.stream.Collectors;

public class RipAlgorithm {
    private List<Router> components;
    private static final int HOP_LIMIT = 16;

    public RipAlgorithm(List<Router> components) {
        this.components = components;
        initializeRoutingTables();
    }

    public void run() {
        boolean converged = false;
        while (!converged) {
            converged = true;
            for (Router component : components) {
                Map<Integer, Integer> distances = calculateDistances(component);
                if (!distances.equals(component.getRoutingTable())) {
                    component.setRoutingTable(distances);
                    converged = false;
                }
            }
        }
    }

    private void initializeRoutingTables() {
        for (Router component : components) {
            Map<Integer, Integer> routingTable = new HashMap<>();
            routingTable.put(component.getId(), 0);
            component.setRoutingTable(routingTable);
        }
    }

    private Map<Integer, Integer> calculateDistances(Router component) {
        Map<Integer, Integer> distances = new HashMap<>();
        for (Router neighbor : component.getNeighbors()) {
            for (int destId : neighbor.getRoutingTable().keySet()) {
                int distance = (int) (neighbor.getRoutingTable().get(destId) + neighbor.getPortTo(component).getConnection().getGbSecSpeed());
                distances.put(destId, Math.min(distances.getOrDefault(destId, Integer.MAX_VALUE), distance));
            }
        }
        distances.put(component.getId(), 0);
        return distances;
    }

    public Queue<Port> getPacketRoute(Router source, Router target) {
        Queue<Port> bestQueue = new ArrayDeque<>();
        Queue<Port> initQueue = new ArrayDeque<>();
        Map<Integer, Queue<Port>> bestQueues = new HashMap<>();
        findBestPath(source, target, bestQueue, bestQueues, initQueue, 0);
        Optional<Map.Entry<Integer, Queue<Port>>> portQueue = bestQueues.entrySet().stream().min(Map.Entry.comparingByKey());
        return portQueue.isPresent() ? portQueue.get().getValue() : null;
    }

    private void findBestPath(Router source, Router target, Queue<Port> bestQueue, Map<Integer, Queue<Port>> bestQueues, Queue<Port> legacyQueue, Integer hops) {
        for (Port port : source.getPorts()) {
            Queue<Port> currentQueue = new ArrayDeque<>(legacyQueue);
            Integer currentHops = hops++;
            if (currentHops == HOP_LIMIT) {
                return;
            }
            else if (port.getConnection() == null || port.getRoot().equals(target)){
                continue;
            }
            Router current = (Router) port.getRoot();
            if (current.getPortTo(target) != null) {
                currentQueue.add(current.getPortTo(target));
                if(!getBestQueue(bestQueue, currentQueue).equals(bestQueue)){
                    bestQueues.put(sumConnectionSpeed(currentQueue),currentQueue);
                }
            } else {
                currentQueue.add(port);
                Router next = (Router) port.getConnection().getTarget().getRoot();
                findBestPath(next, target, bestQueue, bestQueues, currentQueue, hops);
            }
        }
    }

    private Queue<Port> getBestQueue(Queue<Port> bestQueue, Queue<Port> currentQueue) {
        int cost = sumConnectionSpeed(currentQueue);
        int bestCost = sumConnectionSpeed(bestQueue);
        if (cost < bestCost) {
            return currentQueue;
        }
        return bestQueue;
    }

    private int sumConnectionSpeed(Queue<Port> queue) {
        if (!queue.isEmpty()) {
            return queue.stream().mapToInt(port -> (int) port.getConnection().getGbSecSpeed()).sum();
        } else return Integer.MAX_VALUE;
    }

    private List<Integer> getNeighbors(int router) {
        List<Integer> list;
        list = components.get(router).getNeighbors().stream().mapToInt(item -> item.getId()).boxed().collect(Collectors.toList());
        return list;
    }

    private int getCost(int router, int neighbor) {
        return components.get(router).getRoutingTable().get(neighbor);
    }
}
