package edu.unl.cse.csce361.package_tracker.LogicLayer;

import edu.unl.cse.csce361.package_tracker.BackEnd.Database;
import edu.unl.cse.csce361.package_tracker.BackEnd.ObjectsConverter;

import java.util.ArrayList;
import java.util.List;

public class DepotManager {
    public static List<Depot> depotList;

    public static Depot getDepot(String depotID){
        for(Depot depot : depotList){
            if(depot.getDepotID().equalsIgnoreCase(depotID)){
                return depot;
            }
        }
        return null;
    }
    public static void initializeDepot() {
        depotList = Database.readDepots();
    }

    public static boolean isInRange(Location location){
        boolean inRange = false;
        for(Depot depot : depotList){
            if(Location.DistanceWithinMiles(location, depot.getDepotLocation())){
                inRange = true;
            }
        }

        return inRange;
    }

}
