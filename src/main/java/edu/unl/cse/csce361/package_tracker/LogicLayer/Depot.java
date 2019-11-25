package edu.unl.cse.csce361.package_tracker.LogicLayer;

import java.util.List;
import java.util.Queue;


public class Depot {

    public Depot(List<Drone> droneList, Queue<Package> packageQueue, Location depotLocation) {
        this.droneList = droneList;
        this.packageQueue = packageQueue;
        this.depotLocation = depotLocation;
    }

    private List<Drone> droneList;
    private Queue<Package> packageQueue;
    private Location depotLocation;
    private String depotID;

    public List<Drone> getDroneList() {
        return droneList;
    }

    public void setDroneList(List<Drone> droneList) {
        this.droneList = droneList;
    }

    public Queue<Package> getPackageQueue() {
        return packageQueue;
    }

    public void setPackageQueue(Queue<Package> packageQueue) {
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

}
