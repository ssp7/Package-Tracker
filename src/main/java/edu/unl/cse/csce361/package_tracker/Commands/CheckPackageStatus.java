package edu.unl.cse.csce361.package_tracker.Commands;

import edu.unl.cse.csce361.package_tracker.LogicLayer.PackageManager;

public class CheckPackageStatus implements Command {
    @Override
    public void execute() {
        PackageManager.getPackageStatus();
    }

    @Override
    public String toString() {
        return "Check Package Status";
    }
}
