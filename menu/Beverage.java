package menu;

import java.util.Scanner;

public class Beverage extends MenuItem {
  private String size;

  public Beverage(String name, double price, String size) {
    super(name, price);
    this.size = size;
  }
  public String getSize() {
      return size;
  }
  
  public void setSize(String size) {
      this.size = size;
  }
  @Override
  public void display() {
    System.out.println(
        "\u001B[34mBeverage: " + getName() + ", Size: " + size + ", Price: ₹" + getPrice() + "\u001B[0m");
  }

  public void displayCustomizationOptions(Scanner scanner) {
    System.out.println("Customization Options for Beverage:");
    System.out.println("1. Size: " + getSize());

    System.out.print("Enter size for Beverage (Small, Medium, Large, or press Enter for default size): ");
    String newSize = scanner.nextLine().trim();
    if (!newSize.isEmpty()) {
      setSize(newSize);
    }
  }
}
