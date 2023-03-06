package util;

import entity.Connection;
import entity.INetworkComposite;
import entity.Port;

import java.util.Set;

public class TreePrinter {
    public static void printTree(Set<INetworkComposite> nodes) {
        for (INetworkComposite node : nodes) {
            printNode(node, 0);
        }
    }

    private static void printNode(INetworkComposite node, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("- " + node);

        for (Port port : node.getPorts()) {
            if (port.getConnection() != null) {
                printNode(port.getConnection().getTarget().getRoot(), depth + 1);
            }
        }
    }
}
