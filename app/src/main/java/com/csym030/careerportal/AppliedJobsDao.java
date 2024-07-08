package com.csym030.careerportal;

public class AppliedJobsDao {
    String id;
    String title;
    String address;
    String employer;
    String contactNumber;
    String emailId;

    public AppliedJobsDao(String name, String addr, String phn, String org,String email) {
        title = name;
        address = addr;
        contactNumber = phn;
        employer = org;
        emailId = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public AppliedJobsDao(String Id, String name, String Address, String org, String phone, String email) {

        id = Id;
        title = name;
        address = Address;
        contactNumber = phone;
        emailId = email;
        employer = org;
    }
}

