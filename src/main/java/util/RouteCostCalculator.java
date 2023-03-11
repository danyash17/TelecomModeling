package util;

import entity.INetworkComposite;
import entity.Port;
import entity.cable.OpticalFiberCable;

import java.util.Queue;

public class RouteCostCalculator {

    private static double getMaxSpeed(){
        return new OpticalFiberCable().getGbSecSpeed();
    }

    public static int calculateCost(Queue<Port> route){
        return route.stream().mapToInt(port -> (int) (getMaxSpeed() - port.getConnection().getGbSecSpeed())).sum();
    }

}
