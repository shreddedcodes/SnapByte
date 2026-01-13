package menu;

import java.util.Scanner;

public class Dessert extends MenuItem {
  private String flavor;

  public Dessert(String name, double price, String flavor) {
    super(name, price);
    this.flavor = flavor;
  }
  public String getFlavor() {
      return flavor;
  }

  public void setFlavor(String flavor) {
      this.flavor = flavor;
  }
  public void display() {
    System.out.println(
        "\u001B[35mDessert: " + getName() + ", Flavor: " + flavor + ", Price: ₹" + getPrice() + "\u001B[0m");
  }

  public void displayCustomizationOptions(Scanner scanner) {
    System.out.println("Customization Options for Dessert:");
    System.out.println("1. Flavor: " + getFlavor());

    System.out.print("Enter flavor for Dessert: ");
    String newFlavor = scanner.nextLine().trim();
    if (!newFlavor.isEmpty()) {
      setFlavor(newFlavor);
    }
  }
}
