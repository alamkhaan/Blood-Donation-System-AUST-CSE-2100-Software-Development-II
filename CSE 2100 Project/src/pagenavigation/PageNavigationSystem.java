/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagenavigation;

import java.util.Stack;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author labib
 */
public class PageNavigationSystem {
    
    public static PageNavigationSystem pns = new PageNavigationSystem();
    public static Stack<Stage> NAVIGATION_PAGE_STACK = new Stack<>();
    
    public void goNextPage(String location){
        try {         
            Parent root = FXMLLoader.load(getClass().getResource(location));            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();          
            NAVIGATION_PAGE_STACK.peek().close();
            NAVIGATION_PAGE_STACK.push(stage);
            //LoadingPageController.stage = stage;
            //JavaFXApplication2.stage.close();
        } catch (Exception ex) {
            //Logger.getLogger(LoadingPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void back(){
        NAVIGATION_PAGE_STACK.pop().close();
        NAVIGATION_PAGE_STACK.peek().show();
    }
    
    public void upDatePreviousPage(String parentRootLocation, String childRootLocation){
        try{          
                AnchorPane childRoot = (AnchorPane) FXMLLoader.load(getClass().getResource(childRootLocation));
                AnchorPane parentRoot = (AnchorPane) FXMLLoader.load(getClass().getResource(parentRootLocation));
                parentRoot.getChildren().remove(2);
                parentRoot.getChildren().add(childRoot);
                Scene scene = new Scene(parentRoot);
                Stage stage = NAVIGATION_PAGE_STACK.pop();
                NAVIGATION_PAGE_STACK.pop();
                Stage stage2 = new Stage();
                stage2.setScene(scene);              
                //NAVIGATION_PAGE_STACK.peek().setScene(scene);
                NAVIGATION_PAGE_STACK.push(stage2);
                NAVIGATION_PAGE_STACK.push(stage);
        }catch(Exception e){}
    }
    
    public void upDateCurrentPage(String parentRootLocation, String childRootLocation){
        try{          
                AnchorPane childRoot = (AnchorPane) FXMLLoader.load(getClass().getResource(childRootLocation));
                AnchorPane parentRoot = (AnchorPane) FXMLLoader.load(getClass().getResource(parentRootLocation));
                parentRoot.getChildren().remove(2);
                parentRoot.getChildren().add(childRoot);
                Scene scene = new Scene(parentRoot);         
                NAVIGATION_PAGE_STACK.peek().setScene(scene);
        }catch(Exception e){}
    }
    
}
