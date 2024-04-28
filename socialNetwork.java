public class socialNetwork {

    private Graph graph;
    private inputReader ir;

    // constructor to combine and run all functions
    public socialNetwork() {
        String filePath = "test-socialnetworks/social-network1.txt";

        this.createGraph(filePath);

        //testing
        graph.printGraph();
    }

    // creates a graph by calling readLine function to get line of text
    // then createPerson function creates nodes & edges for that line of people/ 
    public void createGraph(String filePath) {
        ir = new inputReader();
        graph = new Graph();

        String[] line = ir.readLine(filePath);
        graph.createPerson(line);
    }

    public static void main(String[] args) {
        socialNetwork Dapper = new socialNetwork();
    }
}
