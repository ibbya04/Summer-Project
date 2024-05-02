import java.util.ArrayList;

public class Node {

    private String name;
    private ArrayList<Edge> edges;

    // constructor
    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<Edge>();
    }

    // Adds an edge to this node's arraylist of edges, using the add keyword from
    // ArrayList lib,
    // initiallising the new edge with constructor.
    public void addEdge(Node endNode) {
        this.edges.add(new Edge(this, endNode));
    }

    // Removes a node's edge by iterating through the node's edges until the edges
    // endNode
    // is equal to the endNode passed into function, then removes using remove func
    // from library.
    public void removeEdge(Node endNode) {
        for (int i = 0; i < this.edges.size(); i++) {
            Edge edge = this.edges.get(i);
            if (edge.getEnd().equals(endNode)) {
                this.edges.remove(i);
                i--;
            }
        }
    }

    // returns node's name
    public String getName() {
        return this.name;
    }

    // returns all of node's edges
    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    // prints all of a single nodes edges
    public void printNodes() {
        String message = "";

        // if node has no edges
        if (this.edges.size() == 0) {
            System.out.println(this.name + " -->");
            return;
        }

        // iterates through all of a nodes edges, printing them, each separated by commas.
        for (int i = 0; i < this.edges.size(); i++) {
            if (i == 0) {
                message += this.edges.get(i).getStart().name + " --> ";
            } else {
                message += ", ";
            }
            message += this.edges.get(i).getEnd().name;
        }
        System.out.println(message);
    }
}