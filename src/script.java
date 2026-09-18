import java.io.*;
import java.util.*;

// Abstract base class demonstrating Abstraction and Encapsulation
abstract class Shipment {
    private String shipmentId;
    private String destination;
    private double weight; // in kg

    public Shipment(String shipmentId, String destination, double weight) {
        this.shipmentId = shipmentId;
        this.destination = destination;
        this.weight = weight;
    }

    public String getShipmentId() { return shipmentId; }
    public String getDestination() { return destination; }
    public double getWeight() { return weight; }

    // Abstract method to be overridden by subclasses (Polymorphism)
    public abstract double calculateCost();

    @Override
    public String toString() {
        return String.format("ID: %s | Destination: %s | Weight: %.2f kg", shipmentId, destination, weight);
    }
}

// Subclass 1: Express Delivery
class ExpressShipment extends Shipment {
    private double priorityFee;

    public ExpressShipment(String shipmentId, String destination, double weight, double priorityFee) {
        super(shipmentId, destination, weight);
        this.priorityFee = priorityFee;
    }

    @Override
    public double calculateCost() {
        return (getWeight() * 12.0) + priorityFee;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Type: Express | Priority Fee: $%.2f | Total: $%.2f", priorityFee, calculateCost());
    }
}

// Subclass 2: Standard Cargo
class CargoShipment extends Shipment {
    private boolean requiresRefrigeration;

    public CargoShipment(String shipmentId, String destination, double weight, boolean requiresRefrigeration) {
        super(shipmentId, destination, weight);
        this.requiresRefrigeration = requiresRefrigeration;
    }

    @Override
    public double calculateCost() {
        double baseCost = getWeight() * 5.0;
        return requiresRefrigeration ? baseCost + 50.0 : baseCost;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Type: Cargo | Refrigerated: %b | Total: $%.2f", requiresRefrigeration, calculateCost());
    }
}

// Core System Application
public class SmartLogisticsSystem {
    private List<Shipment> inventory = new ArrayList<>();
    private final String dataFile = "shipments_data.txt";

    public void addShipment(Shipment shipment) {
        inventory.add(shipment);
        System.out.println("Shipment successfully added!");
    }

    public void displayAllShipments() {
        if (inventory.isEmpty()) {
            System.out.println("No shipments registered in system.");
            return;
        }
        System.out.println("\n--- Current Logistics Inventory ---");
        for (Shipment s : inventory) {
            System.out.println(s);
        }
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(dataFile))) {
            for (Shipment s : inventory) {
                if (s instanceof ExpressShipment) {
                    writer.println("EXPRESS," + s.getShipmentId() + "," + s.getDestination() + "," + s.getWeight() + ",25.0");
                } else if (s instanceof CargoShipment) {
                    writer.println("CARGO," + s.getShipmentId() + "," + s.getDestination() + "," + s.getWeight() + ",false");
                }
            }
            System.out.println("Inventory saved successfully to " + dataFile);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        File file = new File(dataFile);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            inventory.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("EXPRESS")) {
                    inventory.add(new ExpressShipment(parts[1], parts[2], Double.parseDouble(parts[3]), Double.parseDouble(parts[4])));
                } else if (parts[0].equals("CARGO")) {
                    inventory.add(new CargoShipment(parts[1], parts[2], Double.parseDouble(parts[3]), Boolean.parseBoolean(parts[4])));
                }
            }
            System.out.println("Loaded existing record(s) from " + dataFile);
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SmartLogisticsSystem system = new SmartLogisticsSystem();
        system.loadFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== SMART LOGISTICS MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Express Shipment");
            System.out.println("2. Add Standard Cargo Shipment");
            System.out.println("3. Display All Shipments");
            System.out.println("4. Save Inventory Data");
            System.out.println("5. Exit");
            System.out.print("Select an option (1-5): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        String id1 = scanner.nextLine();
                        System.out.print("Enter Destination: ");
                        String dest1 = scanner.nextLine();
                        System.out.print("Enter Weight (kg): ");
                        double weight1 = Double.parseDouble(scanner.nextLine());
                        system.addShipment(new ExpressShipment(id1, dest1, weight1, 25.0));
                        break;
                    case 2:
                        System.out.print("Enter ID: ");
                        String id2 = scanner.nextLine();
                        System.out.print("Enter Destination: ");
                        String dest2 = scanner.nextLine();
                        System.out.print("Enter Weight (kg): ");
                        double weight2 = Double.parseDouble(scanner.nextLine());
                        system.addShipment(new CargoShipment(id2, dest2, weight2, false));
                        break;
                    case 3:
                        system.displayAllShipments();
                        break;
                    case 4:
                        system.saveToFile();
                        break;
                    case 5:
                        System.out.println("Exiting System. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid selection. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input format. Please enter numbers where expected.");
            }
        }
    }
}