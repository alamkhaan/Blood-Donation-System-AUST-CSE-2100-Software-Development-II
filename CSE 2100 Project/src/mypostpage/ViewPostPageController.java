/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypostpage;

import Database.Database;
import static Database.Database.loggedInUserData;
import static Database.Database.specificNewsData;
import static Database.Database.specificUserData;
import Database.News;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pagenavigation.PageNavigationSystem;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class ViewPostPageController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label email;
    @FXML
    private Label contactNo;
    @FXML
    private Label location;
    @FXML
    private Label status;
    @FXML
    private Label postApproved;
    @FXML
    private Label postedBy;
    @FXML
    private Label postId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        title.setText(specificNewsData.getTitle()+" ("+specificNewsData.getRequiredBlood()+" Needed!)");
        description.setText(specificNewsData.getDescription());
        email.setText(specificNewsData.getEmail());
        contactNo.setText(specificNewsData.getContactNo());
        location.setText(specificNewsData.getLocation());
        //status.setText(specificNewsData.getStatus());
        status = setStatusLabel(status, specificNewsData);
        
        if(specificNewsData.getApproved().equals("0")){
            postApproved.setStyle("-fx-text-fill: #cc0000;");
            postApproved.setText("NO");
        }else{
            postApproved.setStyle("-fx-text-fill: #009900;");
            postApproved.setText("YES");
        }
            
        //postedBy.setText(specificNewsData.getPostedById());
        postId.setText(specificNewsData.getId());
        Database db = new Database("realDB","jdbc:mysql://localhost:3306/example","root","");
        db.readOneRow(specificNewsData.getPostedById());
        postedBy.setText(specificUserData.getUserName()+" (ID: "+specificNewsData.getPostedById()+")");
        
    }    

    @FXML
    private void back(ActionEvent event) {
        PageNavigationSystem.pns.back();
    }
    
    public Label setStatusLabel(Label status, News news){
        if(news.getStatus().equals("0")){
            status.setStyle("-fx-text-fill:  #cc0000;");
            status.setText("No Donor Found");
        }else if(news.getStatus().equals("1")){
            status.setStyle("-fx-text-fill: #ff9900;");  
            status.setText("Donor Found");
        }else if(news.getStatus().equals("2")){
            status.setStyle("-fx-text-fill: #009900;");
            status.setText("Donation Received");
        }else if(news.getStatus().equals("3")){
            status.setStyle("-fx-text-fill: #0077b3;");
            status.setText("Edited Post");
            if(loggedInUserData.getAdmin().equals("0")){
                status.setStyle("-fx-text-fill: #808080;");
                status.setText("Pending Approval");
            }
        }else if(news.getStatus().equals("4")){
            status.setStyle("-fx-text-fill:  #cc0000;");
            status.setText("Removed by User");
        }else if(news.getStatus().equals("5")){
            status.setStyle("-fx-text-fill:  #cc0000;");
            status.setText("Removed by Admin");
        }else if(news.getStatus().equals("6")){
            status.setStyle("-fx-text-fill: #808080;");
            status.setText("Pending Approval");
        }
        return status;
    }
    
    
}
