package co.chen.bean;

import java.util.Date;

public class Client {
    /* Fields */
    private int clientId;
    private String name;
    private String surname;
    private String city;
    private String postcode;
    private String address;
    private String phone;
    private String mail;
    private String passwd;
    private String state;
    private Date creationDate;
    
    public Client() {
    }
    
    /* Getters & Setters */
    public int getClientId() {
        return clientId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
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
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    // There is no setter for the creationDate field - it can only be created once

    @Override
    public String toString() {
        return "Client [address=" + address + ", city=" + city + ", clientId=" + clientId + ", creationDate="
                + creationDate + ", mail=" + mail + ", name=" + name + ", passwd=" + passwd + ", phone=" + phone
                + ", postcode=" + postcode + ", state=" + state + ", surname=" + surname + "]";
    }
}
