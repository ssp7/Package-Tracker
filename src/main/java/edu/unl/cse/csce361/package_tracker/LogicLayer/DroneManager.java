package edu.unl.cse.csce361.package_tracker.LogicLayer;

import edu.unl.cse.csce361.package_tracker.BackEnd.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DroneManager {
    public static List<Drone> droneList;
    public static Scanner scan = new Scanner(System.in);
    public static Drone getDrone(String droneID){
        for(Drone drone : droneList){
            if(drone.getDroneID().equalsIgnoreCase(droneID)){
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
    public static void droneDispatch() {
        System.out.println("Here is the list of Drones please select which drone you want to dispatch by entering the droneID or enter CANCEL ");
        for (int i = 0; i < droneList.size(); i++) {
            System.out.println(droneList.get(i).toString());
        }
        String droneId = scan.nextLine();
        boolean check = false;
        Drone drone = getDrone(droneId);
        while (check == false) {
            drone = getDrone(droneId);
            if (drone != null ) {
                if(drone.getShipment() != null ){
                    System.out.println("This particular drone has a package hence cannot dispatch please enter another drone");
                    droneId = scan.nextLine();
                }
                else{
                    check = true;
                }
            }
            else {
                System.out.println("Wrong droneId please enter the droneId again");
                droneId = scan.nextLine();
            }
        }

        boolean depotCheck = false;
        while (depotCheck == false) {
            System.out.println("Here is the List of all the depots Please select where you want the drone to dispatch");
            List<Depot> depotList = new ArrayList<>();
            depotList = DepotManager.depotList;
            for (int i = 0; i < depotList.size(); i++) {
                System.out.println(depotList.get(i).toString());
            }
            String depotId = scan.nextLine();
            boolean check2 = false;
            Depot depot = DepotManager.getDepot(depotId);
            while (check2 == false) {
                depot = DepotManager.getDepot(depotId);
                if (depot == null) {
                    System.out.println("DepotId not found please enter again");
                    depotId = scan.nextLine();
                } else {
                    check2 = true;
                }
            }
            boolean distance = Location.DistanceWithinMiles(drone.getLocation(), depot.getDepotLocation());
          if(distance == false) {
              System.out.println("Please enter the depotId again as the depot is more than 10 miles away");
              depotCheck = false;
          }
          else{
              drone.getLocation().setX(depot.getDepotLocation().getX());
              drone.getLocation().setY(depot.getDepotLocation().getY());
              System.out.println("Drone with ID "+ droneId + " is dispatched to depot with ID "+ depotId );
              depotCheck = true;
          }
        }

    }
}
