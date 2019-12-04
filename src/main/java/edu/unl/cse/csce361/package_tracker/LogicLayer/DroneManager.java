package edu.unl.cse.csce361.package_tracker.LogicLayer;

import edu.unl.cse.csce361.package_tracker.BackEnd.Database;

import java.util.List;
import java.util.Scanner;

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

    public static void getDroneStatus(){
        //print the drones
        if(droneList.isEmpty()){
            System.out.println("No drones");
            return;
        }

        for(Drone drone : droneList){
            System.out.println(drone.getDroneID());
        }
        System.out.println("Please enter the drone ID.");
        Scanner input = new Scanner(System.in);

        String requestedDroneID = input.nextLine();
        Drone requestedDrone = getDrone(requestedDroneID);

        if(requestedDrone != null){
            System.out.println(requestedDrone);
        }else{
            System.out.println("Please input a valid drone ID");
        }

    }
    public static void initializeDrone(){

        droneList = Database.readDrones();
    }
}
