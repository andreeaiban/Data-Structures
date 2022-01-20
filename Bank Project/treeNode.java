public class treeNode {
    protected Customer data;
    protected treeNode left,right;

    //Set a Node by the terms
    public treeNode(Customer data) {
        this.data = data;
        this.left = null;
        this.right=null;
    }
    //create treeNode
    public treeNode(Customer data,treeNode left, treeNode right ){
        this.data=data;
        this.left=left;
        this.right=right;
    }
    //Set the left side treeNode
    public void setleft(treeNode left) {
        this.left = left;
    }
    //set the right side node
    public void setright(treeNode right){
        this.right=right;
    }
    //get the Node's terms
    public Customer getData() {
        return this.data;
    }
    //get the left side node
    public treeNode getleft() {
        return this.left;
    }
    //get the right side node
    public treeNode getRight(){
        return this.right;
    }
}
