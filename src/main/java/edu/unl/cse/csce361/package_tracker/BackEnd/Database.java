package edu.unl.cse.csce361.package_tracker.BackEnd;

import edu.unl.cse.csce361.package_tracker.LogicLayer.*;
import edu.unl.cse.csce361.package_tracker.LogicLayer.Package;

import java.util.List;

public class Database {

    public static List<Package> readPackages(){
        return ObjectsConverter.parsePackage("Packages.csv");
    }

    public static void writePackages(List<Package> packageList){
        ObjectsConverter.writePackage(packageList, "PackagesTest.csv");
    }

    public static List<Drone> readDrones(){
        return ObjectsConverter.parseDrone("Drones.csv");

    }

    public static void writeDrone(List<Drone> droneList){
       ObjectsConverter.writeDrones(droneList, "DronesTest.csv");
    }

    public static List<Depot> readDepots(){
        return ObjectsConverter.parseDepot("depots.csv");
    }

    public static void writeDepots(List<Depot> depotList){
        ObjectsConverter.writeDepot(depotList,"DepotTest.csv");
    }

    public static void initializeData(){
        PackageManager.initializePackage();
        DroneManager.initializeDrone();
        DepotManager.initializeDepot();
    }
}
