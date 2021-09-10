/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myprofilepage;

import Database.Database;
import static Database.Database.loggedInUserData;
import Database.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import static pagenavigation.PageNavigationSystem.NAVIGATION_PAGE_STACK;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class ChangePasswordPageController implements Initializable {

    @FXML
    private Button doneButton;
    @FXML
    private Button backButton;
    @FXML
    private PasswordField previousPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmPassword;
    
    @FXML
    private Label ppStateL;

    @FXML
    private Label npStateL;

    @FXML
    private Label cpStateL;

    @FXML
    private Label resultL;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
     
        Thread t = new Thread( new Runnable(){
            @Override
            public void run() {
                while( NAVIGATION_PAGE_STACK.peek().isShowing() ){
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            verifyInput();
                        }                    
                    });
                    try{Thread.sleep(100);}catch(Exception e){}
                }
            }            
        });
        t.start();
        
    }    

    @FXML
    private void done(ActionEvent event) {
        if(previousPassword.getText().isEmpty()){
            ppStateL.setText(DataVerification.DataVerification.EMPTY_FIELD);
        }else if( previousPassword.getText().equals(loggedInUserData.getPassword()) ){
            if( confirmPassword.getText().equals(newPassword.getText()) ){
                Database db = new Database("realdb","jdbc:mysql://localhost:3306/example","root","");
                User user = loggedInUserData;
                user.setPassword(newPassword.getText());
                db.updatePassword(user);
                resultL.setStyle("-fx-text-fill: green;");
                resultL.setText("Successfully Updated!");
            }            
        }else{
            ppStateL.setText("Wrong password!");
        }
    }

    @FXML
    private void back(ActionEvent event) {
        NAVIGATION_PAGE_STACK.pop().close();
        NAVIGATION_PAGE_STACK.peek().show();
    }
    
    public void verifyInput(){
        if(previousPassword.getText().isEmpty())
            ppStateL.setText(DataVerification.DataVerification.EMPTY_FIELD);
        else
            ppStateL.setText("");
        if(newPassword.getText().isEmpty()){
            npStateL.setStyle("-fx-text-fill: red;");
            npStateL.setText(DataVerification.DataVerification.EMPTY_FIELD);
        }else
            npStateL.setText("");
        if(confirmPassword.getText().isEmpty()){
            cpStateL.setStyle("-fx-text-fill: red;");
            cpStateL.setText(DataVerification.DataVerification.EMPTY_FIELD);
        }else if( confirmPassword.getText().equals(newPassword.getText()) ){
            cpStateL.setStyle("-fx-text-fill: green;");
            cpStateL.setText("Matched!");
        }else if( !confirmPassword.getText().equals(newPassword.getText()) ){
            cpStateL.setStyle("-fx-text-fill: red;");
            cpStateL.setText("No Matched!");
        }else
            cpStateL.setText("");
    }
    

}