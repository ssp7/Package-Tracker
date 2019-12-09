package edu.unl.cse.csce361.package_tracker.LogicLayer;

public class Drone {

    public Drone(String droneID, String status, Location location, Package shipment){
        this.droneID = droneID;
        this.status = status;
        this.location = location;
        this.shipment = shipment;
    }

    public Drone(){
    }

    private String droneID;
    private String status;
    private Location location;
    private Package shipment;

    public String getDroneID() {
        return droneID;
    }

    public void setDroneID(String droneID) {
        this.droneID = droneID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Package getShipment() {
        return shipment;
    }

    public void setShipment(Package shipment) {
        this.shipment = shipment;
    }

    @Override
    public String toString() {
        return "droneID = " + droneID +", status = " + status;
    }
}
