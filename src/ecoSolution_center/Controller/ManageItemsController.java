package ecoSolution_center.Controller;

import ecoSolution_center.Data.DBMethods;
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

public class ManageItemsController implements Initializable, windows {

    Controller myController;

    private DBMethods dbMethods = new DBMethods();


    public static ObservableList<LaundryItem> laundryItems = FXCollections.observableArrayList();
    private String itemID = "";

    private String description = dbMethods.getDescription();
    private String itemStatus = dbMethods.getItemStatus();
    private String clothingTypeName = dbMethods.getClothingTypeName();
    private int laundryItemID = dbMethods.getLaundryItemID();


    @FXML
    private TextField itemID_textfield;
    @FXML
    private Label description_label,clothingType_label,itemID_label,itemStatus_label,orderStatus_label;
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
    @FXML
    private ChoiceBox<String> itemStatus_choiceBox, orderStatus_choiceBox;

    @Override
    public void setScreenParent(Controller screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //this fills the choice boxes
        itemStatus_choiceBox.getItems().addAll(Status.dirtyInShop,Status.cleanInShop,Status.completed);
        itemStatus_choiceBox.setValue(Status.dirtyInShop);
        orderStatus_choiceBox.getItems().addAll(Status.dirtyInShop,Status.cleanInShop,Status.completed);
        orderStatus_choiceBox.setValue(Status.dirtyInShop);

        //these are the columns in the table view
        description_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("description"));
        itemStatus_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("itemStatus"));
        clothingType_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, String>("clothingTypeName"));
        itemID_column.setCellValueFactory(new PropertyValueFactory<LaundryItem, Integer>("laundryItemID"));


    }


    public void go() {
        tableview.getItems().clear();
        itemID = itemID_textfield.getText();
        dbMethods.selectLaundryItems(Integer.parseInt(itemID));
        tableview.setItems(laundryItems);
        dbMethods.selectLaundryItemDetails(Integer.parseInt(itemID));
        dbMethods.selectOrderStatus(Integer.parseInt(itemID));
        description_label.setText("Description: " + dbMethods.getDescription());
        itemID_label.setText("Item ID: " + itemID_textfield.getText());
        clothingType_label.setText("Clothing type: " + dbMethods.getClothingTypeName());
        itemStatus_label.setText(dbMethods.getItemStatus());
        orderStatus_label.setText(dbMethods.getOrderStatus());




    }

    public void setStatuses(){
        itemID = itemID_textfield.getText();
        dbMethods.updateOrderStatus(Integer.parseInt(itemID), orderStatus_choiceBox.getValue());
        dbMethods.updateItemStatus(Integer.parseInt(itemID), itemStatus_choiceBox.getValue());

    }
}