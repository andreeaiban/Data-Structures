/*
Andreea Ibanescu
Nov 20 2021
CSCI 313: Project1 BTS structure w Customer class as it's nodes
 */
import java.util.Random;
public class myBST {
    //Attributes
    treeNode root;

    // Is the BST Empty? returns true if bts is empty
    public boolean isEmpty(){
        return root==null;
    }
    //Insert
    public void insert(Customer c){
        root=insert(c,root);
    }
    //Insert method
    private treeNode insert(Customer c, treeNode t) {

        if (t!=null)
           {
            if(t.data.compareTo(c)>0){
                //c is less than t
                t.left=insert(c,t.left);
            }
            else
                //c is greater than t
                t.right=insert(c,t.right);
               return t;
        }
        return new treeNode(c);
    }
    //In order transversal
    public void inOrder(){
        inOrder(root);
        System.out.println(" ");
    }
    //In order transversal
    public void inOrder(treeNode t){
        if(t!=null){
            inOrder(t.getleft());
            System.out.println(t.getData() + " ");
            inOrder(t.getRight());
        }
    }
    //Search
    public boolean search2(Customer c){
        return search2(c,root);
    }
    private boolean search2(Customer c, treeNode t){
    if(t!=null){
        //if c is less than t
        if(t.data.compareTo(c)>0)
            return search2(c,t.getleft());
        //if c is greater than t
        else if(t.data.compareTo(c)<0)
            return search2(c,t.getRight());
        else
            return true;
    }
    return false;
    }
    //Search but returns the customer that matches
    public Customer search(Customer c) {
        return search(c,root);
    }
    private Customer search (Customer c, treeNode t)  {

        if (t != null) {
            //if temp is less than t
            if (t.data.compareTo(c) > 0)
                return search(c, t.getleft());
                //if temp is greater than t
            else if (t.data.compareTo(c)< 0)
                return search(c, t.getRight());
            else
                return t.getData();
        }
        //null
        return null;
    }
    //FindMin
    public Customer findMin(){
        return findMin(root).data;
    }
    private treeNode findMin(treeNode t){
        if(t!=null){
            //keep going left until its the last child
            if(t.getleft()!=null){
                return findMin(t.getleft());
            }
            else
                //reached the last node on left
                return t;
        }
        //no node found
        return null;
    }
    //FindMax
    public Customer findMax(){
        return findMax(root).data;
    }
    private treeNode findMax(treeNode t){
        if(t!=null){
            //Check right side
            if(t.getRight()!=null)
                return findMax(t.getRight());
            else
                //reached the last node on right
                return t;
        }
        //no node found
        return null;
    }
    //Delete recursion
    public void delete(Customer c){
     root=delete(c,root);
    }
    //Delete function
    private treeNode delete(Customer c,treeNode t){
        if(t==null){
            return null;
        }
        if(t.data.compareTo(c)>0)
            t.setleft(delete(c,t.getleft()));
            else if(t.data.compareTo(c)<0)
            t.setright(delete(c,t.getRight()));
            else{
                //if the root is not < or >
                if(t.right==null)
                    return t.left;
                if(t.left==null)
                    return t.right;

                t=findMax(t.left);
                t.left=delete(t.data,t.left);
        }
            return t;
    }
    //To String method otherwise the binary search tree will be printed by its address not its contents
    public String toString()
    {
        return toString (root);
    }
    private String toString(treeNode root)
    {
        String result = "";
        if (root == null)
            return "";
        result += toString(root.left);
        result += toString(root.right);
        result += root.data.toString();
        return result;
    }
}
