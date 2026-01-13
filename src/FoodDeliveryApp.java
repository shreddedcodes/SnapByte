import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

import menu.*;

class DeliveryPerson {
  private String name;
  private double rating;

  public DeliveryPerson(String name, double rating) {
    this.name = name;
    this.rating = rating;
  }

  public String getName() {
    return name;
  }

  public void deliver() {
    System.out.println(getName() + " will be delivering the order...");
    System.out.println("Rating " + rating);
  }
}

class Order {
  private List<MenuItem> items;

  public Order() {
    items = new ArrayList<>();
  }

  public void addItem(MenuItem item) {
    items.add(item);
  }

  public List<MenuItem> getItems() {
    return items;
  }

  public void displayOrder() {
    for (MenuItem item : items) {
      item.display();
      item.displayCustomizationOptions(); // Display customization options
    }
  }

  public double calculateTotal() {
    double total = 0;
    for (MenuItem item : items) {
      total += item.getPrice();
    }
    return total;
  }
}

class Menu<T extends MenuItem> {
  private Map<String, T> items;

  public Menu() {
    items = new HashMap<>();
  }

  public void addItem(String key, T item) {
    items.put(key, item);
  }

  public T getItem(String key) {
    return items.get(key);
  }

  public Map<String, T> getItems() {
    return items;
  }
}

class InvalidOrderException extends Exception {
  public InvalidOrderException(String message) {
    super(message);
  }
}

class PaymentFailedException extends Exception {
  public PaymentFailedException(String message) {
    super(message);
  }
}

class OrderProcessor {
  public static void processOrder(Order order) throws InvalidOrderException, PaymentFailedException {
    try {
      if (order == null || order.getItems().isEmpty()) {
        throw new InvalidOrderException("Invalid order: Order is empty.");
      }

      if (somePaymentIssueOccurred()) {
        throw new PaymentFailedException("Payment failed: There was an issue processing the payment.");
      }

    } catch (InvalidOrderException | PaymentFailedException e) {
      throw e;
    }
  }

  private static boolean somePaymentIssueOccurred() throws PaymentFailedException {
    // Generate a random number between 1 and 50
    int randomNumber = new Random().nextInt(50) + 1;

    // Check if the random number is greater than 40
    if (randomNumber > 40) {
      throw new PaymentFailedException("Payment failed: There was an issue processing the payment.");
    }

    // Simulate a situation where the payment issue did not occur
    return false;
  }
}

public class FoodDeliveryApp {
  private static Scanner scanner = new Scanner(System.in);

  private static List<DeliveryPerson> loadDeliveryPeople(String filename) {
    List<DeliveryPerson> deliveryPeople = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split(",");
        if (parts.length == 2) {
          String name = parts[0].trim();
          double rating = Double.parseDouble(parts[1].trim());
          DeliveryPerson deliveryPerson = new DeliveryPerson(name, rating);
          deliveryPeople.add(deliveryPerson);
        }
      }
    } catch (IOException e) {
      System.out.println("Error loading delivery people data: " + e.getMessage());
    }

    return deliveryPeople;
  }

  private static DeliveryPerson getRandomDeliveryPerson(List<DeliveryPerson> deliveryPeople) {
    if (!deliveryPeople.isEmpty()) {
      Random random = new Random();
      return deliveryPeople.get(random.nextInt(deliveryPeople.size()));
    } else {
      System.out.println("No delivery people available.");
      return null;
    }
  }

  public static void main(String[] args) {

    List<DeliveryPerson> deliveryPeople = loadDeliveryPeople("delivery_people.csv");

    DeliveryPerson chosenDeliveryPerson = getRandomDeliveryPerson(deliveryPeople);

    Menu<MenuItem> menu = createMenu();

    Order order = createOrder(menu);

    if (order != null) { // Check if the order is not null
      try {
        OrderProcessor.processOrder(order);
        chosenDeliveryPerson.deliver();

      } catch (InvalidOrderException | PaymentFailedException e) {
        System.out.println("Order processing failed: " + e.getMessage());
      }
    }
    System.out.println("Exiting the application. Thank you!");

    // Close the scanner to prevent resource leaks
    scanner.close();
  }

  private static Menu<MenuItem> createMenu() {
    Menu<MenuItem> menu = new Menu<>();
    Scanner sc = new Scanner(System.in);
    menu.addItem("Margherita", new Pizza("Margherita", 199.00, "Large", "Cheese, Tomato"));
    menu.addItem("Pepperoni", new Pizza("Pepperoni", 249.00, "Medium", "Pepperoni, Cheese"));
    menu.addItem("Vegetarian", new Pizza("Vegetarian", 229.00, "Large", "Mushrooms, Onions, Peppers"));
    menu.addItem("Hawaiian", new Pizza("Hawaiian", 449.00, "Medium", "Ham, Pineapple, Cheese"));
    menu.addItem("MeatLovers", new Pizza("Meat Lovers", 499.00, "Large", "Pepperoni, Sausage, Bacon"));

    menu.addItem("ClassicBurger", new Burger("Classic Burger", 159.00, false, true));
    menu.addItem("CheeseBurger", new Burger("Cheese Burger", 199.00, true, true));
    menu.addItem("VeggieBurger", new Burger("Veggie Burger", 249.00, false, true));
    menu.addItem("BaconBurger", new Burger("Bacon Burger", 299.00, true, true));
    menu.addItem("SpicyChickenBurger", new Burger("Spicy Chicken Burger", 339.00, false, true));

    menu.addItem("Cola", new Beverage("Cola", 80.00, "Medium"));
    menu.addItem("OrangeJuice", new Beverage("Orange Juice", 90.00, "Large"));
    menu.addItem("IcedTea", new Beverage("Iced Tea", 100.00, "Small"));
    menu.addItem("Lemonade", new Beverage("Lemonade", 80.00, "Medium"));
    menu.addItem("Water", new Beverage("Water", 20.00, "Small"));

    menu.addItem("Nachos", new Appetizer("Nachos", 149.00, true));
    menu.addItem("MozzarellaSticks", new Appetizer("Mozzarella Sticks", 149.00, false));
    menu.addItem("ChickenWings", new Appetizer("Chicken Wings", 249.00, true));
    menu.addItem("SpinachDip", new Appetizer("Spinach Dip", 45.00, false));
    menu.addItem("OnionRings", new Appetizer("Onion Rings", 149.00, false));

    menu.addItem("ChocolateCake", new Dessert("Chocolate Cake", 179.00, "Chocolate"));
    menu.addItem("Cheesecake", new Dessert("Cheesecake", 129.00, "Plain"));
    menu.addItem("ApplePie", new Dessert("Apple Pie", 179.00, "Apple"));
    menu.addItem("Brownie", new Dessert("Brownie", 99.00, "Chocolate"));
    menu.addItem("IceCream", new Dessert("Ice Cream", 120.00, "Vanilla"));

    menu.addItem("SpaghettiBolognese", new Pasta("Spaghetti Bolognese", 259.85, "Bolognese Sauce"));
    menu.addItem("AlfredoPasta", new Pasta("Alfredo Pasta", 250.00, "Alfredo Sauce"));
    menu.addItem("PestoPasta", new Pasta("Pesto Pasta", 349.00, "Pesto Sauce"));
    menu.addItem("ChickenFettuccine", new Pasta("Chicken Fettuccine", 399.00, "Creamy Sauce"));
    menu.addItem("ShrimpScampi", new Pasta("Shrimp Scampi", 850.00, "Garlic Butter Sauce"));

    return menu;
  }

  private static Order createOrder(Menu<MenuItem> menu) {
    Order order = new Order();

    boolean orderingComplete = false;

    while (!orderingComplete) {
      System.out.println("Categories:");
      displayCategories();

      String category = scanner.nextLine();

      if (isValidCategory(category)) {
        if (category.equals("7")) {
          orderingComplete = true;
          break; // Skip the rest of the loop and go to the next iteration
        } else if (category.equals("8")) {
          return null;
        }

        displayMenuByCategory(menu, category);

        System.out.print("Enter the number of the item you want to add to your order: ");
        String input = scanner.nextLine();

        try {
          int itemNumber = Integer.parseInt(input);
          MenuItem menuItem = findMenuItemByNumber(menu, category, itemNumber);
          if (menuItem != null) {
            if (menuItem instanceof Pizza) {
              Pizza pizza = (Pizza) menuItem;
              System.out.println("Customization Options for Pizza:");
              System.out.println("1. Size: " + pizza.getSize());
              System.out.println("2. Toppings: " + pizza.getToppings());

              System.out.print("Enter size for Pizza (Regular or Large, or press Enter for default size): ");
              String newSize = scanner.nextLine().trim();
              if (!newSize.isEmpty()) {
                pizza.setSize(newSize);
              }

              System.out.print("Enter toppings for Pizza (or press Enter for no toppings): ");
              String newToppings = scanner.nextLine().trim();
              if (!newToppings.isEmpty()) {
                pizza.setToppings(newToppings);
              }
            } else if (menuItem instanceof Burger) {
              Burger burger = (Burger) menuItem;
              System.out.println("Customization Options for Burger:");
              System.out.println("1. Has Cheese: " + burger.isHasCheese());
              System.out.println("2. Fries Included: " + burger.isFriesIncluded());

              System.out.print("1. Has Cheese (Yes/No): ");
              String cheeseInput = scanner.nextLine().trim();
              if (!cheeseInput.isEmpty()) {
                burger.setHasCheese(cheeseInput.equalsIgnoreCase("Yes"));
              }

              System.out.print("2. Fries Included (Yes/No): ");
              String friesInput = scanner.nextLine().trim();
              if (!friesInput.isEmpty()) {
                burger.setFriesIncluded(friesInput.equalsIgnoreCase("Yes"));
              }
            } else if (menuItem instanceof Pasta) {
              Pasta pasta = (Pasta) menuItem;
              System.out.println("Customization Options for Pasta:");
              System.out.println("1. Sauce: " + pasta.getSauce());

              System.out.print("Enter sauce for Pasta: ");
              String newSauce = scanner.nextLine().trim();
              if (!newSauce.isEmpty()) {
                pasta.setSauce(newSauce);
              }
            } else if (menuItem instanceof Dessert) {
              Dessert dessert = (Dessert) menuItem;
              System.out.println("Customization Options for Dessert:");
              System.out.println("1. Flavor: " + dessert.getFlavor());

              System.out.print("Enter flavor for Dessert: ");
              String newFlavor = scanner.nextLine().trim();
              if (!newFlavor.isEmpty()) {
                dessert.setFlavor(newFlavor);
              }
            } else if (menuItem instanceof Appetizer) {
              Appetizer appetizer = (Appetizer) menuItem;
              System.out.println("Customization Options for Appetizer:");
              System.out.println("1. Spicy: " + appetizer.isSpicy());

              System.out.print("Is it spicy? (Yes/No): ");
              String spicyInput = scanner.nextLine().trim();
              if (!spicyInput.isEmpty()) {
                appetizer.setSpicy(spicyInput.equalsIgnoreCase("Yes"));
              }
            }
            if (menuItem instanceof Beverage) {
              Beverage beverage = (Beverage) menuItem;
              System.out.println("Customization Options for Beverage:");
              System.out.println("1. Size: " + beverage.getSize());

              System.out.print("Enter size for Beverage (Small, Medium, Large, or press Enter for default size): ");
              String newSize = scanner.nextLine().trim();
              if (!newSize.isEmpty()) {
                beverage.setSize(newSize);
              }
            }

            order.addItem(menuItem);
            System.out.println(menuItem.getName() + " added to the order.");
          } else {
            System.out.println("Invalid item number. Please choose a valid number from the menu.");
          }
        } catch (NumberFormatException e) {
          System.out.println("Invalid input. Please enter a number.");
        }
      } else {
        System.out.println("Invalid category. Please choose a valid category.");
      }
    }
    // Display order details after the loop
    if (orderingComplete) {
      displayColoredBill(order); // Display the entire order list as a colorful bill
      double totalAmount = order.calculateTotal();
      System.out.println("\u001B[1mTotal Amount: " + totalAmount + "/-\u001B[0m");

      // Write the bill to a separate file
      writeBillToFile(order, "bill.txt");

    }

    return order;
  }

  private static void displayColoredBill(Order order) {
    System.out.println("\u001B[1m\u001B[4mBill:\u001B[0m"); // Bold and underline the heading "Bill"
    for (MenuItem item : order.getItems()) {
      item.display();
      item.displayCustomizationOptions(); // Display customization options
      System.out.println();
    }
  }

  private static void writeBillToFile(Order order, String fileName) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      writer.write("Bill:");
      writer.newLine();

      for (MenuItem item : order.getItems()) {
        writer.write(item.getName() + ", Price: ₹" + item.getPrice());
        writer.newLine();
        writer.newLine();
      }

      double totalAmount = order.calculateTotal();
      writer.write("Total Amount: ₹" + totalAmount);
    } catch (IOException e) {
      System.out.println("Error writing the bill to the file: " + e.getMessage());
    }
  }

  private static MenuItem findMenuItemByNumber(Menu<MenuItem> menu, String category, int itemNumber) {
    int currentNumber = 1;

    for (MenuItem item : menu.getItems().values()) {
      String itemName = item.getClass().getSimpleName();
      if (isValidCategory(category) && itemBelongsToCategory(item, category)) {
        if (currentNumber == itemNumber) {
          return item;
        }
        currentNumber++;
      }
    }

    return null;
  }

  private static boolean itemBelongsToCategory(MenuItem item, String category) {
    switch (category) {
      case "1":
        return item instanceof Pizza;
      case "2":
        return item instanceof Burger;
      case "3":
        return item instanceof Beverage;
      case "4":
        return item instanceof Appetizer;
      case "5":
        return item instanceof Dessert;
      case "6":
        return item instanceof Pasta;
      default:
        return false;
    }
  }

  private static void displayCategories() {
    System.out.println("1. Pizza");
    System.out.println("2. Burger");
    System.out.println("3. Beverage");
    System.out.println("4. Appetizer");
    System.out.println("5. Dessert");
    System.out.println("6. Pasta");
    System.out.println("7. Done Ordering");
    System.out.println("8. Exit application");
    System.out.println("Choose a category by entering its number:");
  }

  private static boolean isValidCategory(String category) {
    return category.matches("[1-8]");
  }

  private static void displayMenuByCategory(Menu<MenuItem> menu, String category) {
    switch (category) {
      case "1":
        displayMenu(menu, Pizza.class);
        break;
      case "2":
        displayMenu(menu, Burger.class);
        break;
      case "3":
        displayMenu(menu, Beverage.class);
        break;
      case "4":
        displayMenu(menu, Appetizer.class);
        break;
      case "5":
        displayMenu(menu, Dessert.class);
        break;
      case "6":
        displayMenu(menu, Pasta.class);
        break;
    }
  }

  private static <T extends MenuItem> void displayMenu(Menu<MenuItem> menu, Class<T> categoryClass) {
    int itemNumber = 1;

    for (MenuItem item : menu.getItems().values()) {
      if (categoryClass.isInstance(item)) {
        System.out.println(itemNumber + ". " + item.getName());
        itemNumber++;
      }
    }
  }
}