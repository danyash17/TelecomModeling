package entity.routing;

import entity.INetworkComposite;
import entity.Port;
import entity.Router;

import java.util.Queue;

public interface INetworkLayerRoutingProtocol extends IRoutingProtocol{
    Queue<Port> route(Router source, Router target);
    long getTtl();
}
