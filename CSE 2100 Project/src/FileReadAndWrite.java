
import Database.User;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author labib
 */
public class FileReadAndWrite {
    
    private FileReader fr;
    private BufferedReader br;
    private FileWriter fw;
    private BufferedWriter bw;

    public FileReadAndWrite(){
        fr = null;
        fw = null;
        br = null;
        bw = null;              
    }
    
    public static void writeUserDataInFile(User user){
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("registered.txt",true);
            bw = new BufferedWriter(fw);            
            bw.write(user.getUserName()+"-"+ user.getEmail() +"-"+user.getPassword()+"-"+user.getGender()+"-"+user.getContactNo()+"-"+user.getBloodGroup()+"-"+
                    user.getBirthDate()+"-"+user.getHouseNo()+"-"+user.getThana()+"-"+user.getUpaZilla()+"-"+user.getZilla());
            bw.newLine();
        } catch (IOException ex) {
            System.out.println(ex);
        }finally{
            try {
                bw.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public static User readUserDataFromFile(String email){
        FileReader fr = null;
        BufferedReader br = null;
        User user = null;
        ArrayList<String> data = new ArrayList<String>();
        try{
            fr = new FileReader("registered.txt");
            br = new BufferedReader(fr);
            while(br.read() != -1){
                String s = br.readLine();
                if(s.contains(email)){                   
                    for(String x : s.split("-")){
                    data.add(x);
                }
                    user = new User(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6), data.get(7), data.get(8), data.get(9), 
                    data.get(10), data.get(11), data.get(12) );
                    return user;
                }               
            }
        }catch(IOException e){
            System.out.println(e);
        }finally{
            try {
                br.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        return null;
    }
    
    
    
}
