/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registrationpage;

//import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class RegistrationPage2Controller implements Initializable {

    @FXML
    private TextField userName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Label userNameStatusL;
    @FXML
    private Label emailStateL;
    @FXML
    private Label passwordStateL;
    @FXML
    private ComboBox<?> gender;
    @FXML
    private ComboBox<?> bloodGroup;
    //@FXML
    //private JFXDatePicker birthDate;
    @FXML
    private Label confirmPasswordL;
    @FXML
    private Label genderStateL;
    @FXML
    private Label contactNoStateL;
    @FXML
    private Label bloodGroupStateL;
    @FXML
    private Label birthDateStateL;
    @FXML
    private TextField contactNo;
    @FXML
    private Label houseNoStateL;
    @FXML
    private Label zillaStateL;
    @FXML
    private TextField houseNo;
    @FXML
    private TextField zilla;
    @FXML
    private TextField upaZilla;
    @FXML
    private TextField thana;
    @FXML
    private Label upaZillaStateL;
    @FXML
    private Label thanaStateL;
    @FXML
    private Button submit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void birthDateChooser(ActionEvent event) {
    }

    @FXML
    private void submit(ActionEvent event) {
    }
    
}
