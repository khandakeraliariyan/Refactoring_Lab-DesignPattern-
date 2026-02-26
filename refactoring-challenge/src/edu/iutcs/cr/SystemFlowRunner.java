package edu.iutcs.cr;

import edu.iutcs.cr.persons.Buyer;
import edu.iutcs.cr.persons.PaymentMethod;
import edu.iutcs.cr.persons.Seller;
import edu.iutcs.cr.system.DisplayService;
import edu.iutcs.cr.system.SystemDatabase;
import edu.iutcs.cr.vehicles.*;

import java.util.Scanner;

/**
 * @author Raian Rahman
 * @since 4/19/2024
 */
public class SystemFlowRunner {

    public static void run() {
        System.out.println("Welcome to Car Hut");

        System.out.println("Loading existing system");
        SystemDatabase database = SystemDatabase.getInstance();
        System.out.println("Existing system loaded");

        MainMenu mainMenu = new MainMenu();
        DisplayService displayService = new DisplayService();

        while (true) {
            System.out.println("\n\n\n");

            int selectedOperation = mainMenu.showAndSelectOperation();

            if (selectedOperation == 9) {
                database.saveSystem();
                return;
            }

            handleOperation(selectedOperation, database, displayService);
        }
    }

    private static void handleOperation(int selectedOperation, SystemDatabase database, DisplayService displayService) {
        switch (selectedOperation) {
            case 1 -> addNewSeller(database);
            case 2 -> addNewCustomer(database);
            case 3 -> addNewVehicle(database);
            case 4 -> viewInventory(database, displayService);
            case 5 -> viewSellerList(database, displayService);
            case 6 -> viewBuyerList(database, displayService);
            case 7 -> {
                System.out.println("\n\n\nCreate order");
                createOrder();
            }
            case 8 -> viewInvoices(database, displayService);
        }
    }

    private static void addNewSeller(SystemDatabase database) {
        System.out.println("\n\n\nAdd new seller");
        Scanner scanner = new Scanner(System.in);
        String name = readMandatoryString(scanner, "Enter name: ");
        String id = readMandatoryString(scanner, "Enter id: ");
        String email = readMandatoryString(scanner, "Enter email: ");
        database.addSeller(new Seller(name, id, email));
        promptToViewMainMenu();
    }

    private static void addNewCustomer(SystemDatabase database) {
        System.out.println("\n\n\nAdd new customer");
        Scanner scanner = new Scanner(System.in);
        String name = readMandatoryString(scanner, "Enter name: ");
        String id = readMandatoryString(scanner, "Enter id: ");
        String email = readMandatoryString(scanner, "Enter email: ");

        System.out.println("Select payment method:");
        PaymentMethod[] methods = PaymentMethod.values();
        for (int i = 0; i < methods.length; i++) {
            System.out.println((i + 1) + ". " + methods[i]);
        }
        int choice = getValidInput(scanner, 1, methods.length);
        PaymentMethod paymentMethod = methods[choice - 1];

        database.addBuyer(new Buyer(name, id, email, paymentMethod));
        promptToViewMainMenu();
    }

    private static void addNewVehicle(SystemDatabase database) {
        System.out.println("\n\n\nAdd new vehicle");
        addCar(database);
        promptToViewMainMenu();
    }

    private static void viewInventory(SystemDatabase database, DisplayService displayService) {
        System.out.println("\n\n\nInventory list");
        displayService.showInventory(database.getVehicles());
        promptToViewMainMenu();
    }

    private static void viewSellerList(SystemDatabase database, DisplayService displayService) {
        System.out.println("\n\n\nSeller's list");
        displayService.showSellerList(database.getSellers());
        promptToViewMainMenu();
    }

    private static void viewBuyerList(SystemDatabase database, DisplayService displayService) {
        System.out.println("\n\n\nCustomer's list");
        displayService.showBuyerList(database.getBuyers());
        promptToViewMainMenu();
    }

    private static void viewInvoices(SystemDatabase database, DisplayService displayService) {
        System.out.println("\n\n\nInvoice list");
        displayService.showInvoices(database.getInvoices());
        promptToViewMainMenu();
    }

    private static void promptToViewMainMenu() {
        System.out.print("\n\nEnter 0 to view main menu: ");

        Scanner scanner = new Scanner(System.in);
        int val = -1;

        do {
            val = scanner.nextInt();
        } while (val != 0);
    }

    private static void addCar(SystemDatabase database) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the type of vehicle [1-5]: ");
        System.out.println("1. Bus");
        System.out.println("2. Car");
        System.out.println("3. Hatchback");
        System.out.println("4. Sedan");
        System.out.println("5. SUV");

        int vehicleType = getValidInput(scanner, 1, 5);
        Vehicle newItem = createVehicle(vehicleType, scanner);
        database.addVehicle(newItem);
    }

    private static int getValidInput(Scanner scanner, int min, int max) {
        int input = -1;
        while (input < min || input > max) {
            System.out.print("Enter your choice: ");
            input = scanner.nextInt();

            if (input < min || input > max) {
                System.out.println("Enter a valid option!");
            }
        }
        return input;
    }

    private static Vehicle createVehicle(int vehicleType, Scanner scanner) {
        String regNum = readMandatoryString(scanner, "Enter registration number: ");
        String make = readMandatoryString(scanner, "Enter make: ");
        String model = readMandatoryString(scanner, "Enter model: ");
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        return switch (vehicleType) {
            case 1 -> {
                System.out.println("\n\nCreate new bus");
                System.out.print("Enter passenger capacity: ");
                yield new Bus(regNum, make, model, year, price, scanner.nextInt());
            }
            case 2 -> {
                System.out.println("\n\nCreate new car");
                System.out.print("Enter seating capacity: ");
                yield new Car(regNum, make, model, year, price, scanner.nextInt());
            }
            case 3 -> {
                System.out.println("\n\nCreate new hatchback");
                System.out.print("Is the hatchback compact? (true/false): ");
                yield new Hatchback(regNum, make, model, year, price, scanner.nextBoolean());
            }
            case 4 -> {
                System.out.println("\n\nCreate new sedan");
                System.out.print("Does the sedan have a sunroof? (true/false): ");
                yield new Sedan(regNum, make, model, year, price, scanner.nextBoolean());
            }
            case 5 -> {
                System.out.println("\n\nCreate new SUV");
                System.out.print("Is the SUV for off-road use? (true/false): ");
                yield new SUV(regNum, make, model, year, price, scanner.nextBoolean());
            }
            default -> throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
        };
    }

    private static String readMandatoryString(Scanner scanner, String prompt) {
        String input = "";
        while (input.isBlank()) {
            System.out.print(prompt);
            input = scanner.nextLine();
        }
        return input;
    }

    private static void createOrder() {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        while (true) {
            int selectedOperation = showOrderMenu(scanner);

            switch (selectedOperation) {
                case 1 -> cart.addItem();
                case 2 -> cart.removeItem();
                case 3 -> cart.viewCart();
                case 4 -> {
                    createInvoice(cart);
                    return;
                }
                case 5 -> {
                    return;
                }
            }
        }
    }

    private static int showOrderMenu(Scanner scanner) {
        System.out.println("Please enter the type of operation: [1-5]");
        System.out.println("1. Add new vehicle to cart");
        System.out.println("2. Remove vehicle from cart");
        System.out.println("3. View cart");
        System.out.println("4. Confirm purchase");
        System.out.println();
        System.out.println("5. Return to main menu");

        int selectedOperation = scanner.nextInt();

        while (selectedOperation < 1 || selectedOperation > 5) {
            System.out.print("Please select a valid operation: ");
            selectedOperation = scanner.nextInt();
        }

        return selectedOperation;
    }

    private static void createInvoice(ShoppingCart cart) {
        Scanner scanner = new Scanner(System.in);
        SystemDatabase database = SystemDatabase.getInstance();

        Buyer buyer = findBuyer(scanner, database);
        Seller seller = findSeller(scanner, database);

        Invoice invoice = new Invoice(buyer, seller, cart);
        invoice.printInvoice();
        database.addInvoice(invoice);
    }

    private static Buyer findBuyer(Scanner scanner, SystemDatabase database) {
        Buyer buyer = null;
        do {
            System.out.print("Enter buyer id: ");
            String buyerId = scanner.nextLine();
            buyer = database.findBuyerById(buyerId);

            if (buyer == null) {
                System.out.println("Buyer not found. Try again!");
            }
        } while (buyer == null);
        return buyer;
    }

    private static Seller findSeller(Scanner scanner, SystemDatabase database) {
        Seller seller = null;
        do {
            System.out.print("Enter seller id: ");
            String sellerId = scanner.nextLine();
            seller = database.findSellerById(sellerId);

            if (seller == null) {
                System.out.println("Seller not found. Try again!");
            }
        } while (seller == null);
        return seller;
    }
}