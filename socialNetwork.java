import java.util.ArrayList;
import java.util.Arrays;
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
    }

    // creates a graph by calling readLine function to get line of text
    // then createPerson function creates nodes & edges for that line of people/ 
    public void createGraph(String filePath) {
        ir = new inputReader();
        graph = new Graph();

        lines = ir.readLines(filePath);

        for (int i = 0; i < lines.size(); i++) {
            String[] names = ir.formatLine(lines, i);
            graph.createPeople(names);
        }
    }

    // Calculates the density of the graph - Task 1
    public void calculateDensity() {
        int numEdges = graph.getNumEdges();
        int numNodes = graph.getNumNodes();

        float density = (float) numEdges / (numNodes*(numNodes-1));
        System.out.println("Task 1: " + density);
    }

    // Calls findHighestFollowers and prints the result - Task 2
    public void mostFollowers() {
        Node mostFollowers = graph.findHighestFollowers();
        System.out.println("Task 2: " + mostFollowers.getName());
    }

    // Calls findHighestFollowing and prints the result - Task 3
    public void mostFollowing() {
        Node mostFollowing = graph.findHighestFollowing();
        System.out.println("Task 3: " + mostFollowing.getName());
    }

    // Returns the first person who appears in the input
    // Uses formatLine function from inputReader to get a string array of all the names on the first line
    // Returns the node of the first person on that line 
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

    // Returns number of people at two degrees of separation from the first person.
    // Uses sets, no duplicates, unordered - Task 4
    public void twoDegreeesofSeparation() {
        // gets the first person from the input and adds their followers to a set
        Node firstPerson = getFirstPerson();
        Set<Node> followers = graph.findFollowers(firstPerson);

        // iterates through each of these followers adding all of the follower's, follower's
        // to the set followersOfFollowers. Removes duplicates then prints result.
        Set<Node> followersOfFollowers = new HashSet<Node>();
        for (Node follower : followers)
            followersOfFollowers.addAll(graph.findFollowers(follower));

        removeDuplicates(firstPerson, followers, followersOfFollowers);

        System.out.println("Task 4: " + followersOfFollowers.size());
    }
    
    // Finds the median value for the number of followers in the network - Task 5
    public void getMedianFollowers() {
        // Finds the number of people in this network
        // creates a new array of size, number of people in this network
        int numberOfPeople = graph.getNumNodes();
        int[] numFollowers = new int[numberOfPeople];

        // populates each instance of the array with each person's number of followers
        numFollowers = graph.initialiseFollowersArray(numFollowers);
        Arrays.sort(numFollowers);

        // Calculates median value, deals with case if number of people is even and prints
        System.out.println("Task 5: " + graph.calculateMedian(numFollowers, numberOfPeople));
    }
    
    // Finds best person to spread a message - Task 6
    public void findBestMessagePropogater() {
        Node highestReach = graph.findHighestReach();

        System.out.println("Task 6: "+ highestReach.getName());
    }

    public static void main(String[] args) {
        // Check if command line argument has more than one argument
        if (args.length != 1) {
            usage();
            return;
        }

        socialNetwork Dapper = new socialNetwork(args[0]);
        Dapper.calculateDensity();
        Dapper.mostFollowers();
        Dapper.mostFollowing();
        Dapper.twoDegreeesofSeparation();
        Dapper.getMedianFollowers();
        Dapper.findBestMessagePropogater();
    }

    private static void usage() {
        System.out.println("Usage: java socialNetwork <file.txt>");
    }
}
