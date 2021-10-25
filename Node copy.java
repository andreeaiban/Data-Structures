public class Node {
    protected int data;
    protected Node next;

    //Empty "dummy" Node
    public Node() {
        this.data=0;
        this.next = null;
    }
    //Set a Node by the terms
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
    public Node(int data,Node next){
        this.data=data;
        this.next=next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    //get the Node's terms
    public int getData() {
        return this.data;
    }
    //get the Node
    public Node getNode() {
        return this.next;
    }

}