package edu.unl.cse.csce361.package_tracker.LogicLayer;

import java.util.List;

public class DroneManager {
    public static List<Drone> droneList;

    public static Drone getDrone(String droneID){
        for(Drone drone : droneList){
            if(drone.getDroneID().equals(droneID)){
                return drone;
            }
        }
        return null;
    }
}
