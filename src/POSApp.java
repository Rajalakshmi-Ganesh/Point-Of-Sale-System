import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import model.Item;
import controller.ItemManager;
import controller.ShoppingCart;

public class POSApp {

 public static void main(String[] args) {

  ItemManager itemManager = new ItemManager();
  Scanner sc =new Scanner(System.in);
  int count=0;
  HashMap<String, Item> itemMap = itemManager.getAllItems();

  Iterator<String> iterator = itemMap.keySet().iterator();

  System.out.println("-----List of Available Items------");
  while (iterator.hasNext()) {
   Item item = itemMap.get(iterator.next()); 
   ArrayList rar=new ArrayList(item.printStatus());
   System.out.println("Item "+(++count));
   System.out.println("_______________________");
   System.out.println("| "+rar.get(0)+" | "+rar.get(1)+" | "+rar.get(2)+" |");
   System.out.println("-----------------------");
   System.out.println("");
  }

  System.out.println("-------------------------------");

  ShoppingCart cart = new ShoppingCart();
  cart.start();
  cart.checkOut();
  count=0;
  System.out.println("Thank you for Purchasing, Have a nice day !!! ");
 }
}