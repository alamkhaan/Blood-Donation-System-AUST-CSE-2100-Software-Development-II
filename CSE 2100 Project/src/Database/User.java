/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

public class User {
    
    private String userName;
    private String email;
    private String password;
    private String gender;
    private String contactNo;
    private String bloodGroup;
    private String birthDate;
    private String houseNo;
    private String thana;
    private String upaZilla;
    private String zilla;
    private String admin;
    private String id;

    public User() {
    }

    public User(String userName, String email, String password, String gender, String contactNo, String bloodGroup, String birthDate, String houseNo, String thana, String upaZilla, String zilla, String admin, String id) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.contactNo = contactNo;
        this.bloodGroup = bloodGroup;
        this.birthDate = birthDate;
        this.houseNo = houseNo;
        this.thana = thana;
        this.upaZilla = upaZilla;
        this.zilla = zilla;
        this.admin = admin;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getThana() {
        return thana;
    }

    public void setThana(String thana) {
        this.thana = thana;
    }

    public String getUpaZilla() {
        return upaZilla;
    }

    public void setUpaZilla(String upaZilla) {
        this.upaZilla = upaZilla;
    }

    public String getZilla() {
        return zilla;
    }

    public void setZilla(String zilla) {
        this.zilla = zilla;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
}

