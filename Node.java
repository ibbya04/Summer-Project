import java.util.ArrayList;

public class Node {

    private String name;
    private ArrayList<Edge> edges;

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<Edge>();
    }

    public void addEdge(Node endNode, Integer weight) {
        this.edges.add(new Edge(this, endNode, weight));
    }

    public void removeEdge(Node endNode) {
        for (int i = 0; i < this.edges.size(); i++){
            Edge edge = this.edges.get(i);
            if (edge.getEnd().equals(endNode))
                this.edges.remove(i);
                i--;
        }
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }
}