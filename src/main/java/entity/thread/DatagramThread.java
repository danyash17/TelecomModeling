package entity.thread;

import entity.Connection;
import entity.Packet;
import entity.Port;

import java.util.Queue;

public class DatagramThread extends Thread{

    private final long UPDATE_PAUSE = 1000;
    private Packet packet;

    public DatagramThread(Packet packet){
        this.packet = packet;
    }

    @Override
    public synchronized void start() {
        Port start = packet.getRoute().poll();
        Port end = packet.getRoute().peek();
        System.out.println(packet + " is starting its route from " + start.getRoot() + " to " + end.getRoot());
        start.getConnection().transfer(packet);
        long startTime = System.currentTimeMillis();
        long time;
        while (!packet.isArrived()){
            time = System.currentTimeMillis() - startTime;
            if (time > packet.getTtl()){
                throw new ThreadDeath();
            }
            try {
                sleep(UPDATE_PAUSE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(packet + " finished its route from " + start.getRoot() + " to " + end);
    }
}
