package entity;

public class Destination {

    private Router source;
    private Router target;

    public Destination(Router source, Router target) {
        this.source = source;
        this.target = target;
    }

    public Router getSource() {
        return source;
    }

    public void setSource(Router source) {
        this.source = source;
    }

    public Router getTarget() {
        return target;
    }

    public void setTarget(Router target) {
        this.target = target;
    }
}
