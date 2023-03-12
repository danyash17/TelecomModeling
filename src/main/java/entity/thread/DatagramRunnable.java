package entity.thread;

import entity.Packet;
import entity.Port;

import static java.lang.Thread.sleep;

public class DatagramRunnable implements Runnable{

    private final long UPDATE_PAUSE = 500;
    private Packet packet;

    public DatagramRunnable(Packet packet){
        this.packet = packet;
    }

    @Override
    public void run() {
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
            }
            try {
                sleep(UPDATE_PAUSE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(packet + " finished its route from " + start.getRoot() + " to " + end.getConnection().getTarget().getRoot());
    }
}
