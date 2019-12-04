package edu.unl.cse.csce361.package_tracker.Commands;

public interface Command {
    void execute();
    @Override
    String toString();
}
