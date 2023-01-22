package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import model.Item;
import model.Price;

public class ItemManager {


 public HashMap<String, Item> getAllItems() {
 
  HashMap<String, Item> itemMap = new HashMap<String, Item>();
 
  //create file object from the external file
  File itemFile = new File("ItemDataBase.csv");
 

  try {

   Scanner scanner = new Scanner(itemFile);
  

   while (scanner.hasNext()) {
   

    String fileRecord = scanner.nextLine();
    String[] fileRecordSplit = fileRecord.split(",");
    Item item = new Item();
    item.setId(fileRecordSplit[0]);
    item.setDescription(fileRecordSplit[1]);

    Price price = new Price();
    price.setAmount(Double.parseDouble(fileRecordSplit[2]));

    item.setPrice(price);
   
    itemMap.put(item.getId(), item);
   }
  } catch (FileNotFoundException e) {

   e.printStackTrace();
  }

  return itemMap;
 }
}
