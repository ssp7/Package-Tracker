package edu.unl.cse.csce361.package_tracker;

import edu.unl.cse.csce361.package_tracker.BackEnd.ObjectsConverter;
import edu.unl.cse.csce361.package_tracker.LogicLayer.Package;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectsConverterTest {

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
}