/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpostpage;

import Database.Database;
import static Database.Database.loggedInUserData;
import Database.News;
import helperMethods.HelperMethods;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pagenavigation.PageNavigationSystem;
import static pagenavigation.PageNavigationSystem.NAVIGATION_PAGE_STACK;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class NewPostPageController implements Initializable {

    @FXML
    private Button postButton;
    @FXML
    private ComboBox<String> title;
    @FXML
    private TextField contactNo;
    @FXML
    private TextField zilla;
    @FXML
    private TextField upaZilla;
    @FXML
    private TextField thana;
    @FXML
    private ComboBox<String> bloodGroup;
    @FXML
    private TextArea description;
    @FXML
    private TextField email;
    @FXML
    private Button backButton;
    @FXML
    private Label titleStateL;
    @FXML
    private Label descriptionStateL;
    @FXML
    private Label emailStateL;
    @FXML
    private Label contactNoStateL;
    @FXML
    private Label bloodGroupStateL;
    @FXML
    private Label zillaStateL;
    @FXML
    private Label upaZillaStateL;
    @FXML
    private Label thanaStateL;
    
    List<Label> listL = new ArrayList<Label>();
    @FXML
    private Label postStateL;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<String> bloodGroupList = FXCollections.observableArrayList("O+","O-","A+","A-","B+","B-","AB+","AB-");
        bloodGroup.setItems(bloodGroupList);
        
        ObservableList<String> titleList = FXCollections.observableArrayList("Urgent Blood");
        title.setItems(titleList);
        
        bloodGroup.setValue("");
        title.setValue("");
        
        listL.add(titleStateL);
        listL.add(descriptionStateL);
        listL.add(emailStateL);
        listL.add(contactNoStateL);        
        listL.add(bloodGroupStateL);
        listL.add(zillaStateL);
        listL.add(upaZillaStateL);
        listL.add(thanaStateL);
        
        HelperMethods hm = new HelperMethods();      
        Thread x = new Thread(new Runnable(){
            @Override
            public void run() {
                while(NAVIGATION_PAGE_STACK.peek().isShowing()){
                    Platform.runLater(new Runnable(){
                        @Override                   
                        public void run() {
                            try{   
                                hm.changeLabelStateEmailVerification(email.getText(), emailStateL);
                                hm.changeLabelStateContactNoVerification(contactNo.getText(), contactNoStateL);                               
                                hm.changeLabelStateGeneralVerification(description.getText(), descriptionStateL);
                                hm.changeLabelStateGeneralVerification(zilla.getText(), zillaStateL);
                                hm.changeLabelStateGeneralVerification(upaZilla.getText(), upaZillaStateL);
                                hm.changeLabelStateGeneralVerification(thana.getText(), thanaStateL);
                                hm.changeComboBoxLabelStateGeneralVerification(bloodGroup.getValue(), bloodGroupStateL);
                                hm.changeComboBoxLabelStateGeneralVerification(title.getValue(), titleStateL);   
                            }catch(Exception e){}
                        }        
                    });
                    try{Thread.sleep(50);}catch(Exception e){}
                }          
            }         
        });
        x.start();

                
    }    

    @FXML
    private void post(ActionEvent event) {
        HelperMethods hm = new HelperMethods();
        if( !hm.isAllCorrectData(listL) ){
            hm.changeButtonStateLWrongSubmission(postStateL);
            return;
        }
        News news = new News();      
        news.setContactNo(contactNo.getText());     
        if(loggedInUserData.getAdmin().equals("0"))
            news.setApproved("0");
        else
            news.setApproved("1");       
        news.setDescription(description.getText());
        news.setPostedById(loggedInUserData.getId());
        news.setRequiredBlood(bloodGroup.getValue().toString());
        if(loggedInUserData.getAdmin().equals("0")){
            news.setStatus("6");
        }else
            news.setStatus("0");      
        news.setThana(thana.getText());
        news.setTitle(title.getValue().toString());
        news.setUpaZilla(upaZilla.getText());
        news.setZilla(zilla.getText());
        news.setEmail(email.getText());
        
        Database db = new Database("posts","jdbc:mysql://localhost:3306/example","root","");
        db.addNewsDataInDatabase(news);
        db.readAllNewsFromDatabase();
        
        hm.changeButtonStateLCorrectSubmission(postStateL);
    }
    
    @FXML
    private void back(ActionEvent event){
        PageNavigationSystem.pns.back();
    }
    
    
}
