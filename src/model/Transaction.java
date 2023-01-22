package model;

public class Transaction {

 private String id;
 private Operator operator;
 private Machine machine;
 private Customer customer;
 private String date;
 private Item[] items;

 public String getDate() {
  return date;
 }
 public void setDate(String date) {
  this.date = date;
 }
 public Item[] getItems() {
  return items;
 }
 public void setItems(Item[] items) {
  this.items = items;
 }
 public String getId() {
  return id;
 }
 public void setId(String id) {
  this.id = id;
 }
 public Operator getOperator() {
  return operator;
 }
 public void setOperator(Operator operator) {
  this.operator = operator;
 }
 public Machine getMachine() {
  return machine;
 }
 public void setMachine(Machine machine) {
  this.machine = machine;
 }
 public Customer getCustomer() {
  return customer;
 }
 public void setCustomer(Customer customer) {
  this.customer = customer;
 }
}