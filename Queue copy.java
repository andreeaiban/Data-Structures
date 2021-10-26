/*
Queue Code w/ Array implementation
 */
public class Queue {
    private static int front, rear, count;
    public static int[] data;

    public Queue(int size)
    {
        data=new int[size];
        front=0;
        rear=-1;
        count=0;
    }
    //Method checks if Queue is empty returns true
    public boolean isEmpty(){
        return count==0;
    }

    //Function to insert an element
    public void enQ(int x){
        if(count==data.length){
            throw new IllegalArgumentException("Array is Full, need to increase size before enQ");
        }
        rear=(rear+1)%data.length;
        data[rear]=x;
        count++;
    }
    // Function to delete an element from the front b/c Queues are FIFO
    public int deQ(){
        if(count==0){
            throw new IllegalArgumentException("Array is empty, nothing to deQ");
        }
        int oldFront=data[front];
        front=(front+1)%data.length;
        count--;

        return oldFront;
    }
}
