package menu;

import java.util.Scanner;

public class Pasta extends MenuItem {
  private String sauce;

  public Pasta(String name, double price, String sauce) {
    super(name, price);
    this.sauce = sauce;
  }
   public String getSauce() {
       return sauce;
   }

   public void setSauce(String sauce) {
       this.sauce = sauce;
   }
  @Override
  public void display() {
    System.out.println(
        "\u001B[36mPasta: " + getName() + ", Sauce: " + sauce + ", Price: ₹" + getPrice() + "\u001B[0m");
  }

  public void displayCustomizationOptions(Scanner scanner) {
    System.out.println("Customization Options for Pasta:");
    System.out.println("1. Sauce: " + getSauce());

    System.out.print("Enter sauce for Pasta: ");
    String newSauce = scanner.nextLine().trim();
    if (!newSauce.isEmpty()) {
      setSauce(newSauce);
    }
  }
}