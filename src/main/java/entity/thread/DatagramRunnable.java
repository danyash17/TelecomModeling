package entity.thread;

import entity.Packet;
import entity.Port;
import generator.LognormalRandomGenerator;

import static java.lang.Thread.sleep;

public class DatagramRunnable implements Runnable{

    private long pause;
    private Packet packet;

    public DatagramRunnable(Packet packet){
        this.packet = packet;
    }

    @Override
    public void run() {
        pause = (long) new LognormalRandomGenerator().generate();
        Port start = packet.getRoute().poll();
        Port end = packet.getRoute().peekLast();
        System.out.println(packet + " is starting its route from " + start.getRoot() + " to " + end.getConnection().getTarget().getRoot());
        start.getConnection().transfer(packet);
        long startTime = System.currentTimeMillis();
        long time;
        while (!packet.isArrived()){
            time = System.currentTimeMillis() - startTime;
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
        System.out.println(packet + " finished its route from " + start.getRoot() + " to " + end.getConnection().getTarget().getRoot());
    }
}
