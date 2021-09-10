/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginpage;

import Database.Database;
import javafx.scene.control.TextField;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import static pagenavigation.PageNavigationSystem.NAVIGATION_PAGE_STACK;

/**
 *
 * @author labib
 */
public class BackWork extends Task<Void>{
    
    TextField email;
    PasswordField password;
    Label emailL;

    public BackWork(TextField email, Label emailL) {
        this.email = email;
        this.password = null;
        this.emailL = emailL;
    }

    public BackWork(PasswordField password) {
        this.email = null;
        this.password = password;
    }

    @Override
    protected Void call() throws Exception {    
               
        while(NAVIGATION_PAGE_STACK.peek().isShowing()){
            if(email != null)
                runForEmail();
            else if( password != null )
                runForPassword();      
            System.out.println("running");
        }
        return null;
    }
    
    private void runForEmail(){
        String emailResult = DataVerification.DataVerification.verifyEmail(email.getText());
        if( emailResult.equals(DataVerification.DataVerification.INVALID_EMAIL) ){
            emailL.setStyle("-fx-text-fill: red;");
            updateMessage(DataVerification.DataVerification.INVALID_EMAIL);
        }else if( emailResult.equals(DataVerification.DataVerification.EMPTY_FIELD) ){
            emailL.setStyle("-fx-text-fill: red;");
            updateMessage(DataVerification.DataVerification.EMPTY_FIELD);
        }else{
            emailL.setStyle("-fx-text-fill: green;");
            updateMessage(DataVerification.DataVerification.CORRECT_DATA);
        }
    }
    
    private void runForPassword(){
        String passwordResult = DataVerification.DataVerification.verifyEmail(password.getText());
        if( passwordResult.equals(DataVerification.DataVerification.EMPTY_FIELD) ){
            updateMessage(DataVerification.DataVerification.EMPTY_FIELD);
        }else{
            updateMessage(" ");
        }
    }

    @Override
    protected void updateMessage(String message) {
        super.updateMessage(message); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
