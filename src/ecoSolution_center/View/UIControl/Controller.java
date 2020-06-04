package ecoSolution_center.View.UIControl;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.HashMap;

public class Controller extends StackPane {


    private HashMap<String, Node> windows = new HashMap<>();



    public Controller(){
        super();
    }

    /**
     *
     * @param name
     * @param window
     */


    public void addWindow(String name, Node window){
        windows.put(name, window);
    }

    /** public Node getWindow(String name){
     return windows.get(name);
     }
     */

    /**
     *
     * @param name
     * @param resource
     * @return
     */

    public boolean loadWindow(String name, String resource){
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("../FXML/" + resource));
            Parent loadWindow = (Parent) myLoader.load();
            ecoSolution_center.View.UIControl.windows myWindowController = ((windows)myLoader.getController());
            myWindowController.setScreenParent(this);
            System.out.println("1:" + name + " 2:" + loadWindow);
            addWindow(name, loadWindow);
            return true;
        }
        // error message when the screen can't load
        catch (Exception e){
            System.out.println("Error: screen hasn't been loaded " + e.getMessage());
            return false;
        }
    }



    public boolean setWindow(final String name) {
        if(windows.get(name) != null) {
            final DoubleProperty opacity = opacityProperty();

            if(!getChildren().isEmpty()){
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(1), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                getChildren().remove(0);
                                getChildren().add(0, windows.get(name));
                                Timeline fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration(1), new KeyValue(opacity, 1.0)));
                                fadeIn.play();
                            }
                        }, new KeyValue(opacity, 0.0)));
                fade.play();

            } else {
                setOpacity(0.0);
                getChildren().add(windows.get(name));
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;

        } else {
            System.out.println("screen hasn't been loaded! \n");  // error message
            return false;
        }
    }

    /**
     *
     * @param name
     * @return
     */

    /**
     public boolean unloadWindow(String name){
     if (windows.remove(name) == null){
     System.out.println("screen didn't exist");
     return false;
     } else {
     return true;
     }
     }
     */

}
