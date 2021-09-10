/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchdonorpage;

import Database.Database;
import Database.User;
import java.util.ArrayList;
import java.util.List;
import javafx.concurrent.Task;

/**
 *
 * @author labib
 */
public class BackWork extends Task<List<User>>{

    @Override
    protected List<User> call() throws Exception {
        
        List<User> list = new ArrayList<User>();             
        Database db = new Database("realDB","jdbc:mysql://localhost:3306/example","root","");        
        list = db.readAllFromDatabase();
        
        for(int i = 0; i < list.size(); i++)
            updateMessage("Name:"+list.get(i).getUserName());
        
        return list;
        
    }

    @Override
    protected void updateMessage(String message) {
        super.updateMessage(message); //To change body of generated methods, choose Tools | Templates.       
    }
    
    
    
}
