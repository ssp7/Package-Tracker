package edu.unl.cse.csce361.package_tracker.LogicLayer;

import edu.unl.cse.csce361.package_tracker.BackEnd.Database;
import edu.unl.cse.csce361.package_tracker.BackEnd.ObjectsConverter;

import java.util.List;

public class DepotManager {
    public static List<Depot> depotList;

    public static Depot getDepot(String depotID){
        for(Depot depot : depotList){
            if(depot.getDepotID().equals(depotID)){
                return depot;
            }
        }
        return null;
    }
    public static void initializeDepot(){
      depotList = Database.readDepots();
    }

}
