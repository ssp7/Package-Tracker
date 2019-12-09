package edu.unl.cse.csce361.package_tracker.LogicLayer;

import java.util.List;


public class Depot {

    public Depot(List<Drone> droneList, List<Package> packageQueue, Location depotLocation, String DepotID) {
        this.droneList = droneList;
        this.packageQueue = packageQueue;
        this.depotLocation = depotLocation;
        this.depotID = DepotID;
    }


    private List<Drone> droneList;
    private List<Package> packageQueue;
    private Location depotLocation;
    private String depotID;

    public List<Drone> getDroneList() {
        return droneList;
    }

    public void setDroneList(List<Drone> droneList) {
        this.droneList = droneList;
    }

    public List<Package> getPackageQueue() {
        return packageQueue;
    }

    public void setPackageQueue(List<Package> packageQueue) {
        this.packageQueue = packageQueue;
    }

    public Location getDepotLocation() {
        return depotLocation;
    }

    public void setDepotLocation(Location depotLocation) {
        this.depotLocation = depotLocation;
    }

    public String getDepotID() {
        return depotID;
    }

    public void setDepotID(String depotID) {
        this.depotID = depotID;
    }

    @Override
    public String toString() {
        return "Depot Id :- " + depotID + ", Depot Location :- ( " + depotLocation.getX() + " ," + depotLocation.getY() + ")";
    }
}
