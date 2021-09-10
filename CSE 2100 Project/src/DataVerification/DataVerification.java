/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataVerification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author labib
 */
public class DataVerification {
    
    public static final String INVALID_USER_NAME = "Invalid user name.";
    public static final String INVALID_CONTACT_NO = "Invalid contact number.";
    public static final String INVALID_EMAIL = "Invalid email.";
    public static final String CORRECT_DATA = "Correct!";
    public static final String EMPTY_FIELD = "This field cannot be empty.";
    public static final String DATA_NOT_SELECTED = "No data selected.";
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    //public static final Pattern VALID_USER_NAME_REGEX = Pattern.compile("^[a-zA-Z0-9]+$");
    public static final Pattern VALID_USER_NAME_REGEX = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
    public static final Pattern VALID_CONTACT_NUMBER_REGEX = Pattern.compile("^[0-9]*$");
    
    public static String verifyEmail(String email){
        if( email.isEmpty() ) return EMPTY_FIELD;
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        if( matcher.find() )
            return CORRECT_DATA;
        else
            return INVALID_EMAIL;
    }
    
    public static String verifyUserName(String userName){
        if( userName.isEmpty() ) return EMPTY_FIELD;
        Matcher matcher = VALID_USER_NAME_REGEX.matcher(userName);
        if( matcher.find() )
            return CORRECT_DATA;
        else
            return INVALID_USER_NAME;
    }
    
    public static String verifyContactNo(String contactNo){
        if( contactNo.isEmpty() ) return EMPTY_FIELD;
        Matcher matcher = VALID_CONTACT_NUMBER_REGEX .matcher(contactNo);
        if( matcher.find() && contactNo.length() == 11 )
            return CORRECT_DATA;
        else
            return INVALID_CONTACT_NO;
    }
    
    public static String verifyGeneralData(String data){
        if( data.isEmpty() ) return EMPTY_FIELD;
        return CORRECT_DATA;        
    }
    
    public static String verifySelectedGeneralData(String data){
        if( data.isEmpty() ) return DATA_NOT_SELECTED;
        return CORRECT_DATA;
    }
}
