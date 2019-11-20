package com.example.jbconsbiosystem.RecyclerClasses;

public class EmployeeModel {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEmp_code() {
        return emp_code;
    }

    public void setEmp_code(String emp_code) {
        this.emp_code = emp_code;
    }

    private String name;
    private String imageUrl;
    private String checkin, checkout, hours;

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getTotalhours() {
        return hours;
    }

    public void setTotalhours(String totalhours) {
        this.hours = totalhours;
    }

    public EmployeeModel (String checkin, String checkout, String hours){
        this.checkin = checkin;
        this.checkout = checkout;
        this.hours = hours;
    }

    public EmployeeModel(String date, String hrs) {
        this.date = date;
        this.hrs = hrs;
    }

    String post;
    String emp_code;

    String date,hrs;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHrs() {
        return hrs;
    }

    public void setHrs(String hrs) {
        this.hrs = hrs;
    }

    public EmployeeModel(String name, String imageUrl, String post, String emp_code) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.post = post;
        this.emp_code = emp_code;
    }


}
