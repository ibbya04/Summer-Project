import java.util.ArrayList;

public class Graph {

    private ArrayList<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<Node>();
    }

    public Node addNode(String name) {
        Node newNode = new Node(name);
        this.nodes.add(newNode);
        return newNode;
    }

    public void removeNode(Node node) {
        this.nodes.remove(node);
    }

    public void addEdge(Node startNode, Node endNode) {
        startNode.addEdge(endNode);
    }

    public void removeEdge(Node startNode, Node endNode) {
        startNode.removeEdge(endNode);
    }

    public ArrayList<Node> getNodes() {
        return this.nodes;
    }

    // iterates over all graphs nodes, assinging each node to n and calling print function on each node
    public void print() {
        for(Node n: this.nodes) {
            n.print();
        }
    }

    // testing
    public static void main(String[] args) {
        Graph socialNetwork = new Graph();
        Node Ibraheem = socialNetwork.addNode("ibraheem");
        Node Ibby = socialNetwork.addNode("ibby");
        Node IA = socialNetwork.addNode("IA");

        socialNetwork.addEdge(Ibraheem, Ibby);
        socialNetwork.addEdge(Ibraheem, IA);
        socialNetwork.print();

        socialNetwork.removeEdge(Ibraheem, IA);
        socialNetwork.print();

        socialNetwork.removeNode(Ibby);
        socialNetwork.print();
    }
}
