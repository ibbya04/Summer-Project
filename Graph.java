import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Graph {

    private ArrayList<Node> nodes;
    private int numEdges;

    // constructor
    public Graph() {
        this.nodes = new ArrayList<Node>();
    }

    // Adds a node to the ArrayList of nodes.
    // checks if node is already in ArrayList, if not it creates
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

    // Removes a node from the array list
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

    // checks if a node is in the array list of nodes, using the node's name
    // Returns null if node is not found, else returns node
    public Node findNode(String name) {
        for (int i = 0; i < this.nodes.size(); i++) {
            Node node = this.nodes.get(i);
            if (node.getName().equals(name))
                return node;
        }
        return null;
    }

    // iterates over all graphs nodes, printing each node and its edges
    public void printGraph() {
        for (Node n : this.nodes)
            n.printNode();
    }

    // Creates a node for every person from an array of names (single line from input file)
    public void createPeople(String[] names) {
        // for each loop creating a node for every name within array
        for (String person : names)
            this.addNode(person);

        // iterates through the array of names,  
        // start node is always first name in the array
        // iterates through all other names creating a directed edge 
        // between first name and nth name.
        for (int i = 1; i < names.length; i++) {
            Node startNode = findNode(names[0]);
            Node endNode = findNode(names[i]);
            startNode.addEdge(endNode);
        }
    }

    // Returns number of edges in this graph.
    // calls getNodesEdges to get number of edges 
    // for every node in this graph
    public int getNumEdges() {
        for (int i = 0; i < this.nodes.size(); i++)
            numEdges += this.nodes.get(i).getNodesEdges();
        return numEdges;
    }

    // Returns number of nodes in this graph
    public int getNumNodes() {
        return this.nodes.size();
    }

    // Finds the person who has the most followers
    public Node findHighestFollowers() {
        // updates every persons followers count
        for (Node person : this.nodes) 
            person.updateFollowers();

        // initialises person (node) with most followers to first node
        Node mostFollowers = this.nodes.get(0);

        // for each loop iterates through all nodes (all people)
        // gets each persons number of followers and if greater than
        // current person with most followers, update Node mostFollowers
        for (Node person : this.nodes) {
            if (person.getNumFollowers() > mostFollowers.getNumFollowers())
                mostFollowers = person;

            // if multiple people with same amount of followers, return first alphabetically
            else if (person.getNumFollowers() == mostFollowers.getNumFollowers())
                mostFollowers = person.returnFirstName(mostFollowers);
        }
        return mostFollowers;
    }

    // Finds the person who follows the most people
    public Node findHighestFollowing() {
        // updates every person following count
        for (Node person : this.nodes) 
            person.updateFollowing();

        // initialises person (node) following most poeple to first node
        Node mostFollowing = this.nodes.get(0);

        // for each loop iterates through all nodes (all people)
        // gets number of people each persons follows and if greater than
        // current person with most following, update Node mostFollowing
        for (Node person : this.nodes) {
            if (person.getNumFollowing() > mostFollowing.getNumFollowing()) 
                mostFollowing = person;

            // if multiple people follow the same amount of people, return first alphabetically
            else if (person.getNumFollowing() == mostFollowing.getNumFollowing())
                mostFollowing = person.returnFirstName(mostFollowing);
        }
        return mostFollowing;
    }

    // Finds followers of the person passed as a parameter.
    // Iterates through every person (node) in this graph
    // if person follows testPerson, the person is added to the set. 
    public Set<Node> findFollowers(Node testPerson) {
        Set<Node> followers = new HashSet<>();

        for (Node person : this.nodes) {
            if (person.checkIfFollows(testPerson) == true)
                 followers.add(person);
        }
        return followers;
    }

    // populates follower array with each person's number of followers
    // using a for loop, then returns the array
    public int[] initialiseFollowersArray(int[] followers) {
        for (int i = 0; i < this.nodes.size(); i++) {
            Node person = this.nodes.get(i); 
            followers[i] = person.getNumFollowers();
        }
        return followers;
    }

    // finds the median value from
    public int calculateMedian(int[] numFollowers, int numberOfPeople) {
        int median = 0;

        if (numberOfPeople % 2 == 0)
            // if number of people is even, takes both middle elements into calculation
            median = (numFollowers[numberOfPeople/2] + numFollowers[(numberOfPeople/2) - 1]) / 2;
        else 
            median = numFollowers[numberOfPeople/2];
        return median;
    }

    // Finds a single persons reach, the number of people the message will spread to.
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
        // set their reach to the size of the set
        testCase.setReach(recipients.size());
    }

    // Finds person with largest reach
    public Node findHighestReach() {
        // initialise person with highest reach
        Node highestReach = this.nodes.get(0);

        // iterates through each person, checking if their reach is >
        // returns alphabetically first name if both have same reach
        for (Node person : this.nodes) {
            findReach(person);
            if (person.getReach() > highestReach.getReach())
                highestReach = person;
            else if (person.getReach() == highestReach.getReach())
                highestReach = person.returnFirstName(highestReach);
        }
        return highestReach;
    }
}
