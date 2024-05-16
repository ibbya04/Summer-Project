import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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
    public int getNumEdges() {
        for (int i = 0; i < this.nodes.size(); i++)
            numEdges += this.nodes.get(i).getNodesEdges();
        return numEdges;
    }

    // Returns number of nodes in this graph
    public int getNumNodes() {
        return this.nodes.size();
    }

    // Checks each persons number of followers
    // Calls checkFollowers on each person using a for each loop, 
    // updating everyones follower count
    public Node findHighestFollowers() {
        for (Node person : this.nodes) {
            person.checkFollowers();
        }

        // initialises person (node) with most followers to first node
        Node mostFollowers = this.nodes.get(0);

        // for each loop iterates through all nodes (all people)
        // gets each persons number of followers and if greater than
        // current person with most followers, update Node mostFollowers
        for (Node person: this.nodes) {
            if (person.getNumFollowers() > mostFollowers.getNumFollowers()) {
                mostFollowers = person;
            }

            // if multiple people with same amount of followers, return first alphabetically
            else if ( person.getNumFollowers() == mostFollowers.getNumFollowers()) {
                mostFollowers = person.returnFirstName(mostFollowers);
            }
        }
        return mostFollowers;
    }

    // Checks number of people each person follows
    // Calls checkFollowing on each person using a for each loop, 
    // updating everyones following count
    public Node findHighestFollowing() {
        for (Node person : this.nodes) {
            person.checkFollowing();
        }

        // initialises person (node) following most poeple to first node
        Node mostFollowing = this.nodes.get(0);

        // for each loop iterates through all nodes (all people)
        // gets number of people each persons follows and if greater than
        // current person with most following, update Node mostFollowing
        for (Node person: this.nodes) {
            if (person.getNumFollowing() > mostFollowing.getNumFollowing()) {
                mostFollowing = person;
            }

            // if multiple people follow the same amount of people, return first alphabetically
            else if ( person.getNumFollowing() == mostFollowing.getNumFollowing()) {
                mostFollowing = person.returnFirstName(mostFollowing);
            }
        }
        return mostFollowing;
    }

    // Finds followers of a person.
    // Iterates through every person (node) in this graph
    // If checkIfFollows is true, that person is added to the 
    // test person's followers. 
    public Set<Node> findFollowers(Node testPerson) {
        Set<Node> followers = new HashSet<Node>();

        for (Node person : this.nodes) {
            if (person.checkIfFollows(testPerson) == true)
                 followers.add(person);
        }
        return followers;
    }

    // populates follower array with each persons number of followers
    // using a for loop, then returns followers array
    public int[] initialiseFollowersArray(int[] followers) {
        for (int i = 0; i < this.nodes.size(); i++) {
            Node person = this.nodes.get(i); 
            followers[i] = person.getNumFollowers();
        }
        return followers;
    }

    // returns finds median value
    public int ifMedianisEven(int[] followers, int numberOfPeople) {
        int median = 0;

        if (numberOfPeople % 2 == 0)
            // if number of people is even, takes both middle elements into calculation
            median = (followers[numberOfPeople/2] + followers[(numberOfPeople/2) - 1]) / 2;
        else 
            median = followers[numberOfPeople/2];
        return median;
    }

    // Find a single persons reach, the number of people the message will spread to.
    public void findReach (Node testCase) {
        // initialise recipients set and queue
        Set<Node> recipients = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(testCase);

        // BFS
        // for every person in the queue,
        // find their followers and add them to the queue
        // if the follower ! already in set of recipients, add them
        while(queue.size() > 0) {
            // pop from the queue and make that person 'current'
            // add person to recipients
            Node current = queue.poll();
            recipients.add(current);

            // find current person's followers
            Set<Node> followers = findFollowers(current);

            // for each of their followers
            // if follower not already in recipients add to queue 
            for (Node follower : followers) {
                if (!recipients.contains(follower))
                    queue.add(follower);
            }
        }

        // For this person we calculated the lsit of recipients for,
        // i.e the number of people the message will spread to,
        // set their reach to this
        testCase.setReach(recipients.size());
    }

    public Node findHighestReach() {
        // initialise person with highest reach
        Node highestReach = this.nodes.get(0);

        for (Node person : this.nodes) {
            findReach(person);
            if (person.getReach() > highestReach.getReach())
                highestReach = person;
        }
        return highestReach;
    }

    // testing
    public static void main(String[] args) {
        Graph socialNetwork = new Graph();
        Node Ibraheem = socialNetwork.addNode("ibraheem");
        Node Ibby = socialNetwork.addNode("ibby");
        Node IA = socialNetwork.addNode("IA");

        socialNetwork.addEdge(Ibby, Ibraheem);
        socialNetwork.addEdge(IA, Ibraheem);
        socialNetwork.addEdge(IA, Ibby);
        socialNetwork.printGraph();

        Set<Node> followers = socialNetwork.findFollowers(Ibraheem);
    }
}
