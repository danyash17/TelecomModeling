import entity.Network;
import generator.*;
import util.TreePrinter;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private static IRandomGenerator generator;

    public static void main(String[] args) {
        Network network = new Network();
        TreePrinter.printTree(network.getNodes());
    }

//    public static void main(String[] args) {
//        generator = new BasicRandomGenerator();
//        List<Router> nodes = new ArrayList<>();
//
//        // Create nodes
//        for (int i = 0; i < 15 + (int) (Math.random() * 6); i++) {
//            Router node = new Router("Router " + i, 0, 0);
//            nodes.add(node);
//        }
//
//        // Create connections between nodes
//        for (int i = 0; i < nodes.size(); i++) {
//            for (int j = i + 1; j < nodes.size(); j++) {
//                if (Math.random() < 0.5) {
//                    Connection connection = new Connection(nodes.get(i), nodes.get(j), 0);
//                }
//            }
//        }
//
//        // Add background threads to some nodes
//        for (Router node : nodes) {
//            int numThreads = 2 + (int) (Math.random() * 3);
//            for (int i = 0; i < numThreads; i++) {
//                BackgroundThread thread = new BackgroundThread(node, alphabet[i]);
//                node.addThread(thread);
//                thread.start();
//            }
//        }
//    }
}