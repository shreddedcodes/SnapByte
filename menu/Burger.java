package menu;

import java.util.Scanner;

public class Burger extends MenuItem {
  private boolean hasCheese;
  private boolean friesIncluded; // New field for fries

  public Burger(String name, double price, boolean hasCheese, boolean friesIncluded) {
    super(name, price);
    this.hasCheese = hasCheese;
    this.friesIncluded = friesIncluded;
  }

  public boolean isHasCheese() {
    return hasCheese;
  }

  public void setHasCheese(boolean hasCheese) {
    this.hasCheese = hasCheese;
  }

  public boolean isFriesIncluded() {
    return friesIncluded;
  }

  public void setFriesIncluded(boolean friesIncluded) {
    this.friesIncluded = friesIncluded;
  }

  @Override
  public void display() {
    System.out.println("\u001B[32mBurger: " + getName() + ", Has Cheese: " + hasCheese + ", Fries Included: "
        + friesIncluded + ", Price: ₹" + getPrice() + "\u001B[0m");
  }

  public void displayCustomizationOptions(Scanner scanner) {
    System.out.println("Customization Options for Burger:");
    System.out.println("1. Has Cheese: " + isHasCheese());
    System.out.println("2. Fries Included: " + isFriesIncluded());

    System.out.print("1. Has Cheese (Yes/No): ");
    String cheeseInput = scanner.nextLine().trim();
    if (!cheeseInput.isEmpty()) {
      setHasCheese(cheeseInput.equalsIgnoreCase("Yes"));
    }

    System.out.print("2. Fries Included (Yes/No): ");
    String friesInput = scanner.nextLine().trim();
    if (!friesInput.isEmpty()) {
      setFriesIncluded(friesInput.equalsIgnoreCase("Yes"));
    }
  }

}