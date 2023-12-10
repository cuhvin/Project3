import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AppClient {
    private List<Appliance> appliances = new ArrayList<>();

    public void readAppFile(String file) {
        Scanner scan;
        try {
            File myFile = new File(file);
            scan = new Scanner(myFile);

            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split(",");
                int locationID = Integer.parseInt(data[0]);
                String appName = data[1];
                int onPower = Integer.parseInt(data[2]);
                float probOn = Float.parseFloat(data[3]);
                String appType = data[4];
                double lowPower = 0.0;
                if (appType.equals("true"))
                lowPower = Double.parseDouble(data[5]);

                Appliance aAppl;
                if (appType.equals("true")) {
                    aAppl = new SmartAppliance(locationID, appName, onPower, lowPower, probOn);
                } else {
                    aAppl = new RegularAppliance(locationID, appName, onPower, probOn);
                }

                appliances.add(aAppl);
            }
            System.out.println("Appliances loaded successfully!");
          
        } catch (IOException ioe) {
            System.out.println("The file can not be read");
        }
    }

    public void addAppliance() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter location ID: ");
        int locationID = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter appliance name: ");
        String appName = scanner.nextLine();
        System.out.print("Enter on power: ");
        int onPower = scanner.nextInt();
        System.out.print("Enter probability of staying on: ");
        float probOn = scanner.nextFloat();
        scanner.nextLine();  // Consume newline
        System.out.print("Is it a smart appliance? (true/false): ");
        boolean isSmart = scanner.nextBoolean();
        int lowPower = 0;

        if (isSmart) {
            System.out.print("Enter low power for smart appliance: ");
            lowPower = scanner.nextInt();
        }

        Appliance newAppliance;
        if (isSmart) {
            newAppliance = new SmartAppliance(locationID, appName, onPower, lowPower, probOn);
        } else {
            newAppliance = new RegularAppliance(locationID, appName, onPower, probOn);
        }

        appliances.add(newAppliance);
        System.out.println("Appliance added successfully!");
       
    }

    public void deleteAppliance() {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Enter the appliance ID to delete: ");
        int applianceIDToDelete = scnr.nextInt();

        for (Appliance appliance : appliances) {
            if (appliance.getApplianceID() == applianceIDToDelete) {
                appliances.remove(appliance);
                System.out.println("Appliance deleted successfully!");
                return;
            }
        }

        System.out.println("Appliance not found with ID: " + applianceIDToDelete);
      
    }

    public void listAppliances() {
        for (Appliance appliance : appliances) {
            System.out.println(appliance);
        }
    }

    public static void main(String[] args) {
        AppClient app = new AppClient();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("Type \"A\" Add an appliance");
            System.out.println("Type \"D\" Delete an appliance");
            System.out.println("Type \"L\" List the appliances");
            System.out.println("Type \"F\" Read Appliances from a file");
            System.out.println("Type \"S\" To Start the simulation");
            System.out.println("Type \"Q\" Quit the program");
            String option1 = scan.nextLine().toUpperCase();

            switch (option1) {
                case "A":
                    app.addAppliance();
                    break;
                case "D":
                    app.deleteAppliance();
                    break;
                case "L":
                    app.listAppliances();
                    break;
                case "F":
                    System.out.print("Enter the path to the appliance file: ");
                    String filePath = scan.nextLine();
                    app.readAppFile(filePath);
                    break;
                case "S":
                    System.out.print("Enter the number of simulation steps: ");
                    int numSteps = scan.nextInt();

                    for (int step = 1; step <= numSteps; step++) {
                        System.out.println("Simulation Step: " + step);
                        
                        // Iterate through each appliance and simulate its behavior
                        for (Appliance appliance : app.appliances) {
                            boolean isTurnedOn = appliance.isTurnedOn();

                            if (isTurnedOn) {
                                System.out.println(appliance.getAppName() + " is turned on at location " + appliance.getLocationID());
                            } else {
                                System.out.println(appliance.getAppName() + " is turned off at location " + appliance.getLocationID());
                            }

                            // You can add more simulation logic here based on the appliance's state
                            // For example, if it's a smart appliance, you can simulate switching to low power.
                        }

                        System.out.println(); // Add a newline for better readability between simulation steps
                    }
                    break;

                case "Q":
                    System.out.println("Quitting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
                   
            }
        }
    }
}
