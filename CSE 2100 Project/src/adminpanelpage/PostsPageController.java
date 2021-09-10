/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpanelpage;

import static Database.Database.NEWS_DATABASE_STACK;
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
public class PostsPageController implements Initializable {

    @FXML
    private AnchorPane outterAnchorPane;
    @FXML
    private ListView<AnchorPane> usersList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for(int i = NEWS_DATABASE_STACK.size()-1; i >= 0; i--){
            if(NEWS_DATABASE_STACK.get(i).getApproved().equals("0"))
                continue;
            try{
                AnchorPane postRows = FXMLLoader.load(getClass().getResource("/adminpanelpage/postDataRows.fxml"));

                Label l = (Label)postRows.getChildren().get(2);
                l.setText(NEWS_DATABASE_STACK.get(i).getId());
                
                l = (Label)postRows.getChildren().get(3);
                l.setText(NEWS_DATABASE_STACK.get(i).getTitle());
                
                l = (Label)postRows.getChildren().get(4);
                l.setText(NEWS_DATABASE_STACK.get(i).getRequiredBlood());
                
                l = (Label)postRows.getChildren().get(5);
                l.setText(NEWS_DATABASE_STACK.get(i).getZilla()+" ,"+NEWS_DATABASE_STACK.get(i).getUpaZilla()+" ,"+NEWS_DATABASE_STACK.get(i).getThana());
                
                l = (Label)postRows.getChildren().get(6);                       
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
                
                usersList.getItems().add(postRows);
            }catch(Exception e){}
        }
        
    }    
    
}
