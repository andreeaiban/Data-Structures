/*
CSCI 313: Project 1
Programmer Andreea Ibanescu
 */
public class Customer {
    //Attributes:
    String last,first, accNo;
    double balance;

    //Constructor:
    public Customer(String last,String first, String accNo, double balance){
        this.last=last;
        this.first=first;
        this.accNo=accNo;
        this.balance=balance;
    }
    //Setters:
    public void setLast(String s){
        this.last=s;
    }
    public void setFirst(String s){
        this.last=s;
    }
    public void setaccNo(String s){
        this.accNo=s;
    }
    public void setBalance(double d){
        this.balance=d;
    }
    //Getters:
    public String getLast(){
        return this.last;
    }
    public String getFirst(){
        return this.first;
    }
    public String getAccNo(){
        return this.accNo;
    }
    public double getBalance() {
        return this.balance;
    }

    //Functions:
    public void deposit(double amount){
        //add an amount into a Costomers balance
        if(amount>0)
        balance+=amount;
        else
            System.out.println("inefficient amount for a deposit");
    }
    public void withdraw(double amount){
        //subtract the amount from the Costomers
        if(balance>=amount)
        balance-=amount;
        else
            System.out.println("inefficient balance for a withdraw");
    }
    public boolean equals(String other){
        //Return true if an other account number is the same as this account number
        return accNo.equals(other);
    }
    public String toString(){
        //JUST PRINT ATTRIBUTES
        return "Customer: \n"+"Name: "+first+" "+ last+"\nAccount Number: "+accNo+"\nBalance: "+balance +"\n";

    }
    public int compareTo(Customer other){
        //Compare customer type of the last name, if the last name is the same, the first name is compared
        String s=this.last;
        int value= s.compareTo(other.last);
        if(value!=0)
            return value;
        //compare the first name here
        return this.first.compareTo(other.first);
    }

}
