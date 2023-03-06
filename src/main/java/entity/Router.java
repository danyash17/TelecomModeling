package entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class Router implements INetworkComposite {

    private Integer id;
    private Set<Port> ports;
    private Map<INetworkComposite, Port> routingTable;
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

    public Port getPort(int id){
        return ports.stream().filter(port -> port.getId().equals(id)).findAny().get();
    }

    private void initPorts(int numberOfPorts) {
        for (int i = 0; i < numberOfPorts; i++) {
            ports.add(new Port(i, this, null));
        }
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
    public void sendData() {

    }

    @Override
    public void recieveData() {

    }

    @Override
    public String toString() {
        return "Router{" +
                "id=" + id +
                '}';
    }
}
