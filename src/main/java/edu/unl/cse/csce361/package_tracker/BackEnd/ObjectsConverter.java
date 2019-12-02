package edu.unl.cse.csce361.package_tracker.BackEnd;

import edu.unl.cse.csce361.package_tracker.LogicLayer.Location;
import edu.unl.cse.csce361.package_tracker.LogicLayer.Package;
import java.util.*;
public class ObjectsConverter {

    public static List<Package> parsePackage (String filename){

        Set<Map<String,String>> packageSet = CSVReaderWriter.readCSV(filename);
        List <Package> packages = new ArrayList<Package>();

        for(Map<String,String> m : packageSet){

        String packageNumber = m.get("OrderNumber");
        Double DestinationX = Double.parseDouble(m.get("DestinationX"));
        Double DestinationY = Double.parseDouble(m.get("DestinationY"));
        Double OriginX = Double.parseDouble(m.get("OriginX"));
        Double OriginY = Double.parseDouble(m.get("OriginY"));
        String Status = m.get("PackageStatus");

        Location Destinations = new Location(DestinationX,DestinationY);
        Location Origins = new Location (OriginX, OriginY);

         Package pack = new Package(packageNumber,Destinations,Origins,Status);

            packages.add(pack);
        }

return packages;
    }

    public static List<Package> parseDepot (String filename){

        Set<Map<String,String>> depotSet = CSVReaderWriter.readCSV(filename);
        List <Package> depots = new ArrayList<Package>();

        for(Map<String,String> m : depotSet){

            //String packageNumber = m.get("");
            //Double DestinationX = Double.parseDouble(m.get(""));
            //Double DestinationY = Double.parseDouble(m.get("DestinationY"));
            //Double OriginX = Double.parseDouble(m.get("OriginX"));
            //Double OriginY = Double.parseDouble(m.get("OriginY"));
            //String Status = m.get("PackageStatus");

     //       Location Destinations = new Location(DestinationX,DestinationY);
       //     Location Origins = new Location (OriginX, OriginY);

         //   Depot d = new Depot(packageNumber,Destinations,Origins,Status);

           // packages.add(pack);
        }

        return depots;
    }


}
