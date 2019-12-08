package edu.unl.cse.csce361.package_tracker.LogicLayer;
import java.awt.geom.Point2D;
public class Location {

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Location(){

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
        String locationString = this.y + ", " + this.x;
        return locationString;
    }

    public static boolean DistanceWithinMiles(Location a, Location b){
        double distance = Point2D.distance(a.getX(),a.getY(),b.getX(),b.getY());
        if(distance > 10.0){
            return false;
        }
        else{
            return true;
        }
    }
}
