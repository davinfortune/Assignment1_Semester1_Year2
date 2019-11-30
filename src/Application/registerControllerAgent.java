package Application;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class registerControllerAgent implements Initializable {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtRepeatPassword;
    @FXML
    private TextField txtAgentID;
    @FXML
    private TextField txtFullName;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextArea txtAreaFeedback;
    @FXML
    private ComboBox<String> locationGeneralSelect = new ComboBox<>();

    public void handleAgentRegisterBtn(ActionEvent e) throws Exception {
            if (txtUsername.getText().length() < 4 || txtPassword.getText().length() < 4) {
                txtAreaFeedback.setText("Username and Password need to be 4 characters or more");
            } else if (!txtPassword.getText().equals(txtRepeatPassword.getText())) {
                txtAreaFeedback.setText("Password must match RepeatPassword");
            } else if (register(txtUsername.getText(), txtPassword.getText(), txtAgentID.getText(), locationGeneralSelect.getValue(), txtFullName.getText(), Integer.parseInt(txtPhoneNumber.getText()))) {
                Main.set_pane(1);
                txtAreaFeedback.setText("Successful Registration");
            }
        }

    private boolean register(String username, String password, String agentId, String location, String name, int phoneNumber) {
        ArrayList<propertyAgent> agents = null;
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("agents.xml"));
            agents = (ArrayList<propertyAgent>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            agents =  new ArrayList<propertyAgent>();
            txtAreaFeedback.setText("New Password File");
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return false;
        }

        try {
            propertyAgent agent = new propertyAgent(username, password, agentId, location, name, phoneNumber);
            agents.add(agent);
            Main.setAgent(agent);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("agents.xml"));
            out.writeObject(agents);
            out.close();
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error writing to Password File");
            return false;
        }
        return true;
    }
    ObservableList<String> locations = FXCollections.observableArrayList("Any", "Co.Waterford", "Co.Kilkenny", "Co.Cork", "Co.Limerick");

    @Override

    public void initialize(URL location, ResourceBundle resources) {

        locationGeneralSelect.setItems(locations);

        locationGeneralSelect.setValue("Any");
    }

    public void handleReturnHomeBtn(ActionEvent e) throws Exception
    {
        Main.set_pane(0);
    }
}
