package bonus.connection;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public String name;
    public ArrayList<String> parents;

    public Node(String name) {
        this.name = name;
        this.parents = new ArrayList<>();
    }

    public Node(String name, Node n) {
        this.name = name;
        parents = n.history();
    }

    public void add(String parent) {
        parents.add(parent);
    }

    public ArrayList<String> history() {
        ArrayList<String> output = new ArrayList<>();
        output.add(name);
        output.addAll(parents);
        return output;
    }
}
