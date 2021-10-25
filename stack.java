/*
 Stack Code W/ Linked implementation (Dynamic Structure)
 */
public class stack {
    private Node top;

    //Check stack if empty, return true. otherwise false.
    public boolean isEmpty(){
        return(top==null);
    }
    //Add a new Node on top of the stack
    public void push(int x){
        top=new Node(x,top);
    }
    //Get the value of the first Node
    public int peek(){
        if(isEmpty()){
            throw new IllegalArgumentException("Stack is empty. ");
        }
        return top.data;
    }
    //Remove the first Node b/c stacks are LIFO
    public int pop(){
        if(isEmpty())
            throw new IllegalArgumentException("Stack is empty. ");

        int oldTop=top.data;
        top=top.next;
        return oldTop;
    }
}
