package bonus.connection;

import java.util.*;


public class ConnectionFinder {
    Map<String, Set<String>> connections = new HashMap<>();

    public void addAll(List<Connection> connectionList) {
        for (Connection c : connectionList) {
            add(c);
        }
    }

    public void add(Connection connection) {
        String from = Objects.requireNonNull(connection.from());
        String to = Objects.requireNonNull(connection.to());

        connections.computeIfAbsent(from, k -> new HashSet<>()).add(to);
        connections.computeIfAbsent(to, k -> new HashSet<>()).add(from);
    }

    public boolean hasConnection(String a, String b) {
        return !findConnection(a, b).isEmpty();
    }

    private List<String> buildPath(Node a, Node b) {
        List<String> pathA = a.history();
        List<String> pathB = b.history();

        Collections.reverse(pathB);

        pathB.addAll(pathA);
        return pathB;
    }

    private List<String> checkName(
            HashSet<Node> newAEndNodes,
            HashSet<Node> bEndNodes,
            HashSet<String> seenANames,
            HashSet<String> seenBNames,
            String name,
            Node mainNode) {

        if (seenANames.contains(name)) {
            return List.of();
        }

        if (seenBNames.contains(name)) {
            Node meetingNodeFromB = findNodeByName(bEndNodes, name);
            if (meetingNodeFromB != null) {
                return buildPath(mainNode, meetingNodeFromB);
            }
        }

        newAEndNodes.add(new Node(name, mainNode));
        seenANames.add(name);
        return List.of();
    }

    private Node findNodeByName(HashSet<Node> nodes, String name) {
        for (Node node : nodes) {
            if (node.name.equals(name)) {
                return node;
            }
        }
        return null;
    }

    private List<String> expand(
            HashSet<Node> aEndNodes,
            HashSet<Node> bEndNodes,
            HashSet<String> seenANames,
            HashSet<String> seenBNames) {

        HashSet<Node> newAEndNodes = new HashSet<>();

        for (Node n : aEndNodes) {
            for (String name : connections.get(n.name)) {
                List<String> out = checkName(newAEndNodes, bEndNodes, seenANames, seenBNames, name, n);
                if (!out.isEmpty()) {
                    return out;
                }
            }
        }

        aEndNodes.clear();
        aEndNodes.addAll(newAEndNodes);

        return List.of();
    }

    public List<String> findConnection(String a, String b) {
        if (!connections.containsKey(a) || !connections.containsKey(b)) {
            return List.of();
        }

        if (a.equals(b)) {
            return List.of(a, b);
        }

        HashSet<Node> aEndNodes = new HashSet<Node>(List.of(new Node(a)));
        HashSet<Node> bEndNodes = new HashSet<Node>(List.of(new Node(b)));

        HashSet<String> seenANames = new HashSet<String>();
        HashSet<String> seenBNames = new HashSet<String>();

        List<String> found;

        while (!aEndNodes.isEmpty() && !bEndNodes.isEmpty()) {
            found = expand(aEndNodes, bEndNodes, seenANames,seenBNames);

            if (!found.isEmpty()) {
                return found;
            }

            found = expand(bEndNodes, aEndNodes, seenBNames, seenANames);

            if (!found.isEmpty()) {
                return found;
            }
        }


        return List.of();
    }
}
