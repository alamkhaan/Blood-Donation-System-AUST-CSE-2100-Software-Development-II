/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import static pagenavigation.PageNavigationSystem.NAVIGATION_PAGE_STACK;


public class FXMLMain extends Application{
    
    public static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("loadingpage/LoadingPage.fxml")); 
        //Parent root = FXMLLoader.load(getClass().getResource("searchdonorpage/SearchDonorPage.fxml"));  
        //Parent root = FXMLLoader.load(getClass().getResource("/mainmenu2/MainMenuPage.fxml")); 
        //Parent root = FXMLLoader.load(getClass().getResource("/adminpanelpage/AdminPanelPage2.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/adminpanelpage/AdminPanelViewProfilePage.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("myprofilepage/MyProfilePage2.fxml")); 
        //Parent root = FXMLLoader.load(getClass().getResource("/registrationpage/DebugPage.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        NAVIGATION_PAGE_STACK.push(stage);

        //System.out.println(rs.getString("username"));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Stage getStage(){
        return stage;
    }
    
    
}
