package entity;

import entity.cable.Cat7TwistedPair;
import entity.cable.OpticalFiberCable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Network {

    private Set<INetworkComposite> nodes;

    public Network() {
        nodes = new HashSet<>();
        initNetworkStructure();
    }

    public Set<INetworkComposite> getNodes() {
        return nodes;
    }

    public void setNodes(Set<INetworkComposite> nodes) {
        this.nodes = nodes;
    }

    private void initNetworkStructure(){
        Router r0 = new Router(0);
        Router r1 = new Router(1);
        Router r2 = new Router(2);
        Router r3 = new Router(3);
        Router r4 = new Router(4);
        Router r5 = new Router(5);
        Router r6 = new Router(6);
        Router r7 = new Router(7);
        Router r8 = new Router(8);
        nodes.addAll(Arrays.asList(r0,r1,r2,r3,r4,r5,r6,r7,r8));
        ///8
        Port r8_0 = r8.getPort(0);
        Port r8_1 = r8.getPort(1);
        ///7
        Port r7_0 = r7.getPort(0);
        Port r7_1 = r7.getPort(1);
        r7_1.establishConnection(r8_0, new Cat7TwistedPair());
        ///6
        Port r6_0 = r6.getPort(0);
        Port r6_1 = r6.getPort(1);
        r6_1.establishConnection(r8_1, new OpticalFiberCable());
        ///5
        Port r5_0 = r5.getPort(0);
        Port r5_1 = r5.getPort(1);
        ///4
        Port r4_0 = r4.getPort(0);
        Port r4_1 = r4.getPort(1);
        Port r4_2 = r4.getPort(2);
        Port r4_3 = r4.getPort(3);
        r4_1.establishConnection(r5_1, new OpticalFiberCable());
        r4_2.establishConnection(r6_0, new OpticalFiberCable());
        r4_3.establishConnection(r7_0, new Cat7TwistedPair());
        ///3
        Port r3_0 = r3.getPort(0);
        ///2
        Port r2_0 = r2.getPort(0);
        Port r2_1 = r2.getPort(1);
        r2_1.establishConnection(r5_0, new OpticalFiberCable());
        ///1
        Port r1_0 = r1.getPort(0);
        Port r1_1 = r1.getPort(1);
        r1_1.establishConnection(r4_0, new Cat7TwistedPair());
        ///0
        Port r0_0 = r0.getPort(0);
        Port r0_1 = r0.getPort(1);
        Port r0_2 = r0.getPort(2);
        r0_0.establishConnection(r1_0, new Cat7TwistedPair());
        r0_1.establishConnection(r2_0, new OpticalFiberCable());
        r0_2.establishConnection(r3_0, new Cat7TwistedPair());
    }
}