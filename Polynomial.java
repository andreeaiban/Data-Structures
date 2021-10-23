import javax.swing.*;
/* Programmer: Andreea Ibanescu
Part A: Create a polynomial class in java with basic mathematical methods
 */
public class Polynomial {
    //declaring variables/attributes
    private double[] ceofs;
    private int deg=0;

    //Constructor of a Polynomial
    public Polynomial(double []c) {
        int len=c.length;
        ceofs= new double[len];
        for(int i=0; i<len; i++)
            ceofs[i]=c[i];

    }
    /* Useless Method
    public int getDegree(int c) {
        int d=0;
        for (int i = 0; i < ceofs.length; i++)
            if (ceofs[i] == c)
                d=i;
                return d;
    }
     */
    //My own version of Math.power to calculate a^b and return the answer in an int
    public int powDegree(int a, int b){
        //a^b
        int sum=1;
        for(int i=0; i<b; i++){
            sum*=a;
        }
        return sum;
    }
    //Function to find f(x) of a polynomial
    public double evaluate(int x){
    int total=0;
    double cur=0;
    for(int i=0; i<ceofs.length; i++) {
        if (i > 0) {
            cur=ceofs[i];
            total += (powDegree(x, i))*cur;
        } else
            total += ceofs[i];
    }
        return total;
    }
    //Will multiply the polynomial with a constant p=a*p
    public void scale(int a){
      double cur=0;
      for(int i=0; i<ceofs.length; i++){
          cur=ceofs[i];
          ceofs[i]=cur*a;
      }
    }

    //Add a polynomial p to the polynomial q, so p=p+q
    public void add(Polynomial q) {
        int lenq = q.ceofs.length;
        int lenp = ceofs.length;
        int final_len = 0;
        if (lenq > lenp)
            final_len = lenq;
        else
            final_len = lenp;
        double[] newcoefs = new double[final_len];

        for (int i = 0; i < final_len; i++) {
            if (i < lenq && i < lenp)
                newcoefs[i] = q.ceofs[i] + ceofs[i];
            else if (i < lenq && i >= lenp)
                newcoefs[i] = q.ceofs[i];
            else
                newcoefs[i] = ceofs[i];
        }
        ceofs = newcoefs;
    }
    //Subtract a polynomial p to the polynomial q, so p=p-q
    public void subtract(Polynomial q){
        int lenq = q.ceofs.length;
        int lenp = ceofs.length;
        int final_len = 0;

        if (lenq > lenp)
            final_len = lenq;
        else
            final_len = lenp;

        double[] newcoefs = new double[final_len];
        for (int i = 0; i < final_len; i++) {
            if (i < lenq && i < lenp)
                newcoefs[i] = ceofs[i]-q.ceofs[i];
            else if (i < lenq && i >= lenp)
                newcoefs[i] = (-1)*q.ceofs[i];
            else
                newcoefs[i] = ceofs[i];
        }
        ceofs = newcoefs;
    }
    //multily the polynomial p to the polynomial q, so p=p*q
    public void multiply(Polynomial q){
        int lenq = q.ceofs.length;
        int lenp = ceofs.length;
        int final_len = lenq+lenp;


        double[] prod= new double[final_len];

        // Initialize the product polynomial
        for (int i = 0; i < lenq + lenp - 1; i++)
        {
            prod[i] = 0;
        }

        // Multiply two polynomials term by term
        // Take ever term of first polynomial
        for (int i = 0; i < lenq; i++)
        {
            // Multiply the current term of first polynomial
            // with every term of second polynomial.
            for (int j = 0; j < lenp; j++)
            {
                prod[i + j] += ceofs[i] * q.ceofs[j];
            }
        }

        ceofs=prod;
    }

    //Adds 2 polynomials (p & q) and creates & returns a new polynomial "sum" r=p+q
    public Polynomial sum(Polynomial q){
        int lenq = q.ceofs.length;
        int lenp = ceofs.length;
        int final_len = 0;

        if (lenq > lenp)
            final_len = lenq;
        else
            final_len = lenp;

        double[] newcoefs = new double[final_len];

        for (int i = 0; i < final_len; i++) {
            if (i < lenq && i < lenp)
                newcoefs[i] = q.ceofs[i] + ceofs[i];
            else if (i < lenq && i >= lenp)
                newcoefs[i] = q.ceofs[i];
            else
                newcoefs[i] = ceofs[i];
        }
        Polynomial sum= new Polynomial(newcoefs);
        return sum;
    }
    //Subtracts 2 polynomials (p & q) and creates & returns a new polynomial "diff" so r=p-q
    public Polynomial diff(Polynomial q){
        int lenq = q.ceofs.length;
        int lenp = ceofs.length;
        int final_len = 0;

        if (lenq > lenp)
            final_len = lenq;
        else
            final_len = lenp;

        double[] newcoefs = new double[final_len];
        for (int i = 0; i < final_len; i++) {
            if (i < lenq && i < lenp)
                newcoefs[i] = ceofs[i]-q.ceofs[i];
            else if (i < lenq && i >= lenp)
                newcoefs[i] = (-1)*q.ceofs[i];
            else
                newcoefs[i] = ceofs[i];
        }
        Polynomial diff= new Polynomial(newcoefs);
        return diff;
    }
    //Multiplies 2 polynomials (p & q) and creates & returns a new polynomial "prod" so r=p*q
    public Polynomial product(Polynomial q){
        int lenq = q.ceofs.length;
        int lenp = ceofs.length;
        int final_len = lenq+lenp;


        double[] prod= new double[final_len];

        // Initialize the product polynomial
        for (int i = 0; i < lenq + lenp - 1; i++)
        {
            prod[i] = 0;
        }

        // Multiply two polynomials term by term
        // Take ever term of first polynomial
        for (int i = 0; i < lenq; i++)
        {
            // Multiply the current term of first polynomial
            // with every term of second polynomial.
            for (int j = 0; j < lenp; j++)
            {
                prod[i + j] += ceofs[i] * q.ceofs[j];
            }
        }


        Polynomial product= new Polynomial(prod);
        return product;
    }
    //Method to Print a Polynomial
    public String toString(){
        String answer="";
        double cur=0;

        int len= ceofs.length;
        for(int i=len-1; i>=0; i--){
            cur = ceofs[i];
            if(i>1)
                answer +=cur+"X^"+i+" ";
            else if(i==1)
                answer+=cur+"X"+" ";
            else
                answer+=cur+"";
        }
        return answer;
    }
    //The main method area not necessary but used for testing different cases
    public static void main(String[] args) {

        double[]a= {1,-2,3};
        double[]b= {1,2,3,4};

        Polynomial p1= new Polynomial(a);
        Polynomial p2= new Polynomial(b);

        System.out.println(p2.evaluate(3));
        //p1.scale(3);
        //System.out.print(p1.toString());
        //p1.multiply(p2);
        //System.out.println(p1.toString());
        //2x^2+4x+6
        double[]c={6,4,2};
        //-8x^2+9x+7
        double[]d={7,9,-8};
        Polynomial p3= new Polynomial(c);
        Polynomial p4= new Polynomial(d);
        p3.add(p4);
        System.out.println(p3);
    }
}
