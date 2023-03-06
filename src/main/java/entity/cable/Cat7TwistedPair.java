package entity.cable;

public class Cat7TwistedPair implements ITwistedPair{

    private static final double GB_SEC_SPEED = 30;

    @Override
    public double getGbSecSpeed() {
        return GB_SEC_SPEED;
    }
}
