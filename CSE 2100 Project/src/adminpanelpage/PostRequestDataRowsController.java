/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpanelpage;

import Database.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
public class PostRequestDataRowsController implements Initializable {

    @FXML
    private Button viewPostButton;
    @FXML
    private Button removePostButton;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label bloodGroup;
    @FXML
    private Label location;
    @FXML
    private Label status;

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
        PageNavigationSystem.pns.goNextPage("/adminPanelPage/ViewPostPage.fxml");
        ViewPostPageController.page = 2;
        //PageNavigationSystem.pns.goNextPage("/adminpanelpage/AdminPanelViewProfilePage.fxml");        
    }

    @FXML
    private void removePost(ActionEvent event) {
        Database db = new Database("posts","jdbc:mysql://localhost:3306/example","root","");
        db.removeOneRow(id.getText());
        db.readAllNewsFromDatabase();
        
        Thread x = new Thread(new Runnable(){
            @Override
            public void run() {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        try{                           
                            PageNavigationSystem.pns.upDateCurrentPage("/adminpanelpage/AdminPanelPage2.fxml", "/adminpanelpage/PostRequestsPage.fxml");           
                        }catch(Exception e){}
                    }        
                });
            }         
        });
        x.start();
    }
    
}
