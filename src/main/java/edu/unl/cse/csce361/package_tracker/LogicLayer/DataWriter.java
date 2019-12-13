package edu.unl.cse.csce361.package_tracker.LogicLayer;

import edu.unl.cse.csce361.package_tracker.BackEnd.Database;

public class DataWriter {

    public static void DataBackup(){
        Database.writeDrone(DroneManager.droneList);
        Database.writePackages(PackageManager.packageList);
        Database.writeDepots(DepotManager.depotList);
    }
}
