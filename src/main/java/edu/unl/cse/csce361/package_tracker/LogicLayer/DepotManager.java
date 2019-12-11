package edu.unl.cse.csce361.package_tracker.LogicLayer;

import edu.unl.cse.csce361.package_tracker.BackEnd.Database;
import edu.unl.cse.csce361.package_tracker.BackEnd.ObjectsConverter;
import java.awt.geom.Point2D;

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

    public static Depot getDepot(Location depotLocation){
        for(Depot depot : depotList){
            if(depot.getDepotLocation().equals(depotLocation)){
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


    public static Depot getNextClosestDepot(Location droneLocation, Location packageDestination){
        Depot nextDepot = null;
        double shortestDistanceToDepot = 0;
        double shortestDistanceToDestination = 0;
        for(Depot depot : depotList){
            Location depotLocation = depot.getDepotLocation();
            double distanceToDepot = Point2D.distance(droneLocation.getX(),droneLocation.getY(), depotLocation.getX(), depotLocation.getY());

            if(distanceToDepot < 10){
                double distanceToDestination = Point2D.distance(packageDestination.getX(),packageDestination.getY(),depotLocation.getX(),depotLocation.getY());

                if(nextDepot == null){
                    nextDepot = depot;
                    shortestDistanceToDepot = distanceToDepot;
                    shortestDistanceToDestination = distanceToDestination;
                }else if(distanceToDepot < shortestDistanceToDepot && distanceToDestination < shortestDistanceToDestination){
                    nextDepot = depot;
                    shortestDistanceToDepot = distanceToDepot;
                    shortestDistanceToDestination = distanceToDestination;
                }
            }
        }

        return nextDepot;
    }

}
