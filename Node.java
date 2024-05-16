import java.util.ArrayList;

public class Node {

    private String name;
    private ArrayList<Edge> edges;
    private int followers;
    private int following;
    private int reach;

    // constructor
    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<Edge>();
    }

    // Adds an edge to this node's arraylist of edges, using the add keyword from
    // ArrayList lib, initiallising the new edge with constructor.
    public void addEdge(Node endNode) {
        this.edges.add(new Edge(this, endNode));
    }

    // Removes a node's edge by iterating through all the node's edges,
    // until the edges endNode, is equal to the endNode passed 
    // into function, then removes using remove func from library.
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
    public void printNode() {
        String message = "";

        // if node has no edges
        if (this.edges.size() == 0) {
            System.out.println(this.name + " -->");
            return;
        }

        // iterates through all of a single nodes edges, printing each one
        for (int i = 0; i < this.edges.size(); i++) {
            if (i == 0)
                message += this.edges.get(i).getStart().name + " --> ";
            else
                message += ", ";
            
            message += this.edges.get(i).getEnd().name;
        }
        System.out.println(message);
    }

    // returns number of edges of this single node
    public int getNodesEdges() {
        return this.edges.size();
    }

    // iterates through all of this node's edge's, finds the start node of each edge,
    // i.e. the person who is doing the following, and adds one to their following count
    public void updateFollowing() {
        for (Edge edge : edges) {
            Node n = edge.getStart();
            n.following ++;
        } 
    }

    // Returns how many people node(person) is following
    public int getNumFollowing() {
        return this.following;
    }

    // iterates through all of this nodes edges, find the end node of each edge,
    // i.e. the person who is being followed, and adds one to their follower count
    public void updateFollowers() {
        for (Edge edge : edges) {
            Node n = edge.getEnd();
            n.followers ++;
        } 
    }

    // Returns how many people followers this node(person) has
    public int getNumFollowers() {
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
    // iterates through all of the first persons edges, if any of the
    // end nodes == the test person, 1st person follows test person.
    public Boolean checkIfFollows(Node test) {
        for (Edge edge : edges) {
            Node n = edge.getEnd();
            if (n == test)
                return true;
        } 
        return false; 
    }

    public int getReach() {
        return this.reach;
    }

    public void setReach(int reach) {
        this.reach = reach;
    }
}