package entity.routing;

import entity.Port;
import entity.Router;
import entity.cable.OpticalFiberCable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AdvancedRipAlgorithm extends RipAlgorithm{

    private static final double GB_MAX_SPEED = OpticalFiberCable.GB_SEC_SPEED;
    private static final double LOAD_WEIGHT = 2;
    private Map<Router, Integer> routersLoad;
    private double medianLoad;

    public AdvancedRipAlgorithm(List<Router> components, Map<Router, Integer> routersLoad) {
        this.components = components;
        this.routersLoad = routersLoad;
        this.medianLoad = getMedianLoad();
        initializeRoutingTables();
    }

    @Override
    protected Map<Integer, Integer> calculateDistances(Router component) {
        Map<Integer, Integer> distances = new HashMap<>();
        for (Router neighbor : component.getNeighbors()) {
            for (int destId : neighbor.getRoutingTable().keySet()) {
                if (neighbor.isCrashed()){
                    continue;
                }
                int distance = (int) (neighbor.getRoutingTable().get(destId) +
                        (GB_MAX_SPEED - neighbor.getPortTo(component).getConnection().getGbSecSpeed()) +
                        LOAD_WEIGHT * getLoadBalancier(neighbor.getLoad()));
                distances.put(destId, Math.min(distances.getOrDefault(destId, Integer.MAX_VALUE), distance));
            }
        }
        distances.put(component.getId(), 0);
        return distances;
    }

    @Override
    protected int sumConnectionSpeed(Queue<Port> queue) {
        if (!queue.isEmpty()) {
            return queue.stream().mapToInt(port -> (int) ((int) port.getConnection().getGbSecSpeed() + getLoadBalancier(((Router)port.getRoot()).getLoad()))).sum();
        } else return Integer.MAX_VALUE;
    }

    private double getMedianLoad(){
        return routersLoad.entrySet().stream().mapToInt(Map.Entry::getValue).summaryStatistics().getAverage();
    }

    private double getLoadBalancier(int load){
        return (load*100) / medianLoad;
    }
}
