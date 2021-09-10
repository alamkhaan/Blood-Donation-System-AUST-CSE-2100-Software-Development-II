/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registrationpage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class DebugPageController implements Initializable {

    @FXML
    private ComboBox<String> box;
    @FXML
    private Label label;
    @FXML
    private Button b;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("M","F","O");
        box.setItems(list);
        box.setValue("");
        
        
    }    

    @FXML
    private void button(ActionEvent event) {
        if(box.getValue().isEmpty())
            label.setText(box.getValue().toString());
        else
            label.setText("Empty");
    }
    
}
