package com.example.jbconsbiosystem.RecyclerClasses;

public class model {


    String name;
    String date;

    public model(String name, String date, String total_hours) {
        this.name = name;
        this.date = date;
        this.total_hours = total_hours;
    }

    String total_hours;
    String check_in;
    String check_out;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal_hours() {
        return total_hours;
    }

    public void setTotal_hours(String total_hours) {
        this.total_hours = total_hours;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }
}
