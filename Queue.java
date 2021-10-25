/*
Queue using Linked Implementation
*/

public class Queue {
    private Node front,rear;

    //Check if Queue is empty, return true if yes.
    public boolean isEmpty(){
        return front==null;
    }
    //Enter data into the queue, add data
    public void enQ(int x){
        if(rear!=null){
            rear.next=new Node(x);
            rear=rear.next;
        }
        else{
            rear=new Node(x);
            front=rear;
        }
    }
    //Delete Queue, delete the first Node. B/c Queues are FIFO
    public int deQ(){
        if(front==null) {
            throw new IllegalArgumentException("Front of the Queue is empty. ");
        }
        int oldFront=front.data;
        front=front.next;
        if(front==null)
            rear=null;
        return oldFront;
    }
}
