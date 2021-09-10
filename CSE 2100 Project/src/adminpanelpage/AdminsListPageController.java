/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpanelpage;

import static Database.Database.DONOR_DATABASE_LIST;
import Database.User;
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
public class AdminsListPageController implements Initializable {

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
        
        try {         
            
            AnchorPane root;           
            for(int i = 0; i < DONOR_DATABASE_LIST.size() && DONOR_DATABASE_LIST.get(i).getAdmin().equals("1"); i++){               
                User user;
                root = FXMLLoader.load(getClass().getResource("/adminpanelpage/dataAdminRows.fxml")); 
                user = DONOR_DATABASE_LIST.get(i);
                Label l = (Label) root.getChildren().get(2);
                l.setText(user.getId());
                
                l = (Label) root.getChildren().get(3);
                l.setText(user.getUserName());
                
                l = (Label) root.getChildren().get(4);
                l.setText(user.getBloodGroup());
                
                l = (Label) root.getChildren().get(5);
                l.setText(user.getGender());
                
                l = (Label) root.getChildren().get(6);
                l.setText(user.getZilla());
                
                l = (Label) root.getChildren().get(7);
                l.setText(user.getUpaZilla());
                
                l = (Label) root.getChildren().get(8);
                l.setText(user.getThana());
   
                usersList.getItems().add(root);
            }
                
            //Scene scene = new Scene(r);
            //Stage stage = new Stage();
            //stage.setScene(scene);
            //stage.show();          
            //NAVIGATION_PAGE_STACK.peek().close();
            //NAVIGATION_PAGE_STACK.push(stage);
            //LoadingPageController.stage = stasge;
            //JavaFXApplication2.stage.close();
        } catch (Exception ex) {
            //Logger.getLogger(LoadingPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
