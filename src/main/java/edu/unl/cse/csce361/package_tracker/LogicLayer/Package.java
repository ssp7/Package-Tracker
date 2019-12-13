package edu.unl.cse.csce361.package_tracker.LogicLayer;

public class Package {

    private String OrderNumber;
    private Location destination;
    private Location origin;
    private String status;
    private Location currentLocation;

    @Override
    public String toString() {
        return "OrderNumber='" + OrderNumber + '\'' +
                ", status='" + status + '\'';
    }

    public Package(String packageID, Location destination , Location origin, Location currentLocation, String status  ) {
        this.OrderNumber = packageID;
        this.destination = destination;
        this.origin = origin;
        this.currentLocation = currentLocation;
        this.status = status;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.OrderNumber = orderNumber;
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
    public Location getCurrentLocation(){
        return currentLocation;
    }
    public void setCurrentLocation(Location currentLocation){
        this.currentLocation = currentLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
