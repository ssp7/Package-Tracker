package edu.unl.cse.csce361.package_tracker.LogicLayer;

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
}
