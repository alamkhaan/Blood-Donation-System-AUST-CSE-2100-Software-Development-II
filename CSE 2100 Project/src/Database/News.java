/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author labib
 */
public class News {
    
    String id;
    String description;
    String contactNo;
    String zilla;
    String upaZilla;
    String thana;
    String title;
    String requiredBlood;
    String approved;
    String status;
    String postedById;
    String email;
    String location;

    public News(String id, String description, String contactNo, String zilla, String upaZilla, String thana, String title, String requiredBlood, String approved, String status, String postedById, String email) {
        this.id = id;
        this.description = description;
        this.contactNo = contactNo;
        this.zilla = zilla;
        this.upaZilla = upaZilla;
        this.thana = thana;
        this.title = title;
        this.requiredBlood = requiredBlood;
        this.approved = approved;
        this.status = status;
        this.postedById = postedById;
        this.email = email;
    }
    
    public News(){
        ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getZilla() {
        return zilla;
    }

    public void setZilla(String zilla) {
        this.zilla = zilla;
    }

    public String getUpaZilla() {
        return upaZilla;
    }

    public void setUpaZilla(String upaZilla) {
        this.upaZilla = upaZilla;
    }

    public String getThana() {
        return thana;
    }

    public void setThana(String thana) {
        this.thana = thana;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequiredBlood() {
        return requiredBlood;
    }

    public void setRequiredBlood(String requiredBlood) {
        this.requiredBlood = requiredBlood;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPostedById() {
        return postedById;
    }

    public void setPostedById(String postedById) {
        this.postedById = postedById;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        this.location = this.zilla+" ,"+this.upaZilla+" ,"+this.thana;
        return location;
    }
    
    
    
}
