package bonus.connection;

import java.util.ArrayList;

public class Node {

    public String name;
    public ArrayList<String> parents;

    public Node(String name) {
        this.name = name;
        this.parents = new ArrayList<>();
    }

    public Node(String name, ArrayList<String> parents) {
        this.name = name;
        this.parents = parents;
    }

    public void add(String parent) {
        parents.add(parent);
    }
}
