import java.util.ArrayList;

public class socialNetwork {

    private Graph graph;
    private inputReader ir;
    private ArrayList<String> lines;

    // constructor to combine and run all functions
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
    public float calculateDensity() {
        float numEdges = graph.getNumEdges();
        float numNodes = graph.getNumNodes();

        float density = numEdges / (numNodes*(numNodes-1));
        return density;
    }

    public static void main(String[] args) {
        String filePath = "test-socialnetworks/social-network1.txt";

        // Check if command line argument has more than one argument
        // if (args.length != 1) {
        //     usage();
        //     return;
        // }

        socialNetwork Dapper = new socialNetwork(filePath);
        System.out.println(Dapper.calculateDensity());
    }

    private static void usage() {
        System.out.println("Usage: java socialNetwork <file.txt>");
    }
}
