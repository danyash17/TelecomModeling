package entity;

import entity.cable.ICable;

public class Port {

    private Integer id;
    private INetworkComposite root;
    private Connection connection;

    public Port(Integer id, INetworkComposite root, Connection connection) {
        this.id = id;
        this.root = root;
        this.connection = connection;
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

    public void establishConnection(Port port, ICable cable) {
        this.connection = new Connection(this, port, cable);
        //full-duplex
        if (port.getConnection() != null && port.getConnection().getTarget() != null && !port.getConnection().getTarget().equals(this)) {
            port.establishConnection(this, cable);
        }
    }

}
