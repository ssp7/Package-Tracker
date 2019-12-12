package edu.unl.cse.csce361.package_tracker.Commands;

import edu.unl.cse.csce361.package_tracker.LogicLayer.DroneManager;

public class PickUpPackage implements Command{


    @Override
    public void execute() {
        DroneManager.pickUpPackage();
    }

    @Override
    public String toString() {
        return "Pick Up Package";
    }
}
