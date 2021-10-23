public class Main {

    public static void main(String[] args) {

        Polynomial ex1 = new Polynomial();
        Polynomial ex2 = new Polynomial();

        Terms ter = new Terms(5, 6);
        Terms ter2 = new Terms(7, 3);
        Terms ter3 = new Terms(2, 4);
       // Terms ter4=  new Terms(5,6);
        ex1.insertSorted(ter);
        ex1.insertSorted(ter2);
        ex1.insertSorted(ter3);

        Terms bruh = new Terms(2, 2);
        Terms yo = new Terms(4, 3);
        Terms facts = new Terms(7, 4);
        ex2.insertSorted(bruh);
        ex2.insertSorted(yo);
        ex2.insertSorted(facts);

        //Printing test cases
        System.out.println("ex1: "+ ex1.toString());
        System.out.println("ex2: " + ex2.toString());

        //System.out.println(ex1.evaluate(2)+"sum");
        //ex1.scale(2);
        //System.out.println(ex1);
        //ex1.multi(ex2);

        Polynomial ex6= new Polynomial();
        ex1.add(ex2);
        System.out.println(ex1.toString()+"test");

    }
}