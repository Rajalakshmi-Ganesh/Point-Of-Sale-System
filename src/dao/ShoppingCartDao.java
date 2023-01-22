package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import model.Item;
import controller.ItemManager;

public class ShoppingCartDao {


 static ArrayList<String> itemIdList = new ArrayList<String>();


 public void create() {
  Scanner scanner = new Scanner(System.in);
  String itemId = "0";
 
  do {

   System.out.println("Enter ItemId, [0 = Exit] : ");
   itemId = scanner.next();

   if (!itemId.equals("0")) {
    itemIdList.add(itemId);
   }
  
  } while (!itemId.equals("0"));
 }


 public ArrayList<String> read() {
  return itemIdList;
 }


 public void update() {
 }


 public void delete(String itemid) {
  itemIdList.remove(itemid);
 }


 public ArrayList<Item> loadItemDetails(ArrayList<String> itemIds) {
 
  ArrayList<Item> purchasedItemList = new ArrayList<Item>();
 

  ItemManager itemManager = new ItemManager();
  HashMap<String,Item> allItems = itemManager.getAllItems();
 
  for (int i = 0; i < itemIds.size(); i++) {
   purchasedItemList.add(allItems.get(itemIds.get(i)));
  }
  return purchasedItemList;
 }
}
