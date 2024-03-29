package Application;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class registerControllerAdmin {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtRepeatPassword;
    @FXML
    private TextField txtAdminID;
    @FXML
    private TextField txtFullName;
    @FXML
    private PasswordField txtMasterPassword;
    @FXML
    private TextArea txtAreaFeedback;

    public void handleAdminRegisterBtn(ActionEvent e) throws Exception {
        if(txtMasterPassword.getText().equals("root")) {
            if (txtUsername.getText().length() < 4 || txtPassword.getText().length() < 4) {
                txtAreaFeedback.setText("Username and Password need to be 4 characters or more");
            } else if (!txtPassword.getText().equals(txtRepeatPassword.getText())) {
                txtAreaFeedback.setText("Password must match RepeatPassword");
            } else if (register(txtUsername.getText(), txtPassword.getText(), txtAdminID.getText(), txtFullName.getText())) {
                Main.set_pane(1);
                txtAreaFeedback.setText("Successful Registration");
            }
        }
        else{
            txtAreaFeedback.setText("unSuccessful Registration: Wrong Master Password");
        }
    }

    private boolean register(String username, String password, String adminID, String fullName) {
        ArrayList<propertyAdmin> admins = null;
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("admins.xml"));
            admins = (ArrayList<propertyAdmin>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            admins =  new ArrayList<propertyAdmin>();
            txtAreaFeedback.setText("New Password File");
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return false;
        }

        try {
            propertyAdmin admin = new propertyAdmin(username, password, adminID, fullName);
            admins.add(admin);
            Main.setAdmin(admin);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("admins.xml"));
            out.writeObject(admins);
            out.close();
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error writing to Password File");
            return false;
        }
        return true;
    }

    public void handleReturnHomeBtn(ActionEvent e) throws Exception
    {
        Main.set_pane(0);
    }
}
