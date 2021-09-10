/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author labib
 */
public class Database {
    
    public static List<User> DONOR_DATABASE_LIST = new ArrayList<User>();
    public static User loggedInUserData = new User();
    public static User specificUserData = new User();
    public static List<News> NEWS_DATABASE_STACK = new ArrayList<News>();
    public static News specificNewsData = new News();
    
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    private String databaseName;
    private String url;
    private String databaseUserName;
    private String databasePassword;

    public Database(String databaseName, String url, String databaseUserName, String databasePassword) {
        this.databaseName = databaseName;
        this.url = url;
        this.databaseUserName = databaseUserName;
        this.databasePassword = databasePassword;
    }

    public List<News> readAllNewsFromDatabase() {
        List<News> list = new ArrayList<News>();
        try{
            openDatabaseConnection();
            resultSet = statement.executeQuery("SELECT * FROM "+databaseName);           
            while(resultSet.next()){               
                list.add(setValuesForNews(resultSet));                
            }
        }catch(SQLException e){
        }
        finally{
            closeDatabaseConnection();
        }
        NEWS_DATABASE_STACK = list;
        return list;
    }
    
    public List<User> readAllFromDatabase() {
        List<User> list = new ArrayList<User>();
        try{
            openDatabaseConnection();
            resultSet = statement.executeQuery("SELECT * FROM "+databaseName);           
            while(resultSet.next()){               
                list.add(setValuesForUser());                
            }
        }catch(SQLException e){
        }
        finally{
            closeDatabaseConnection();
        }
        DONOR_DATABASE_LIST = list;
        return list;
    }
    
    public void readOneRow(String id){
        try{
            openDatabaseConnection();
            resultSet = statement.executeQuery("SELECT * FROM `"+databaseName+"` WHERE `id` = "+Integer.parseInt(id)+"");
            //resultSet = statement.executeQuery("SELECT * FROM `realdb` WHERE `id` = 5");
            resultSet.next();
            specificUserData = setValuesForUser(resultSet);
        }catch(Exception e){
        }
        finally{
            closeDatabaseConnection();
        }     
    }
    
    public void readOneNewsRow(String id){
        try{
            openDatabaseConnection();
            resultSet = statement.executeQuery("SELECT * FROM `"+databaseName+"` WHERE `id` = "+Integer.parseInt(id)+"");
            //resultSet = statement.executeQuery("SELECT * FROM `realdb` WHERE `id` = 5");
            resultSet.next();
            specificNewsData = setValuesForNews(resultSet);
        }catch(Exception e){
        }
        finally{
            closeDatabaseConnection();
        }     
    }
    
    public void removeOneRow(String id){
        try{
            openDatabaseConnection();
            statement.executeUpdate("DELETE FROM `"+databaseName+"` WHERE `id` = "+Integer.parseInt(id)+"");
            //resultSet = statement.executeQuery("SELECT * FROM `realdb` WHERE `id` = 5");
        }catch(Exception e){
        }
        finally{
            closeDatabaseConnection();
        }     
    }
    
    public void makeAdmin(String id){
        try{
            openDatabaseConnection();
            statement.executeUpdate(""
                    + "UPDATE `"+databaseName+"`"
                    + "SET `admin` = '1' "                    
                    + "WHERE `"+databaseName+"`.id = "+Integer.parseInt(id)+" ");
        }catch(Exception e){
        }
        finally{
            closeDatabaseConnection();
        }
    }
    
    public void removeAdmin(String id){
        try{
            openDatabaseConnection();
            statement.executeUpdate(""
                    + "UPDATE `"+databaseName+"`"
                    + "SET `admin` = '0' "                    
                    + "WHERE `"+databaseName+"`.id = "+Integer.parseInt(id)+" ");
        }catch(Exception e){
        }
        finally{
            closeDatabaseConnection();
        }
    }
    
    public void approvePost(String id){
        try{
            openDatabaseConnection();
            statement.executeUpdate(""
                    + "UPDATE `"+databaseName+"`"
                    + "SET `approved` = '1' "                    
                    + "WHERE `"+databaseName+"`.id = "+Integer.parseInt(id)+" ");
        }catch(Exception e){
        }
        finally{
            closeDatabaseConnection();
        }
    }
    
    public void disapprovePost(String id){
        try{
            openDatabaseConnection();
            statement.executeUpdate(""
                    + "UPDATE `"+databaseName+"`"
                    + "SET `approved` = '0' "                    
                    + "WHERE `"+databaseName+"`.id = "+Integer.parseInt(id)+" ");
        }catch(Exception e){
        }
        finally{
            closeDatabaseConnection();
        }
    }

   
    
    public void addDataInDatabase(User user){
        try{
            openDatabaseConnection();    
            statement.executeUpdate("INSERT INTO `"+databaseName+"` (`id`, `user_name`, `email`, `gender`, `birth_date`, `blood_group`, `contact_no`, `house_no`, `thana`, `zilla`, `upa_zilla`, `admin`, `password`) VALUES (NULL, '"+user.getUserName()+"', '"+user.getEmail()+"', '"+user.getGender()+"', '"+user.getBirthDate()+"', '"+user.getBloodGroup()+"', '"+user.getContactNo()+"', '"+user.getHouseNo()+"', '"+user.getThana()+"', '"+user.getZilla()+"', '"+user.getUpaZilla()+"', '"+user.getAdmin()+"', '"+user.getPassword()+"')");                                  
        }catch(Exception e){}
        finally{
            closeDatabaseConnection();
        }
    }
    
    
    public void addNewsDataInDatabase(News news){
        try{
            openDatabaseConnection();    
            statement.executeUpdate("INSERT INTO `"+databaseName+"` (`id`, `title`, `description`, `contact_no`, `email`, `required_blood`, `zilla`, `upa_zilla`, `thana`, `approved`, `status`, `posted_by_id`) VALUES (NULL, '"+news.getTitle()+"', '"+news.getDescription()+"', '"+news.getContactNo()+"', '"+news.getEmail()+"', '"+news.getRequiredBlood()+"', '"+news.getZilla()+"', '"+news.getUpaZilla()+"', '"+news.getThana()+"', '"+news.getApproved()+"', '"+news.getStatus()+"', "+Integer.parseInt(news.getPostedById())+" )");                                  
        }catch(Exception e){}
        finally{
            closeDatabaseConnection();
        }
    }
    
    
    
    public void updateDataInDatabase(User user){
        try{
            openDatabaseConnection();             
            
            statement.executeUpdate(""
                    + "UPDATE `"+databaseName+"`"
                    + "SET `user_name` = '"+user.getUserName()+"', "
                    + "`email` = '"+user.getEmail()+"', "
                    + "`gender` = '"+user.getGender()+"', "
                    + "`birth_date` = '"+user.getBirthDate()+"', "
                    + "`blood_group` = '"+user.getBloodGroup()+"', "
                    + "`contact_no` = '"+user.getContactNo()+"', "
                    + "`house_no` = '"+user.getHouseNo()+"', "
                    + "`thana` = '"+user.getThana()+"', "
                    + "`zilla` = '"+user.getZilla()+"', "
                    + "`upa_zilla` = '"+user.getUpaZilla()+"', "
                    + "`admin` = '"+user.getAdmin()+"', "
                    + "`password` = '"+user.getPassword()+"' "
                    + "WHERE `"+databaseName+"`.id = "+Integer.parseInt(user.getId())+" ");     
            
            //statement.executeUpdate("UPDATE `realdb` SET `email` = '"+user.getEmail()+"' WHERE `realdb`.id = "+Integer.parseInt(user.getId())+" ");
            //statement.executeUpdate("UPDATE `realdb` SET `email` = 'miftah444@gmail.com' WHERE `realdb`.id = 1 ");
            
        }catch(Exception e){}
        finally{
            closeDatabaseConnection();
        }
    }
    
    public void updatePassword(User user){
        try{
            openDatabaseConnection();                  
            statement.executeUpdate(""
                    + "UPDATE `"+databaseName+"`"
                    + "SET"
                    + "`password` = '"+user.getPassword()+"' "
                    + "WHERE `"+databaseName+"`.id = "+Integer.parseInt(user.getId())+" ");              
        }catch(Exception e){}
        finally{
            closeDatabaseConnection();
        }
    }

    public void updateOneNewRow(News news){
        try{
            openDatabaseConnection();             
            //`id`, `title`, `description`, `contact_no`, `email`, `required_blood`, `zilla`, `upa_zilla`, `thana`, `approved`, `status`, `posted_by_id`
            statement.executeUpdate(""
                    + "UPDATE `"+databaseName+"`"
                    + "SET `title` = '"+news.getTitle()+"', "
                    + "`description` = '"+news.getDescription()+"', "
                    + "`contact_no` = '"+news.getContactNo()+"', "
                    + "`email` = '"+news.getEmail()+"', "
                    + "`required_blood` = '"+news.getRequiredBlood()+"', "
                    + "`zilla` = '"+news.getZilla()+"', "
                    + "`upa_zilla` = '"+news.getUpaZilla()+"', "
                    + "`thana` = '"+news.getThana()+"', "
                    + "`approved` = '"+news.getApproved()+"', "
                    + "`status` = '"+news.getStatus()+"', "
                    + "`posted_by_id` = "+Integer.parseInt(news.getPostedById())+" "                   
                    + "WHERE `"+databaseName+"`.id = "+Integer.parseInt(news.getId())+" ");     
            
            //statement.executeUpdate("UPDATE `realdb` SET `email` = '"+user.getEmail()+"' WHERE `realdb`.id = "+Integer.parseInt(user.getId())+" ");
            //statement.executeUpdate("UPDATE `realdb` SET `email` = 'miftah444@gmail.com' WHERE `realdb`.id = 1 ");
            
        }catch(Exception e){}
        finally{
            closeDatabaseConnection();
        }
    }
    
    
    public boolean isValidAccount(String email, String password){
        try{
            openDatabaseConnection();
            //resultSet = statement.executeQuery("SELECT * FROM '"+databaseName+"' WHERE `email` LIKE '"+email+"' AND `password` LIKE '"+password+"' ");
            resultSet = statement.executeQuery("SELECT * FROM `"+databaseName+"` WHERE `email` LIKE '"+email+"' AND `password` LIKE '"+password+"'");
            if(resultSet.next() == false){
                System.out.println("false");
                return false;
            }               
            else{
                System.out.println("true");
                loggedInUserData = setValuesForUser();
                return true;
            }
                
        }catch(Exception e){
            return false;
        }
        finally{
            closeDatabaseConnection();
        }
    }
    
    public User setValuesForUser(){
        User user = new User();    
        try{
            user.setUserName(resultSet.getString("user_name"));               
            user.setEmail(resultSet.getString("email"));
            user.setGender(resultSet.getString("gender"));
            user.setBirthDate(resultSet.getString("birth_date"));
            user.setBloodGroup(resultSet.getString("blood_group"));
            user.setContactNo(resultSet.getString("contact_no"));
            user.setPassword(resultSet.getString("password"));
            user.setHouseNo(resultSet.getString("house_no"));
            user.setThana(resultSet.getString("thana"));
            user.setZilla(resultSet.getString("zilla"));
            user.setUpaZilla(resultSet.getString("upa_zilla"));
            user.setAdmin(resultSet.getString("admin"));
            user.setId( Integer.toString(resultSet.getInt("id")) );
        }catch(Exception e){}
        return user;
    }
    
    public User setValuesForUser(ResultSet resultSet){
        User user = new User();    
        try{
            user.setUserName(resultSet.getString("user_name"));               
            user.setEmail(resultSet.getString("email"));
            user.setGender(resultSet.getString("gender"));
            user.setBirthDate(resultSet.getString("birth_date"));
            user.setBloodGroup(resultSet.getString("blood_group"));
            user.setContactNo(resultSet.getString("contact_no"));
            user.setPassword(resultSet.getString("password"));
            user.setHouseNo(resultSet.getString("house_no"));
            user.setThana(resultSet.getString("thana"));
            user.setZilla(resultSet.getString("zilla"));
            user.setUpaZilla(resultSet.getString("upa_zilla"));
            user.setAdmin(resultSet.getString("admin"));
            user.setId( Integer.toString(resultSet.getInt("id")) );
        }catch(Exception e){}
        return user;
    }
    
    public News setValuesForNews(ResultSet resultSet){
        News news = new News();
        try{
            news.setApproved(resultSet.getString("approved"));
            news.setContactNo(resultSet.getString("contact_no"));
            news.setDescription(resultSet.getString("description"));
            news.setEmail(resultSet.getString("email"));
            news.setId(String.valueOf(resultSet.getInt("id")));
            news.setPostedById(String.valueOf(resultSet.getInt("posted_by_id")));
            news.setRequiredBlood(resultSet.getString("required_blood"));
            news.setStatus(resultSet.getString("status"));
            news.setThana(resultSet.getString("thana"));
            news.setTitle(resultSet.getString("title"));
            news.setUpaZilla(resultSet.getString("upa_zilla"));
            news.setZilla(resultSet.getString("zilla"));
        }catch(Exception e){}
        return news;
    }
    
    
    public void openDatabaseConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();                       
            connection = DriverManager.getConnection(url, databaseUserName, databasePassword);
            statement = connection.createStatement(); 
        }catch(Exception e){
        }
    }
    
    public void closeDatabaseConnection(){
        try{
            statement.close();
            connection.close();
            resultSet.close();
        }catch(Exception e){}
    }
    
    
    
//    try{
//        openDatabaseConnection();
//            
//        }catch(Exception e){}
//    finally{
//        closeDatabaseConnection();
//            
//    }
    
    
}
