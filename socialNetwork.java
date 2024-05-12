import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class socialNetwork {

    private Graph graph;
    private inputReader ir;
    private ArrayList<String> lines;

    // constructor to create graph
    public socialNetwork(String filePath) {

        this.lines = new ArrayList<String>();
        this.createGraph(filePath);

        //testing
        graph.printGraph();
    }

    // creates a graph by calling readLine function to get line of text
    // then createPerson function creates nodes & edges for that line of people/ 
    public void createGraph(String filePath) {
        ir = new inputReader();
        graph = new Graph();

        lines = ir.readLine(filePath);

        for (int i = 0; i < lines.size(); i++) {
            String[] names = ir.formatLine(lines, i);
            graph.createPeople(names);
        }
    }

    // Calcultes density of the social network
    public void calculateDensity() {
        float numEdges = graph.getNumEdges();
        float numNodes = graph.getNumNodes();

        float density = numEdges / (numNodes*(numNodes-1));
        System.out.println("Task 1: " + density);
    }

    // Calls checkNumberofFollowers and prints result
    public void mostFollowers() {
        Node mostFollowers = graph.findHighestFollowers();
        System.out.println("Task 2: " + mostFollowers.getName());
    }

    // Calls checkNumberofFollowing and prints result
    public void mostFollowing() {
        Node mostFollowing = graph.findHighestFollowing();
        System.out.println("Task 3: " + mostFollowing.getName());
    }

    // Returns the first person who appears in the input
    // Uses formatLine function from inputReader to get a string array of all the names on the first line
    // Finds the node of the first person on that line and returns that node. 
    public Node getFirstPerson() {
        String[] firstline = ir.formatLine(lines, 0);
        Node firstPerson = graph.findNode(firstline[0]);
        return firstPerson;
        }

    // Removes the first person from the set followersOfFollowers
    // Removes any duplicates which are already followers of the first person.
    public Set<Node> removeDuplicates(Node firstPerson, Set<Node> followers, Set<Node> followersOfFollowers) {
        if (followersOfFollowers.contains(firstPerson)) 
            followersOfFollowers.remove(firstPerson);

        followersOfFollowers.removeAll(followers);
        return followersOfFollowers;
    }

    // Returns number of people at two degrees of separation.
    // Calls getFirstPerson to get the first person from the input.
    // Both groups of followers are put into Sets, no duplicates, unordered.
    // The set of followers of the first person is found by calling findFollowers.
    // Then it iterates through each of these followers adding their followers
    // to the set followersOfFollowers. Then duplicates are removed.
    public void twoDegreeesofSeparation() {
        Node firstPerson = getFirstPerson();
        Set<Node> followers = graph.findFollowers(firstPerson);

        Set<Node> followersOfFollowers = new HashSet<Node>();
        for (Node follower : followers)
            followersOfFollowers.addAll(graph.findFollowers(follower));

        removeDuplicates(firstPerson, followers, followersOfFollowers);

        System.out.println("Task 4: " + followersOfFollowers.size());
    }
        

    public static void main(String[] args) {
        String filePath = "test-socialnetworks/social-network1.txt";

        // Check if command line argument has more than one argument
        // if (args.length != 1) {
        //     usage();
        //     return;
        // }

        socialNetwork Dapper = new socialNetwork(filePath);
        Dapper.calculateDensity();
        Dapper.mostFollowers();
        Dapper.mostFollowing();
        Dapper.twoDegreeesofSeparation();
    }

    private static void usage() {
        System.out.println("Usage: java socialNetwork <file.txt>");
    }
}
