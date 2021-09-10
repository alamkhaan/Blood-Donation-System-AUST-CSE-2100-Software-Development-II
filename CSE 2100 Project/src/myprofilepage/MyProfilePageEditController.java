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
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pagenavigation.PageNavigationSystem;
import static pagenavigation.PageNavigationSystem.NAVIGATION_PAGE_STACK;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class MyProfilePageEditController implements Initializable {

    @FXML
    private Button doneButton;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextField contactNo;

    @FXML
    private ChoiceBox<String> bloodGroup;

    @FXML
    private TextField zilla;

    @FXML
    private TextField upaZilla;

    @FXML
    private TextField thana;

    @FXML
    private TextField houseNo;
    
    @FXML
    private ChoiceBox<String> gender;

    @FXML
    private DatePicker birthDate;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Button backButton;
    
    @FXML
    private Label userL;

    @FXML
    private Label emailL;

    @FXML
    private Label contactNoL;
    
    @FXML
    private Label resultL;
    
    //Thread t;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<String> genderList = FXCollections.observableArrayList("Male","Female","Others");
        gender.setItems(genderList);
        
        ObservableList<String> bloodGroupList = FXCollections.observableArrayList("O+","O-","A+","A-","B+","B-","AB+","AB-");
        bloodGroup.setItems(bloodGroupList);
        
        name.setText(loggedInUserData.getUserName());
        email.setText(loggedInUserData.getEmail());
        contactNo.setText(loggedInUserData.getContactNo());
        bloodGroup.setValue(loggedInUserData.getBloodGroup());
        zilla.setText(loggedInUserData.getZilla());
        upaZilla.setText(loggedInUserData.getUpaZilla());
        thana.setText(loggedInUserData.getThana());
        houseNo.setText(loggedInUserData.getHouseNo());
        
        birthDate.getEditor().setText(loggedInUserData.getBirthDate());
        
        if( loggedInUserData.getGender().equals("M") )
            gender.setValue(genderList.get(0));
        else if( loggedInUserData.getGender().equals("F") )
            gender.setValue(genderList.get(1));
        else
            gender.setValue(genderList.get(2));
        
        //BackWork
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                while(NAVIGATION_PAGE_STACK.peek().isShowing()){
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            verityInput();                         
                        }                   
                    });
                    try{
                        Thread.sleep(500);
                    }catch(Exception e){}
                }
            }          
        });
        t.start();
            
    }    
     
    
    @FXML
    void back(ActionEvent event) {
        NAVIGATION_PAGE_STACK.pop().close();
        NAVIGATION_PAGE_STACK.peek().show();
        //PageNavigationSystem.pns.back();
    }

    @FXML
    void changePassword(ActionEvent event) {
        PageNavigationSystem.pns.goNextPage("/myprofilepage/ChangePasswordPage.fxml");
    }

    @FXML
    void done(ActionEvent event) {
       // if(!t.isAlive())
       //     t.start();
        
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        //birthDate.getEditor().setText( formatter.format(birthDate.getValue()) );
        resultL.setText("");
        if( userL.getText().equals(DataVerification.DataVerification.CORRECT_DATA) && emailL.getText().equals(DataVerification.DataVerification.CORRECT_DATA) && contactNoL.getText().equals(DataVerification.DataVerification.CORRECT_DATA) ){
            Database db = new Database("realDB","jdbc:mysql://localhost:3306/example","root","");
            User user = new User();
            user.setUserName( name.getText() );               
            user.setEmail( email.getText() );
            user.setGender( Character.toString( gender.getValue().charAt(0) ) );    
            
            
            
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
            //birthDate.getEditor().setText( formatter.format(birthDate.getValue()) );           
            
            user.setBirthDate(birthDate.getEditor().getText());
            
            
            user.setBloodGroup(bloodGroup.getValue());
            user.setContactNo(contactNo.getText());
            user.setHouseNo(houseNo.getText());
            user.setThana(thana.getText());
            user.setZilla(zilla.getText());
            user.setUpaZilla(upaZilla.getText());                       
            user.setAdmin(loggedInUserData.getAdmin());
            user.setPassword(loggedInUserData.getPassword());
            user.setId(loggedInUserData.getId());
            
            db.updateDataInDatabase(user);
            
            Thread dbThread = new Thread( new Runnable(){
                @Override
                public void run() {
                    Platform.runLater( new Runnable(){
                        @Override
                        public void run() {
                            //Loading everything for the latest database version.
                            db.readAllFromDatabase();
                            db.isValidAccount(user.getEmail(), user.getPassword());
                        }   
                    });
                }               
            });
            dbThread.start();
            resultL.setText("Successfully Updated!");
        }
        
             
    }
    
    @FXML
    void date(ActionEvent event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
        //birthDate.getEditor().setText( formatter.format(birthDate.getValue()) );
        Thread x = new Thread( new Runnable(){
            @Override
            public void run() {
                while( NAVIGATION_PAGE_STACK.peek().isShowing() ){
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            birthDate.getEditor().setText( formatter.format(birthDate.getValue()) );
                        }                    
                    });
                    try{Thread.sleep(10);}catch(Exception e){}
                }
            }            
        });
        x.start();
    }
    
    public void verityInput(){
        if(DataVerification.DataVerification.verifyEmail(email.getText()).equals(DataVerification.DataVerification.INVALID_EMAIL)){
            emailL.setStyle("-fx-text-fill: red;");
            emailL.setText(DataVerification.DataVerification.INVALID_EMAIL);
        }else if( DataVerification.DataVerification.verifyEmail(email.getText()).equals(DataVerification.DataVerification.EMPTY_FIELD) ){
            emailL.setStyle("-fx-text-fill: red;");
            emailL.setText(DataVerification.DataVerification.EMPTY_FIELD);
        }else{
            emailL.setStyle("-fx-text-fill: green;");
            emailL.setText(DataVerification.DataVerification.CORRECT_DATA);
        }
        
        if( DataVerification.DataVerification.verifyUserName(name.getText()).equals(DataVerification.DataVerification.INVALID_USER_NAME )){
            userL.setStyle("-fx-text-fill: red;");
            userL.setText(DataVerification.DataVerification.INVALID_USER_NAME);
        }else if( DataVerification.DataVerification.verifyUserName(name.getText()).equals(DataVerification.DataVerification.EMPTY_FIELD ) ){
            userL.setStyle("-fx-text-fill: red;");
            userL.setText(DataVerification.DataVerification.EMPTY_FIELD);
        }else{
            userL.setStyle("-fx-text-fill: green;");
            userL.setText(DataVerification.DataVerification.CORRECT_DATA);
        }
        
        if( DataVerification.DataVerification.verifyContactNo(contactNo.getText()).equals(DataVerification.DataVerification.INVALID_CONTACT_NO )){
            contactNoL.setStyle("-fx-text-fill: red;");
            contactNoL.setText(DataVerification.DataVerification.INVALID_CONTACT_NO);
        }else if( DataVerification.DataVerification.verifyContactNo(contactNo.getText()).equals(DataVerification.DataVerification.INVALID_CONTACT_NO ) ){
            contactNoL.setStyle("-fx-text-fill: red;");
            contactNoL.setText(DataVerification.DataVerification.EMPTY_FIELD);
        }else{
            contactNoL.setStyle("-fx-text-fill: green;");
            contactNoL.setText(DataVerification.DataVerification.CORRECT_DATA);
        }
    }
    
}
