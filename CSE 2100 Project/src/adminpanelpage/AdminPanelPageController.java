/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpanelpage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class AdminPanelPageController implements Initializable {
    
    @FXML
    private AnchorPane TheMainAnchorPane;
    @FXML
    private Button statisticsButton;
    @FXML
    private Button adminsButton;
    @FXML
    private Button donorsButton;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private SplitPane splitPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void statistics(ActionEvent event) {
    }

    @FXML
    private void admins(ActionEvent event) {
    }

    @FXML
    private void donors(ActionEvent event) {
        try{
            AnchorPane root = FXMLLoader.load(getClass().getResource("/adminpanelpage/DonorsListPage2.fxml"));
            TheMainAnchorPane = root;
            //splitPane.getChildrenUnmodifiable().set(1, root);           
            Scene scene = new Scene(TheMainAnchorPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
        }catch(Exception e){};
        
    }
    
}
