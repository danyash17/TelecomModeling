package entity.cable;

public class OpticalFiberCable implements IFiber{

    public static final double GB_SEC_SPEED = 40;

    public OpticalFiberCable() {
    }

    @Override
    public double getGbSecSpeed(){
        return GB_SEC_SPEED;
    }

}
