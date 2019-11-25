package edu.unl.cse.csce361.package_tracker.LogicLayer;

import java.util.List;

public class DepotManager {
    private List<Depot> depotList;

    public List<Depot> getDepotList() {
        return depotList;
    }

    public void setDepotList(List<Depot> depotList) {
        this.depotList = depotList;
    }

    public Depot getDepot(String depotID){
        for(Depot depot : depotList){
            if(depot.getDepotID().equals(depotID)){
                return depot;
            }
        }
        return null;
    }
}
