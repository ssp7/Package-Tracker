package edu.unl.cse.csce361.package_tracker.LogicLayer;

import java.util.ArrayList;
import java.util.List;
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
}
