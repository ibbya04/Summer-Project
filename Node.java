import java.util.ArrayList;

public class Node {

    private String name;
    private ArrayList<Edge> edges;
    private int followers;
    private int following;

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

    // returns number of edges of this single node
    public int getNodesEdges() {
        return this.edges.size();
    }

    // iterates through all of this nodes edges, adding one to the start nodes 
    // follower count - directed edges, start node is always the person who is following someone else
    public void checkFollowing() {
        for (Edge edge : edges) {
            Node n = edge.getStart();
            n.following ++;
        } 
    }

    // Returns how many people node(person) is following
    public int getFollowing() {
        return this.following;
    }

    // iterates through all of this nodes edges, adding one to the end nodes 
    // follower count - directed edges, end node is always the person who is being followed
    public void checkFollowers() {
        for (Edge edge : edges) {
            Node n = edge.getEnd();
            n.followers ++;
        } 
    }

    // Returns how many people followers node(person) has
    public int getFollowers() {
        return this.followers;
    }

    // Returns which name comes alphabetically first, for findHighestFollowers/Following functions
    public Node returnFirstName(Node second) {
        char a = this.getName().charAt(0);
        char b = second.getName().charAt(0);

        if (a < b)
            return this;
        else
            return second;
    }

    // Returns a Boolean value depending on if the test person follows the person
    // the function is called on.
    public Boolean checkIfFollows(Node test) {
        for (Edge edge : edges) {
            Node n = edge.getEnd();
            if (n == test)
                return true;
        } 
        return false; 
    }

}