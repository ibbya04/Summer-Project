public class Edge {
    private Node start;
    private Node end;

    public Edge(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    // return where node starts from
    public Node getStart() {
        return this.start;
    }

    // return where node ends
    public Node getEnd() {
        return this.end;
    }
}
