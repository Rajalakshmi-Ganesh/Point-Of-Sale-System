package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import model.Customer;
import model.Item;
import model.Machine;
import model.Operator;
import model.Transaction;
import dao.ShoppingCartDao;

import javax.sound.midi.Soundbank;

public class ShoppingCart {

 ShoppingCartDao cartDao = new ShoppingCartDao();


 public void start() {
 
  int choice = 0;
 
  Scanner scanner  = new Scanner(System.in);
 
  do {
   System.out.println("Enter [1 = Add, 2 = Delete, 0 = Exit]");
   choice = scanner.nextInt();
  
   switch(choice) {
    case 1 : 
     add();
     break;
    case 2 :
     Scanner sc = new Scanner(System.in);
     System.out.println("Enter Item id to be removed = ");
     String itemId = sc.next();
     delete(itemId);
     break;
    case 0 :
     break;
    default:
    System.out.println("Invalid choice.");

   }
  } while (choice != 0);
 }

 public void add() {

  cartDao.create();
 }


 public void checkOut() {

  ArrayList<String> itemIds = cartDao.read();
  Scanner sc = new Scanner(System.in);

  ArrayList<Item> purchaseditems = cartDao.loadItemDetails(itemIds);
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
  SimpleDateFormat timing = new SimpleDateFormat("HH:mm:ss");
  Date date = new Date();
  double total = 0;
  double gst=0;
  double gst_calc=0;
  int points;

  System.out.println("CUSTOMER DETAILS");
  System.out.print("Name: ");
  String Name= sc.nextLine();
  System.out.print("Address: ");
  String Address = sc.nextLine();
  System.out.print("Contact_No: ");
  String Number = sc.nextLine();
  System.out.println("************** COMPANY NAME ***************");
  System.out.println("************* COMPANY ADDRESS *************");
  System.out.println("*************CUSTOMER DETAILS**************");
  System.out.println("Name : "+Name+"         "+"DATE :"+formatter.format(date)       );
  System.out.println("Ph_Number :"+Number+"      "+"TIME :"+timing.format(date));
  System.out.println("ADDRESS : "+Address);
  System.out.println("+++++++++ List of items purchased +++++++++");
 
  for (Item item : purchaseditems) {
    total = total + item.getPrice().getAmount();
    System.out.println("                    "+item.printStatus());
  }

  gst=18;
  gst_calc=total*(gst*0.01);

  points=(int)(total/100)*10;

  System.out.println("-------------------------------------------");
  System.out.println("                   Price        :"  + total);
  System.out.println("                   Tax          :"  + gst_calc);
  total+=gst_calc;
  System.out.println("                   Total amount :"  + total);
  System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
  System.out.println("                   Points Earned :"  + points);

  recordTransaction(purchaseditems);
 }


 private void recordTransaction(ArrayList<Item> purchaseditems) {
  //create Transaction
  Transaction transaction = new Transaction();
  transaction.setId("T-788");
 
  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
  transaction.setDate(dateFormat.format(new Date()));
 
  Operator operator = new Operator();
  operator.setId("O-005");
  transaction.setOperator(operator);
 
  Machine machine = new Machine();
  machine.setId("M-101");
  transaction.setMachine(machine);
 

  Customer customer = new Customer();
  customer.setId("C-101");
  transaction.setCustomer(customer); 


  Item[] items = purchaseditems.toArray(new Item[purchaseditems.size()]);
  transaction.setItems(items);
 

   try {
             FileWriter writer = new FileWriter("alltransaction.csv", true); //set true for append mode
             BufferedWriter bufferedWriter =new BufferedWriter(writer);
             
             for (Item item : items) {             
              bufferedWriter.write(transaction.getId());
              bufferedWriter.write(",");
              bufferedWriter.write(transaction.getDate());
              bufferedWriter.write(",");
              bufferedWriter.write(transaction.getOperator().getId());
              bufferedWriter.write(",");
              bufferedWriter.write(transaction.getMachine().getId());
              bufferedWriter.write(",");
              bufferedWriter.write(transaction.getCustomer().getId());
              bufferedWriter.write(",");
              bufferedWriter.write(item.getId());
              bufferedWriter.write(",");
              bufferedWriter.write(String.valueOf(item.getPrice().getAmount())); 
              bufferedWriter.write("\n");
             }
             
             bufferedWriter.flush();
             bufferedWriter.close();
             writer.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
 }

 public void delete(String itemId) {

  cartDao.delete(itemId);
 }
}