package generator;

import java.util.Random;

public class BasicRandomGenerator implements IRandomGenerator{
    @Override
    public int generate() {
        Random rand = new Random();
        int randomNum = rand.nextInt(5000) + 1;
        return randomNum;
    }
}
