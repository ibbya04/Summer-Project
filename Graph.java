import java.util.ArrayList;

public class Graph {

    private ArrayList<Node> nodes;
    private int numEdges;

    public Graph() {
        this.nodes = new ArrayList<Node>();
    }

    // Adds a node to the ArrayList of nodes.
    // Calls findNode to check if node is already in ArrayList, if not it creates
    // the node using ArrayList add funciton.
    // If node is in the array list already, the node is returned by calling
    // findNode again.
    public Node addNode(String name) {
        if (findNode(name) == null) {
            Node newNode = new Node(name);
            this.nodes.add(newNode);
            return newNode;
        } else
            return findNode(name);
    }

    // Removes a node from the aray list
    public void removeNode(Node node) {
        this.nodes.remove(node);
    }

    // Adds a directed edge betwen 2 nodes, building on the addEdge function in
    // Node.java
    public void addEdge(Node startNode, Node endNode) {
        startNode.addEdge(endNode);
    }

    // Removes a directed edge betwen 2 nodes, building on the removeEdge function
    // in Node.java
    public void removeEdge(Node startNode, Node endNode) {
        startNode.removeEdge(endNode);
    }

    // checks if a node is in the array list of nodes, using the node's name.
    // Returns null if node is not found
    public Node findNode(String name) {
        for (int i = 0; i < this.nodes.size(); i++) {
            Node node = this.nodes.get(i);
            if (node.getName().equals(name))
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

    // Creates a node for every person from an array of names
    public void createPeople(String[] names) {
        // for each loop creating a node for every name within data array
        for (String person : names)
            this.addNode(person);

        // for loop iterating through the single row of names we gave it
        // start node is always first name on the row of data
        // iterates through all other names creating a directed edge between first name
        // and nth name.
        for (int i = 1; i < names.length; i++) {
            Node startNode = findNode(names[0]);
            Node endNode = findNode(names[i]);
            startNode.addEdge(endNode);
        }
    }

    // Returns number of edges, recursively calling getNodesEdges 
    // from Node.java for every node in this graph
    public float getNumEdges() {
        for (int i = 0; i < this.nodes.size(); i++)
            numEdges += this.nodes.get(i).getNodesEdges();
        return numEdges;
    }

    // Returns number of nodes in this graph
    public float getNumNodes() {
        return this.nodes.size();
    }

    // testing
    public static void main(String[] args) {
        Graph socialNetwork = new Graph();
        Node Ibraheem = socialNetwork.addNode("ibraheem");
        Node Ibby = socialNetwork.addNode("ibby");
        Node IA = socialNetwork.addNode("IA");

        socialNetwork.addEdge(Ibraheem, Ibby);
        socialNetwork.addEdge(Ibraheem, IA);
        socialNetwork.addEdge(IA, Ibby);
        socialNetwork.printGraph();

        System.out.println(socialNetwork.getNumEdges());
        System.out.println(socialNetwork.getNumNodes());

    }
}
