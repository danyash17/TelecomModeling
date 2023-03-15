package entity;

import entity.cable.ICable;
import generator.LognormalRandomGenerator;

public class Connection {

    private static final int MOCK_BEST_TRANSFER_TIME = 100;
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

    public double getGbSecSpeed(){
        return cable.getGbSecSpeed();
    }

    public void transfer(Packet packet) {
        try {
            Thread.sleep((long) (MOCK_BEST_TRANSFER_TIME - getGbSecSpeed()) + addPacketWeightCorrection(packet));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        target.recieve(packet);
    }

    private long addPacketWeightCorrection(Packet packet) {
        return (long) new LognormalRandomGenerator().generate() * packet.getVolumeBalancier();
    }
}
