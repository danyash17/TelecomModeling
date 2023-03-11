package entity;

import java.util.Set;

public interface INetworkComposite {
    Integer getId();
    Set<Port> getPorts();
    void send(Packet packet);
    void receive(Packet packet);
}
