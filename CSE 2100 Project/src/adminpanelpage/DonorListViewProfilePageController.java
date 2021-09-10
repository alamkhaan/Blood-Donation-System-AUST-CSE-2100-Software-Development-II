/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpanelpage;

import Database.Database;
import static Database.Database.specificUserData;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pagenavigation.PageNavigationSystem;
import static pagenavigation.PageNavigationSystem.NAVIGATION_PAGE_STACK;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class DonorListViewProfilePageController implements Initializable {

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
    private Label id;
    @FXML
    private Button backButton;
    @FXML
    private Button makeAdminButton;
    @FXML
    private Label admin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        if(specificUserData.getAdmin().equals("1")){
            makeAdminButton.setText("Remove Admin");
            makeAdminButton.setStyle("-fx-background-color : red;");
            admin.setText("YES");
        }else
            admin.setText("NO");
        
        id.setText(specificUserData.getId());
        name.setText(specificUserData.getUserName());
        email.setText(specificUserData.getEmail());
        contactNo.setText(specificUserData.getContactNo());
        bloodGroup.setText(specificUserData.getBloodGroup());
        zilla.setText(specificUserData.getZilla());
        upaZilla.setText(specificUserData.getUpaZilla());
        thana.setText(specificUserData.getThana());
        houseNo.setText(specificUserData.getHouseNo());
        birthDate.setText(specificUserData.getBirthDate());
        gender.setText(specificUserData.getGender());
        
    }    

    @FXML
    private void back(ActionEvent event) {
        NAVIGATION_PAGE_STACK.peek().close();
        PageNavigationSystem.pns.back();
    }
    
    @FXML
    void makeAdmin(ActionEvent event) {
        Database db = new Database("realDB","jdbc:mysql://localhost:3306/example","root","");
        if(specificUserData.getAdmin().equals("0")){      
            db.makeAdmin(specificUserData.getId());
            Thread x = new Thread(new Runnable(){
                @Override
                public void run() {
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            makeAdminButton.setText("Remove Admin");
                            makeAdminButton.setStyle("-fx-background-color : red;");
                            admin.setText("YES");
                            specificUserData.setAdmin("1");
                            
                        }
                    });
                }  
            });
            x.start();
            
        }
        else{
            db.removeAdmin(specificUserData.getId());          
            Thread x = new Thread(new Runnable(){
                @Override
                public void run() {
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            makeAdminButton.setText("Make Admin");
                            makeAdminButton.setStyle("-fx-background-color :  #3399ff;");
                            admin.setText("NO");
                            specificUserData.setAdmin("0");
                        }
                    });
                }  
            });
            x.start();
            
        }
        //db.readOneRow(specificUserData.getId());
        db.readAllFromDatabase();        
        PageNavigationSystem.pns.upDatePreviousPage("/adminpanelpage/AdminPanelPage2.fxml", "/adminpanelpage/DonorsListPage.fxml");              
    } 
}
