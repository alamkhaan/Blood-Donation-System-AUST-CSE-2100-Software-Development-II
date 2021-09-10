/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypostpage;

import Database.Database;
import static Database.Database.loggedInUserData;
import static Database.Database.specificNewsData;
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
import javafx.stage.Stage;
import pagenavigation.PageNavigationSystem;
import static pagenavigation.PageNavigationSystem.NAVIGATION_PAGE_STACK;

/**
 * FXML Controller class
 *
 * @author labib
 */
public class EditMyPostPageController implements Initializable {

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
    private ComboBox<String> title;
    @FXML
    private TextField email;
    @FXML
    private Button backButton;
    List<Label> listL = new ArrayList<Label>();
    @FXML
    private Button updateButton;
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
        
        contactNo.setText(specificNewsData.getContactNo());
        description.setText(specificNewsData.getDescription());
        email.setText(specificNewsData.getEmail());
        zilla.setText(specificNewsData.getZilla());
        upaZilla.setText(specificNewsData.getUpaZilla());
        thana.setText(specificNewsData.getThana());
        
        bloodGroup.setValue(specificNewsData.getRequiredBlood());
        title.setValue(specificNewsData.getTitle());
        
        
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
    private void update(ActionEvent event) {
        HelperMethods hm = new HelperMethods();
        if( !hm.isAllCorrectData(listL) ){
            hm.changeButtonStateLWrongSubmission(postStateL);
            return;
        }
        News news = new News();
        news.setApproved("0");
        news.setContactNo(contactNo.getText());
        news.setDescription(description.getText());
        news.setEmail(email.getText());
        news.setId(specificNewsData.getId());
        news.setPostedById(specificNewsData.getPostedById());
        news.setRequiredBlood(bloodGroup.getValue());
        //news.setStatus("3");
        if(loggedInUserData.getAdmin().equals("0")){
            news.setStatus("6");
        }else{
            news.setStatus("3");
        }       
        news.setThana(thana.getText());
        news.setTitle(title.getValue());
        news.setUpaZilla(upaZilla.getText());
        news.setZilla(upaZilla.getText());    
        
        Database db = new Database("posts","jdbc:mysql://localhost:3306/example","root","");
        db.updateOneNewRow(news);
        db.readAllNewsFromDatabase();
        
        try {         
            
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/mypostpage/MyPostsPage.fxml"));     
            Stage stage = PageNavigationSystem.NAVIGATION_PAGE_STACK.pop();
            PageNavigationSystem.NAVIGATION_PAGE_STACK.pop();
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/mainmenu2/MainMenuPage.fxml"));
            root.getChildren().remove(1);
            root.getChildren().add(anchorPane);
            Scene scene = new Scene(root);            
            Stage stage2 = new Stage();
            stage2.setScene(scene);
            PageNavigationSystem.NAVIGATION_PAGE_STACK.push(stage2);
            PageNavigationSystem.NAVIGATION_PAGE_STACK.push(stage);
            
            //LoadingPageController.stage = stage;
            //JavaFXApplication2.stage.close();
        } catch (Exception ex) {
            //Logger.getLogger(LoadingPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        hm.changeButtonStateLCorrectSubmission(postStateL);
    }

    @FXML
    private void back(ActionEvent event) {
        PageNavigationSystem.pns.back();
    }
    
}
