package edu.unl.cse.csce361.package_tracker.LogicLayer;

import java.util.List;


public class Depot {

    public Depot(Location depotLocation, String DepotID) {

        this.depotLocation = depotLocation;
        this.depotID = DepotID;
    }

    private Location depotLocation;
    private String depotID;

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
        return "Depot Id: " + depotID + ", Depot Location: (" + depotLocation.getX() + " ," + depotLocation.getY() + ")\n";
    }
}
