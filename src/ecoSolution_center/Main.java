package ecoSolution_center;

import ecoSolution_center.View.UIControl.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {


        //  window id and the fxml file
        public static String windowId1 = "Manage items";
        public static String window1File = "manageItems.fxml";
        public static String windowId2 = "Manage shops";
        public static String window2File = "manageShops.fxml";



        /**
         *
         * @param primaryStage
         */

        @Override
        public void start(Stage primaryStage){


            Controller mainContainer = new Controller();
            mainContainer.loadWindow(Main.windowId1, Main.window1File);
            mainContainer.loadWindow(Main.windowId2, Main.window2File);


            // set the default screen
            mainContainer.setWindow(Main.windowId1);


            Group root = new Group();
            root.getChildren().addAll(mainContainer);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.show();

        }



        public static void main(String[] args) {
            launch(args);
        }
    }
