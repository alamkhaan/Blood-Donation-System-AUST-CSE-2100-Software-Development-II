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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
public class MainMenuContentsController implements Initializable {
    
    public static Stage stage;
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button searchDonorButton;
    @FXML
    private Button adminPanelButton;
    @FXML
    private Button backButton;
    @FXML
    private Button myProfileButton;
    @FXML
    private Button writeNewPostButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(loggedInUserData.getAdmin().equals("0")){
            adminPanelButton.setVisible(false);
        }
        
    }    

    @FXML
    private void searchDonor(ActionEvent event) {
        try {         
            Parent root = FXMLLoader.load(getClass().getResource("/searchdonorpage/SearchDonorPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            NAVIGATION_PAGE_STACK.peek().hide();
            NAVIGATION_PAGE_STACK.push(stage);
            //LoadingPageController.stage = stage;
            //JavaFXApplication2.stage.close();
        } catch (Exception ex) {
            //Logger.getLogger(LoadingPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void adminPanel(ActionEvent event) {
        PageNavigationSystem.pns.goNextPage("/adminpanelpage/AdminPanelPage2.fxml");
    }
    
    @FXML
    void myProfile(ActionEvent event) {
        PageNavigationSystem.pns.goNextPage("/myprofilepage/MyProfilePage2.fxml");
    }
    
    @FXML
    void writeNewPost(ActionEvent event) {
        PageNavigationSystem.pns.goNextPage("/newpostpage/NewPostPage.fxml");
    }
    

    @FXML
    private void back(ActionEvent event) {
        NAVIGATION_PAGE_STACK.pop().close();
        NAVIGATION_PAGE_STACK.peek().show();
    }
    
    @FXML
    void myPosts(ActionEvent event) {
        try {         
            
            anchorPane = FXMLLoader.load(getClass().getResource("/mypostpage/MyPostsPage.fxml"));          
            AnchorPane root = (AnchorPane) PageNavigationSystem.NAVIGATION_PAGE_STACK.peek().getScene().getRoot();
            root.getChildren().remove(1);
            root.getChildren().add(anchorPane);
            Scene scene = new Scene(root);
            
            PageNavigationSystem.NAVIGATION_PAGE_STACK.peek().setScene(scene);
            //LoadingPageController.stage = stage;
            //JavaFXApplication2.stage.close();
        } catch (Exception ex) {
            //Logger.getLogger(LoadingPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
