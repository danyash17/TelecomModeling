package entity;

import entity.cable.Cat7TwistedPair;
import entity.cable.OpticalFiberCable;
import entity.routing.INetworkLayerRoutingProtocol;
import entity.routing.RipAlgorithm;
import entity.thread.DatagramRunnable;
import util.RouteCostCalculator;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Network {

    private static final int ACTIVE_USERS = 5;

    private Set<INetworkComposite> nodes;
    private Set<INetworkLayerRoutingProtocol> routingProtocols;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(ACTIVE_USERS);

    public Network() {
        nodes = new HashSet<>();
        routingProtocols = new HashSet<>();
        initNetworkStructure();
        routingProtocols.add(new RipAlgorithm(getRouterNodes()));
    }

    private List<Router> getRouterNodes(){
        List<Router> routers = new ArrayList<>();
        for (INetworkComposite node : nodes) {
            if (node instanceof Router) {
                routers.add((Router) node);
            }
        }
        return routers;
    }

    public void route(Packet packet, INetworkComposite source, INetworkComposite target){
        Map<Integer, Deque<Port>> routeMap = new HashMap<>();
        Map<Integer, INetworkLayerRoutingProtocol> routeTtls = new HashMap<>();
        for (INetworkLayerRoutingProtocol protocol: routingProtocols){
            if (source instanceof Router && target instanceof Router){
                Deque<Port> currentRoute = protocol.route((Router) source, (Router) target);
                int cost = RouteCostCalculator.calculateCost(currentRoute);
                routeMap.put(cost, currentRoute);
                routeTtls.put(cost, protocol);
            }
        }
        int minCost = routeMap.keySet().stream().min(Integer::compare).get();
        packet.setTtl(routeTtls.get(minCost).getTtl());
        packet.setRoute(routeMap.get(minCost));
        DatagramRunnable datagramRunnable = new DatagramRunnable(packet);
        executorService.submit(datagramRunnable);
    }

    public Set<INetworkComposite> getNodes() {
        return nodes;
    }

    public void setNodes(Set<INetworkComposite> nodes) {
        this.nodes = nodes;
    }

    private void initNetworkStructure() {
        Router r0 = new Router(0);
        Router r1 = new Router(1);
        Router r2 = new Router(2);
        Router r3 = new Router(3);
        Router r4 = new Router(4);
        Router r5 = new Router(5);
        Router r6 = new Router(6);
        Router r7 = new Router(7);
        Router r8 = new Router(8);
        Router r9 = new Router(9);
        Router r10 = new Router(10);
        Router r11 = new Router(11);
        Router r12 = new Router(12);
        Router r13 = new Router(13);
        Router r14 = new Router(14);
        Router r15 = new Router(15);
        Router r16 = new Router(16);
        Router r17 = new Router(17);
        Router r18 = new Router(18);
        nodes.addAll(Arrays.asList(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18));
        ///18
        Port r18_0 = r18.getPort(0);
        ///17
        Port r17_0 = r17.getPort(0);
        Port r17_1 = r17.getPort(1);
        r17_1.establishConnection(r18_0, new Cat7TwistedPair());
        ///16
        Port r16_0 = r16.getPort(0);
        Port r16_1 = r16.getPort(1);
        Port r6_2 = r6.getPort(2);
        r16_0.establishConnection(r6_2, new OpticalFiberCable());
        r16_1.establishConnection(r17_0, new Cat7TwistedPair());
        ///15
        Port r15_0 = r15.getPort(0);
        ///14
        Port r14_0 = r14.getPort(0);
        Port r14_1 = r14.getPort(1);
        Port r14_2 = r14.getPort(2);
        Port r3_1 = r3.getPort(1);;
        r14_0.establishConnection(r3_1, new OpticalFiberCable());
        r14_1.establishConnection(r15_0, new Cat7TwistedPair());
        ///13
        Port r13_0 = r13.getPort(0);
        Port r13_1 = r13.getPort(1);
        Port r13_2 = r13.getPort(2);
        r13_0.establishConnection(r14_2, new Cat7TwistedPair());
        ///12
        Port r12_0 = r12.getPort(0);
        Port r12_1 = r12.getPort(1);
        r12_0.establishConnection(r13_1, new Cat7TwistedPair());
        ///11
        Port r11_0 = r11.getPort(0);
        Port r11_1 = r11.getPort(1);
        Port r11_2 = r11.getPort(2);
        Port r11_3 = r11.getPort(3);
        r11_0.establishConnection(r13_2, new OpticalFiberCable());
        r11_1.establishConnection(r12_1, new OpticalFiberCable());
        ///10
        Port r10_0 = r10.getPort(0);
        r10_0.establishConnection(r11_3, new Cat7TwistedPair());
        ///9
        Port r9_0 = r9.getPort(0);
        r9_0.establishConnection(r11_2, new OpticalFiberCable());
        ///8
        Port r8_0 = r8.getPort(0);
        Port r8_1 = r8.getPort(1);
        ///7
        Port r7_0 = r7.getPort(0);
        Port r7_1 = r7.getPort(1);
        r7_1.establishConnection(r8_0, new Cat7TwistedPair());
        ///6
        Port r6_0 = r6.getPort(0);
        Port r6_1 = r6.getPort(1);
        r6_1.establishConnection(r8_1, new OpticalFiberCable());
        ///5
        Port r5_0 = r5.getPort(0);
        Port r5_1 = r5.getPort(1);
        ///4
        Port r4_0 = r4.getPort(0);
        Port r4_1 = r4.getPort(1);
        Port r4_2 = r4.getPort(2);
        Port r4_3 = r4.getPort(3);
        r4_1.establishConnection(r5_1, new OpticalFiberCable());
        r4_2.establishConnection(r6_0, new OpticalFiberCable());
        r4_3.establishConnection(r7_0, new Cat7TwistedPair());
        ///3
        Port r3_0 = r3.getPort(0);
        ///2
        Port r2_0 = r2.getPort(0);
        Port r2_1 = r2.getPort(1);
        r2_1.establishConnection(r5_0, new OpticalFiberCable());
        ///1
        Port r1_0 = r1.getPort(0);
        Port r1_1 = r1.getPort(1);
        r1_1.establishConnection(r4_0, new Cat7TwistedPair());
        ///0
        Port r0_0 = r0.getPort(0);
        Port r0_1 = r0.getPort(1);
        Port r0_2 = r0.getPort(2);
        r0_0.establishConnection(r1_0, new Cat7TwistedPair());
        r0_1.establishConnection(r2_0, new OpticalFiberCable());
        r0_2.establishConnection(r3_0, new Cat7TwistedPair());
    }
}
