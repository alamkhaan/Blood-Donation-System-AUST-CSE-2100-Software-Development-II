/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loadingpage;

import Database.Database;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javafx.concurrent.Task;

/**
 *
 * @author labib
 */
public class BackWork extends Task<Integer>{
 
    @Override
    protected Integer call() throws Exception {
        Database db = new Database("realDB","jdbc:mysql://localhost:3306/example","root","");        
        db.readAllFromDatabase();
        
        Database dbNews = new Database("posts","jdbc:mysql://localhost:3306/example","root","");
        dbNews.readAllNewsFromDatabase();
                
        for(int i = 0; i <= 100; i += 1){
            updateProgress(i+1, 100);
            Thread.sleep(10);
        }
        return 100;
    }
    
    @Override
    protected void updateProgress(double workDone, double max) {
        super.updateProgress(workDone, max); //To change body of generated methods, choose Tools | Templates.
    }

}
