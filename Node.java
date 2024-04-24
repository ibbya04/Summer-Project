import java.util.ArrayList;

public class Node {

    private String data;
    private ArrayList<Edge> edges;

    public Node(String data) {
        this.data = data;
        this.edges = new ArrayList<Edge>();
    }

    public void addEdge(Node endNode, Integer weight) {
        this.edges.add(new Edge(this, endNode, weight));
    }

    public void removeEdge(Node endNode) {
        if ()
        this.edges.remove();
    }
}