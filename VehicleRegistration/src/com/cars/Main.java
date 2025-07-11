package com.cars;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // the list will hold all vehicles we register
        Collection<Car> vehicleList = new ArrayList<>();

        System.out.println(" Welcome to My Vehicle Tracker!!");

        int menuOption;

        // keeps showing the menu until the user chooses to exit
        do {
            System.out.println("\nChoose an option:");
            System.out.println("(1) Register a new vehicle");
            System.out.println("(2) View all registered vehicles");
            System.out.println("(3) Delete a vehicle by VIN");
            System.out.println("(4) Exit application");

            menuOption = input.nextInt();

            switch (menuOption) {
                case 1:
                    // Allow user to register as many cars as they want in one go
                    String continueOption;
                    do {
                        Car car = new Car();

                        System.out.print("Enter vehicle make (e.g. Toyota): ");
                        String make = input.next();

                        System.out.print("Enter vehicle model (e.g. Corolla): ");
                        String model = input.next();

                        System.out.print("Enter VIN (17 characters): ");
                        String vin = input.next();
                        while (vin.length() != 17) {
                            System.out.print("Oops! VIN must be exactly 17 characters. Try again: ");
                            vin = input.next();
                        }

                        // Ask the user what kind of plate format they want to enter
                        System.out.println("Select plate number format:");
                        System.out.println("(1) Old format (e.g. AASSQP)");
                        System.out.println("(2) New format (e.g. AA66QQGP)");
                        int plateOption = input.nextInt();

                        String plateNumber;
                        if (plateOption == 1) {
                            System.out.print("Enter plate number (old format): ");
                        } else {
                            System.out.print("Enter plate number (new format): ");
                        }
                        plateNumber = input.next();

                        System.out.print("Enter mileage (number of kilometers driven): ");
                        int mileage = input.nextInt();

                        System.out.print("Enter year of manufacture (e.g. 2020): ");
                        int year = input.nextInt();

                        // assigning all values to our car object
                        car.setMake(make);
                        car.setModel(model);
                        car.setVin(vin);
                        car.setPlateNumber(plateNumber);
                        car.setMileage(mileage);
                        car.setYear(year);

                        // adding the car to our list of registered vehicles
                        vehicleList.add(car);
                        System.out.println("\n Vehicle registered successfully!");

                        // asking if the user wants to register another one
                        System.out.print("Would you like to add another vehicle? (yes/no): ");
                        continueOption = input.next().toLowerCase();

                    } while (continueOption.equals("yes"));
                    break;

                case 2:
                    // Time to show off all the cars we've captured
                    if (vehicleList.isEmpty()) {
                        System.out.println("No vehicles have been registered yet.");
                    } else {
                        System.out.println("\n VEHICLE REPORT ");
                        for (Car v : vehicleList) {
                            System.out.println("----------------------------------");
                            System.out.println("Make: " + v.getMake());
                            System.out.println("Model: " + v.getModel());
                            System.out.println("Plate Number: " + v.getPlateNumber());
                            System.out.println("VIN: " + v.getVin());
                            System.out.println("Year: " + v.getYear());
                            System.out.println("Mileage: " + v.getMileage());
                        }
                        System.out.println("----------------------------------");
                        System.out.println("Total vehicles registered: " + vehicleList.size());
                    }
                    break;

                case 3:
                    //to delete a vehicle — we’ll use the VIN to find it
                    if (vehicleList.isEmpty()) {
                        System.out.println("No vehicles to delete.");
                    } else {
                        System.out.print("Enter the VIN of the vehicle you want to delete: ");
                        String vinToDelete = input.next();
                        boolean found = false;

                        // Loop through a copy of the list to avoid modification issues
                        for (Car car : new ArrayList<>(vehicleList)) {
                            if (car.getVin().equalsIgnoreCase(vinToDelete)) {
                                vehicleList.remove(car);
                                System.out.println("Vehicle with VIN " + vinToDelete + " has been successfully deleted.");
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println("No vehicle found with that VIN.");
                        }
                    }
                    break;

                case 4:

                    System.out.println("Thank you for using My Vehicle Tracker. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1, 2, 3, or 4.");
                    break;
            }

        } while (menuOption != 4); // Keeps looping until user selects Exit
    }
}
