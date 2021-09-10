/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helperMethods;

import static Database.Database.loggedInUserData;
import Database.News;
import java.util.List;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author labib
 */
public class HelperMethods {
    
    public static Label setStatusLabel(Label status, News news){
        if(news.getStatus().equals("0")){
            status.setStyle("-fx-text-fill:  #cc0000;");
            status.setText("No Donor Found");
        }else if(news.getStatus().equals("1")){
            status.setStyle("-fx-text-fill: #ff9900;");  
            status.setText("Donor Found");
        }else if(news.getStatus().equals("2")){
            status.setStyle("-fx-text-fill: #009900;");
            status.setText("Donation Received");
        }else if(news.getStatus().equals("3")){
            status.setStyle("-fx-text-fill: #0077b3;");
            status.setText("Edited Post");
            if(loggedInUserData.getAdmin().equals("0")){
                status.setStyle("-fx-text-fill: #808080;");
                status.setText("Pending Approval");
            }
        }else if(news.getStatus().equals("4")){
            status.setStyle("-fx-text-fill:  #cc0000;");
            status.setText("Removed by User");
        }else if(news.getStatus().equals("5")){
            status.setStyle("-fx-text-fill:  #cc0000;");
            status.setText("Removed by Admin");
        }else if(news.getStatus().equals("6")){
            status.setStyle("-fx-text-fill: #808080;");
            status.setText("Pending Approval");
        }
        return status;
    }
    
    public static void changeLabelStateUsernameVerification(String userName ,Label userNameStateL){
        String result = DataVerification.DataVerification.verifyUserName(userName);
        if( result.equals(DataVerification.DataVerification.INVALID_USER_NAME) ){
            userNameStateL.setStyle("-fx-text-fill: red;");
            userNameStateL.setText(DataVerification.DataVerification.INVALID_USER_NAME);
        }else if( result.equals(DataVerification.DataVerification.EMPTY_FIELD) ){
            userNameStateL.setStyle("-fx-text-fill: red;");
            userNameStateL.setText(DataVerification.DataVerification.EMPTY_FIELD);
        }else{
            userNameStateL.setStyle("-fx-text-fill: green;");
            userNameStateL.setText(DataVerification.DataVerification.CORRECT_DATA);
        }
    }
    
    public void changeLabelStateEmailVerification(String email, Label emailStateL){
        String result = DataVerification.DataVerification.verifyEmail(email);
        if( result.equals(DataVerification.DataVerification.INVALID_EMAIL) ){
            emailStateL.setStyle("-fx-text-fill: red;");
            emailStateL.setText(DataVerification.DataVerification.INVALID_EMAIL);
        }else if( result.equals(DataVerification.DataVerification.EMPTY_FIELD) ){
            emailStateL.setStyle("-fx-text-fill: red;");
            emailStateL.setText(DataVerification.DataVerification.EMPTY_FIELD);
        }else{
            emailStateL.setStyle("-fx-text-fill: green;");
            emailStateL.setText(DataVerification.DataVerification.CORRECT_DATA);
        }
    }
    
    public void changeLabelStateContactNoVerification(String contactNo ,Label contactNoStateL){
        String result = DataVerification.DataVerification.verifyContactNo(contactNo);
        if( result.equals(DataVerification.DataVerification.INVALID_CONTACT_NO) ){
            contactNoStateL.setStyle("-fx-text-fill: red;");
            contactNoStateL.setText(DataVerification.DataVerification.INVALID_CONTACT_NO);
        }else if( result.equals(DataVerification.DataVerification.EMPTY_FIELD) ){
            contactNoStateL.setStyle("-fx-text-fill: red;");
            contactNoStateL.setText(DataVerification.DataVerification.EMPTY_FIELD);
        }else{
            contactNoStateL.setStyle("-fx-text-fill: green;");
            contactNoStateL.setText(DataVerification.DataVerification.CORRECT_DATA);
        }
    }
    
    public void changeLabelStateGeneralVerification(String data, Label stateL){
        String result = DataVerification.DataVerification.verifyGeneralData(data);        
        if( result.equals(DataVerification.DataVerification.EMPTY_FIELD) ){
            stateL.setStyle("-fx-text-fill: red;");
            stateL.setText(DataVerification.DataVerification.EMPTY_FIELD);
            stateL.setVisible(true);
        }else{
            stateL.setStyle("-fx-text-fill: green;");
            stateL.setText(DataVerification.DataVerification.CORRECT_DATA);
            stateL.setVisible(false);
        }
    }
    
    public void changeComboBoxLabelStateGeneralVerification(String data, Label stateL){
        String result = DataVerification.DataVerification.verifySelectedGeneralData(data);        
        if( result.equals(DataVerification.DataVerification.DATA_NOT_SELECTED) ){
            stateL.setStyle("-fx-text-fill: red;");
            stateL.setText(DataVerification.DataVerification.DATA_NOT_SELECTED);
            stateL.setVisible(true);
        }else{
            stateL.setStyle("-fx-text-fill: green;");
            stateL.setText(DataVerification.DataVerification.CORRECT_DATA);
            stateL.setVisible(false);
        } 
    }
    
    public boolean isCorrectData(Label stateL){
        if( stateL.getText().equals(DataVerification.DataVerification.CORRECT_DATA) ){
            return true;
        }else
            return false;
    }
    
    public boolean isAllCorrectData(List<Label> listL){
        for(int i = 0; i < listL.size(); i++){
            if( !isCorrectData( listL.get(i) ) ){
                return false;
            }
        }
        return true;
    }
    
    public void changeButtonStateLCorrectSubmission(Label stateL){
        stateL.setStyle("-fx-text-fill :#009900;");
        if(loggedInUserData.getAdmin().equals("0")) 
            stateL.setText("Successfully submitted. Please wait for approval.");
        else
            stateL.setText("Successfully submited in news.");
    }
    
    public void changeButtonStateLWrongSubmission(Label stateL){
        stateL.setStyle("-fx-text-fill :red;");
        stateL.setText("Wrong data inputs.");
    }
    
}
