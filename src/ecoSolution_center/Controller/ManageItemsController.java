package ecoSolution_center.Controller;

import ecoSolution_center.View.UIControl.Controller;
import ecoSolution_center.View.UIControl.windows;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageItemsController implements Initializable, windows {

    Controller myController;

    @Override
    public void setScreenParent(Controller screenPage) {
        myController = screenPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
