package ecoSolution_center.Controller;

import ecoSolution_center.Data.DBMethods;
import ecoSolution_center.Main;
import ecoSolution_center.Model.LaundryItem;
import ecoSolution_center.Model.Status;
import ecoSolution_center.View.UIControl.Controller;
import ecoSolution_center.View.UIControl.windows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageShopsController implements Initializable, windows {

    Controller myController;

    private DBMethods dbMethods = new DBMethods();


    public static ObservableList<LaundryItem> laundryItems = FXCollections.observableArrayList();
    private String shopID = "";

    private String description = dbMethods.getDescription();
    private String itemStatus = dbMethods.getItemStatus();
    private String clothingTypeName = dbMethods.getClothingTypeName();
    private int laundryItemID = dbMethods.getLaundryItemID();


    @FXML
    private TextField shopID_textfield;
    @FXML
    private Label amount_label;
    @FXML
    private TableView<LaundryItem> tableview;
    @FXML
    private TableColumn<LaundryItem, String> description_column;
    @FXML
    private TableColumn<LaundryItem, String> itemStatus_column;
    @FXML
    private TableColumn<LaundryItem, String> clothingType_column;
    @FXML
    private TableColumn<LaundryItem, Integer> itemID_column;



    @Override
    public void setScreenParent(Controller screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //these are the columns in the table view
        description_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("description"));
        itemStatus_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("itemStatus"));
        clothingType_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("clothingTypeName"));
        itemID_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, Integer>("laundryItemID"));

    }


    public void goToManageItems(){
        myController.setWindow(Main.windowId1);

    }

    public void go(){

        tableview.getItems().clear();
        shopID = shopID_textfield.getText();
        dbMethods.listItemsInShop(Integer.parseInt(shopID));
        amount_label.setText("Amount of laundry items in shop: " + String.valueOf(dbMethods.countItems(Integer.parseInt(shopID))));
        System.out.println("controller" + shopID);
        tableview.setItems(laundryItems);

    }
}
