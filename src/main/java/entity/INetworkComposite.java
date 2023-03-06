package entity;

import java.util.Set;

public interface INetworkComposite {
    Set<Port> getPorts();
    void sendData();
    void recieveData();
}
