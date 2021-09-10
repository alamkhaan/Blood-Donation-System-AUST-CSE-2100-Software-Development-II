/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypostpage;

import static Database.Database.NEWS_DATABASE_STACK;
import static Database.Database.loggedInUserData;
import helperMethods.HelperMethods;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class MyPostsPageController implements Initializable {

    @FXML
    private AnchorPane newsAnchorPane;
    @FXML
    private ListView<AnchorPane> newsList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for(int i = NEWS_DATABASE_STACK.size()-1; i >= 0; i--){
            if(!NEWS_DATABASE_STACK.get(i).getPostedById().equals(loggedInUserData.getId()))
                continue;
            try{
                AnchorPane postPane = FXMLLoader.load(getClass().getResource("/mypostpage/myNewsRow.fxml"));
                
                Label l = (Label) postPane.getChildren().get(0);
                l.setText(NEWS_DATABASE_STACK.get(i).getTitle()+" ("+NEWS_DATABASE_STACK.get(i).getRequiredBlood()+" Needed!)");
                
                l = (Label) postPane.getChildren().get(1);
                l.setText(NEWS_DATABASE_STACK.get(i).getDescription());
                
                l = (Label) postPane.getChildren().get(2);
                l.setText(NEWS_DATABASE_STACK.get(i).getZilla()+",  "+NEWS_DATABASE_STACK.get(i).getUpaZilla()+",  "+NEWS_DATABASE_STACK.get(i).getThana());
                
                l = (Label) postPane.getChildren().get(3);
                
                /*
                if( NEWS_DATABASE_STACK.get(i).getStatus().equals("0") ){
                    l.setText("No Donor Found");
                    l.setStyle("-fx-text-fill: red;");
                }else if( NEWS_DATABASE_STACK.get(i).getStatus().equals("1") ){
                    l.setText("Found Donor");
                    l.setStyle("-fx-text-fill: #ff9900;");
                }else if( NEWS_DATABASE_STACK.get(i).getStatus().equals("2") ){
                    l.setText("Found Donor");
                    l.setStyle("-fx-text-fill: #009900;");
                } 
                */
                
                HelperMethods.setStatusLabel(l, NEWS_DATABASE_STACK.get(i));
                
                l = (Label) postPane.getChildren().get(7);
                l.setText(NEWS_DATABASE_STACK.get(i).getId());
                
                newsList.getItems().add(postPane);
            }catch(Exception e){}
        }
        
    }    
    
}
