package edu.unl.cse.csce361.package_tracker.Commands;

import edu.unl.cse.csce361.package_tracker.LogicLayer.PackageManager;

public class DeliverPackage implements Command{

    @Override
    public void execute() {
        PackageManager.movePackage();
    }
    @Override
    public String toString(){
        return "Move drone to next closest stop";
    }


}
