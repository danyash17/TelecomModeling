package entity;

import entity.cable.ICable;

public class Port {

    private Integer id;
    private INetworkComposite root;
    private Connection connection;

    private int packetsRecieved;

    public Port(Integer id, INetworkComposite root, Connection connection) {
        this.id = id;
        this.root = root;
        this.connection = connection;
        this.packetsRecieved = 0;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public INetworkComposite getRoot() {
        return root;
    }

    public void setRoot(INetworkComposite root) {
        this.root = root;
    }

    public int getPacketsRecieved() {
        return packetsRecieved;
    }

    public void setPacketsRecieved(int packetsRecieved) {
        this.packetsRecieved = packetsRecieved;
    }

    public void establishConnection(Port port, ICable cable) {
        this.connection = new Connection(this, port, cable);
        if (port.getConnection() == null)
            port.establishConnection(this, cable);
    }

    public void recieve(Packet packet) {
        packetsRecieved++;
        root.receive(packet);
    }
}
