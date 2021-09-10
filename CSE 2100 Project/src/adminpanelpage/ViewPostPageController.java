/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpanelpage;

import Database.Database;
import static Database.Database.specificNewsData;
import static Database.Database.specificUserData;
import Database.News;
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
    @FXML
    private Button ApproveButton;
    @FXML
    private Button removePostButton;
    
    public static int page = 0;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        init();
        
        
    }    

    public void init(){
        title.setText(specificNewsData.getTitle());
        description.setText(specificNewsData.getDescription());
        email.setText(specificNewsData.getEmail());
        contactNo.setText(specificNewsData.getContactNo());
        location.setText(specificNewsData.getLocation());
        //status.setText(specificNewsData.getStatus());
        status = setStatusLabel(status, specificNewsData);
        
        if(specificNewsData.getApproved().equals("0")){
            postApproved.setStyle("-fx-text-fill: #cc0000;");
            postApproved.setText("NO");      
            ApproveButton.setText("Approve");
            ApproveButton.setStyle("-fx-background-color:  #0077b3;");
        }else{
            postApproved.setStyle("-fx-text-fill: #009900;");
            postApproved.setText("YES");
            ApproveButton.setText("Disapprove");
            ApproveButton.setStyle("-fx-background-color: red;");
            
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

    @FXML
    private void approve(ActionEvent event) {
        Database db = new Database("posts","jdbc:mysql://localhost:3306/example","root","");
        if(specificNewsData.getApproved().equals("0")){
            db.approvePost(specificNewsData.getId());     
            specificNewsData.setApproved("1");
        }else{
            db.disapprovePost(specificNewsData.getId());
            specificNewsData.setApproved("0");
        }       
        init();
        db.readAllNewsFromDatabase();   
        Thread x = new Thread(new Runnable(){
            @Override
            public void run() {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        try{
                            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/adminpanelpage/PostRequestsPage.fxml"));
                            AnchorPane root2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/adminpanelpage/PostsPage.fxml"));
                            AnchorPane parentRoot = (AnchorPane) FXMLLoader.load(getClass().getResource("/adminpanelpage/AdminPanelPage2.fxml"));
                            
                            parentRoot.getChildren().remove(2);
                            if(page == 2)
                                parentRoot.getChildren().add(2, root);
                            else if(page == 1)
                                parentRoot.getChildren().add(2, root2);
                            
                            Scene scene = new Scene(parentRoot);          
                            Stage stage = PageNavigationSystem.NAVIGATION_PAGE_STACK.pop();
                            PageNavigationSystem.NAVIGATION_PAGE_STACK.peek().setScene(scene);
                            PageNavigationSystem.NAVIGATION_PAGE_STACK.push(stage);
                            
                            
                        }catch(Exception e){}
                    }        
                });
            }         
        });
        x.start();
    }

    @FXML
    private void removePost(ActionEvent event) {
        Database db = new Database("posts","jdbc:mysql://localhost:3306/example","root","");
        db.removeOneRow(specificNewsData.getId());
        db.readAllNewsFromDatabase();
        Thread x = new Thread(new Runnable(){
            @Override
            public void run() {
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        try{
                            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/adminpanelpage/PostRequestsPage.fxml"));
                            AnchorPane root2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/adminpanelpage/PostsPage.fxml"));
                            AnchorPane parentRoot = (AnchorPane) FXMLLoader.load(getClass().getResource("/adminpanelpage/AdminPanelPage2.fxml"));
                            
                            parentRoot.getChildren().remove(2);
                            if(page == 2)
                                parentRoot.getChildren().add(2, root);
                            else if(page == 1)
                                parentRoot.getChildren().add(2, root2);
                            
                            Scene scene = new Scene(parentRoot);     
                            
                            Stage stage = PageNavigationSystem.NAVIGATION_PAGE_STACK.pop();
                            PageNavigationSystem.NAVIGATION_PAGE_STACK.peek().setScene(scene);
                            PageNavigationSystem.NAVIGATION_PAGE_STACK.push(stage);
                            
                            PageNavigationSystem.pns.back();

                        }catch(Exception e){}
                    }        
                });
            }         
        });
        x.start();
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
