package edu.unl.cse.csce361.package_tracker.LogicLayer;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Location {

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Location() {

    }

    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        String locationString = this.x + ", " + this.y;
        return locationString;
    }

    public static boolean DistanceWithinMiles(Location a, Location b) {
        double distance = Point2D.distance(a.getX(), a.getY(), b.getX(), b.getY());
        if (distance > 10.0) {
            return false;
        } else {
            return true;
        }
    }

    public static Location getPackageCurrentLocation (String packageId){
        boolean check = false;
        Package packX = PackageManager.getPackage(packageId);
        Scanner scan = new Scanner(System.in);

        while (check != true) {
            if (packX == null) {
                System.out.println("Package wasn't found from the given ID, Please re-Enter");
                packageId = scan.nextLine();
                packX = PackageManager.getPackage(packageId);

            } else {
                check = true;
            }

        }
        Location packageLocation = packX.getCurrentLocation();

        return packageLocation;
    }

    public static Location getDroneCurrentLocation(String droneId) {
        boolean check = false;
        Drone droneX = DroneManager.getDrone(droneId);
        Scanner scan = new Scanner(System.in);

        while (check != true) {
            if (droneX == null) {
                System.out.println("Package wasn't found from the given ID, Please re-Enter");
                droneId = scan.nextLine();
                droneX = DroneManager.getDrone(droneId);

            } else {
                check = true;
            }

        }
        Location droneLocation = droneX.getLocation();

        return droneLocation;
    }


    public static List<Package> getAllPackagesAtDepot(String depotId) {

        boolean check = false;
        Scanner scan = new Scanner(System.in);
        Depot d = DepotManager.getDepot(depotId);
        List<Package> packageListAtDepot = new ArrayList<>();

        //Keeps asking for input until the depot Id matches with any depots we have in the database
        while (check != true) {
            if (d == null) {
                System.out.println("The depot Id doesn't match! Please re-Enter depotId");
                depotId = scan.nextLine();
                d = DepotManager.getDepot(depotId);
            } else {
                check = true;
            }

        }
        Location depotLocation = d.getDepotLocation();
        double depotX = depotLocation.getX();
        double depotY = depotLocation.getY();

        double packageLocationX;
        double packageLocationY;

        for (Package p : PackageManager.packageList) {
            Location packageLocation = p.getCurrentLocation();
            packageLocationX = packageLocation.getX();
            packageLocationY = packageLocation.getY();

            if (packageLocationX == depotX && packageLocationY == depotY) {
                packageListAtDepot.add(p);
            }

        }

        return packageListAtDepot;
    }

    public static List<Drone> getAllDronesAtDepot(String depotId) {

        boolean check = false;
        Scanner scan = new Scanner(System.in);
        Depot d = DepotManager.getDepot(depotId);
        List<Drone> droneListAtDepot = new ArrayList<>();

        //Keeps asking for input until the depot Id matches with any depots we have in the database
        while(check != true) {
            if (d == null) {
                System.out.println("The depot Id doesn't match! Please re-Enter depotId");
                depotId = scan.nextLine();
                d = DepotManager.getDepot(depotId);
            }
            else {
                check = true;
            }

        }
        Location depotLocation = d.getDepotLocation();
        double depotX = depotLocation.getX();
        double depotY = depotLocation.getY();

        double depotLocationX;
        double depotLocationY;

        for (Drone dr : DroneManager.droneList) {
            Location depotLocations = dr.getLocation();
            depotLocationX = depotLocations.getX();
            depotLocationY = depotLocations.getY();

            if (depotLocationX == depotX && depotLocationY == depotY) {
                droneListAtDepot.add(dr);
            }

        }

        return droneListAtDepot;
    }

    public boolean equals(Location obj) {
        return this.x == obj.x && this.y == obj.y;
    }

}
