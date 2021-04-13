package co.chen.bean;

import java.util.Date;

import co.chen.utils.ClientState;

public class Admin {
    
    private int adminId;
    private String identifier;
    private String password;
    private String name;
    private String surname;
    private String mail;
    private int remainingLoginAttempts;
    private Date creationDate;
    private ClientState state;


    public Admin() {
    }

    public Admin(int adminId, String identifier, String password, String name, String surname, String mail,
            int remainingLoginAttempts, Date creationDate, ClientState state) {
        this.adminId = adminId;
        this.identifier = identifier;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.remainingLoginAttempts = remainingLoginAttempts;
        this.creationDate = creationDate;
        this.state = state;
    }

    public int getAdminId() {
        return adminId;
    }
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public int getRemainingLoginAttempts() {
        return remainingLoginAttempts;
    }
    public void setRemainingLoginAttempts(int remainingLoginAttempts) {
        this.remainingLoginAttempts = remainingLoginAttempts;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public ClientState getState() {
        return state;
    }
    public void setState(ClientState state) {
        this.state = state;
    }

    
}
