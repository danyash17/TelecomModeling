package entity;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Router implements INetworkComposite {

    private Integer id;
    private Set<Port> ports;
    private Map<Integer, Integer> routingTable;
    private static final int PORT_NUMBER = 4;

    public Router(Integer id, Set<Port> ports) {
        this.id = id;
        this.ports = ports;
        this.routingTable = new HashMap<>();
        initPorts(PORT_NUMBER);
    }

    public Router(Integer id) {
        this.id = id;
        this.ports = new HashSet<>();
        this.routingTable = new HashMap<>();
        initPorts(PORT_NUMBER);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Set<Port> getPorts() {
        return ports;
    }

    public void setPorts(Set<Port> ports) {
        this.ports = ports;
    }

    public Port getPort(int id) {
        return ports.stream().filter(port -> port.getId().equals(id)).findAny().get();
    }

    public Map<Integer, Integer> getRoutingTable() {
        return routingTable;
    }

    public void setRoutingTable(Map<Integer, Integer> routingTable) {
        this.routingTable = routingTable;
    }

    private void initPorts(int numberOfPorts) {
        for (int i = 0; i < numberOfPorts; i++) {
            ports.add(new Port(i, this, null));
        }
    }

    public Port getPortTo(Router router) {
        Optional<Port> temp = ports.stream().filter(port -> {
            if (port.getConnection() != null) {
                return port.getConnection().getTarget().getRoot().equals(router);
            }
            return false;
        }).findAny();
        return temp.isPresent() ? temp.get() : null;
    }

    public Set<Router> getNeighbors() {
        Set<Router> routers = new HashSet<>();
        ports.stream().forEach(port -> {
            if (port.getConnection() != null && !routers.contains(port.getConnection().getTarget().getRoot())) {
                routers.add((Router) port.getConnection().getTarget().getRoot());
            }
        });
        return routers;
    }

    public void establishConnection(Integer portId, INetworkComposite node) {
        AtomicReference<Port> targetPort = null;
        if (ports.stream().filter(port -> {
                    if (port.getId().equals(portId)) {
                        targetPort.set(port);
                    }
                    return true;
                })
                .findAny().isPresent()) {

        }
    }

    @Override
    public void send(Packet packet) {
        packet.getRoute().poll().getConnection().transfer(packet);
    }

    @Override
    public void receive(Packet packet) {
        System.out.println(packet + " arrived to " + this);
        if(packet.getRoute().isEmpty()){
            packet.setArrived(true);
            return;
        }
        send(packet);
    }

    @Override
    public String toString() {
        return "Router{" +
                "id=" + id +
                '}';
    }
}
