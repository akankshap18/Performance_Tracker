package com.example.performancetracker.Admin;

public class LeadModel {
    String leadname, leademail, leadphone;

    public LeadModel(String leadname, String leademail, String leadphone) {
        this.leadname = leadname;
        this.leademail = leademail;
        this.leadphone = leadphone;
    }

    public String getLeadname() {
        return leadname;
    }

    public void setLeadname(String leadname) {
        this.leadname = leadname;
    }

    public String getLeademail() {
        return leademail;
    }

    public void setLeademail(String leademail) {
        this.leademail = leademail;
    }

    public String getLeadphone() { return leadphone; }

    public void setLeadphone(String leadphone) {
        this.leadphone = leadphone;
    }
}
