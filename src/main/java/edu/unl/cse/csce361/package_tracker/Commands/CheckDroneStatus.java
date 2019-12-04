package edu.unl.cse.csce361.package_tracker.Commands;

import edu.unl.cse.csce361.package_tracker.LogicLayer.DroneManager;

public class CheckDroneStatus implements Command {
    @Override
    public void execute() {
        DroneManager.getDroneStatus();
    }

    @Override
    public String toString() {
        return "Check Drone Status";
    }
}
