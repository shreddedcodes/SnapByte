package menu;

public abstract class MenuItem {
  private String name;
  private double price;

  public MenuItem(String name, double price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  public abstract void display();

  // Add a method to display customization options
  public void displayCustomizationOptions() {
    // Default implementation (can be overridden by subclasses)
  }
}
