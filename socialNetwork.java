import java.util.ArrayList;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

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

    public Node getFirstPerson() {
        //ArrayList<String> line = ir.readLine(filePath);
        String[] firstline = ir.formatLine(lines, 0);
        Node firstPerson = graph.findNode(firstline[0]);
        return firstPerson;
        }

    public void twoDegreeesofSeparation() {
        Node firstPerson = getFirstPerson();
        Set<Node> followers = graph.findFollowers(firstPerson);

        int count = 0;
        for (Node follower : followers) {
            Set<Node> followersOfFollowers = null; 
            followersOfFollowers = graph.findFollowers(follower);
            
            for (Node secondfollower : followersOfFollowers) {
                count++;
            }
        }
        System.out.println(count);
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
