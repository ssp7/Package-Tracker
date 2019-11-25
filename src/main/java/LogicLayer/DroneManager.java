package LogicLayer;

import java.util.List;

public class DroneManager {
    public List<Drone> droneList;

    public List<Drone> getDroneList() {
        return this.droneList;
    }

    public void setDroneList(List<Drone> droneList) {
        this.droneList = droneList;
    }

    public Drone getDrone(int droneID){
        for(Drone drone : droneList){
            if(drone.getDroneID() == droneID){
                return drone;
            }
        }
        return null;
    }
}