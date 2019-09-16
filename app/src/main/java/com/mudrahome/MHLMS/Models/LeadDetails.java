package com.mudrahome.MHLMS.Models;

import java.util.ArrayList;
import java.util.List;

public class LeadDetails {
    String name, contactNumber, assignerContact, assigneeContact, loanAmount, loanType, propertyType, employment,
            employmentType, location, salesmanRemarks, date, assignedTo, status, key,
            assigner, assignedToUId, assignerUId,
            time, assignDate, assignTime;

    ArrayList<String> telecallerRemarks, salesmanReason;
    long timeStamp;

    public String getAssignerContact() {
        return assignerContact;
    }

    public void setAssignerContact(String assignerContact) {
        this.assignerContact = assignerContact;
    }

    public String getAssigneeContact() {
        return assigneeContact;
    }

    public void setAssigneeContact(String assigneeContact) {
        this.assigneeContact = assigneeContact;
    }

    List<String> banks = new ArrayList<>();

    public List<String> getBanks() {
        return banks;
    }

    public void setBanks(List<String> banks) {
        this.banks = banks;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public String getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(String assignTime) {
        this.assignTime = assignTime;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getKey() {
        return key;
    }

    public String getSalesmanRemarks() {
        return salesmanRemarks;
    }

    public void setSalesmanRemarks(String salesmanRemarks) {
        this.salesmanRemarks = salesmanRemarks;
    }

    public ArrayList<String> getTelecallerRemarks() {
        return telecallerRemarks;
    }

    public void setTelecallerRemarks(ArrayList<String> telecallerRemarks) {
        this.telecallerRemarks = telecallerRemarks;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getAssigner() {
        return assigner;
    }

    public void setAssigner(String assigner) {
        this.assigner = assigner;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getEmployment() {
        return employment;
    }

    public String getLoanType() {
        return loanType;
    }

    public String getLocation() {
        return location;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public String getDate() {
        return date;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setEmployement(String employement) {
        this.employment = employment;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public LeadDetails() {
    }

    public String getAssignedToUId() {
        return assignedToUId;
    }

    public void setAssignedToUId(String assignedToUId) {
        this.assignedToUId = assignedToUId;
    }

    public String getAssignerUId() {
        return assignerUId;
    }

    public void setAssignerUId(String assignerUId) {
        this.assignerUId = assignerUId;
    }

    public ArrayList<String> getSalesmanReason() {
        return salesmanReason;
    }

    public void setSalesmanReason(ArrayList<String> salesmanReason) {
        this.salesmanReason = salesmanReason;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LeadDetails(String name, String contactNumber, String assignerContact,
                       String assigneeContact, String loanAmount, String employment,
                       String employmentType, String loanType, String propertyType, String location,
                       ArrayList<String> telecallerRemarks, String date, String assignedTo, String status,
                       String assigner, String key, String assignedToUId,
                       String assignerUId, String time, String assignDate,
                       String assignTime, long timeStamp) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.assignerContact = assignerContact;
        this.assigneeContact = assigneeContact;
        this.propertyType = propertyType;
        this.employment = employment;
        this.loanType = loanType;
        this.location = location;
        this.loanAmount = loanAmount;
        this.date = date;
        this.assignedTo = assignedTo;
        this.status = status;
        this.key = key;
        this.employmentType = employmentType;
        this.assigner = assigner;
        this.telecallerRemarks = telecallerRemarks;
        this.salesmanRemarks = salesmanRemarks;
        this.assignedToUId = assignedToUId;
        this.assignerUId = assignerUId;
        this.time = time;
        this.assignDate = assignDate;
        this.assignTime = assignTime;
        this.timeStamp = timeStamp;
    }
}
