public class socialNetwork {

    private Graph graph;
    private inputReader ir;

    public socialNetwork() {
        String filePath = "test-socialnetworks/social-network1.txt";

        this.createGraph(filePath);
        graph.printGraph();
    }

    //
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
