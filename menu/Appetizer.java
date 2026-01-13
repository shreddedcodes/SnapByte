package menu;

import java.util.Scanner;

public class Appetizer extends MenuItem {
  private boolean isSpicy;

  public Appetizer(String name, double price, boolean isSpicy) {
    super(name, price);
    this.isSpicy = isSpicy;
  }

  public boolean isSpicy() {
      return isSpicy;
  }

  public void setSpicy(boolean isSpicy) {
      this.isSpicy = isSpicy;
  }

  @Override
  public void display() {
    String spiceLevel = isSpicy ? "Spicy" : "Mild";
    System.out.println("\u001B[31mAppetizer: " + getName() + ", Spice Level: " + spiceLevel + ", Price: ₹"
        + getPrice() + "\u001B[0m");
  }

  public void displayCustomizationOptions(Scanner scanner) {
    System.out.println("Customization Options for Appetizer:");
    System.out.println("1. Spicy: " + isSpicy());

    System.out.print("Is it spicy? (Yes/No): ");
    String spicyInput = scanner.nextLine().trim();
    if (!spicyInput.isEmpty()) {
      setSpicy(spicyInput.equalsIgnoreCase("Yes"));
    }
  }
}
