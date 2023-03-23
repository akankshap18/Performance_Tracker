package com.example.performancetracker.Manager;

public class ReportModel {

    String report, doneby;

    public ReportModel(){}

    public ReportModel(String report, String doneby) {
        this.report = report;
        this.doneby = doneby;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String name) {
        this.report = report;
    }

    public String getDoneby() {
        return doneby;
    }

    public void setDoneby(String doneby) {
        this.doneby = doneby;
    }

}