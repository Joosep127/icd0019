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
        if (!connections.containsKey(a) || !connections.containsKey(b)) {
            return false;
        }

        Set<String> minList;
        String maxItem;

        if (connections.get(a).size() < connections.get(b).size()) {
            minList = connections.get(a);
            maxItem = b;
        } else {
            minList = connections.get(b);
            maxItem = a;
        }

        if (minList.contains(maxItem)) {
            return true;
        }

        List<String> connection = findConnection(a, b);
        return connection != null;
    }

    public List<String> expand(
            HashSet<Node> aEndNodes,
            HashSet<Node> bEndNodes,
            HashSet<String> seenANames,
            HashSet<String> seenBNames) {

        HashSet<Node> newAEndNodes = new HashSet<>();

        for (Node n : aEndNodes) {
            for (String name : connections.get(n.name)) {
                if (!seenANames.contains(name)) {
                    if (seenBNames.contains(name)) {
                        Node meetingNodeFromB = null;
                        for (Node bNode : bEndNodes) {
                            if (bNode.name.equals(name)) {
                                meetingNodeFromB = bNode;
                                break;
                            }
                        }
                        List<String> pathA = n.history();
                        List<String> pathB = meetingNodeFromB.history();

                        Collections.reverse(pathB);

                        pathB.addAll(pathA);
                        return pathB;
                    }
                    newAEndNodes.add(new Node(name, n));
                    seenANames.add(name);
                }
            }
        }

        aEndNodes.clear();
        aEndNodes.addAll(newAEndNodes);

        return null;
    }

    public List<String> findConnection(String a, String b) {
        if (!connections.containsKey(a) || !connections.containsKey(b)) {
            return null;
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

            if (found != null) {
                return found;
            }

            found = expand(bEndNodes, aEndNodes, seenBNames, seenANames);

            if (found != null) {
                return found;
            }
        }


        return null;
    }
}
