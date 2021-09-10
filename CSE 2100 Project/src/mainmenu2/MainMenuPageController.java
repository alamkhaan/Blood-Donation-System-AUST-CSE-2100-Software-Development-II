/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainmenu2;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pagenavigation.PageNavigationSystem;



/**
 * FXML Controller class
 *
 * @author labib
 */
public class MainMenuPageController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    
    
    @FXML
    private Button menuButton;
    
    @FXML
    private Button newsButton;
    
    @FXML
    private AnchorPane innerAnchorPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void menu(ActionEvent event) {
        menuButton.setStyle("-fx-background-color : #cc0000");
        newsButton.setStyle("-fx-background-color : #ff0000");
        try {         
            anchorPane.getChildren().remove(innerAnchorPane);
            innerAnchorPane = FXMLLoader.load(getClass().getResource("/mainmenu2/MainMenuContents.fxml"));
            anchorPane.getChildren().add(innerAnchorPane);
            Scene scene = new Scene(anchorPane);
            PageNavigationSystem.NAVIGATION_PAGE_STACK.peek().setScene(scene);
            //LoadingPageController.stage = stage;
            //JavaFXApplication2.stage.close();
        } catch (Exception ex) {
            //Logger.getLogger(LoadingPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void news(ActionEvent event) {
        menuButton.setStyle("-fx-background-color : #ff0000");
        newsButton.setStyle("-fx-background-color : #cc0000");
        try {         
            anchorPane.getChildren().remove(innerAnchorPane);
            innerAnchorPane = FXMLLoader.load(getClass().getResource("/mainmenu2/NewsPageContents.fxml"));
            anchorPane.getChildren().add(innerAnchorPane);
            Scene scene = new Scene(anchorPane);
            PageNavigationSystem.NAVIGATION_PAGE_STACK.peek().setScene(scene);
            //LoadingPageController.stage = stage;
            //JavaFXApplication2.stage.close();
        } catch (Exception ex) {
            //Logger.getLogger(LoadingPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
