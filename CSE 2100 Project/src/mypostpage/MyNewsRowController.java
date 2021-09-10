/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypostpage;

import Database.Database;
import static Database.Database.loggedInUserData;
import static Database.Database.specificNewsData;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import pagenavigation.PageNavigationSystem;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class MyNewsRowController implements Initializable {

    @FXML
    private AnchorPane rowAnchorPane;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label location;
    @FXML
    private Label status;
    @FXML
    private Button viewPostButton;
    @FXML
    private Label location1;
    @FXML
    private Label location11;
    @FXML
    private Label id;
    @FXML
    private Button editPostButton;
    @FXML
    private Button removePostButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void viewPost(ActionEvent event) {
        Database db = new Database("posts","jdbc:mysql://localhost:3306/example","root","");
        db.readOneNewsRow(id.getText());
        PageNavigationSystem.pns.goNextPage("/mypostpage/ViewPostPage.fxml");
    }
    
    @FXML
    void editPost(ActionEvent event) {
        Database db = new Database("posts","jdbc:mysql://localhost:3306/example","root","");
        db.readOneNewsRow(id.getText());     
        PageNavigationSystem.pns.goNextPage("/mypostpage/EditMyPostPage.fxml");
    }

    @FXML
    void removePost(ActionEvent event) {
        Database db = new Database("posts","jdbc:mysql://localhost:3306/example","root","");
        db.removeOneRow(id.getText());
        if(loggedInUserData.getAdmin().equals("0")){
            specificNewsData.setStatus("4");
        }else{
            specificNewsData.setStatus("5");
        }
        db.updateOneNewRow(specificNewsData);
        db.readAllNewsFromDatabase();
        try {         
            
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/mypostpage/MyPostsPage.fxml"));          
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
