package entity;

import entity.cable.ICable;

public class Connection {

    private Port source;
    private Port target;
    private ICable cable;

    public Connection(Port source, Port target, ICable cable) {
        this.source = source;
        this.target = target;
        this.cable = cable;
    }

    public Port getSource() {
        return source;
    }

    public void setSource(Port source) {
        this.source = source;
    }

    public Port getTarget() {
        return target;
    }

    public void setTarget(Port target) {
        this.target = target;
    }

    public ICable getCable() {
        return cable;
    }

    public void setCable(ICable cable) {
        this.cable = cable;
    }
}
