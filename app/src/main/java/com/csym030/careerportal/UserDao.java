package com.csym030.careerportal;

public class UserDao {

    String userId;
    String name;
    String emailId;
    String password;
    String country;
    String contactNumber;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String userName) {
        this.emailId = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String address) {
        this.country = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public UserDao(String id, String Name, String contry, String phone, String email, String Password)
    {
        userId = id;
        name = Name;
        country = contry;
        contactNumber = phone;
        emailId = email;
        password = Password;
    }
}
