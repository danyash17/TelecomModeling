package entity.thread;

import entity.Packet;
import entity.Port;
import generator.ExponentialRandomGenerator;
import generator.IRandomGenerator;
import generator.LognormalRandomGenerator;
import generator.NormalRandomGenerator;

import java.util.*;

import static java.lang.Thread.sleep;

public class DatagramRunnable implements Runnable{

    private long pause;
    private Packet packet;
    private List<IRandomGenerator> finishGenerators = List.of(new NormalRandomGenerator(), new LognormalRandomGenerator(), new ExponentialRandomGenerator());

    public DatagramRunnable(Packet packet){
        this.packet = packet;
    }

    @Override
    public void run() {
        pause = (long) new LognormalRandomGenerator().generate() * packet.getVolumeBalancier();
        Port start = packet.getRoute().poll();
        Port end = packet.getRoute().peekLast();
        System.out.println(packet + " is starting its route from " + start.getRoot() + " to " + end.getConnection().getTarget().getRoot());
        start.getConnection().transferForward(packet);
        long startTime = System.nanoTime();
        long time;
        while (!packet.isArrived()){
            time = System.nanoTime() - startTime;
            if (time > packet.getTtl()){
                System.out.println(packet + " is LOST having transfered from " + start.getRoot() + " to " + end.getConnection().getTarget().getRoot());
                return;
            }
            try {
                sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //arriving simulation
        try {
            sleep(pause + (long) finishGenerators.get(new Random().nextInt(3)).generate());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(packet + " finished its route from " + start.getRoot() + " to " + end.getConnection().getTarget().getRoot() + ",estimated time: " + (double)(System.nanoTime()-startTime)/1000_000_000 + " seconds");
    }
}
