public class Node {
    protected Terms term;
    protected Node next;

    //Empty "dummy" Node
    public Node() {
        term = null;
        next = null;
    }
    //Set a Node by the terms
    public Node(Terms term) {
        this.term = term;
        this.next = null;
    }

    public Node(Terms term, Node next) {
        this.term = term;
        this.next = next;
    }

    //let the user add terms
    public void setTerms(Terms term) {
        this.term = term;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    //get the Node's terms
    public Terms getTerms() {
        return this.term;
    }

    //get the Node
    public Node getNode() {
        return this.next;
    }

}