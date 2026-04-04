package bonus.connection;

import java.util.*;


public class ConnectionFinder {
    Map<String, List<String>> connections = new HashMap<>();

    public void addAll(List<Connection> connections) {
        for (Connection c : connections) {
            add(c);
        }
    }

    public void add(Connection connection) {
        connections.computeIfAbsent(connection.from(), k -> new ArrayList<>())
                .add(connection.to());

        connections.computeIfAbsent(connection.to(), k -> new ArrayList<>())
                .add(connection.from());
    }

    public boolean hasConnection(String a, String b) {
        if (a == null || b == null) {
            return false;
        }

        List<String> minList;
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

    private Node extend(Queue<Node> queue, Set<String> seen, Set<String> goalSeen) {
        Node node = queue.poll();

        if (node == null) {
            return null;
        }

        if (goalSeen.contains(node.name)) {
            return node;
        }

        if (seen.contains(node.name)) {
            return null;
        }
        seen.add(node.name);

        ArrayList<String> newParents = new ArrayList<>(node.parents);
        newParents.add(node.name);

        for (String s : connections.get(node.name)) {
            if (!seen.contains(s)) {
                queue.add(new Node(s, newParents));
            }
        }

        return null;
    }

    public List<String> deduceRoute(Node connectingNode, Queue<Node> queue, String goal) {
        if (connectingNode.name.equals(goal)) {
            return List.of(connectingNode.parents.getFirst(), goal);
        }
        for (Node n : queue) {
            if (n.name.equals(connectingNode.name)) {
                ArrayList<String> path = new ArrayList<>(n.parents);

                ArrayList<String> other = new ArrayList<>(connectingNode.parents);
                Collections.reverse(other);

                path.addAll(other);
                return path;
            }
        }
        return null;
    }

    public List<String> findConnection(String a, String b) {
        if (!connections.containsKey(a) || !connections.containsKey(b)) {
            return null;
        }

        if (a.equals(b)) {
            return List.of(a, b);
        }

        Queue<Node> queueStart = new LinkedList<>();
        Queue<Node> queueGoal = new LinkedList<>();

        HashSet<String> seenA = new HashSet<>();
        HashSet<String> seenB = new HashSet<>();

        queueStart.add(new Node(a, new ArrayList<>(List.of(b))));
        queueGoal.add(new Node(b, new ArrayList<>(List.of(a))));

        Node found;

        while (!queueStart.isEmpty() && !queueGoal.isEmpty()) {
            found = extend(queueStart, seenA, seenB);

            if (found != null) {
                return deduceRoute(found, queueStart, b);
            }

            found = extend(queueGoal, seenB, seenA);

            if (found != null) {
                return deduceRoute(found, queueGoal, a);
            }
        }

        return null;
    }
}
