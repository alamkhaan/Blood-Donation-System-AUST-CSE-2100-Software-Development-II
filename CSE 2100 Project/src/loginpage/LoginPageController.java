/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginpage;

import Database.Database;
import static Database.Database.DONOR_DATABASE_LIST;
import Database.User;
import helperMethods.HelperMethods;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pagenavigation.PageNavigationSystem;
import static pagenavigation.PageNavigationSystem.NAVIGATION_PAGE_STACK;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class LoginPageController implements Initializable {
    
    public static Stage stage;
    
    @FXML
    Button loginButton;
    
    @FXML
    Button createButton;
    
    @FXML
    TextField email;
    
    @FXML
    PasswordField password;
    
    @FXML
    private Label stateOfPasswordLabel;

    @FXML
    private Label stateOfEmailLabel;

    @FXML
    private Label stateOfLogin;
    
    List<Label> listL = new ArrayList<Label>();
    
    @FXML
    public void onClickLoginButton(){
        HelperMethods hm = new HelperMethods();
        if(!hm.isAllCorrectData(listL)){
            stateOfLogin.setText("Wrong email or password.");
            return;
        }
        stateOfLogin.setText("");
        if( stateOfEmailLabel.getText().equals(DataVerification.DataVerification.CORRECT_DATA) && !password.getText().isEmpty() ){            
            Database db = new Database("realDB","jdbc:mysql://localhost:3306/example","root","");            
            if( db.isValidAccount(email.getText(), password.getText()) ){
                try {                   
                    PageNavigationSystem.pns.goNextPage("/mainmenu2/MainMenuPage.fxml");
                    //LoadingPageController.stage.close();
                    } catch (Exception ex) {
                        //Logger.getLogger(LoadingPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }else{
                stateOfLogin.setText("");
                stateOfLogin.setText("Wrong email or password!"); 
            }                           
        }   
    }
    
    @FXML
    public void onClickCreateButton(){
        PageNavigationSystem.pns.goNextPage("/registrationpage/RegistrationPage2.fxml");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        BackWork bw = new BackWork(email, stateOfEmailLabel);
//        stateOfEmailLabel.textProperty().bind(bw.messageProperty());
//        Thread t1 = new Thread(bw);
//        t1.start();
//        
//        BackWork bw2 = new BackWork(password);
//        stateOfPasswordLabel.textProperty().bind(bw2.messageProperty());
//        Thread t2 = new Thread(bw2);
//        t2.start();
//       
        listL.add(stateOfEmailLabel);
        listL.add(stateOfPasswordLabel);
        
        HelperMethods hm = new HelperMethods();
        Thread x = new Thread(new Runnable(){
            @Override
            public void run() {
                while(NAVIGATION_PAGE_STACK.peek().isShowing()){
                    Platform.runLater(new Runnable(){
                    @Override
                        public void run() {
                            hm.changeLabelStateEmailVerification(email.getText(), stateOfEmailLabel);
                            hm.changeLabelStateGeneralVerification(password.getText(), stateOfPasswordLabel);                        
                        }                    
                    });
                    try{Thread.sleep(50);}catch(Exception e){}
                }                         
            }            
        });
        x.start();
        
        
    }    
    
}
