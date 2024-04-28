import java.util.ArrayList;

public class Graph {

    private ArrayList<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<Node>();
    }

    public Node addNode(String name) {
        if (findNode(name) == null){
            Node newNode = new Node(name);
            this.nodes.add(newNode);
            return newNode;
        }
        else
            return null;
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

    public Node findNode(String name) {
        for (int i = 0; i < this.nodes.size(); i++)
        {
            Node node = this.nodes.get(i);
            if (node.getName() == name)
                return node;
        }
        return null;
    }

    // iterates over all graphs nodes, assinging each node to n and calling print
    // function on each node
    public void printGraph() {
        for (Node n : this.nodes) {
            n.printNodes();
        }
    }

    //
    public void createPerson(String[] names) {
        // for each loop creating a node for every name within data array 
        for (String person : names)
            this.addNode(person);

        // for loop iterating through the single row of names we gave it
        // start node is always first name on the row of data
        // iterates through all other names creating a directed edge between first name and nth name.
        for (int i = 1; i < names.length; i++){
            Node startNode = findNode(names[0]);
            Node endNode = findNode(names[i]);
            startNode.addEdge(endNode);
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
        socialNetwork.printGraph();

        socialNetwork.removeEdge(Ibraheem, IA);
        socialNetwork.printGraph();

        socialNetwork.removeNode(Ibby);
        socialNetwork.printGraph();
    }
}
