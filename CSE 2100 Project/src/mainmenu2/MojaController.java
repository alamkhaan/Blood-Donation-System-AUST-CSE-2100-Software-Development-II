/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainmenu2;

import static Database.Database.loggedInUserData;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class MojaController implements Initializable {

    @FXML
    private ImageView userImage;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label contactNo;
    @FXML
    private Label bloodGroup;
    @FXML
    private Label zilla;
    @FXML
    private Label upaZilla;
    @FXML
    private Label thana;
    @FXML
    private Label houseNo;
    @FXML
    private Label birthDate;
    @FXML
    private Label gender;
    @FXML
    private Button editProfileButton;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        name.setText(loggedInUserData.getUserName());
        email.setText(loggedInUserData.getEmail());
        contactNo.setText(loggedInUserData.getContactNo());
        bloodGroup.setText(loggedInUserData.getBloodGroup());
        zilla.setText(loggedInUserData.getZilla());
        upaZilla.setText(loggedInUserData.getUpaZilla());
        thana.setText(loggedInUserData.getThana());
        houseNo.setText(loggedInUserData.getHouseNo());
        birthDate.setText(loggedInUserData.getBirthDate());
        gender.setText(loggedInUserData.getGender());
    }    

    @FXML
    private void editProfile(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
    }
    
}
