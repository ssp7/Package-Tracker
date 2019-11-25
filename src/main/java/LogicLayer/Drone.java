package LogicLayer;

public class Drone {

    public Drone(int droneID, String status, Location location, Package shipment){
        this.droneID = droneID;
        this.status = status;
        this.location = location;
        this.shipment = shipment;
    }

    private int droneID;
    private String status;
    private Location location;
    private Package shipment;

    public int getDroneID() {
        return droneID;
    }

    public void setDroneID(int droneID) {
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
}
