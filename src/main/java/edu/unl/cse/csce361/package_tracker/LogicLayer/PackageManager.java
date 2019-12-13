package edu.unl.cse.csce361.package_tracker.LogicLayer;

import edu.unl.cse.csce361.package_tracker.BackEnd.Database;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PackageManager {
    public static List<Package> packageList = new ArrayList<>();


    /**
     * Finds the package from the list with the given order number.
     * @param orderNumber order number associated with the package
     * @return package with given order number if success, null if not found;
     */
    public static Package getPackage(String orderNumber){
        for(Package order : packageList){
            if(order.getOrderNumber().equals(orderNumber)){
                return order;
            }
        }

        return null;
    }

    public static void getPackageStatus(){
        //print the drones
        if(packageList.isEmpty()){
            System.out.println("No packages");
            return;
        }
        System.out.println("List of package IDs:");
        for(Package order : packageList){
            System.out.println(order.getOrderNumber());
        }
        System.out.println("Please enter the order number of the package.");
        Scanner input = new Scanner(System.in);

        String requestedOrder = input.nextLine();
        Package requestedPackage = getPackage(requestedOrder);

        if(requestedPackage != null){
            System.out.println(requestedPackage);
        }else{
            System.out.println("Please input a valid order number");
        }

    }
    //This just initialize the data from the package csv file
    public static void initializePackage(){
        packageList = Database.readPackages();
    }

    //This method just makes sure that the customer or the staff is able to request package from the system
    public static void packageRequests(){

        double originLocationX;
        double originLocationY;
        Location origin = new Location();
        Location destination = new Location();
        Location current  = new Location();
        double DestinationLocationX;
        double DestinationLocationY;

        boolean packageDistanceOrigin = false;
        boolean packageDistanceDestination = false;
        //Scanner declared
        Scanner scan = new Scanner(System.in);
        //The method main source code

        System.out.println("Please input the packageID that you would like to setup for the package");
        String PackageID = scan.nextLine();
        System.out.println("Please input the Origin location with a decimal point");

        while(!packageDistanceOrigin) {
            System.out.print("X:");
            originLocationX = Double.parseDouble(scan.nextLine());
            System.out.print("Y:");
            originLocationY = Double.parseDouble(scan.nextLine());

            origin = new Location(originLocationX, originLocationY);

            packageDistanceOrigin = DepotManager.isInRange(origin);

            if(!packageDistanceOrigin){
                System.out.println("The location you provided is not in range for any \"SUAS\" to be picked up");
                System.out.println("Input the location again");
            }
        }

        System.out.println("Please input the Destination Location of your package with a decimal point");

        while(!packageDistanceDestination) {

            System.out.print("X:");
            DestinationLocationX = Double.parseDouble(scan.nextLine());
            System.out.print("Y:");
            DestinationLocationY = Double.parseDouble(scan.nextLine());

            destination = new Location(DestinationLocationX, DestinationLocationY);

            packageDistanceDestination = DepotManager.isInRange(destination);

            if(!packageDistanceDestination){
                System.out.println("The location for the destination of the package is not in range of any SUAS to be delivered");
                System.out.println("Input the destination location again such that it is in range");
            }


        }

        Package p = new Package(PackageID, destination, origin,origin,"Waiting for pickup");

        packageList.add(p);
        System.out.println("Package request created for order number "+ p.getOrderNumber() +"!");
    }

    /*This method just uses two methods made in drone
            to decide that the package is ready to be moved from depot to depot
            or to be moved from depot to destination
     */
    public static void movePackage(){
        //Initialize the scanner
        Scanner scan = new Scanner(System.in);
        //Print all the pakcages at all the depots;
        int count_packages_left = 0;
        System.out.println("List of packages available at depots!");
        System.out.println("Package-Id              Distance from Destination");
        for(Package p : PackageManager.packageList){

            if(p.getStatus().equals("In Depot")){
                Location package_origin = p.getCurrentLocation();
                Location package_dest = p.getDestination();
                double distance = Point2D.distance(package_origin.getX(),package_origin.getY(),package_dest.getX(),package_dest.getY());
                System.out.println(p.getOrderNumber() + "                    " + distance);
                count_packages_left++;
            }
        }

        if(count_packages_left == 0){
            System.out.println("There are no packages left to be delivered");
        }
        else {
            //Only enters in this loop if the no.of packages at depots is more than 0

            System.out.println("Please enter the package Id to Move it!!");
            String packageId = scan.nextLine();

            Package shipment = PackageManager.getPackage(packageId);
            boolean check = false;

            while (check != true) {
                if (shipment == null) {
                    System.out.println("Please re-Enter the package Id");
                    packageId = scan.nextLine();
                    shipment = PackageManager.getPackage(packageId);
                } else {
                    check = true;
                }
            }
            Location location_Package = shipment.getCurrentLocation();
            Location final_destination = shipment.getDestination();

            if (Location.DistanceWithinMiles(location_Package, final_destination) == false) {
                //if enters this loop then basically moves from depot to depot
                //Move package
                System.out.println("The packages is being sent to the next closest depot!");
                DroneManager.transferPackage(shipment);

            } else {
                //moves package from depot to destination
                //deliver package
                System.out.println("Package is going to be delivered to the final destination");
                DroneManager.deliverToCustomer(shipment);
                System.out.println("Package has been delivered!!");
            }
        }
    }

}
