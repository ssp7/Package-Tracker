package edu.unl.cse.csce361.package_tracker.FrontEnd;

import edu.unl.cse.csce361.package_tracker.Commands.Command;
import edu.unl.cse.csce361.package_tracker.Commands.ExitCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private List<Command> commands;
    private boolean running;
    private Scanner scan = new Scanner(System.in);

    public CLI() {
        commands = new ArrayList<>();
        addInitialCommands();
        running = true;
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void removeCommand(Command command) {
        commands.remove(command);
    }

    public void terminate() {
        running = false;
    }

    private void addInitialCommands() {
        addCommand(new ExitCommand(this));

    }

    public void run() {
        while (running) {
            System.out.println("    ____        __         _          ____                            \n" +
                    "   / __ )____  / /_  ____ ( )_____   / __ \\_________  ____  ___  _____\n" +
                    "  / __  / __ \\/ __ \\/ __ \\|// ___/  / / / / ___/ __ \\/ __ \\/ _ \\/ ___/\n" +
                    " / /_/ / /_/ / / / / / / / (__  )  / /_/ / /  / /_/ / / / /  __(__  ) \n" +
                    "/_____/\\____/_/ /_/_/ /_/ /____/  /_____/_/   \\____/_/ /_/\\___/____/  ");

            for (int i=0; i<commands.size(); i++) {
                System.out.println(i + ". " + commands.get(i));
            }
            System.out.println("Please enter the number to execute the command");
            int input = scan.nextInt();

            commands.get(input).execute();
        }

    }

    public static void main(String[] args) {
        new CLI().run();
    }
}
