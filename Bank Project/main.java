import java.io.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/*
Andreea Ibanescu
CSCI 313 | Project 1 Part 3
This project is case sensitive, for user input. Search by Name Method is located in myBST class.
Uses class: Customer,myBST,treeNode.
Explain why Array AccountNum DataBase is a better method? But why we still need BST DataBase for the customer names.
The array AccountNum is a better method because its more direct, you will get the customer by simply doing it at the index. There would be no searching through the array. Unlike searching through a BST
 which is O(log n). The AccDB is O(1) since we know where the customer is located. We still need BST for the names because you may have a case where two customers have the same account number then you have to check the names as well.
Or some customers don't remember their account number, which is usually the case in most real life sanarios.
 */

public class main {

    public static void main(String[] args) throws IOException {

        //Creating a new Binary Search Tree (Empty Database structure)
        myBST tree= new myBST();

        //Open the file
        File file=new File("/Users/andreeaibanescu/Desktop/project1/src/customerInfo.txt");

        //Go Through the File: First,Last,Account Number, Balance for binary search tree data base
        Scanner sc = new Scanner(file);

        //Scanner for array data base
        Scanner asc=new Scanner(file);

        //Call LoadCustomer Method
        loadCustomers(sc,tree);

        //Add an array of customers
        Customer[] AccDB= new Customer[10000000];

        //Go through the file, and add the customers into an array
        loadArray(asc,AccDB);

        System.out.println("ARRAY INFO : ");
        //Print the array data base
        for(int i=0; i<AccDB.length; i++){
            if(AccDB[i]!=null)
            System.out.println(AccDB[i].toString());
        }

        //Program Menu:
        Scanner reader_one= new Scanner(System.in);
        System.out.println("Welcome to Andreea's Online Banking!\nWhat would you like to do?\n(Options: Open/Close/Withdraw/Deposit/Check-Balance/Exit/) \nCase Sensitive, Type Here:");
        String task=reader_one.next();

            while(!"Exit".equals(task)) {
                while (!"Exit".equals(task)) {
                    if ("Withdraw".equals(task)) {
                        withdraw(tree,AccDB);
                        break;
                    }
                    if ("Deposit".equals(task)) {
                        deposit(tree,AccDB);
                        break;
                    }
                    if("Close".equals(task)){
                        close(tree,AccDB);
                        break;
                    }
                    if("Check-Balance".equals(task)) {
                        checkBalance(tree,AccDB);
                        break;
                    }
                    if("Open".equals(task)){
                        open(tree,AccDB);
                        break;
                    }
                    else {
                        System.out.println("Invalid Option. Make sure you Capitalized the first word. Retry.");
                        break;
                    }

                }
                System.out.println("What else would you like to do? \n(Options: Open/Close/Withdraw/Deposit/Check-Balance/Exit/) \nType Here:");
                task = reader_one.next();
            }
        //Exit Menu & Log
            exit(tree);


        }
       //Method that will read the text file of the customers and stores the customers in the BST (WORKS)
        public static void loadCustomers(Scanner s, myBST t){
            while(s.hasNextLine()){
                String line = s.nextLine();
                //Split the textFile the empty spaces
                String[] list = line.split(" ");
                //Will be storing each lines of text file into an array(temp) for each line then finding first,last,accountNumber,balance
                String first= list[0];
                String last= list[1];
                String accountNum=list[2];
                //Changing Balance from a String to a Double
                Double balance= Double.parseDouble(list[3]);

                //Creating a Customer then adding them to the BST
                t.insert(new Customer(last,first,accountNum,balance));
            }
            //Close the File b/c all data was transfered into the BST
            s.close();

            //Print The BST Data Base in Order:
            t.inOrder();
        }
        //Load customers from textfile into an array, with the customer at the index
        public static void loadArray(Scanner s, Customer [] array){
            while(s.hasNextLine()){
                String line = s.nextLine();
                //Split the textFile the empty spaces
                String[] list = line.split(" ");
                //Will be storing each lines of text file into an array(temp) for each line then finding first,last,accountNumber,balance
                String first= list[0];
                String last= list[1];
                String accountNum=list[2];
                //Changing Balance from a String to a Double
                Double balance= Double.parseDouble(list[3]);

                int accNum=Integer.parseInt(accountNum);
                //Creating a Customer then adding them to the BST
               array[accNum]=(new Customer(last,first,accountNum,balance));
            }
            //Close the File b/c all data was transfered into the BST
            s.close();

        }
         //Search for a Customer based on account number only
        public static Customer lookUp(String accNum, Customer array[]) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    if((array[i].getAccNo()).equals(accNum))
                        return array[i];
                }
            }
            //if no customer matches the account number
            return null;
        }
        //Method withdraw user's input (keyboard input) WORKS
        public static void withdraw(myBST t, Customer a[]) {

            //Get users first name
            Scanner reader3 = new Scanner(System.in);
            System.out.println("How do you wanna search for your bank account (Name/AccNum): ");
            String option = reader3.next();

            if (option.equals("Name")) {
                System.out.println("Enter your first name: ");
                String fname = reader3.next();

                //Get users last name
                System.out.println("Enter your last name: ");
                String lname = reader3.next();

                //Find the customer in the database if not found print "user not found"
                Customer temp = new Customer(lname, fname, "00000", 0.0);
                Customer found = t.search(temp);
                if (found != null) {
                    //If found:
                    System.out.println("Enter the withdraw amount: ");
                    int n = reader3.nextInt(); // Scans the next token of the input as an int.

                    //Withdraw into account
                    found.withdraw(n * 1.0);
                    System.out.println("New Current Balance: " + found.getBalance());

                }
                //If user was not found
                else
                    System.out.println("User not found. Try opening an account. ");
            }
            else {
                //Search by accountNum
                System.out.println("Enter your accountNumber: ");
                String accNum = reader3.next();
                Customer found= lookUp(accNum,a);
                if (found != null) {
                    //If found:
                    System.out.println("Enter the withdraw amount: ");
                    int n = reader3.nextInt(); // Scans the next token of the input as an int.

                    //Withdraw into account
                    found.withdraw(n * 1.0);
                    System.out.println("New Current Balance: " + found.getBalance());

                }
                //If user was not found
                else
                    System.out.println("User not found. Try opening an account. ");
            }
        }
        //Search for user by account name then deposit the amount (keyboard input) WORKS
        public static void deposit(myBST t,Customer a[]){
            Scanner reader1= new Scanner(System.in);
            //Ask user
            System.out.println("How do you wanna search for your bank account (Name/AccNum): ");
            String option = reader1.next();

            if (option.equals("Name")) {

                //Get users first name

                System.out.println("Enter your first name: ");
                String fname = reader1.next();

                //Get users last name
                System.out.println("Enter your last name: ");
                String lname = reader1.next();

                //Find the customer in the database if not found print "user not found"
                Customer temp = new Customer(lname, fname, "00000", 0.0);
                Customer found = t.search(temp);

                if (found != null) {
                    //If user found:
                    System.out.println("Enter the deposit amount: ");
                    int n = reader1.nextInt(); // Scans the next token of the input as an int.

                    //Deposit amount into account
                    found.deposit(n * 1.0);
                    System.out.println("New Current Balance: " + found.getBalance());
                }
                //User not found with given LastName & FirstName
                else
                    System.out.println("User not found. Try opening an account. ");
            }
            else{
                //Search by accountNum
                System.out.println("Enter your accountNumber: ");
                String accNum = reader1.next();
                Customer found= lookUp(accNum,a);
                if (found != null) {
                    //If user found:
                    System.out.println("Enter the deposit amount: ");
                    int n = reader1.nextInt(); // Scans the next token of the input as an int.

                    //Deposit amount into account
                    found.deposit(n * 1.0);
                    System.out.println("New Current Balance: " + found.getBalance());
                }
                //User not found with given LastName & FirstName
                else
                    System.out.println("User not found. Try opening an account. ");
            }
        }
        //Method that checks a customer's balance WORKS
        public static void checkBalance(myBST t,Customer a[]) {

            Scanner reader7 = new Scanner(System.in);
            //Ask user
            System.out.println("How do you wanna search for your bank account (Name/AccNum): ");
            String option = reader7.next();

            //Option
            if (option.equals("Name")) {
                //Get users first name
                System.out.println("Enter your first name: ");
                String fname = reader7.next();

                //Get users last name
                System.out.println("Enter your last name: ");
                String lname = reader7.next();

                //Find the customer in the database if not found print "user not found"
                Customer temp = new Customer(lname, fname, "00000", 0.0);
                Customer found = t.search(temp);

                //if user was found get balance
                if (found != null)
                    System.out.println("Your Current Balance: " + found.getBalance());
                else
                    //if user was not found
                    System.out.println("User not found. Try opening an account.");
            }
            else {
                //Search by accountNum
                System.out.println("Enter your accountNumber: ");
                String accNum = reader7.next();
                Customer found= lookUp(accNum,a);
                if (found != null)
                    System.out.println("Your Current Balance: " + found.getBalance());
                else
                    //if user was not found
                    System.out.println("User not found. Try opening an account.");
            }
        }

        //Method to Open a new account: Creates a new Customer and adds the new customer to the BST. There should then be a deposit. WORKS

        public static void open(myBST t,Customer a[]) {
            //Have the Scanner ready
            Scanner reader6 = new Scanner(System.in);

                //Get users first name
                System.out.println("Enter your first name: ");
                String fname = reader6.next();

                //Get users last name
                System.out.println("Enter your last name: ");
                String lname = reader6.next();

                //Get users account Num
                System.out.println("Enter your account number (if you have one already, if not type N/A: ");
                String accNum=reader6.next();

                Customer found2= lookUp(accNum,a);

                //Find the customer in the database if not found print "user not found"
                Customer temp = new Customer(lname, fname, "00000", 0.0);
                Customer found = t.search(temp);

                if (found == null && found2==null) {
                    //MUST HAVE A DEPOSIT OTHERWISE METHOD WILL NOT WORK!
                    System.out.println("How much do you want to deposit? \n Type here: ");
                    int depositNum = reader6.nextInt();

                    //You cannot make an account with a valid deposit
                    while (depositNum <= 0) {
                        System.out.println("Invalid deposit number, try again. Type here: ");
                        depositNum = reader6.nextInt();
                    }

                    //Starting Balance is always 0
                    Double b = 0.0;

                    //Create the account Number
                    String accountNum = "";
                    // Generate random integers in range 0 to 9
                    for (int i = 0; i < 5; i++) {
                        int random = (int) (Math.random() * 9);
                        accountNum += "" + random;
                    }
                    ///Creating a new customer and adding it to the binary search tree
                    //Adding the deposit into the 0.0 starting balance
                    t.insert(new Customer(lname, fname, accountNum, (b + depositNum)));
                }
                else
                    //if user was not found
                    System.out.println("User was found. Cannot make another account.");
            }
        // Method will close an account: delete the customer from the BST. WORKS
        public static void close(myBST t, Customer a[]) {
            //Have scanner ready
            Scanner reader8 = new Scanner(System.in);
            //Ask user
            System.out.println("How do you wanna search for your bank account (Name/AccNum): ");
            String option = reader8.next();

            //Option
            if (option.equals("Name")) {

                //Get users first name
                System.out.println("Enter your first name: ");
                String fname = reader8.next();

                //Get users last name
                System.out.println("Enter your last name: ");
                String lname = reader8.next();

                //Find the customer in the database if not found print "user not found"
                Customer temp = new Customer(lname, fname, "00000", 0.0);
                Customer found = t.search(temp);

                if (found != null) {
                    t.delete(found);
                    System.out.println("Your account has been deleted");
                }
                //User not found with given LastName & FirstName
                else
                    System.out.println("User was not found in the Data Base.");
             }
            else {
                //Search by accountNum
                System.out.println("Enter your accountNumber: ");
                String accNum = reader8.next();
                Customer found= lookUp(accNum,a);
                if (found != null){
                    t.delete(found);
                    System.out.println("Your account has been deleted");
                }
                  //User not found with given LastName & FirstName
                else
                     System.out.println("User was not found in the Data Base.");
            }
        }
        //Method will save the new BST into a separate text file (log)
        public static void exit(myBST t) throws FileNotFoundException {
            //create the file where the log transcations will be
            PrintWriter customers = new PrintWriter("file.txt");
            customers.println(t.toString());
            customers.close();
        }
    }

