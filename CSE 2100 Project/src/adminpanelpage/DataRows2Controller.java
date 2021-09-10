/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpanelpage;

import Database.Database;
import static Database.Database.DONOR_DATABASE_LIST;
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
import javafx.stage.Stage;
import pagenavigation.PageNavigationSystem;
import static pagenavigation.PageNavigationSystem.NAVIGATION_PAGE_STACK;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class DataRows2Controller implements Initializable {

    @FXML
    private Button viewProfileButton;
    @FXML
    private Button removeUserButton;
    @FXML
    private Label id;
    @FXML
    private Label username;
    @FXML
    private Label bloodGroup;
    @FXML
    private Label gender;
    @FXML
    private Label zilla;
    @FXML
    private Label upaZilla;
    @FXML
    private Label thana;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    

    @FXML
    private void viewProfile(ActionEvent event) {
        
        Thread x = new Thread(new Runnable(){
            @Override
            public void run() {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        Database db = new Database("realDB","jdbc:mysql://localhost:3306/example","root","");
                        db.readOneRow(id.getText());
                    }        
                });
            }         
        });
        //x.start();
        
        Database db = new Database("realDB","jdbc:mysql://localhost:3306/example","root","");
        db.readOneRow(id.getText());
        PageNavigationSystem.pns.goNextPage("/adminpanelpage/DonorListViewProfilePage.fxml");
    }

    @FXML
    private void removeUser(ActionEvent event) {
        Database db = new Database("realDB","jdbc:mysql://localhost:3306/example","root","");
        db.removeOneRow(id.getText());
        db.readAllFromDatabase();
        
        Thread x = new Thread(new Runnable(){
            @Override
            public void run() {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        try{                 
                            PageNavigationSystem.pns.upDateCurrentPage("/adminpanelpage/AdminPanelPage2.fxml", "/adminpanelpage/DonorsListPage.fxml");
                        }catch(Exception e){}
                    }        
                });
            }         
        });
        x.start();

    }
    
    

    
}
