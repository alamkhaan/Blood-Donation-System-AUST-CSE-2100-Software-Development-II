/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loadingpage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import static pagenavigation.PageNavigationSystem.NAVIGATION_PAGE_STACK;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class LoadingPageController implements Initializable {
    
    public static Stage stage;
    
    @FXML
    private ProgressBar progressBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BackWork bw = new BackWork();
        progressBar.setProgress(0.0);
        progressBar.progressProperty().bind(bw.progressProperty());      
               
        progressBar.progressProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue.doubleValue() >= 1.0){
                    try {
                    Parent root = FXMLLoader.load(getClass().getResource("/loginpage/LoginPage.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                    LoadingPageController.stage = stage;
                    
                    NAVIGATION_PAGE_STACK.pop().hide();
                    NAVIGATION_PAGE_STACK.push(stage);
                    
                    //JavaFXApplication2.stage.close();
                    } catch (Exception ex) {
                        //Logger.getLogger(LoadingPageController.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }
            }     
        });
        
        
        new Thread(bw).start();
    }    
    
}
