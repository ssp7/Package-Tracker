package edu.unl.cse.csce361.package_tracker.LogicLayer;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import edu.unl.cse.csce361.package_tracker.BackEnd.Database;
import edu.unl.cse.csce361.package_tracker.Commands.PackageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DroneManager {
    public static List<Drone> droneList;
    public static Scanner scan = new Scanner(System.in);

    public static Drone getDrone(String droneID) {
        for (Drone drone : droneList) {
            if (drone.getDroneID().equalsIgnoreCase(droneID)) {
                return drone;
            }
        }
        return null;
    }

    public static void getDroneStatus() {
        //print the drones
        if (droneList.isEmpty()) {
            System.out.println("No drones");
            return;
        }

        for (Drone drone : droneList) {
            System.out.println(drone.getDroneID());
        }
        System.out.println("Please enter the drone ID.");
        Scanner input = new Scanner(System.in);

        String requestedDroneID = input.nextLine();
        Drone requestedDrone = getDrone(requestedDroneID);

        if (requestedDrone != null) {
            System.out.println(requestedDrone);
        } else {
            System.out.println("Please input a valid drone ID");
        }

    }

    public static void initializeDrone() {
        droneList = Database.readDrones();
    }

    public static void droneDispatch() {
        System.out.println("Here is the list of Drones please select which drone you want to dispatch by entering the droneID or enter CANCEL ");
        for (int i = 0; i < droneList.size(); i++) {
            System.out.println(droneList.get(i).toString());
        }
        String droneId = scan.nextLine();
        boolean check = false;
        Drone drone = getDrone(droneId);
        while (check == false) {
            drone = getDrone(droneId);
            if (drone != null) {
                if (drone.getShipment() != null) {
                    System.out.println("This particular drone has a package hence cannot dispatch please enter another drone");
                    droneId = scan.nextLine();
                } else {
                    check = true;
                }
            } else {
                System.out.println("Wrong droneId please enter the droneId again");
                droneId = scan.nextLine();
            }
        }
        boolean depotCheck = false;
        while (depotCheck == false) {
            System.out.println("Here is the List of all the depots Please select where you want the drone to dispatch");
            List<Depot> depotList = new ArrayList<>();
            depotList = DepotManager.depotList;
            for (int i = 0; i < depotList.size(); i++) {
                System.out.println(depotList.get(i).toString());
            }
            String depotId = scan.nextLine();
            boolean check2 = false;
            Depot depot = DepotManager.getDepot(depotId);
            while (check2 == false) {
                depot = DepotManager.getDepot(depotId);
                if (depot == null) {
                    System.out.println("DepotId not found please enter again");
                    depotId = scan.nextLine();
                } else {
                    check2 = true;
                }
            }
            boolean distance = Location.DistanceWithinMiles(drone.getLocation(), depot.getDepotLocation());
            if (distance == false) {
                System.out.println("Please enter the depotId again as the depot is more than 10 miles away");
                depotCheck = false;
            } else {
                drone.getLocation().setX(depot.getDepotLocation().getX());
                drone.getLocation().setY(depot.getDepotLocation().getY());
                System.out.println("Drone with ID " + droneId + " is dispatched to depot with ID " + depotId);
                depotCheck = true;
            }
        }
    }

    public void deliverToCustomer(Package shipment){
        Location originalDepotLocation = shipment.getCurrentLocation();
        Depot nextDepot = null;
        for(Drone d: droneList){
            if (d.getShipment() == null){
                nextDepot = DepotManager.getNextClosestDepot(d.getLocation(), shipment.getDestination());
            }
        }
        Drone nextDrone = null;
        for (Drone d: droneList){
            if(d.getLocation().equals(shipment.getCurrentLocation())){
                nextDrone = d;
            }
        }
        System.out.println("Drone is picking up package from depot to head to the destination.");
        nextDrone.setShipment(shipment);
        nextDrone.setLocation(shipment.getDestination());
        shipment.setCurrentLocation(shipment.getDestination());
        System.out.println("Drone has arrived at destinationn with package: " + shipment.getOrderNumber());
        shipment.setStatus("Delivered");

        nextDrone.setShipment(null);
        nextDrone.setLocation(originalDepotLocation);
        System.out.println("Drone has arrived back at the Depot");
    }

    //TODO: pickup package command
    public static void pickUpPackage() {
        Package packageToPickup = null;
        System.out.println("Packages waiting to be picked up:");
        int waitingPackages = 0;
        for (Package shipment : PackageManager.packageList) {
            if (shipment.getStatus().equals("Waiting for pickup")) {
                System.out.println(shipment);
                waitingPackages++;
            }
        }

        if (waitingPackages == 0) {
            System.out.println("No packages to pick up.");
            return;
        }

        boolean validPackage = false;
        while (!validPackage) {
            System.out.println("Enter a package ID to deploy a drone:");
            String packageID = scan.nextLine();
            packageToPickup = PackageManager.getPackage(packageID);
            if (packageToPickup != null) {
                validPackage = true;
            }
        }

        System.out.println("Package " + packageToPickup.getOrderNumber() + " selected.");
        //look for available drones
        Location packageLocation = packageToPickup.getCurrentLocation();
        boolean droneAvailable = false;
        Drone deliveryDrone = null;
        for (Drone drone : droneList) { //check drones around package
            if (Location.DistanceWithinMiles(packageLocation, drone.getLocation())) {
                droneAvailable = true;
                deliveryDrone = drone;
                break;
            }
        }

        if (!droneAvailable) { //check drones around depots near package
            //find a drone
            System.out.println("No drone in proximity, routing drone to nearest depot");
            //get nearest depot
            for (Depot depot : DepotManager.depotList) {
                if (Location.DistanceWithinMiles(depot.getDepotLocation(), packageLocation)) {
                    for (Drone drone : droneList) {
                        if (Location.DistanceWithinMiles(packageLocation, drone.getLocation()) && drone.getStatus().equals("In Depot")) {
                            deliveryDrone = drone;
                            break;
                        }
                    }
                }
                if (deliveryDrone != null) {
                    System.out.println("Routing drone " + deliveryDrone.getDroneID() + "to depot " + depot.getDepotID() + ".");
                    deliveryDrone.setLocation(depot.getDepotLocation()); //fly the drone to the depot.
                    break;
                }
            }
        }

        //deploy drone
        if (deliveryDrone != null) {
            System.out.println("Deploying drone " + deliveryDrone.getDroneID() + ".");
            Location droneLocation = deliveryDrone.getLocation();
            System.out.println("Drone flying from: " + droneLocation);
            deliveryDrone.setLocation(packageLocation);  //fly to package
            deliveryDrone.setShipment(packageToPickup);  //pick up package
            packageToPickup.setStatus("In transit");
            deliveryDrone.setStatus("En Route");
            System.out.println(deliveryDrone); //display drone.
            deliveryDrone.setLocation(droneLocation);    //fly back to depot
            packageToPickup.setCurrentLocation(droneLocation); //put the package in the depot
            packageToPickup.setStatus("In Depot");
            deliveryDrone.setStatus("In Depot");
            deliveryDrone.setShipment(null); // empty drone
            //find out what depot it flew to.


            Depot depot = DepotManager.getDepot(droneLocation);
            System.out.println("Package delivered to depot " + depot.getDepotID() + ".");
        }
    }

    public static void transferPackage(Package shipment) {

        Depot currentDepot = DepotManager.getDepot(shipment.getCurrentLocation());
        Depot destinationDepot = DepotManager.getNextClosestDepot(shipment.getCurrentLocation(), shipment.getDestination());
        System.out.println(destinationDepot);
        List<Drone> droneListAtDepot = Location.getAllDronesAtDepot(currentDepot.getDepotID());
        List<Drone> emptyDrones = new ArrayList<>();

        if (!droneListAtDepot.isEmpty()) {
            for (Drone droneIteration : droneListAtDepot) {
                if (droneIteration.getShipment() == null) {
                    emptyDrones.add(droneIteration);
                } else {
                    droneListAtDepot = Location.getAllDronesAtDepot(destinationDepot.getDepotID());
                    if (droneIteration.getShipment() == null) {
                        emptyDrones.add(droneIteration);
                    }
                }
            }
        } else {
            droneListAtDepot = Location.getAllDronesAtDepot(destinationDepot.getDepotID());
            for (Drone droneIteration : droneListAtDepot) {
                if (droneIteration.getShipment() == null) {
                    emptyDrones.add(droneIteration);
                }
            }
        }
        Drone transportDrone = emptyDrones.get(0);
        transportDrone.setLocation(currentDepot.getDepotLocation());
        System.out.println("Drone is at "+transportDrone.getLocation() + " right now");
        System.out.println(transportDrone);
        transportDrone.setShipment(shipment);
        transportDrone.setLocation(destinationDepot.getDepotLocation());
        System.out.println("Drone is at "+transportDrone.getLocation() + " right now");
        shipment.setCurrentLocation(destinationDepot.getDepotLocation());
        System.out.println("The package with Id" + shipment.getOrderNumber() + " is at depot " + destinationDepot.getDepotID());
        shipment.setStatus("In Depot");
    }
}
