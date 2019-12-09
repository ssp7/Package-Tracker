package edu.unl.cse.csce361.package_tracker.Commands;

import edu.unl.cse.csce361.package_tracker.LogicLayer.DroneManager;

public class DispatchDrone implements Command {

    @Override
    public void execute() {
        DroneManager.droneDispatch();
    }
    @Override
    public String toString(){
        return "Dispatch drone to the depot";
    }

}
