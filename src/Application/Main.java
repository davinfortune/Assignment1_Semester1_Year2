package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;


public class  Main extends Application {
    static AnchorPane root;
    static List<AnchorPane> anchor = new ArrayList<AnchorPane>();
    private static int sceneIndex = 0;
    private static propertyAdmin admin = null;
    private static propertyAgent agent = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = (AnchorPane)FXMLLoader.load(getClass().getResource("Anchor.fxml"));

        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("homeScreenGeneral.fxml"))); //index 0
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("homeScreenAdmin.fxml"))); //index 1
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("homeScreenAgent.fxml"))); //index 2
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("loginAdmin.fxml"))); //index 3
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("loginAgent.fxml"))); //index 4
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("registerAdmin.fxml"))); //index 5
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("registerAgent.fxml"))); //index 6
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("createProperty.fxml"))); //index 7
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("deleteProperty.fxml"))); //index 8
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("updateProperty.fxml"))); //index 9
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("updateAgent.fxml"))); //index 10
        root.getChildren().add(anchor.get(0));

        primaryStage.setTitle("Davt.ie");
        primaryStage.setScene(new Scene(root, 700, 550));
        primaryStage.show();
    }

    public static propertyAdmin getAdmin() { return admin; }

    public static propertyAgent getAgent() { return agent; }

    public static void setAdmin(propertyAdmin admin) {Main.admin = admin;}

    public static void setAgent(propertyAgent agent) {Main.agent = agent;}

    private void init_app(){
        for(int i=0; i<anchor.size();i++){
            //can be used to initiate some details about each pane
        }
    }

    public static AnchorPane get_pane(int index){
        return anchor.get(index);
    }

    public static void set_pane(int index){
        root.getChildren().remove(anchor.get(sceneIndex));
        root.getChildren().add(anchor.get(index));
        sceneIndex=index;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
