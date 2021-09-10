/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminpanelpage;

//import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class DonorsListPage2Controller implements Initializable {

    @FXML
    private AnchorPane donorsListAnchorPane;
    
    //@FXML
    //private JFXListView<Pane> usersList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {         
            
            Pane root;           
            for(int i = 0; i < 6; i++){
                //root = FXMLLoader.load(getClass().getResource("/adminpanelpage/dataRows.fxml")); 
                //usersList.getChildrenUnmodifiable().add(root);
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
