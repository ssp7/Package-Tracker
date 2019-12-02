package edu.unl.cse.csce361.package_tracker.LogicLayer;

public class Package {

    public Package(String orderNumber, Location destination, Location origin, String status) {
        this.OrderNumber = orderNumber;
        this.destination = destination;
        this.origin = origin;
        this.status = status;
    }

    public Package(){

    }

    private String OrderNumber;
    private Location destination;
    private Location origin;
    private String status;

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public Location getOrigin() {
        return origin;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
