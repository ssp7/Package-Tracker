package edu.unl.cse.csce361.package_tracker.LogicLayer;

import edu.unl.cse.csce361.package_tracker.BackEnd.Database;

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

        Double originLocationX;
        Double originLocationY;
        Location origin = new Location();
        Location destination = new Location();
        Location current  = new Location();
        Double DestinationLocationX;
        Double DestinationLocationY;

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
}
