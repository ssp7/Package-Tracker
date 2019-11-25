package LogicLayer;

import java.util.List;

public class PackageManager {
    private List<Package> packageList;

    public List<Package> getPackageList() {
        return packageList;
    }

    public void setPackageList(List<Package> packageList) {
        this.packageList = packageList;
    }


    /**
     * Finds the package from the list with the given order number.
     * @param orderNumber order number associated with the package
     * @return package with given order number if success, null if not found;
     */
    public Package getPackage(String orderNumber){
        for(Package order : packageList){
            if(order.getOrderNumber().equals(orderNumber)){
                return order;
            }
        }

        return null;
    }
}
