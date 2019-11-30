package Application;

import java.io.*;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;

import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;


public class controllerAdmin {
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private TextArea txtAreaFeedback;

    public void handleLoginBtn(ActionEvent e) throws Exception {
        if(txtUsername.getText().length()<4 || txtPassword.getText().length()<4 ){
            txtAreaFeedback.setText("Username and Password need to be 4 characters or more");
        }
        else if(login(txtUsername.getText(),txtPassword.getText())){
            txtAreaFeedback.setText("Successful Login");
             Main.set_pane(1);
        }
        else {
            txtAreaFeedback.setText("Un-Successful Login");
            txtPassword.clear();
        }
    }

    private boolean login(String username, String password) {
        ArrayList<propertyAdmin> admins;
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("admins.xml"));
            admins = (ArrayList<propertyAdmin>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            admins =  new ArrayList<propertyAdmin>();
            txtAreaFeedback.setText("Password File not located");
            return false;

        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return false;
        }

        for (propertyAdmin admin: admins) {
            if(admin.getUsername().equals(username) && admin.getPassword().equals(password))
                return true;
        }
        return false;
    }

    public void handleHomeBtn(ActionEvent e) throws Exception {
        Main.set_pane(0);
    }

    public void handleRegisterAgentBtn(ActionEvent e) throws Exception {
        Main.set_pane(6);
    }
}

