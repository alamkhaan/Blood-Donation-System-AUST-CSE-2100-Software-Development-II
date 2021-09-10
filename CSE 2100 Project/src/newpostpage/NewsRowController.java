/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpostpage;

import Database.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import pagenavigation.PageNavigationSystem;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class NewsRowController implements Initializable {

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
    private Label id;

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
    
}
