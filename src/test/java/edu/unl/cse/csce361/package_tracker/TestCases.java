package edu.unl.cse.csce361.package_tracker;

import edu.unl.cse.csce361.package_tracker.BackEnd.Database;
import edu.unl.cse.csce361.package_tracker.BackEnd.ObjectsConverter;
import edu.unl.cse.csce361.package_tracker.Commands.CheckDroneStatus;
import edu.unl.cse.csce361.package_tracker.Commands.Command;
import edu.unl.cse.csce361.package_tracker.Commands.ExitCommand;
import edu.unl.cse.csce361.package_tracker.FrontEnd.CLI;
import edu.unl.cse.csce361.package_tracker.LogicLayer.Package;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestCases {
    private CLI cli;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void parsePackage() {
        System.out.println(ObjectsConverter.parsePackage("Packages.csv"));
    }

    @Test
    public void parseDepot() {
    }

    @Test
    public void parseDrone() {
    }

    @Test
    public void testParsePackage() {
    }

    @Test
    public void testParseDepot() {
    }

    @Test
    public void testParseDrone() {
    }

    @Test
    public void writePackage() {
        List<Package> pack = ObjectsConverter.parsePackage("Packages.csv");

        boolean success = ObjectsConverter.writePackage(pack, "TestPackages.csv");
        System.out.println(success);
       // List<Package> pack2 = ObjectsConverter.parsePackage("TestPackages.csv");
       // assertEquals(pack.get(0).getOrderNumber(), pack2.get(0).getOrderNumber());
        System.out.println(pack);
        //System.out.println(pack2);

    }

    @Test
    public void Exit() {
        Database.initializeData();
        Command c = new ExitCommand(cli);
        c.execute();

    }

    @Test(timeout = 1000)
    public void CheckDroneStatus() {
        Database.initializeData();
        Command c = new CheckDroneStatus();

        c.execute();
    }
}