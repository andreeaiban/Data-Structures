/*
Programmer: Andreea Ibanescu
Class: CSCI 313
Polynomial Class as a Linkedlist using subclasses -> Nodes/Term classes
Including different math methods to evaulate a polynomial
 */

public class Polynomial {
    private Node head;
    private int degree;

    public Polynomial() {
        head = new Node();
    }

    //Calculate the power of a coef & degree
    public int powDegree(int a, int b) {
        //a^b
        int sum = 1;
        for (int i = 0; i < b; i++) {
            sum *= a;
        }
        return sum;
    }

    //Find f(x) of a poly and return the sum as a double
    public double evaluate(int x) {
        int total = 0;
        int pow;
        double coef;
        Node curr = head.next;
        while (curr != null) {
            pow = curr.term.getPower();
            coef = curr.term.getCoeff();
            total += coef * powDegree(x, pow);
            curr = curr.next;
        }
        return total;
    }

    //Scale the poly by a
    public void scale(int a) {
        Node curr = head.next;
        double coef;
        while (curr != null) {
            coef = curr.term.getCoeff();
            curr.term.setCoeff(coef * a);
            curr = curr.next;
        }
    }

    //method to see the poly linkedlist so that i can check what my code is actually doing
    public String toString() {
        String result = "";
        Node curr = head.next;
        while (curr != null) {
            result += curr.term + " " + "+" + " ";
            curr = curr.next;
        }
        return result;
    }

    //Insert or add more parts to the poly linkedlist
    public void insertSorted(Terms t) {
        Node curr = head.next;
        Node prev = head;

        //compareTO() returns the int of the greater power
        while ((curr != null) && (curr.term.compareTo(t) < 0)) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = new Node(t, curr);
    }

    //Used at the start, didn't work. Useless method for this project.
    public void delete(Terms t) {
        Node curr = head.next;
        Node prev = head;
        while (curr != null && curr.term.compareTo(t) < 0) {
            if (curr.term == t) {
                prev.next = curr.next;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    //Sum of r=p+q (WORKS)
    public Polynomial sum(Polynomial q) {
        Node p1 = head.next;
        Node p2 = q.head.next;

        //Create the result poly for r=p+q
        Polynomial r = new Polynomial();

        //Make sure u don't get a null exemption caused by the polys being empty
        while (p1 != null || p2 != null)
        {
            //If this part is empty then insert the second one then stop the loop
            if(p1 == null)
            {
                Terms t = new Terms(p2.term.coeff,p2.term.power);
                r.insertSorted(t);
                p2 = p2.next;
                break;
            }
            //If the next part is empty then insert the first part then stop the loop
            else if(p2 == null)
            {
                Terms t = new Terms(p1.term.coeff,p1.term.power);
                r.insertSorted(t);
                p1 = p1.next;
                break;
            }
            //If powers r the same, we can finally add the liketerms
            else if(p1.term.power == p2.term.power)
            {
                Terms t = new Terms(p1.term.coeff+p2.term.coeff,p1.term.power);
                r.insertSorted(t);
                p1 = p1.next;
                p2 = p2.next;
            }
            //Just insert value into the result because its no like terms since p1>p2
            else if (p1.term.power> p2.term.power)
            {
                Terms t = new Terms(p1.term.coeff,p1.term.power);
                r.insertSorted(t);

                p1 = p1.next;

            }
            //Like before p2>p1 so u just insert value because no like terms
            else if(p2.term.power > p1.term.power)
            {
                Terms t = new Terms(p2.term.coeff,p2.term.power);
                r.insertSorted(t);
                p2 = p2.next;
            }
        }
        //return the result
        return r;

    }

    //Add p=p+q (WORKS)
    public void add(Polynomial q) {
        Node currP = head.next;
        Node currQ = q.head.next;

        //Create a temporary poly
        Polynomial temp = new Polynomial();

        //Do p+q and store it into the temp
        temp=this.sum(q);

        //start point to tranverse thought p
        this.head.next = null;

        //tranverse and transfer temp poly values into p
        for(Node curr = temp.head.next; curr!= null; curr = curr.next)
        {
            Terms temp2 = new Terms(curr.term.coeff,curr.term.power);
            this.insertSorted(temp2);
        }
        //P will be p=p+q by the end of the for loop
    }
    //r=p-q (WORKS)
    public Polynomial diff(Polynomial q)
    {
        Node p1 = this.head.next;
        Node p2 = q.head.next;

        //R will be the result poly of the diff
        Polynomial r = new Polynomial();

        while (p1 != null || p2 != null)
        {
            if(p1 == null)
            {
                Terms t = new Terms(p2.term.coeff,p2.term.power);
                r.insertSorted(t);
                p2 = p2.next;
                break;
            }
            else if(p2 == null)
            {
                Terms t = new Terms(p1.term.coeff,p1.term.power);
                r.insertSorted(t);
                p1 = p1.next;
                break;
            }
            //If the powers match, you can sub the like terms, keeping the power the same
            else if(p1.term.power == p2.term.power)
            {
                Terms t = new Terms(p1.term.coeff-p2.term.coeff,p1.term.power);
                r.insertSorted(t);
                p1 = p1.next;
                p2 = p2.next;
            }
            //If theres p1>p2 power that means theres no like terms in this part so u add it to the poly, since u can't add unless its like terms
            else if (p1.term.power> p2.term.power)
            {
                Terms t = new Terms(p1.term.coeff,p1.term.power);
                r.insertSorted(t);

                p1 = p1.next;

            }
            //If its p2>p1 the as before there no like terms,add to the poly (no like terms)
            else if(p2.term.power > p1.term.power)
            {
                Terms t = new Terms(p2.term.coeff,p2.term.power);
                r.insertSorted(t);
                p2 = p2.next;
            }
        }
        //return the result poly, of r=p-q
        return r;
    }

    //p=p-q (WORKS)
    public void sub(Polynomial q) {
        Node p1 = this.head.next;
        Node p2 = q.head.next;

        //Create a temporary poly
        Polynomial temp = new Polynomial();

        //store the diff of p-q into the temp poly
        temp=diff(q);

        //Startpoint to Tranverse from the start of poly p
        this.head.next = null;

        //Start the Tranverse
        for(Node curr = temp.head.next; curr!= null; curr = curr.next)
        {
            Terms temp2 = new Terms(curr.term.coeff,curr.term.power);
            //insert terms from temp to p
            this.insertSorted(temp2);
        }
        //p will be p=p-q by the end of the for loop
    }

    //p=p*q
    public void multi(Polynomial q){
        Node currP = head.next;
        Node currQ = q.head.next;
        Polynomial temp = new Polynomial();

        //Multiply p*q and store product in a temp poly
        temp=product(q);

        Node tempCurr= temp.head.next;
        //Set product values of temp poly  into p poly w/o destroying p
        while(tempCurr!=null){
            if(currP!=null){
                currP.term.setCoeff(tempCurr.term.coeff);
                currP.term.setPower(tempCurr.term.power);
                currP=currP.next;
                tempCurr=tempCurr.next;
            }
            else {
                //add the rest of the temp values into the p poly
                Terms temp2 = new Terms(tempCurr.term.coeff, tempCurr.term.power);
                insertSorted(temp2);
                tempCurr=tempCurr.next;
            }
        }
    }

    //r=p*q
    public Polynomial product(Polynomial q){
        Node currP = head.next;
        Node currQ = q.head.next;
        Node partp = head.next;
        Node partq = q.head.next;

        Polynomial r = new Polynomial();

        //To do foil when you multiply you gotta tranverse through p first term to all of the terms in q & vice versa
        while(  partp!=null){
            while(partq!=null){
                Terms temp1 = new Terms((partp.term.coeff)*(partq.term.coeff), (partq.term.power)+(partp.term.power));
                r.insertSorted(temp1);
                partq=partq.next;
            }
            partq=currQ;
            partp=partp.next;
        }
        return r;
    }

}




