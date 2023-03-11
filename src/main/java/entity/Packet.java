package entity;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.UUID;

public class Packet {
    private String id;
    private volatile Queue<Port> route;
    private boolean arrived;
    private String data;
    private long ttl;

    public Packet(String data, long ttl) {
        this.id = UUID.randomUUID().toString();
        this.data = data;
        this.ttl = ttl;
        this.route = new ArrayDeque<>();
    }

    public Queue<Port> getRoute() {
        return route;
    }

    public void setRoute(Queue<Port> route) {
        this.route = route;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isArrived() {
        return arrived;
    }

    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "id='" + id + '\'' +
                ", data='" + data + '\'' +
                ", ttl=" + ttl +
                '}';
    }
}
