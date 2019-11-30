package Application;
import java.io.*;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;

import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
public class controllerAgent {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtPasswordAdmin;
    @FXML
    private TextField txtIndexList;
    @FXML
    private TextField txtAgentId;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtLocation;
    @FXML
    private TextField txtName;
    @FXML
    private Button readButton;
    @FXML
    private Button updateButton;
    @FXML
    private TextArea txtAreaFeedback;
    @FXML
    private TextArea txtListDetailAgent;
    @FXML
    private TextArea txtListAllAgents;
    @FXML
    ArrayList<propertyAgent> agents;

    public void handleLoginBtn(ActionEvent e) throws Exception {
        if(txtUsername.getText().length()<4 || txtPassword.getText().length()<4 ){
            txtAreaFeedback.setText("Username and Password need \nto be 4 characters or more");
        }
        else if(login(txtUsername.getText(),txtPassword.getText())){
            txtAreaFeedback.setText("Successful Login");
            Main.set_pane(2);
        }
        else {
            txtAreaFeedback.setText("Un-Successful Login");
            txtPassword.clear();
        }
    }

    private boolean login(String username, String password) {
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("agents.xml"));
            agents = (ArrayList<propertyAgent>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            agents =  new ArrayList<propertyAgent>();
            txtAreaFeedback.setText("Password File not located\n" + e);
            return false;

        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File\n" + e);
            return false;
        }

        for (propertyAgent agent: agents) {
            if(agent.getUsername().equals(username) && agent.getPassword().equals(password))
                return true;
        }
        return false;
    }


    public void handleReturnHomeBtn(ActionEvent e) throws Exception
    {
        Main.set_pane(0);
    }


    public void listAllAgents(ActionEvent e){
        try {
            String displayAgents = "";
            for (int i = 0; i < agents.size(); i++) {
                displayAgents += "\nAgent Number " + i + ": \n" + agents.get(i).getName() + "\n";
            }
            txtListAllAgents.setText(displayAgents);
        }
        catch (Exception r){
            txtAreaFeedback.setText("Please Make sure Agents are loaded \n" + r);
        }
    }

    public boolean deleteAgent(int index){
        if(index <= agents.size()){
            agents.remove(index);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean updateAgent(){
        return false;
    }

    public void listDetailAgent() {
        try{
            int index = Integer.parseInt(txtIndexList.getText());
            String displayDetail;
            displayDetail = " Agent Number " + index + " : \n\n" + agents.get(index);
            txtListDetailAgent.setText(displayDetail);
        }
        catch (Exception a){
            txtAreaFeedback.setText("Please enter a integer");
        }
    }


    public void handleLoadAgentBtn(ActionEvent e) throws Exception {
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream
                    (new FileReader("agents.xml"));
            agents = (ArrayList<propertyAgent>) is.readObject();
            is.close();
            txtAreaFeedback.setText("Agents Loaded");
        }
        catch(Exception a){
            txtAreaFeedback.setText("Cannot Load Agents\n" + a);
        }
    }

    public void loadAgents() {
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream
                    (new FileReader("agents.xml"));
            agents = (ArrayList<propertyAgent>) is.readObject();
            is.close();
            txtAreaFeedback.setText("Agents Loaded");
        }
        catch(Exception a){
            txtAreaFeedback.setText("Cannot Load Agents\n" + a);
        }
    }

    public void handleSystemExitBtn(ActionEvent e) throws Exception {
    System.exit(0);
    }

    public void handleRegisterAgentBtn(ActionEvent e) throws Exception {
        Main.set_pane(6);
    }

    public void handleLogOutBtn(ActionEvent e) throws Exception {
        Main.set_pane(0);
    }

    public void handleListAgentsUpadate(ActionEvent e) throws Exception {
        try{
            String displayAgents = "";
            for (int i = 0; i < agents.size(); i++) {
                displayAgents += "\nAgent Number " + i + ": \n" + agents.get(i).getName() + "\n";
            }
            txtListAllAgents.setText(displayAgents);
        }
        catch(Exception a){
            txtAreaFeedback.setText("Cannot Load Agents\n" + a);
        }
    }

    public void handleUpdatePane(ActionEvent e) throws Exception {
        Main.set_pane(10);
    }

}
