import java.util.ArrayList;

public class socialNetwork {

    private Graph graph;
    private inputReader ir;
    private ArrayList<String> lines;

    // constructor to combine and run all functions
    public socialNetwork() {
        String filePath = "test-socialnetworks/social-network1.txt";

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
            graph.createPerson(names);
        }
    }

    public static void main(String[] args) {
        socialNetwork Dapper = new socialNetwork();
    }
}
