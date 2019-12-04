package edu.unl.cse.csce361.package_tracker.BackEnd;

import edu.unl.cse.csce361.package_tracker.LogicLayer.Depot;
import edu.unl.cse.csce361.package_tracker.LogicLayer.Drone;
import edu.unl.cse.csce361.package_tracker.LogicLayer.Package;

import java.util.List;

public class Database {

    public static List<Package> readPackages(){
        return ObjectsConverter.parsePackage("Packages.csv");
    }

    public static int writePackages(List<Package> packageList){
        return 0;
    }

    public static List<Drone> readDrones(){
        return ObjectsConverter.parseDrone("Drones.csv");

    }

    public static int writeDrone(List<Drone> droneList){
        return 0;
    }

    public static List<Depot> readDepots(){
        return ObjectsConverter.parseDepot("depots.csv");
    }

    public static int writeDepots(List<Depot> depotList){
        return 0;
    }
}
