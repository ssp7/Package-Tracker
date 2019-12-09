package edu.unl.cse.csce361.package_tracker.Commands;

import edu.unl.cse.csce361.package_tracker.LogicLayer.PackageManager;

public class PackageRequest implements Command{

    @Override
    public void execute() {
        PackageManager.packageRequests();
    }

    @Override
    public String toString() {
        return "Request a Package Delivery";
    }
}
