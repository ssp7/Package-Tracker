package edu.unl.cse.csce361.package_tracker.BackEnd;

//Objects converter class converts the set of maps to lists and gets backup of all the list after the list is changed and write to backup files
import edu.unl.cse.csce361.package_tracker.LogicLayer.*;
import edu.unl.cse.csce361.package_tracker.LogicLayer.Package;

import java.util.*;
public class ObjectsConverter {

    public static List<Package> parsePackage(String filename) {

        Set<Map<String, String>> packageSet = CSVReaderWriter.readCSV(filename);
        List<Package> packages = new ArrayList<Package>();

        for (Map<String, String> m : packageSet) {

            String packageNumber = m.get("PackageID");
            Double DestinationX = Double.parseDouble(m.get("DestinationX"));
            Double DestinationY = Double.parseDouble(m.get("DestinationY"));
            Double OriginX = Double.parseDouble(m.get("OriginX"));
            Double OriginY = Double.parseDouble(m.get("OriginY"));
            Double currentX = Double.parseDouble(m.get("CurrentX"));
            Double currentY = Double.parseDouble(m.get("CurrentY"));

            String Status = m.get("PackageStatus");

            Location Destinations = new Location(DestinationX, DestinationY);
            Location Origins = new Location(OriginX, OriginY);
            Location currentLocation = new Location(currentX,currentY);

            Package pack = new Package(packageNumber, Destinations, Origins,currentLocation, Status);

            packages.add(pack);
        }

        return packages;
    }

    public static List<Depot> parseDepot(String filename) {

        Set<Map<String, String>> depotSet = CSVReaderWriter.readCSV(filename);
        List<Depot> depots = new ArrayList<Depot>();
        for (Map<String, String> m : depotSet) {
            String depotNumber = m.get("DepotID");
            Double LocationX = Double.parseDouble(m.get("LocationX"));
            Double LocationY = Double.parseDouble(m.get("LocationY"));

            Location l = new Location(LocationX, LocationY);

            Depot de = new Depot(l, depotNumber);

            depots.add(de);
        }

        return depots;
    }

    public static List<Drone> parseDrone(String filename) {

        Set<Map<String, String>> droneSet = CSVReaderWriter.readCSV(filename);

        List<Drone> drones = new ArrayList<Drone>();

        for (Map<String, String> m : droneSet) {
            Package p = null;
            String droneId = m.get("DroneID");
            String Status = m.get("Status");
            Double LocationX = Double.parseDouble(m.get("locationX"));
            Double LocationY = Double.parseDouble(m.get("locationY"));

            Location l = new Location(LocationX, LocationY);

            String packageId = m.get("packageID");
            if (packageId != null && packageId.length()>0) {
                p = PackageManager.getPackage(packageId);
            }

            Drone d = new Drone(droneId, Status, l, p);
            drones.add(d);
        }
        return drones;
    }

    public static boolean writePackage(List<Package> packages, String filename) {
        Set<Map<String, String>> data = new HashSet<Map<String, String>>();

        for (Package pack : packages) {
            Map<String, String> packageMap = new HashMap<>();
            packageMap.put("OrderNumber", pack.getOrderNumber());
            packageMap.put("DestinationX", Double.toString(pack.getDestination().getX()));
            packageMap.put("DestinationY", Double.toString(pack.getDestination().getY()));
            packageMap.put("CurrentX",Double.toString(pack.getCurrentLocation().getX()));
            packageMap.put("CurrentY",Double.toString(pack.getCurrentLocation().getY()));
            packageMap.put("OriginX", Double.toString(pack.getOrigin().getX()));
            packageMap.put("OriginY", Double.toString(pack.getOrigin().getY()));
            packageMap.put("PackageStatus", pack.getStatus());
            data.add(packageMap);

        }

        boolean check = CSVReaderWriter.writeCSV(filename, data);

        return check;
    }

    public static boolean writeDrones(List<Drone> drones, String filename) {
        Set<Map<String, String>> data = new HashSet<Map<String, String>>();
        for (Drone drone : drones) {
            Map<String, String> droneMap = new HashMap<>();
            droneMap.put("DroneID", drone.getDroneID());
            droneMap.put("Status", drone.getStatus());
            droneMap.put("LocationX", Double.toString(drone.getLocation().getX()));
            droneMap.put("LocationY", Double.toString(drone.getLocation().getY()));
            if(drone.getShipment()!=null) {
                droneMap.put("PackageID", drone.getShipment().getOrderNumber());
            }else{
                droneMap.put("PackageID",null);
            }
            data.add(droneMap);
        }
        boolean check = CSVReaderWriter.writeCSV(filename, data);
        return check;
    }

    public static boolean writeDepot(List<Depot> depots, String filename){


        Set<Map<String,String>> data = new HashSet<Map<String,String>>();
        for(Depot depot: depots) {
            Map<String, String> depotMap = new HashMap<>();
            depotMap.put("DepotID", depot.getDepotID());
            depotMap.put("LocationX", Double.toString(depot.getDepotLocation().getX()));
            depotMap.put("LocationY", Double.toString(depot.getDepotLocation().getY()));
            data.add(depotMap);
        }
        boolean check = CSVReaderWriter.writeCSV(filename, data);
        return check;
    }
}