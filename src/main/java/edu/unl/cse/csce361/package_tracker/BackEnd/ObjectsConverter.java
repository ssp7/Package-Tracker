package edu.unl.cse.csce361.package_tracker.BackEnd;


import edu.unl.cse.csce361.package_tracker.LogicLayer.*;
import edu.unl.cse.csce361.package_tracker.LogicLayer.Package;

import java.util.*;
public class ObjectsConverter {

    public static List<Package> parsePackage(String filename) {

        Set<Map<String, String>> packageSet = CSVReaderWriter.readCSV(filename);
        List<Package> packages = new ArrayList<Package>();

        for (Map<String, String> m : packageSet) {

            String packageNumber = m.get("OrderNumber");
            Double DestinationX = Double.parseDouble(m.get("DestinationX"));
            Double DestinationY = Double.parseDouble(m.get("DestinationY"));
            Double OriginX = Double.parseDouble(m.get("OriginX"));
            Double OriginY = Double.parseDouble(m.get("OriginY"));
            String Status = m.get("PackageStatus");

            Location Destinations = new Location(DestinationX, DestinationY);
            Location Origins = new Location(OriginX, OriginY);

            Package pack = new Package(packageNumber, Destinations, Origins, Status);

            packages.add(pack);
        }

        return packages;
    }

    public static List<Depot> parseDepot(String filename) {

        Set<Map<String, String>> depotSet = CSVReaderWriter.readCSV(filename);
        List<Depot> depots = new ArrayList<Depot>();


        for (Map<String, String> m : depotSet) {

            List<Drone> d = new ArrayList<Drone>();
            List<Package> p = new ArrayList<Package>();

            String depotNumber = m.get("DepotID");
            Double LocationX = Double.parseDouble(m.get("LocationX"));
            Double LocationY = Double.parseDouble(m.get("LocationY"));

            Location l = new Location(LocationX, LocationY);

            String package1 = m.get("Package1");
            if (package1 != null && !package1.isEmpty()) {
                Package p1 = PackageManager.getPackage(package1);
                p.add(p1);
            }
            String package2 = m.get("Package2");
            if (package2 != null && !package2.isEmpty()) {
                Package p2 = PackageManager.getPackage(package2);
                p.add(p2);
            }
            String package3 = m.get("Package3");
            if (package3 != null && !package3.isEmpty()) {
                Package p3 = PackageManager.getPackage(package3);
                p.add(p3);
            }
            String package4 = m.get("Package4");
            if (package4 != null && !package4.isEmpty()) {
                Package p4 = PackageManager.getPackage(package4);
                p.add(p4);
            }
            String package5 = m.get("Package5");
            if (package5 != null && !package5.isEmpty()) {
                Package p5 = PackageManager.getPackage(package5);
                p.add(p5);
            }
            String Drone1 = m.get("Drone1");
            if (Drone1 != null && !Drone1.isEmpty()) {
                Drone d1 = DroneManager.getDrone(Drone1);
                d.add(d1);
            }
            String Drone2 = m.get("Drone2");
            if (Drone2 != null && !Drone2.isEmpty()) {
                Drone d2 = DroneManager.getDrone(Drone2);
                d.add(d2);
            }
            String Drone3 = m.get("Drone3");
            if (Drone3 != null && !Drone3.isEmpty()) {
                Drone d3 = DroneManager.getDrone(Drone3);
                d.add(d3);
            }
            String Drone4 = m.get("Drone4");
            if (Drone4 != null && !Drone4.isEmpty()) {
                Drone d4 = DroneManager.getDrone(Drone4);
                d.add(d4);
            }
            String Drone5 = m.get("Drone5");
            if (Drone5 != null && !Drone5.isEmpty()) {
                Drone d5 = DroneManager.getDrone(Drone5);
                d.add(d5);
            }

            Depot de = new Depot(d, p, l, depotNumber);

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

            String packageId = m.get("packageId");
            if (packageId != null && !packageId.isEmpty()) {
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
            packageMap.put("DestinationX", Double.toString(pack.getDestination().getLatitude()));
            packageMap.put("DestinationY", Double.toString(pack.getDestination().getLongitude()));
            packageMap.put("OriginX", Double.toString(pack.getOrigin().getLongitude()));
            packageMap.put("OriginY", Double.toString(pack.getOrigin().getLatitude()));
            packageMap.put("PackageStatus", pack.getStatus());
            data.add(packageMap);
        }

        boolean check = CSVReaderWriter.writeCSV(filename, data);

        return check;
    }

    public static boolean writeDrones(List<Drone> drones, List<Package> packages, String filename) {
        Set<Map<String, String>> data = new HashSet<Map<String, String>>();
        for (Drone drone : drones) {
            Map<String, String> droneMap = new HashMap<>();
            droneMap.put("DroneID", drone.getDroneID());
            droneMap.put("Status", drone.getStatus());
            droneMap.put("LocationX", Double.toString(drone.getLocation().getLatitude()));
            droneMap.put("LocationY", Double.toString(drone.getLocation().getLongitude()));
            for (Package pack : packages) {
                if (pack.getOrderNumber() != null) {
                    droneMap.put("packageID", pack.getOrderNumber());
                }
            }
            data.add(droneMap);
        }
        boolean check = CSVReaderWriter.writeCSV(filename, data);
        return check;
    }

    public static boolean writeDepot(List<Depot> depots, List<Package> packages, List<Drone> drones, String filename) {
        Set<Map<String,String>> data = new HashSet<Map<String,String>>();
        for(Depot depot: depots) {
            Map<String, String> depotMap = new HashMap<>();
            depotMap.put("DepotID", depot.getDepotID());
            depotMap.put("LocationX", Double.toString(depot.getDepotLocation().getLatitude()));
            depotMap.put("LocationY", Double.toString(depot.getDepotLocation().getLatitude()));

            for (int i = 0; i < packages.size(); i++) {
                depotMap.put("Package" + i, packages.get(i).getOrderNumber());
            }
            for (int j = 0; j < drones.size(); j++) {
                depotMap.put("Drone" + j, drones.get(j).getDroneID());
            }
            data.add(depotMap);
        }
        boolean check = CSVReaderWriter.writeCSV(filename, data);
        return check;
    }
}