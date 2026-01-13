package menu;

import java.util.Scanner;

public class Pizza extends MenuItem {
  private String size;
  private String toppings;

  public Pizza(String name, double price, String size, String toppings) {
    super(name, price);
    this.size = size;
    this.toppings = toppings;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getToppings() {
    return toppings;
  }

  public void setToppings(String toppings) {
    this.toppings = toppings;
  }

  @Override
  public void display() {
    System.out.println("\u001B[32mPizza: " + getName() + ", Size: " + size + ", Price: ₹" + getPrice() + "\u001B[0m");
  }
  public void displayCustomizationOptions() {
    System.out.println("Customization Options for Pizza:");
    System.out.println("1. Size: " + size);
    System.out.println("2. Toppings: " + toppings);
  }
}