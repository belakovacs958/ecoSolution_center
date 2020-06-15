package ecoSolution_center.Data;

import ecoSolution_center.Controller.ManageItemsController;
import ecoSolution_center.Controller.ManageShopsController;
import ecoSolution_center.Model.LaundryItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMethods {
    ////////////////////////////////////////fields////////////////////////////////////

    private String description = "";
    private String itemStatus = "";
    private String clothingTypeName = "";
    private String orderStatus = "";
    private int laundryItemID = 0;


    //////////////////////////////Methods////////////////////////////////////////

    /**
     * updates order status in database
     * @param laundryItemID
     * @param orderStatus
     */
    public void updateOrderStatus(int laundryItemID, String orderStatus){
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("update tblOrder \n" +
                    "set fldOrderStatus = ? \n" +
                    "where fldOrderID = (select fldOrderID from tblLaundryItem where fldLaundryItemID = ?)");
            query.setString(1, orderStatus);
            query.setInt(2, laundryItemID);
            query.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * updates item status in database
     * @param laundryItemID
     * @param itemStatus
     */
    public void updateItemStatus(int laundryItemID, String itemStatus){
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("update tblLaundryItem\n" +
                    "set fldItemStatus = ? \n" +
                    "where fldLaundryItemID = ? ");
            query.setString(1, itemStatus);
            query.setInt(2, laundryItemID);
            query.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * selects order status from database to display in UI
     * @param laundryItemID
     */
    public void selectOrderStatus(int laundryItemID){
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("SELECT fldOrderStatus \n" +
                    "FROM tblOrder \n" +
                    "WHERE fldOrderID IN (SELECT fldOrderID FROM tblLaundryItem WHERE fldLaundryItemID = ?) ");
            query.setInt(1, laundryItemID);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                orderStatus = resultSet.getString(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * selects laundry item details to display in UI
     * @param laundryItemID
     */
    public void selectLaundryItemDetails( int laundryItemID){
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("select * from tblLaundryItem where fldLaundryItemID = ? ");
            query.setInt(1, laundryItemID);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                description = resultSet.getString(2);
                itemStatus = resultSet.getString(5);
                clothingTypeName = resultSet.getString(4);
                laundryItemID = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * selects all laundry items from the same order as the scanned item
     * @param itemID
     */
    public void selectLaundryItems(int itemID){
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("SELECT * \n" +
                    "  FROM tblLaundryItem \n" +
                    " WHERE fldOrderID IN (SELECT fldOrderID\n" +
                    "                FROM tblLaundryItem\n" +
                    "               WHERE fldLaundryItemID = ?) ");
            query.setInt(1, itemID);
            ResultSet resultSet = query.executeQuery();
            while(resultSet.next()){

                ManageItemsController.laundryItems.add(new LaundryItem(resultSet.getString(2),resultSet.getInt(1),
                        resultSet.getString(5),resultSet.getString(4)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    /**
     * selects all dirty laundry from the selected shop
     * @param shopID
     */
    public void listItemsInShop(int shopID){
        try {
            System.out.println("dbmethods");
            PreparedStatement query = DBConnection.getConnect().prepareStatement("select * from tblLaundryItem where fldItemStatus = 'Dirty in shop' and fldOrderID " +
                    " in (select fldOrderID from tblOrder where fldShopID = ?);");
            query.setInt(1, shopID);
            ResultSet resultSet = query.executeQuery();
            while(resultSet.next()){
                ManageShopsController.laundryItems.add(new LaundryItem(resultSet.getString(2),resultSet.getInt(1),
                        resultSet.getString(5),resultSet.getString(4)));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * counts dirty laundry items in the selected shop
     * @param shopID
     * @return
     */
    public int countItems(int shopID) {
        int itemAmount = 0;
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("select count(fldLaundryItemID) from tblLaundryItem where fldItemStatus = 'Dirty in shop' and fldOrderID  in (select fldOrderID from tblOrder where fldShopID = ?);");
            query.setInt(1, shopID);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                itemAmount = resultSet.getInt(1);
                return itemAmount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemAmount;
    }

    /**
     * selects the shop name for a given laundry item id, where that item was registered
     * @param laundryItemID
     * @return
     */
    public String selectShopName(int laundryItemID){
        String name = "";
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("select fldName from tblShop where  fldShopID =" +
                    " (select fldShopID from tblOrder where fldOrderID = " +
                    "(select fldOrderID from tblLaundryItem where fldLaundryItemID = ?))");
            query.setInt(1, laundryItemID);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString(1);
                return name;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * selects the shop ID for a given laundry item id, where that item was registered
     * @param laundryItemID
     * @return
     */
    public int selectShopID(int laundryItemID){
        int shopID = 0;
        try {
            PreparedStatement query = DBConnection.getConnect().prepareStatement("select fldShopID from tblOrder where fldOrderID = " +
                    "(select fldOrderID from tblLaundryItem where fldLaundryItemID = ?)");
            query.setInt(1, laundryItemID);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                shopID = resultSet.getInt(1);
                return shopID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shopID;
    }





//////////////////////////////////////getters and setters/////////////////////////


    public String getDescription() {
        return description;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public String getClothingTypeName() {
        return clothingTypeName;
    }

    public String getOrderStatus() {return orderStatus;}

    public int getLaundryItemID() {
        return laundryItemID;
    }


}
