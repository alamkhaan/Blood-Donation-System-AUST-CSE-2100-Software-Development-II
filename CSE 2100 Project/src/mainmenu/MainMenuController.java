/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainmenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class MainMenuController implements Initializable {
    
    public static Stage stage;
    
    @FXML
    private Button searchDonorButton;
    @FXML
    private Button adminPanelButton;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void searchDonor(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/searchdonorpage/SearchDonorPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();           
            MainMenuController.stage = stage;
            //LoginPageController.stage.close();
        } catch (IOException ex) {
            //Logger.getLogger(LoadingPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void adminPanel(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
    }
    
}
