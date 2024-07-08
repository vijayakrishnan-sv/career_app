package com.csym030.careerportal;

public class JobsDao {

    String id;
    String title;
    String address;
    String employer;
    String contactNumber;

    //Getter and Setter Methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String phoneNumber) {
        this.contactNumber = phoneNumber;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String organisation) {
        this.employer = organisation;
    }

    public JobsDao(String name, String Address, String phone, String org)
    {
        title = name;
        address = Address;
        employer = org;
        contactNumber = phone;
    }

    public JobsDao(String Id, String Name, String org, String Address, String phone) {
        id = Id;
        title = Name;
        employer = org;
        address = Address;
        contactNumber = phone;
    }

    public JobsDao(int Id, String Name, String org, String Address, String phone) {
        id = ""+Id;
        title = Name;
        employer = org;
        address = Address;
        contactNumber = phone;
    }


}