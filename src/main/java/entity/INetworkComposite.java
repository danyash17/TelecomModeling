package entity;

import java.util.Set;

public interface INetworkComposite {
    Integer getId();
    Set<Port> getPorts();
    void sendData();
    void recieveData();
}
