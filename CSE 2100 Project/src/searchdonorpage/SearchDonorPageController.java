/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchdonorpage;

import Database.Database;
import Database.User;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.sql.*;
import java.util.List;
import pagenavigation.PageNavigationSystem;

/**
 *
 * @author labib
 */
public class SearchDonorPageController implements Initializable {
    
    @FXML
    private ListView<Pane> listView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
        
        //BackWork bw = new BackWork();
        List<User> list = Database.DONOR_DATABASE_LIST;
        
            //try {
            //while(resultSet.next()){
            for(int i = 0; i < list.size() ; i++){  

                Pane pane1 = new Pane();
                
                pane1.setPrefHeight(180.0); //247
                pane1.setPrefWidth(300.0);
                
                ImageView imageView = new ImageView();
                imageView.setLayoutX(14.0);
                imageView.setLayoutY(10.0);
                imageView.setFitHeight(160.0);
                imageView.setFitWidth(145.0);
                imageView.setPickOnBounds(true);
                imageView.setPreserveRatio(true);
                
                File file;
                file = new File("src/images/no_person_male.jpg");

                Image image = new Image(file.toURI().toString());
                imageView.setImage(image);
                
                pane1.getChildren().add(imageView);
                Label userNameLabel = createLabel("Name: "+list.get(i).getUserName(),231.0,42.0);
                //userNameLabel.textProperty().bind(bw.messageProperty());
                pane1.getChildren().add( userNameLabel );
                pane1.getChildren().add( createLabel("BloodGroup: "+list.get(i).getBloodGroup(),180.0, 67.0) );
                pane1.getChildren().add( createLabel("Region: "+list.get(i).getThana(),223.0, 92.0) );
                pane1.getChildren().add( createLabel("Contact No: "+list.get(i).getContactNo(),187.0, 117.0) );
                
                //new Thread(bw).start();
                
                listView.getItems().add(pane1);              
            }
            
            
            
            
            
            //vbox.getChildren().add(pane);
            //vbox.getChildren().add(pane);
        //} catch (SQLException ex) {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        //}

    }    
    
    public Label createLabel(String text, double X, double Y){
        Label label = new Label(text);
        label.setLayoutX(X);
        label.setLayoutY(Y);
        label.setFont( Font.font("AWLUnicode", FontWeight.NORMAL, 18.0) );
        return label;
    }
    
    @FXML
    private void back(ActionEvent event){
        PageNavigationSystem.pns.back();
    }
    
    
}

