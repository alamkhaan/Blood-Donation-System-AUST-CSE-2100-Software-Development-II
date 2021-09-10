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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pagenavigation.PageNavigationSystem;
import static pagenavigation.PageNavigationSystem.NAVIGATION_PAGE_STACK;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class AdminPanelPage2Controller implements Initializable {

    @FXML
    private AnchorPane parentAnchorPane;
    @FXML
    private AnchorPane secondaryAnchorPane;
    @FXML
    private Button statisticsButton;
    @FXML
    private Button adminsButton;
    @FXML
    private Button donorsButton;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Button postRequestsButton;
    @FXML
    private Button postButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void statistics(ActionEvent event) {
        try{
            parentAnchorPane.getChildren().remove(mainAnchorPane);
            mainAnchorPane = FXMLLoader.load(getClass().getResource("/adminpanelpage/StatisticsPage.fxml"));
            parentAnchorPane.getChildren().add(mainAnchorPane);
            //splitPane.getChildrenUnmodifiable().set(1, root);           
            Scene scene = new Scene(parentAnchorPane);
            //Stage stage = new Stage();
            NAVIGATION_PAGE_STACK.peek().setScene(scene);
            //stage.setScene(scene);
            //stage.show();
            
        }catch(Exception e){};
    }

    @FXML
    private void admins(ActionEvent event) {
        try{
            parentAnchorPane.getChildren().remove(mainAnchorPane);
            mainAnchorPane = FXMLLoader.load(getClass().getResource("/adminpanelpage/AdminsListPage.fxml"));
            parentAnchorPane.getChildren().add(mainAnchorPane);
            //splitPane.getChildrenUnmodifiable().set(1, root);           
            Scene scene = new Scene(parentAnchorPane);
            //Stage stage = new Stage();
            NAVIGATION_PAGE_STACK.peek().setScene(scene);
            //stage.setScene(scene);
            //stage.show();
            
        }catch(Exception e){};
    }

    @FXML
    private void donors(ActionEvent event) {
        try{
            parentAnchorPane.getChildren().remove(mainAnchorPane);
            mainAnchorPane = FXMLLoader.load(getClass().getResource("/adminpanelpage/DonorsListPage.fxml"));
            parentAnchorPane.getChildren().add(mainAnchorPane);
            //splitPane.getChildrenUnmodifiable().set(1, root);           
            Scene scene = new Scene(parentAnchorPane);
            //Stage stage = new Stage();
            NAVIGATION_PAGE_STACK.peek().setScene(scene);
            //stage.setScene(scene);
            //stage.show();
            
        }catch(Exception e){};
    }
    
    @FXML
    void postRequests(ActionEvent event) {
        try{
            parentAnchorPane.getChildren().remove(mainAnchorPane);
            mainAnchorPane = FXMLLoader.load(getClass().getResource("/adminpanelpage/PostRequestsPage.fxml"));
            parentAnchorPane.getChildren().add(mainAnchorPane);
            //splitPane.getChildrenUnmodifiable().set(1, root);           
            Scene scene = new Scene(parentAnchorPane);
            //Stage stage = new Stage();
            NAVIGATION_PAGE_STACK.peek().setScene(scene);
            //stage.setScene(scene);
            //stage.show();
            
        }catch(Exception e){};
    }
    
    @FXML
    void post(ActionEvent event) {
        try{
            parentAnchorPane.getChildren().remove(mainAnchorPane);
            mainAnchorPane = FXMLLoader.load(getClass().getResource("/adminpanelpage/PostsPage.fxml"));
            parentAnchorPane.getChildren().add(mainAnchorPane);
            //splitPane.getChildrenUnmodifiable().set(1, root);           
            Scene scene = new Scene(parentAnchorPane);
            //Stage stage = new Stage();
            NAVIGATION_PAGE_STACK.peek().setScene(scene);
            //stage.setScene(scene);
            //stage.show();
            
        }catch(Exception e){};
    }
    
    @FXML
    void back(ActionEvent event) {
        PageNavigationSystem.pns.back();
    }
    
}
