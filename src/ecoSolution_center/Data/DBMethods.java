package ecoSolution_center.Data;

import ecoSolution_center.Controller.ManageItemsController;
import ecoSolution_center.Controller.ManageShopsController;
import ecoSolution_center.Model.LaundryItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMethods {
    ////////////////////////////////////////fields////////////////////////////////////
    private String name;
    private String phoneNo;
    private String description = "";
    private String itemStatus = "";
    private String clothingTypeName = "";
    private String orderStatus = "";
    private int laundryItemID = 0;


    //////////////////////////////Methods////////////////////////////////////////


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
               // System.out.println(resultSet.getString(2)+resultSet.getInt(1)+
                      //  resultSet.getString(5)+resultSet.getString(4));
                ManageItemsController.laundryItems.add(new LaundryItem(resultSet.getString(2),resultSet.getInt(1),
                        resultSet.getString(5),resultSet.getString(4)));
                //System.out.println(CheckOrderController.laundryItems.get() + "  list");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void listItemsInShop(int shopID){
        try {
            System.out.println("dbmethods");
            PreparedStatement query = DBConnection.getConnect().prepareStatement("select * from tblLaundryItem where fldItemStatus = 'Dirty in shop' and fldOrderID  in (select fldOrderID from tblOrder where fldShopID = ?);");
            query.setInt(1, shopID);
            ResultSet resultSet = query.executeQuery();
            while(resultSet.next()){
                ManageShopsController.laundryItems.add(new LaundryItem(resultSet.getString(2),resultSet.getInt(1),
                        resultSet.getString(5),resultSet.getString(4)));

                System.out.println(resultSet.getString(2)+resultSet.getInt(1)+
                          resultSet.getString(5)+resultSet.getString(4));
                System.out.println(shopID);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


//////////////////////////////////////getters and setters/////////////////////////

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getName() {
        return name;
    }

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
